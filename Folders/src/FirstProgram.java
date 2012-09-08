

public class FirstProgram {
	
	public static void main (String[] args){
		int farray[][]={{1,2,3,4},{4,5,6,7},{7,9,9,20},{0,2,3,0}};
		int sarray[][]={{0,1,3},{0,2,0},{3,0,0}};
		
		System.out.println("The first array is: \n");
		display(farray);
		
		System.out.println("\nThe second array is: \n");
		display(sarray);
	}
	
	public static void display(int x[][]){
	for(int row=0;row<x.length;row++)
	{
		for(int col=0;col<x[row].length;col++)
		{
			System.out.print(x[row][col]+"\t");
		}
		System.out.println();
	}
	}
}