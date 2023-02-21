/*
	Karthik, Sahib, David
	12/12/2021
	Main Java page 
	The main Java page that calls in the other java pages
*/
import java.util.*; //import util
class Main {
  public static void main(String[] args) throws InterruptedException{
    Main.mainMenu(args);
  }
  public static void mainMenu(String[] args) throws InterruptedException{
		Scanner scan = new Scanner(System.in);
    //declare the new scanner
		String option = null;
    //options for menu are listed below
    System.out.println("Welcome to Java Multi-Algorithm Program");
    System.out.println("1. Encryption");
    System.out.println("2. Sorting");
    System.out.println("3. Searching");
    System.out.println("4. Recursion");
		System.out.println("5. Quit");
    System.out.println();
		System.out.println("Please choose an option (Integers only): ");
		option = scan.nextLine().trim();
    //trim value and scan
		while(!(option.equals("1")) && !(option.equals("2")) && !(option.equals("3")) && !(option.equals("4")) && !(option.equals("5"))){
			System.out.println("Sorry please enter a number between 1 and 5.");
      //Ensure the user input is valid
			option = scan.nextLine().trim();
		}
		if(option.equals("1"))
			encryption.main(args);
      //load encryption
		if(option.equals("2"))
			sorting.main(args);
      //load sorting
		if(option.equals("3"))
			searchAlgorithm.main(args);
      //load search algorithm
		if(option.equals("4"))
			recursion.main(args);
      //load recursion
		if(option.equals("5")){
			System.out.println();
			System.out.println("Thank you, have a good day!");
		}
  }
}//end of main