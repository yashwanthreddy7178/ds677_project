/*
    julian arcila
*/
//importing scanner for user 
import java.util.Scanner;
//starting class for main argument below
/*
        This program will print out odd,even,sum,average,and greatest number of even or odd.
        This will depend on the users input of numbers less than 99.
*/
public class Homework4 {
    /*
        This program is first going to ask the user to input their name, then the system will say hi and whatever the users name is.
        Then it will ask to put postive numbers that are not negative and can stop putting numbers once they put the number 99.
        Then it will gather all numbers and it will figure out the sum and average of all numbers, sum and average of even numbers, 
        sum and average of odd numbers, find the greatest even number, find the greatest odd number.
        As well  will automatically find out how many numbers was inputed, and how many even and odd numbers there will be.
    
    */
    public static void main(String[] args) {
        //declaring scanner into our program
        Scanner input = new Scanner(System.in);
        //declaring variables
        int nCount = 0; //LCV
        int nNumber = 0; // //varibale to recieve the number entered by the user
        int nSumOfNumbers = 0; // to get the values of everyrthing
        int nSumOfEven = 0;//to get the total sum of even numbers
        int nSumOfOdd = 0;//to get the total sum of odd numbers
        int nEven = 0;//even variable
        int nOdd = 0;//odd variable
        int nNumEven = 0;//hold this variable to be able to solve the average and sum of even numbers
        int nNumOdd = 0;//hold this variable to be able to solve the average and sum of odd numbers
        int nMaxOdd= 0;//store greatest odd number
        int nMaxEven = 0;//store greatest even number     
        String sName = "";//varibale holding the name of user
        double dAverageTotal = 0.0; // average for total numbers
        double dAverageEven = 0.0; // average for even numbers
        double dAverageOdd = 0.0; // average for odd numbers
        
        
        
        //declaring constant variable that is a sentinal value that goes in the while loop
       final int SENTINAL = 99;//adding a sentinal value of 99 since it wont change
       
        
        
        
        //Displaying what the user will be inputing 
        System.out.println("*************************************");//printing out asterisk like program sample
        System.out.print("Please enter student name: ");//asking user to print student name
        sName = input.nextLine();//users input
        System.out.println("*************************************");//printing out asterisk like program sample
        
        System.out.println("");//printing blank line
        System.out.println("Hello, "+ sName);//printing users name to say hi
        System.out.println("This program will run until you type 99"); //telling user that this program will run until they put the number 99
        
        System.out.println("");// space break based on sample run
        
        System.out.print("Please enter a positive number: ");//printing out for the user to input a 
        nNumber = input.nextInt();//users input
        
        while (nNumber != SENTINAL){ // sentinal varibale is 99
         //validating only taking postive numbers
         if (nNumber > 0 && nNumber%2==0){//have formula to set postive number, set even number, and set max even number
             nCount++;//variable holding the amount of numbers there are
             nEven++;//storing even numbers in this variable
             nSumOfNumbers = nSumOfNumbers + nNumber;//equation of sum of numbers
             nNumEven = nNumEven + nNumber;//will store number here if its even in this bracket
             if (nNumber > nMaxEven){//logic for finding even number
                nMaxEven = nNumber;//will set max even number to this every time it loops
             }
             System.out.print("Please enter a positive number: ");//asking user for input after they put a postivie number
             nNumber = input.nextInt();//users input
         }//positive number if statements
         
         else if (nNumber > 0 && nNumber%2!=0 ) {//have formula to set postive number, set odd number, and set max odd number
             nCount++;//variable holding the amount of numbers there are
             nOdd++;//storing odd numbers in this variable
             nSumOfNumbers = nSumOfNumbers + nNumber;//equation of sum of numbers
             nNumOdd = nNumOdd + nNumber;//will store number here if its odd in this bracket
             if (nNumber > nMaxOdd){//logic for finding odd number
                nMaxOdd = nNumber;//will set max odd number to this every time it loops
             }
             System.out.print("Please enter a positive number: ");//asking user for input after they put a postivie number
             nNumber = input.nextInt();//users input
         }//odd number else else if statement if its not even
         
         else {
               System.out.println("Sorry, the number must be > 0");//asking user to enter a positive number instead of a negative or lower than 0
               System.out.print("Please enter a positive number: ");//asking user to enter a positive number
               nNumber = input.nextInt();//users input
           }//end of program 
           
       }// end of while loop for program that will recieve the postive number only
        
        
        dAverageTotal = (double) nSumOfNumbers / nCount;//formula for average in total
        dAverageEven =  (double) nNumEven / nEven;//formula for average for even numbers
        dAverageOdd = (double) nNumOdd / nOdd;//formula for average for odd numbers
        
  
        
        System.out.println("");//space break for sample run format
        
        System.out.println("*********************************************************");//sample run format
        System.out.println("*                 SUMARY REPORT                         *");//printing summary report
        System.out.println("*********************************************************");//sample run format
        
        System.out.println("you entered "+ nCount + " numbers");//printing out the amount of numbers that were written
        System.out.println(nEven + " of them are even numbers");//printing the amount of even numbers there were
        System.out.println(nOdd + " of them are odd numbers");//printing the amount of odd numbers there were
        System.out.println("The sum of the numbers is: " + nSumOfNumbers);//printing out the sum of numbers in total
        System.out.println("The average of the numbers is: " + dAverageTotal);//printing out the average of total of all numbers
        System.out.println("The sum of the odd numbers is: " + nNumOdd);//printing out the sum of odd numbers in total
        System.out.println("The average of the odd number is: " + dAverageOdd);//printing out the average of total odd numbers
        System.out.println("The sum of the even numbers is: " + nNumEven);//printing out the sum of even numbers in total
        System.out.println("The average of the even number is: " + dAverageEven );//printing out the average of total even numbers
        System.out.println("The greatest odd number is " + nMaxOdd);//showcasing the greatest odd number depending on what the user put
        System.out.println("The greatest even number is " + nMaxEven);//showcasing the greatest even number depending on what the user put
        
        
                
    }//end main class program
    
}// end of class program
