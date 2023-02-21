/*
	Sahib Johar
	12/15/2021
	Encryption Java Page
  Solution to problem #1 in the grade 12 Java Assignment
*/
import java.io.*; //class for FileWriter and BufferedWriter.
import java.util.*; //class for Scanner.
class encryption {
  public static void main(String[] args) throws InterruptedException{
    Scanner scan = new Scanner(System.in);

    //Variables
    String line = null, key = null, key1 = null;
    String[] lineArray = null,decryptedName = new String[1001];
    String[][] data1 = new String[1001][6], data2 = new String[1001][6];
    int count1 = 0, count2 = 0;

    try{
      //------------------------------------------------------------//ENCRYPTION SECTION//------------------------------------------------------------

      System.out.println("Enter the KEY for encryption: "); //Ask the User for the Key
      key = scan.nextLine().toUpperCase(); // Scans the user's input and store it into "key" variable.

      BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.csv")); //Creates a class used for reading the file.
      line = br.readLine(); //reads the current line
      while(line != null){ //loops while the line is not null.
        if(line == null)break;  
        lineArray = line.split(","); //assigns the array with all the values of each line. 
        for(int x = 0; x < data1[0].length; x++){ //loops through each rows of the array.
          data1[count1][x] = lineArray[x]; //stores all the elements of the line array.
        } 
        count1++; //increase the count used for the position.
        line = br.readLine(); //reads the next line of the file.
      }
      br.close(); //closes the Reader Class

      //encryption.print2DStrArray(data1);
      for(int x = 0; x < data1.length; x++){ //For loop used to loop through the whole file
        //Variables
        String message = data1[x][2]; // Assign the "Last Name" of the current line of the CSV File into a string variable
        if(message == null)break; // Check if the message reaches the end of the file
        message = message.toLowerCase(); // Make all letters of the string lower case
        int[] keyVal = encryption.convertASCII(key);  // Calls to a function that converts the key into ASCII values and stores it in an integer array.
        int[] tempVal = encryption.convertASCII(key); // Calls to a function that converts the key into ASCII values and stores it in an integer array (used for sorting).
        double messageLength = message.length(); // Assigns the length of the "Last Name" into a double Variable.
        double keyLength = key.length(); //Assigns the length of the "Key" into a double Variable.
        int count = 0; //

        while(messageLength % keyLength != 0){ //Check if there is enough rows and columns to store the "Last Name"
          message += " "; //Adds empty spaces to the end of the "Last Name"
          messageLength = message.length(); //Reassigns the length of the message
        }

        String[][] table = new String[(int)Math.ceil(messageLength/keyLength)][(int)keyLength]; //Creates a 2D String array where the length of the column is the length of the message divided by the length of the key and the length of the rows is the length of the key
        for(int z = 0; z < table.length; z++){ //loops through the columns of the array
          for(int y = 0; y < table[z].length; y++){ //loops through the rows of each columns in the array
            table[z][y] = message.valueOf(message.charAt(count)); //Assigns the letter at index of "count" to the table array at element z and y.
            count++; //increases the count variable so it keeps track of where in the string we are assigning to the 2D array
          }
        }

        int[] sortedVal = encryption.insertionSort(tempVal); //Calls to a function that sorts the ASCII values fo the key and stores in a integer array.
        int[] order = new int[keyVal.length]; //Creates a integer array used for referring to when encrypting the message
        data1[x][2] = encrptMessage(order, keyVal, sortedVal, table); //Calls to a function that encrypts the message and re-assigns it into the current line of the csv file
      }//end for Loop

      BufferedWriter bw = new BufferedWriter(new FileWriter("MOCK_DATAenc.csv")); //Create a class used for writing to a file
      for(int x = 0; x < data1.length; x++){ //loops through the columns of the entire csv file
        for(int y = 0; y < data1[x].length; y++){ //loops through each row of the file
          bw.write(data1[x][y]+","); //writes all the elements of the array into the file
        }
        bw.newLine();//Creates a new line
      }
      bw.close(); //closes the Writer Class

			System.out.println();
      System.out.println("Encryption is complete!\nPlease check MOCK_DATAenc.csv"); 
			System.out.println();

      //------------------------------------------------------------//DECRYPTION SECTION//------------------------------------------------------------
      
      //Ask user for "key"
      System.out.println("Enter the KEY for decryption: ");
      key1 = scan.nextLine().toUpperCase(); // Scans the user's input and store it into "key" variable.

      BufferedReader br1 = new BufferedReader(new FileReader("MOCK_DATAenc.csv")); //Creates a class used for reading the file.
      line = br1.readLine(); //Reads the current line
      while(line != null){ //Loops while the line is not null.
        if(line == null)break;
        lineArray = line.split(","); //Assigns the array with all the values of each line. 
        for(int x = 0; x < data2[0].length; x++){ //Loops through each rows of the array.
          data2[count2][x] = lineArray[x]; //Stores all the elements of the line array.
        } 
        count2++; //Increase the count used for the position.
        line = br1.readLine(); //Reads the next line of the file.
      }
      br1.close(); //Close the Writer class.

      for(int x = 0; x < data2.length; x++){ 
        //VARIABLES
        String message1 = data2[x][2]; //Assign the "Last Name" of the current line of the CSV File into a string variable
        if(message1 == null)break; //Check if the message reaches the end of the file
        double encryptedLength = message1.length();  //Assigns the length of the "Last Name" into a double Variable.
        double encryptedKeyLength = key1.length(); //Assigns the length of the "Key" into a double Variable.
        String[][] decryptedTable = new String[(int)Math.ceil(encryptedLength/encryptedKeyLength)][(int)encryptedKeyLength]; //Creates a 2D String array where the length of the column is the length of the message divided by the length of the key and the length of the rows is the length of the key
        int[] decKeyVal = encryption.convertASCII(key1); // Calls to a function that converts the key into ASCII values and stores it in an integer 
        int[] decTempKeyVal = encryption.convertASCII(key1); // Calls to a function that converts the key into ASCII values and stores it in an integer(used for sorting) 

        while(encryptedLength % encryptedKeyLength != 0){ //Check if there is enough rows and columns to store the "Last Name"
          message1 += " "; //Adds empty spaces to the end of the "Last Name"
          encryptedLength = message1.length(); //Reassigns the length of the message
        }

        int[] decSortedVal = encryption.insertionSort(decTempKeyVal); //Calls to a function that sorts the ASCII values fo the key and stores in a integer 
        int[] decOrder = new int[decKeyVal.length]; //Creates a integer array used for referring to when encrypting the message

        data2[x][2] = encryption.decryptMessage(decOrder, decKeyVal, decSortedVal, decryptedTable, message1); //Calls to a function that decrypts the message and re-assigns it into the current line of the csv file
        data2[x][2] = data2[x][2].trim();  //Trims all the blank spaces around the string.
        
      }//end for loop

      BufferedWriter bw1 = new BufferedWriter(new FileWriter("MOCK_DATAdec.csv")); //Create a class used for writing to a file
      for(int x = 0; x < data2.length; x++){ //Loops through the columns of the entire csv file
        for(int y = 0; y < data2[x].length; y++){ //Loops through each row of the file
          bw1.write(data2[x][y]+","); //Writes all the elements of the array into the file
        }
        bw1.newLine(); //Creates a new line
      }
      bw1.close();//Closes the Writer Class
      
			System.out.println(); 
      System.out.println("Decryption is complete!\nPlease check MOCK_DATAdec.csv");
			System.out.println();
    }catch(IOException ex){
      System.out.println("There was an error with the file.");
    }
		System.out.println();
		Thread.sleep(1000); //Stops the program for a small period.
    Main.mainMenu(args); //calls the function at Main.
  }//end main();  


