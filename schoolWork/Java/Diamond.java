/***************************************************************************************
Program Name: Diamond.java
Programmer's Name: Jeremy Myser
Program Description: Draw a diamond based on user input integer.
***************************************************************************************/

package diamond;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Diamond {

	public static void main(String[] args) {
		int diamondSize = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (diamondSize % 2 == 0){
			System.out.println("Please Enter an Odd Number: ");
			try {
				diamondSize =  Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.out.println("IO error trying to read your number!");
		        System.exit(1);
			}
		}
		
		diamondOfAsterisks(diamondSize);

	}
	public static void diamondOfAsterisks(int n){
	
		for (int i = 1; i <= n; i=i+2)
        {
              for (int j = 0; j < (n - i); j = j+2)
                    System.out.print(" ");
              for (int j = 1; j <= i; j = j+2)
                    System.out.print("*");
              for (int k = 1; k < i; k = k+2)
                    System.out.print("*");
              System.out.println();
        }

        for (int i = n - 2; i >= 1; i=i-2)
        {
              for (int j = 0; j < (n - i); j = j+2)
                    System.out.print(" ");
              for (int j = 1; j <= i; j = j+2)
                    System.out.print("*");
              for (int k = 1; k < i; k = k+2)
                    System.out.print("*");
              System.out.println();
        }

        System.out.println();
		
		
	}

}
