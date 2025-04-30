package legoSim;

import ch.aplu.robotsim.*;
import java.io.*;

public class TurtleBot2 {

	public static void main(String args[]) throws NumberFormatException, IOException {
		TurtleRobot slowturtle = new TurtleRobot();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("How many times?");
		int n = Integer.parseInt(br.readLine());

		while ((n--) > 0) {
			slowturtle.forward(25);
			slowturtle.right(90.0);
			slowturtle.forward(25);
			slowturtle.right(90.0);
			slowturtle.forward(25);// up-right-straight-right-down

			slowturtle.left(90.0);
			slowturtle.forward(25);// left-straight

			slowturtle.left(90.0);

		}
	}
}
