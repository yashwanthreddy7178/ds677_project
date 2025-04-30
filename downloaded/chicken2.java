package 치킨배달;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class chicken2 {
	static class Point{
		int r,c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		int[][] map = new int[N][N];
		
		//int cR = 0,cC=0; //단 하나 존재하는 치킨집 위치 기억
		Point chicken=null;
		List<Point> houses = new ArrayList<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==2) {
//					cR=i;
//					cC=j;
					chicken = new Point(i,j);
				}
				else if(map[i][j]==1)
					houses.add(new Point(i,j)); //집들 기억
			}
		}
		//맵을 다시 순회하면서 집(1)마다 기억했던 cR cC까지의 거리를 구한다
		int sum=0;
		for (int i = 0; i < houses.size(); i++) {
			Point house = houses.get(i);
			sum += (Math.abs(house.r-chicken.r)+Math.abs(house.c-chicken.c));
			
		}
	}

}