  //FUNCTIONS

  //The function below is used to convert the "key" of type string into an integer and returns the integer array.
  public static int[] convertASCII(String key){
    int[] data = new int[key.length()]; //Create new integer array with length of the key.
    for(int x = 0; x < data.length; x++){
      data[x] = (int)key.charAt(x); //Assigns each ASCII values of the character to the array 
    }
    return data; 
  }//end convertASCII()
  
  //The following function is used to sort the "key" integer array and returns the sorted integer array.
  public static int[] insertionSort(int[] data){
    int key = 0; 
    int temp = 0; //used for swapping the values.

    for(int x = 1; x < data.length; x++){
      key = data[x]; //start by making the second element of the array into the key
      for(int y = x - 1; y >= 0; y--){
        if(key < data[y]){ //checks if the element before is greater than the key
          temp = data[y]; //assign the value at element y into the temp variable
          data[y] = key; //re-assign the element at index y as the key
          data[y+1] = temp; //assign the next element over into the temp variable
        }else break; 
      }
    }
    return data; 
  }//end insertionSort()

  //The following function is used to search through the original ASCII array to find the searchElement and return the position.
  public static int modifiedLinearSearch(int[]order, int searchElement, int[]keyVal){
    for(int x = 0; x < order.length; x++){ //Loop through the array.
      if(searchElement == keyVal[x]){ //if the value is found at certain index.
        return x; //return the index.
      }
    }
    return -1; //if value is not found, return -1
  }//end linearSearch();

