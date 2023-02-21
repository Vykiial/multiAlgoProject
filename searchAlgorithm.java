/*
	Sahib, David, Karthik
	12/12/2021
	SearchAlgorithm Java Page
	Solution to problem #3 in the grade 12 Java Assignment
*/
import java.util.*; //import util
import java.io.*; //import java.io
class searchAlgorithm{
  public static void main(String[] args) throws InterruptedException{ //java throws
    Scanner scan = new Scanner(System.in); //declare scanner
		System.out.println("What value are we looking for? (Integers only)"); //ask user for the integer value
		String num = scan.nextLine(); //set string num to what the user enters
		int test = modifiedBinarySearch("years.csv", num); //set test variable by calling the modifiedBinraySearch function with csv file years.csv
		System.out.println();
		Thread.sleep(1000); //delay feature  
		Main.mainMenu(args); 
  }
	/* The following function searches through a file containing a list of numbers for a user specified number. It returns -1 if the number is not found and returns the line number of the specified value if found. It takes two parameters, the file name as a string as well as the number to search for as a string.*/
	public static int modifiedBinarySearch(String fileName, String searchElement){
		// The following two lines create two arraylists to contain the contents of the file provided. The first one named file is created to be edited while the second, fullFile
		ArrayList<String> file = new ArrayList<String>();
		ArrayList<String> fullFile = new ArrayList<String>();
		// The following creates integer variables to be used in the binary sort
		int bottom = 0;
		int top = 0;
		int middle = 0;
		// The following creates an integer variable to use as an exponent later on
		int ex = 0;
		// The following fills the arraylist "file" with a sorted list of numbers using the newSort function to later be edited 
		file = newSort(file);
		// The following fills the arraylist "fullFile" with the same sorted list as above to be able to access the full list later on 
		fullFile = newSort(fullFile);
		// The following prints the statements in the brackets into the console
		System.out.println("Let's look at position 0.");
		// The following uses the printArray function to print out the "file" arrayList
		printArray(file);
		// The following checks to see if the first element in the list is greater than the search element meaning that the search element is not in the list and if so returns -1
		if(Integer.parseInt(file.get(0)) > Integer.parseInt(searchElement)){
			// The following sets the first element of the list to a "*" to show which element is currently being looked at
			file.set(0, "*");
			// The following uses the printArray function to print out the "file" arrayList
			printArray(file);
			// The following prints the statements in the brackets into the console
			System.out.println("Is less than the first element! Element could not be found in list.");
			// The following returns -1 meaning that the element that was being searched for cannot be found
			return -1;
		}
		// The following checks to see if the first element of the array is equal to the first element and if so returns the position it was found at (0)
		if(file.get(0).equals(searchElement)){
			// The following sets the first element of the list to a "*" to show which element is currently being looked at
			file.set(0, "*");
			// The following uses the printArray function to print out the "file" arrayList
			printArray(file);
			// The following prints the statements in the brackets into the console
			System.out.println("Found here! Found at position 0!");
			// The following returns 0 because the element was found at position 0
			return 0;
		}
		else{
			// The following sets the first element of the list to a "*" to show which element is currently being looked at
			file.set(0, "*");
			// The following uses the printArray function to print out the "file" arrayList
			printArray(file);
			// The following prints the statements in the brackets into the console
			System.out.println("Not there so look at the next position.");
		}
		// The following creates a loop to be able to search through the list and determine two points to conduct a binary search between
		for(int i = 0; i < file.size(); i++){
			// The following prints the statements in the brackets into the console
			System.out.println("Look at position 2^" + ex + ".");
			// The following uses the printArray function to print out the "fullFile" arrayList
			printArray(fullFile);
			// The following sets the specified element of the list to a "_" to show which element has already been looked at
			file.set((int)Math.pow(2, ex - 1), "_");
			// The following sets the specified element of the list to a "*" to show which element is currently being looked at
			file.set((int)Math.pow(2, ex), "*");
			// The following uses the printArray function to print out the "fullFile" arrayList
			printArray(file);
			// The following checks to see if the specified element (a power of 2) is equal to the search element
			if(Integer.parseInt(fullFile.get((int)Math.pow(2, ex))) == Integer.parseInt(searchElement)){
				// The following prints the statements in the brackets into the console if the element is found
				System.out.println("Found here! Found at position " + (int)Math.pow(2, ex) +  "!");
				// The following returns the position of where the element was found
				return (int)Math.pow(2, ex);
			}
			// The following checks to see if the specified element (a power of 2) is greater than the search element
			if(Integer.parseInt(fullFile.get((int)Math.pow(2, ex))) > Integer.parseInt(searchElement)){
				// The following assigns corresponding values to top and bottom varibles for the binary search if the interval for the search is found
				top = (int)Math.pow(2, ex);
				bottom = (int)Math.pow(2, ex - 1);
				// // The following prints the statements in the brackets into the console if the interval for the search is found
				System.out.println("Not there! Is less than " + fullFile.get(((int)Math.pow(2, ex))) + "!");
				// // The following prints the statements in the brackets into the console if the interval for the search is found
				System.out.println("Performing ***BINARY SEARCH*** between positions 2^" + ex + " and 2^" + (ex - 1) + ".");
				// The following sets the last looked at element in the list to an underscore to show that it's been looked at
				file.set((int)Math.pow(2, ex), "_");
				// The following breaks out of the exponential search loop
				break;
			}
			// The following increases the exponent variable to be able to check the next interval in the list the next time the loop iterates
			ex++;
			// The following checks to make sure that the next iteration of the loop won't exceed the bounds of the array
			if(Math.pow(2, ex) >= file.size()){
				// The following prints the statements in the brackets into the console if the the element position will exceed the bounds on the next iteration
				System.out.println("Not there! Not less than " + fullFile.get((int)Math.pow(2, ex - 1)) + "!");
				// The following prints the statements in the brackets into the console if the element position will exceed the bounds on the next iteration
				System.out.println("Look at position 2^" + ex);
				// The following checks if the search element is at the end of the list 
				if(Integer.parseInt(fullFile.get(fullFile.size() - 2)) == Integer.parseInt(searchElement)){
					// The following prints the statements in the brackets into the console if the elementis at the end of the list
					System.out.println("There is no position 2^" + ex + "! Look at position " + (fullFile.size() - 2) + "!");
					// The following uses the printArray function to print out the "fullFile" arrayList
					printArray(fullFile);
					// The following sets the element currently being looked at to a "*"
					file.set(fullFile.size() - 2, "*");
					// The following sets the last element looked at to an underscore
					file.set((int)Math.pow(2, ex - 1), "_");
					// The following uses the printArray function to print out the "file" arrayList
					printArray(file);
					// The following prints the statements in the brackets into the console if the element is at the end of the list
					System.out.println("Found at end! Found at position " + (fullFile.size() - 2) + "!");
					// The following returns the elements index once it was found
					return i * i - 1;
				}
				// The following prints the statements in the brackets into the console if the element is not at the end of the list
				System.out.println("There is no position 2^" + ex + "! Look at position " + (fullFile.size() - 2) + "!");
				// The following uses the printArray function to print out the "fullFile" arrayList
				printArray(fullFile);
				// The following sets the element currently being looked at to a "*"
				file.set(fullFile.size() - 2, "*");
				// The following sets the last element looked at to an underscore
				file.set((int)Math.pow(2, ex - 1), "_");
				// The following uses the printArray function to print out the "file" arrayList
				printArray(file);
				// The following prints the statements in the brackets into the console if the element is not at the end of the list
				System.out.println("Not there! Is less than " + fullFile.get((fullFile.size() - 2)) + "!");
				// The following two lines sets the binary searching interval to the end of the array and the last exponential position looked at within the array
				top = fullFile.size() - 2;
				bottom = (int)Math.pow(2, (ex - 2));
				// The following prints the statements in the brackets into the console if the element is not at the end of the list
				System.out.println("Performing ***BINARY SEARCH*** between positions 2^" + (ex - 1) + " and " + (fullFile.size() - 2) + ".");
				// The following sets the last element looked at to an underscore
				file.set(fullFile.size() - 2, "_");
				// The following breaks out of the exponential search loop
				break;
			}
			// The following prints the statements in the brackets into the console if the element is not at the end of the list
			System.out.println("Not there! Not less than " + fullFile.get((int)Math.pow(2, ex - 1)) + "!");
		}
		// The following loop is the binary search loop
		while(true){
			// The following sets the element thats being looked at to the middle of the maximum and minimum of the current interval
			middle = (int)((top + bottom) / 2);
			// The following uses the printArray function to print out the "fullFile" arrayList
			printArray(fullFile);
			// The following sets the element currently being looked at to a "*"
			file.set(middle, "*");
			// The following uses the printArray function to print out the "file" arrayList
			printArray(file);
			// The following checks to see if the minimum range exceeds the maximum which indicated that the element is not in the list
			if(bottom > top){
				// The following prints the statements in the brackets into the console if the element is not in the list
				System.out.println("Your value could not be found.");
				// The following returns -1 if the element could not be found
				return -1;
			}
			// The following checks to see if the current element being looked at is equal to the search element
			if(searchElement.compareToIgnoreCase(fullFile.get(middle)) == 0){
				// The following prints the statements in the brackets into the console if the element is found
				System.out.println("The middle is " + fullFile.get(middle) + ". Found here! Found at position " + (middle) + "!");
				// The following returns the elements position in the array
				return middle - 1;
			}
			// The following checks to see if the element in the array being looked at is less than the element being searched for
			if(searchElement.compareToIgnoreCase(fullFile.get(middle)) < 0){
				// The following prints the statements in the brackets into the console if the element is less than the element being looked at
				System.out.println("The middle is " + fullFile.get(middle) + ". Not found here!");
				// The following sets the last element looked at to an underscore
				file.set(middle, "_");
				// The following lowers the top range of the interval to the one less than the position of the current element
				top = (middle - 1);
			}
			// The following checks to see if the element in the array being looked at is greater than the element being searched for
			if(searchElement.compareToIgnoreCase(fullFile.get(middle)) > 0){
				// The following prints the statements in the brackets into the console if the element is greater than the element being looked at
				System.out.println("The middle is " + fullFile.get(middle) + ". Not found here!");
				// The following sets the last element looked at to an underscore
				file.set(middle, "_");
				// The following increases the bottom range of the interval to the one more than the position of the current element
				bottom = (middle + 1);
			}
		}
	}
	// The following function prints the array without printing any null values
	public static void printArray(ArrayList<String> array){
		for(int i = 0; i < array.size(); i++){ //loop through array size
			if(i != array.size() - 1 && array.get(i) != null) //if i is not the end of array size and array at index i is not null
				System.out.print(array.get(i) + " "); //print array at i
			if(array.get(i) == null) //if array at index i is null
				break; //break out
			if(i == array.size() - 1) //if i is at end of array size
				System.out.print(array.get(i)); //print the array at index i
		}
		System.out.println();
	}
	public static ArrayList<String> newSort(ArrayList<String> file) {
		ArrayList<Integer> nums = new ArrayList<Integer>(); //new integer array list
		ArrayList<String> newFile = new ArrayList<String>(); //new string array list
		String fileLine = null; //sets fileLine to empty
      try{ 
				BufferedReader br = new BufferedReader(new FileReader("years.csv")); //declare buffered reader and let it read years.csv
				fileLine = br.readLine(); //set fileLine variable to buffered reader line value
				file.add(fileLine); //add the fileLine to file
				while(fileLine != null){ //while the fileLine is not empty
					fileLine = br.readLine(); //set fileLine to line being read
					file.add(fileLine); //add fileLine variable to file
				}
				br.close(); //close buffered reader
			}catch(IOException er){ //IO exception
				System.out.println("Sorry there was an error in your search.");
			}
			for(int i = 1; i < file.size(); i++) //loop through file size
				if(file.get(i) != null) //if file(i) is not null
					nums.add(Integer.parseInt(file.get(i))); //parse the ineger of nums
      for(int x=0; x<nums.size()-1; x++)
      {
        int min = x; //min is equal to x counter
        for(int j=x+1; j<nums.size(); j++) 
        {
          if(nums.get(j)<nums.get(min)) //if nums at j is less than nums at min
            min = j; //min becomes j value
        }
      int temp = nums.get(x); //lets temp equal nums at x
      nums.set(x, nums.get(min)); //sets x to nums(min)
      nums.set(min, temp); //sets min to temp
    }
		for(int i = 0; i < nums.size(); i++)
			if(nums.get(i) != null) //checks to make sure element is not null
				newFile.add(nums.get(i).toString()); //add the value of nums at index i 
		return newFile; //return the variable
  } //end of arrayList function
} //end of search algorithm