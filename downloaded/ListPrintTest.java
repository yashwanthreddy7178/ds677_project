package Others.Test;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ListPrintTest {


    public static void main(String[] args) throws Exception{
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        PrintByOrder pa = new PrintByOrder("A",c,a);
        PrintByOrder pb = new PrintByOrder("B",a,b);
        PrintByOrder pc = new PrintByOrder("C",b,c);

        new Thread(pa).start();
//        Thread.sleep(10);
        new Thread(pb).start();
//        Thread.sleep(10);
        new Thread(pc).start();
//        Thread.sleep(10);
    }

    public static class PrintByOrder implements Runnable{
        Object pre = new Object();
        String cur = new String();
        Object next = new Object();

        private PrintByOrder(String cur,Object pre,Object next){
            this.cur = cur;
            this.pre = pre;
            this.next = next;
        }


        @Override
        public void run() {
            int n =10;
            while (n>0){
                synchronized (pre){
                    synchronized (next){
                        System.out.println(cur);
                        n--;
                        next.notifyAll();
                    }
                    try {
                        pre.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