  //The following function is used to encrypt the message and returns the encrypted string.
  public static String encrptMessage(int[] order, int[]keyVal, int[]sortedVal, String[][]table){
    String encrypt = ""; //create an empty string.
    int count = 0; //create an integer used to find the position.
    for(int x = 0; x < sortedVal.length; x++){ //loops through the array
      count = encryption.modifiedLinearSearch(order, sortedVal[x], keyVal); //calls to the function that finds the position at which the values match
      order[count] = x + 1; //assign the order of the value.
      keyVal[count] = 1000; //reassign the element of the original array, so it would avoid repeating letters.
      for(int z = 0; z < table.length; z++){ //loop through columns of the array.
        for(int y = 0; y < table[z].length;y++){ //loop through rows of the array.
          encrypt += table[z][count]; //add the character at the position into the empty string.
          break;
        }//end for
      }//end for
    }
    return encrypt; //returns the encrypted message.
  }//end encrptMessager()

  //The following function is used to decrypt the message and return the decrypted string.
  public static String decryptMessage(int[] decOrder,int[] decKeyVal,int[] decSortedVal, String[][]decryptedTable, String encryptedMessage){
    String decrypt = ""; //create an empty string.
    int count = 0; //create an integer used to find the position.
    int counter = 0; //create an integer used save the position of the string.
    for(int x = 0; x < decSortedVal.length; x++){ //loops through the array
      count = encryption.modifiedLinearSearch(decOrder, decSortedVal[x], decKeyVal); //calls to the function that finds the position at which the values match
      decOrder[count] = x + 1; //assign the order of the value.
      decKeyVal[count] = 1000; //reassign the element of the original array, so it would avoid repeating letters.
      for(int z = 0; z < decryptedTable.length; z++){  //loop through columns of the array.
        for(int y = 0; y < decryptedTable[z].length; y++){ //loop through rows of the array.
          decryptedTable[z][count] = encryptedMessage.valueOf(encryptedMessage.charAt(counter)); //add the characters to the table.
          counter++; //increase the counter.
          break;
        }
      }
    }
    for(int z = 0; z < decryptedTable.length; z++){ //loop through columns of the array.
        for(int y = 0; y < decryptedTable[z].length; y++){ //loop through rows of the array.
          decrypt += decryptedTable[z][y]; //assign the letters into the string.
        }
      }
    return decrypt; //returns the decrypted message.
  }//end decryptMessage()
}

