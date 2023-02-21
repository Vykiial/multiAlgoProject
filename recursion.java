/*
	Sahib, David, Karthik
	12/15/2021
	Recursion Java Page
  Solution to problem #4 in the grade 12 Java Assignment
*/
import java.util.Scanner;
import java.util.Random;
// 0 = empty
// 1 = miss
// 2 = element
// 3 = hit
// 4 = start
class recursion {
  public static void main(String[] args) throws InterruptedException{
		// The following creates a new random object and assigns it to rand
		Random rand = new Random(); 
		// The following creates a new scanner object and assigns it to scan
		Scanner scan = new Scanner(System.in);
		// The following prints out the value that is contained in the brackets
		System.out.println("Please enter how many rows you'd like in the table (Only integers):");
		// The following sets the column variable to an integer the user enters
		int columns = scan.nextInt();
		// The following ensures the user enters a number greater than 1
		while(columns < 2){
			// The following prints out the value that is contained in the brackets
			System.out.println("Please enter a value greater than 1!");
			// The following sets the column variable to an integer the user enters
			columns = scan.nextInt();
		}
		// The following prints out the value that is contained in the brackets
		System.out.println("Please enter how many columns you'd like in the table (Only integers):");
		// The following sets the rows variable to an integer the user enters
		int rows = scan.nextInt();
		// The following ensures the user enters a number greater than 1
		while(rows < 2){
			// The following prints out the value that is contained in the brackets
			System.out.println("Please enter a value greater than 1!");
			// The following sets the rows variable to an integer the user enters
			rows = scan.nextInt();
		}
		// The following sets the the column of the element to look for somewhere within the table
		int elementPositionColumn = rand.nextInt(columns);
		// The following sets the the row of the element to look for somewhere within the table
		int elementPositionRow = rand.nextInt(rows);
		// The following sets the the column of the start element for somewhere within the table
		int startPositionColumn = rand.nextInt(columns);
		// The following sets the the row of the start element for somewhere within the table
		int startPositionRow = rand.nextInt(rows);
		// The following loop ensures that the start position of the start element and element to search for are not the same
		while(startPositionColumn == elementPositionColumn && startPositionRow == elementPositionRow){
			// The following rerandomizes the column of the start position
			startPositionColumn = rand.nextInt(columns);
			// The following rerandomizes the column of the start position
			startPositionRow = rand.nextInt(rows);
		}
		// The following creates a 2D array with dimensions that the user entered
    int[][] table = new int[columns][rows];
		// The following loop fills the table with 0's
		for(int i = 0; i < table.length; i++)
			for(int x = 0; x < table[i].length; x++)
				table[i][x] = 0;   
		// The following sets the position of the element to search for to 2 
		table[elementPositionColumn][elementPositionRow] = 2;
		// The following sets the position of the element to search for to 4
		table[startPositionColumn][startPositionRow] = 4;
		// The following uses the print2DArray to print out the table
		print2DArray(table);
		// The following calls the recursive function using the specified elements
		recursiveFunction(table, elementPositionColumn, elementPositionRow, startPositionRow, startPositionColumn);
		System.out.println();
		Thread.sleep(1000);
		Main.mainMenu(args);
  }
	// The following function uses recrusion to find an element in a 2D array 
	public static void recursiveFunction(int[][] table, int elementColumn, int elementRow, int lastColumn, int lastRow) throws InterruptedException{
		// The following pausing the program for a second
		Thread.sleep(1000);
		// The following is the base case to exit the recursion loop
		if(table[elementColumn][elementRow] != 2){
			// The following prints out the value that is contained in the brackets
			System.out.println("Found!");
			// The following exits the function
			return;
		}
		// The following creates a new random object and assigns it to rand
		Random rand = new Random();
		// The following creates a variable to decide which direction the program will search next and randomly chooses 0 or 1
		int direction = rand.nextInt(2);
		// The following creates a randomNumber variable to be used later on
		int randomNumber = 0;
		// The following creates a variable to be used to detwermine if an element on the table has been changed or not
		boolean changed = false;
		// The following integer counter is created as a counter
		int counter = 0;
		// The following variable is created to be used to determine if a linear search should be executed later on
		boolean newSpot = false;
		// The following loop is the main function of the algoirthm and continues looping until an element is changed within the table
		while(changed == false){
			// The following loop is executed when the direction is selected as vertical
			while(direction == 0){ // vertical movement
				// The following resets the counter variable to 0
				counter = 0;
				// The following sets randomNumber to a random integer within the vertical dimensions of the table
				randomNumber = rand.nextInt(table.length);
				// The following loops checks to see if an element in the current column has already been searched
				for(int i = 0; i < table.length; i++){
					// The following adds 1 to counter if an element in the current column has already been searched
					if(table[i][lastColumn] == 4 || table[i][lastColumn] == 1)
						counter++;
				}
				// The following checks to see if the entire column of the current element has already been searched
				if(counter >= table.length){
					// The following loops checks to see if an element in the current row has already been searched
					for(int i = 0; i < table[0].length; i++){ //check if row and column are full
						// The following adds 1 to counter if an element in the current row has already been searched
						if(table[lastRow][i] == 4 || table[lastRow][i] == 1)
						counter++;
					}
					// The following checks to see if the entire row and column of the current element has already been searched
					if(counter >= (table.length) + (table[0].length)){
						// The next two loops loop through the entire table
						for(int i = 0; i < table.length; i++){ //look for next available square
							for(int x = 0; x < table[i].length; x++){
								// The following checks for the next unsearched element in the array
								if(table[i][x] == 0 || table[i][x] == 2){
									// The following set the new element to be searched to the next unsearched element in the table
									lastRow = i;
									lastColumn = x;
									// The following used the newSpot variable to be able to break out of the nested loop
									newSpot = true;
									break;
								}
								// The following breaks out of the loop once the next unsearched element has been found
								if(newSpot == true)
									break;
							}
						}
					}
					// The following changes the direction of the search if the row/column is full
					direction = 1;
					break;
				}
				// The following checks if the current element has already been searched or not
				if(table[randomNumber][lastColumn] == 0){ //hits empty square
					// The following marks the element as searched if it had not been searched and is not the element that the program is searching for
					table[randomNumber][lastColumn] = 1;
					// The following lets the program know that an element has been changed inside the table
					changed = true;
					// The following lets the program know what the row of the element that was just searched is
					lastRow = randomNumber;
					break;
				}
				// The following executes if the program finds the element it was searching for
				if(table[randomNumber][lastColumn] == 2){ //hits element
					// The following sets the position of the element to 3
					table[randomNumber][lastColumn] = 3;
					// The following lets the program know that an element has been changed inside the table
					changed = true;
					break;
				}
			}
			// The following loop is executed when the direction is selected as horizontal
			while(direction == 1){ // horizontal movement
				// The following resets the counter variable to 0
				counter = 0;
				// The following sets randomNumber to a random integer within the horizontal dimensions of the table
				randomNumber = rand.nextInt(table[0].length);
				// The following loops checks to see if an element in the current row has already been searched
				for(int i = 0; i < table[0].length; i++){ //check if entire row is full
				// The following adds 1 to counter if an element in the current column has already been searched
				if(table[lastRow][i] == 4 || table[lastRow][i] == 1)
					counter++;
				}
				// The following checks to see if the entire row of the current element has already been searched
				if(counter >= table[0].length){
					// The following loops checks to see if an element in the current column has already been searched
					for(int i = 0; i < table.length; i++){ //check if row and column are full
						// The following adds 1 to counter if an element in the current row has already been searched
						if(table[i][lastColumn] == 4 || table[i][lastColumn] == 1)
						counter++;
					}
					// The following checks to see if the entire row and column of the current element has already been searched
					if(counter >= (table.length) + (table[0].length)){
						// The next two loops loop through the entire table
						for(int i = 0; i < table.length; i++){ //look for next available square
							for(int x = 0; x < table[i].length; x++){
								// The following checks for the next unsearched element in the array
								if(table[i][x] == 0 || table[i][x] == 2){
									// The following set the new element to be searched to the next unsearched element in the table
									lastRow = i;
									lastColumn = x;
									// The following used the newSpot variable to be able to break out of the nested loop
									newSpot = true;
									break;
								}
								// The following breaks out of the loop once the next unsearched element has been found
								if(newSpot == true)
									break;
							}
						}
					}
					// The following changes the direction of the search if the row/column is full
					direction = 0;
					break;
				}
				// The following checks if the current element has already been searched or not
				if(table[lastRow][randomNumber] == 0){ //hits empty square
					// The following marks the element as searched if it had not been searched and is not the element that the program is searching for
					table[lastRow][randomNumber] = 1;
					// The following lets the program know that an element has been changed inside the table
					changed = true;
					// The following lets the program know what the row of the element that was just searched is
					lastColumn = randomNumber;
					break;
				}
				// The following executes if the program finds the element it was searching for
				if(table[lastRow][randomNumber] == 2){ //hits element
					// The following sets the position of the element to 3
					table[lastRow][randomNumber] = 3;
					// The following lets the program know that an element has been changed inside the table
					changed = true;
					// The following lets the program know what the row of the element that was just searched is
					lastColumn = randomNumber;
					break;
				}
			}
		}
		// The following prints out the table using the print2DArray function 
		print2DArray(table);
		// The following recursively calls the funtions with the updated parameters
		recursiveFunction(table, elementColumn, elementRow, lastColumn, lastRow);
	}
	public static void print2DArray(int[][] table){
		for(int i = 0; i < table.length; i++){
			for(int x = 0; x < table[i].length; x++){
        if(table[i][x] == 0)
          System.out.print("🚨"); //must use double quotes to avoid character literal error
				if(table[i][x] == 1)
          System.out.print("🌖"); //must use double quotes to avoid character literal error
        if(table[i][x] == 2)
          System.out.print("🌎"); //must use double quotes to avoid character literal error
				if(table[i][x] == 3)
          System.out.print("🔥"); //must use double quotes to avoid character literal error
        if(table[i][x] == 4)
          System.out.print("👾"); //must use double quotes to avoid character literal error
			}
			System.out.println();
		}
		System.out.println();
	}
} //end of recursion