
public class EXthree {

	public static void main(String[] args){
		
		System.out.println(average(10,50,100,20,50));
	}
	
	public static int average(int...numbers){
		int sum=0;
		
		for(int x:numbers)
			sum+=x;
		return sum/numbers.length;
			
	}
}
