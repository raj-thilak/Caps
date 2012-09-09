
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// File_read is used to read the data file and write it line by line.

public class File_read {

	public static void main(String[] args){
		
		
		File file = new File("Data_662.dat");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;

	    try {
	      fis = new FileInputStream(file); //Input Stream that reads from file
	      bis = new BufferedInputStream(fis); //Creates an internal buffer array to perform fast read
	      dis = new DataInputStream(bis);

	      // dis.available() returns 0 if the file does not have more lines.
	      
	      while (dis.available() != 0) {

	      // this statement reads the line from the file and print it to
	        // the console.
	      
	    	  System.out.println(dis.readLine());
	      }

	      // dispose all the resources after using them.
	      fis.close();
	      bis.close();
	      dis.close();

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	}
	
