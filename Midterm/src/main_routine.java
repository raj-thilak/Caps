
public class main_routine {

	public static void main (String[] args){
		
		Matrix Element=new Matrix();	//Create Object to Class File_read
		Element.fileread();	//Uses the object to execute the method 'fileread'
		Element.Tinney_Ordering();
		Element.Create_LU();
		Element.rowtocolumn();
		Element.columntorow();
		Element.Factorize_LU();
		Element.Forward_Substitue();
	}
}
