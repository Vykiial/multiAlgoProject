/*
	Karthik, Sahib, David
	12/12/2021
	Sorting Java Page
	Solution to problem #2 in the grade 12 Java Assignment
*/
import java.io.*; //import the java.io
import java.util.*; //import the java.util
class sorting {
  public static void main(String[] args) throws InterruptedException{
		ArrayList<String> file = new ArrayList<String>(); //string array list
		ArrayList<Integer> nums = new ArrayList<Integer>(); //int array list
		String fileLine = null; //set fileLine to null
      try{ 
				BufferedReader br = new BufferedReader(new FileReader("years.csv")); //declare buffered reader
				fileLine = br.readLine(); 
				file.add(fileLine);
				while(fileLine != null){ //check that the line in the file being read is not empty before/while executing the read line code
					fileLine = br.readLine();
					file.add(fileLine);
				}
				br.close(); //close the buffered reader
			}catch(IOException er){ //IO exception 
				System.out.println("Sorry there was an error in your search.");
			}
			for(int i = 1; i < file.size(); i++)
				if(file.get(i) != null) //check to ensure the line in file is not empty
					nums.add(Integer.parseInt(file.get(i)));
			System.out.print("Iteration 1: "); 
      printArray(nums); //print the unsorted array
			System.out.println();
      for(int x=0; x<nums.size()-1; x++)
      {
			int min = x; //sets min to the value of the x in the for loop
			for(int j=x+1; j<nums.size(); j++)
			{
				if(nums.get(j)<nums.get(min))
					min = j; //changes the min value to the j value if it is less than min
			}
			int temp = nums.get(x); //temporarily stores the x value
			nums.set(x, nums.get(min)); //replaces the x value with the min value
			nums.set(min, temp); //replacess the min value with the x
			//this process swaps the x and min values
			System.out.print("Iteration " + (x + 2) + ": ");
			printArray(nums); //display the iteration
			System.out.println();
			//code to print the array
		} 
		Thread.sleep(1000);
		Main.mainMenu(args);
  }//end of public static void
  //function below is used to print the array 
	public static void printArray(ArrayList<Integer> array){
		for(int i = 0; i < array.size(); i++){ //loops through array
			if(i != array.size() - 1 && array.get(i) != null) 
				System.out.print(array.get(i) + " "); //if the i value is not 1 less than length of array and is not null than print array at i =
			if(array.get(i) == null)
				break; //if array value at i is null, then break out
			if(i == array.size() - 1)
				System.out.print(array.get(i)); //if the i value is 1 less then length of array size
		}
		System.out.println(); //acts as line break
	}//end of printArray
}//end of main