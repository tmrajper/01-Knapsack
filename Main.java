/**
 * 
 */
package edu.cpp.cs.cs331;

/**
 * @author tarikrajper
 *
 */

import java.util.Scanner;

public class Main {
	
	private static Scanner scan;
	//Gets Knapsack Capacity
	private static int capacity;
	//Gets Number of Objects
	private static int objects;
	//Stores weight Inputs
	private static int[] weightInput;
	//Stores Profit Inputs
	private static int[] profitInput;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		capacity = 0;
		objects = 0;
		
		//Gets User's Knapsack Capacity
		getKnapsackCapacity();
		//Gets Number of Objects
		getNumberOfObjects();
		//Initializes profit and weight arrays
		weightInput = new int[objects + 1];
		profitInput = new int[objects + 1];
		//Stores User's weight inputs
		weightInput();
		//Stores User's Profit inputs
		profitInput();
		scan.close();
		//Calculates Max profit using 0/1 knapsack
		dynamicKnapsack(weightInput, profitInput, capacity, objects);

	}

	//Stores Weight input
	public static void weightInput() {
		System.out.println("Enter Weight For Each Object: ");
		for (int i = 1; i <= objects; i++)
			weightInput[i] = scan.nextInt();
	}
	
	//Stores Profit input
	public static void profitInput() {
		System.out.println("Enter Profit For Each Object: ");
		for (int i = 1; i <= objects; i++)
			profitInput[i] = scan.nextInt();
	}

	/**
	 * 
	 * @param weight, Stores weight inputs
	 * @param value, Stores profit inputs
	 * @param knap, knapsack capacity
	 * @param obj, number of objects
	 */
	public static void dynamicKnapsack(int[] weight, int[] value, int knap, int obj) {
		
		//Smallest negative value
		int neg = Integer.MIN_VALUE;
		
		
		int temp[][] = new int[obj + 1][knap + 1];
		int[][] solution = new int[obj + 1][knap + 1];
		
		//Sorts items based on weight
		for (int i = 1; i <= obj; i++)  {
			for (int j = 0; j <= knap; j++) {
				int temp1 = temp[i-1][j];
				int temp2 = neg;
				
				if (j >= weight[i]) 
					temp2 = temp[i-1][j - weight[i]] + value[i];
					temp[i][j] = Math.max(temp1, temp2);
					solution[i][j] = temp2 > temp1 ? 1 : 0;
				
			}
		}
		
		int[] selec = new int[obj + 1];
		
		//Selects the item obtained through the algorithm, marks objects with 1 if selected
		for (int i = obj, wt = knap; i > 0; i--) {
			if (solution[i][wt] != 0) {
				selec[i] = 1;
				wt = wt - weight[i];
			} else 
				selec[i] = 0;
		}
		
		System.out.print("Items with weight: ");
		
		int maxProfit = 0;
		
		//Calculates max profit, uses select array
		for (int j = 1; j < obj + 1; j++) {
			if (selec[j] == 1) {
				System.out.print(value[j] + " ");
				maxProfit += value[j];
			}
		}
		
		System.out.println("\nThe Maximum Profit is: " + maxProfit);
		
	}

	//Gets Knapsack Capacity
	public static void getKnapsackCapacity() {
		System.out.println("Enter Knapsack Capacity: ");
		capacity = scan.nextInt();
	}

	//Gets Number of objects from user
	public static void getNumberOfObjects() {
		System.out.println("Get Number Of Objects: ");
		objects = scan.nextInt();
		
	}

}
