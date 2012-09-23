
import java.io.*;
import java.util.*;

//File_read is used to read the data file and write it line by line.

public class File_read {

	// Create a method to read file

	public void fileread() {

		File file = new File("Data_662.dat");
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		FileInputStream fis = null;//Initialize the functions

		ArrayList<String> data = new ArrayList<String> ();
		String line=null; 

		try {

			fis = new FileInputStream(file); //Input Stream that reads from file
			bis = new BufferedInputStream(fis); //Creates an internal buffer array to perform fast read
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.

			while (dis.available() != 0) {

				// this statement reads the line from the file and print it to the console.

				line=dis.readLine();
				//System.out.println(line);
				data.add(line);

			}

			System.out.println("File Read Successful!!");
			fis.close();
			bis.close();
			dis.close();//Close all functions after use.


			// **Routine for converting Arraylist to String Array**//

			/*
			 * To convert ArrayList containing String elements to String array, use
			 * Object[] toArray() method of ArrayList class.
			 *
			 * Please note that toArray method returns Object array, not String array.
			 */
			/*for( String s:data)
	    	  System.out.println(s);
			 */
			/*

	      Object[] arraylist1 = data.toArray(); 
	      String[] stringdata = Arrays.copyOf(arraylist1, arraylist1.length, String[].class);
			 */

			//Routine for printing the array 'stringdata'- which contains data as strings




			// **Routine for converting String Array to string**//

			/*
			 * To convert String Array containing String elements to String , use
			 * String Buffer which is like a String, but can be modified.
			 *
			 * Please note that toArray method returns Object array, not String array.
			 */
			/*
	      int stringstore_size = 1;
	      String[] stringstore = new String[66];
	      for(int j=0;j<stringdata.length;j++)
	      {
	      String[] tempstring = stringdata[j].split(" ");
	      for(int k=0;k<tempstring.length;k++)
	      {
	    	  stringstore[stringstore_size]= tempstring[k];
	    	  stringstore_size++;
	      }

	      }
	      for(int i=0;i<stringstore.length;i++)
			System.out.println(stringstore[i]);
			 */
			int irp=0,ci=0,me=0;
			for(int i=0;i<data.size();i++)
			{
				if(data.get(i).equals("Initial Row Pointer,"))
					irp=i;
			}

			for(int k=0;k<data.size();k++)
			{
				if(data.get(k).equals("Column Indices,"))
					ci=k;
			}

			for(int k=0;k<data.size();k++)
			{
				if(data.get(k).equals("Matrix Elements,"))
					me=k;
			}
			
			//System.out.println("The lines are :"+ irp +" " + ci +" "+ me);
			
			ArrayList<String> tInitialRowPointer = new ArrayList<String> ();
			ArrayList<String> tColumnIndices = new ArrayList<String> ();
			ArrayList<String> tMatrixElements = new ArrayList<String> ();
			
			for(int i=irp;i<ci-1;i++)
			{
				tInitialRowPointer.add(data.get(i));
				String[] tempstring = tInitialRowPointer[i].split(" ");
			}
			
			//for( String q:tInitialRowPointer)
		    	//  System.out.println(q);
			
			for(int i=ci+2;i<me-2;i++)
			{
				tColumnIndices.add(data.get(i));
				
			}
			
			for( String q:tColumnIndices)
				System.out.println(q);
			
			for(int i=me-2;i<data.size();i++)
			{
				tMatrixElements.add(data.get(i));
			}
			
			//for( String q:tMatrixElements)
		    	//  System.out.println(q);
			
	}


	catch (FileNotFoundException e) {
		e.printStackTrace();
	} 

	catch (IOException e) {
		e.printStackTrace();
	}
}
}
