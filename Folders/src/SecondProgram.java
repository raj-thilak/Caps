 
public class SecondProgram {
 private String gname;
 
 public SecondProgram(String name){
	 gname=name;
 }

 
 public void step1(String name)
 {
	 gname=name;
 }
public String step2()
{
	return gname;
}
public void step3(){
	
	System.out.printf("Your such an A$$ Ho!e %s", step2());
}
}
