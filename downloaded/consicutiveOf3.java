
import java.util.Scanner;



public class consicutiveOf3{
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter size: ");
        int size = input.nextInt();
        int []arr = new int[size];

        System.out.print("Enter numbers: ");
        for(int i = 0; i < size; i++ ){

            arr[i] = input.nextInt();
        }

        for(int i = 0; i < arr.length; i++){

            int num1 = 0; 
            int num2 = 1; 
            int num3 = 2;

            if ((i + 2) < arr.length ){

                num1 = arr[i];
                num2 = arr[i+1];
                num3 = arr[i+2];

            }


            if (num1 == num2 && num2 == num3){ 

                System.out.print(arr[i] + " ");

            }

        }

        System.out.println();
        
    }
}
