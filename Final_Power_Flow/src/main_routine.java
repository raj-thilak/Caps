
public class main_routine {
	
	public static void main (String[] args)

	{
	Sub_Routines Element = new Sub_Routines();
	Element.Create_Y_Bus();
	Element.Tinney_Ordering();
	Element.Create_LU();
	Element.rowtocolumn();
	Element.columntorow();
	Element.initialize_voltage();
	Element.injection_flow();
	}
	

}
