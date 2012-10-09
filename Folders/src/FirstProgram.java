import java.util.*;

public class FirstProgram {
	       
	        public static void main(String args[]){
	        	
	        	int y[]={0,2,-1,3,4,2,1,6,8,7,3,2,8,10,10,9,3,2,12,3,0,14};
	        	int cindx[]={0,1,5,4,2,7,6,3,4,7,1,3,4,1,5,6,2,5,6,2,3,7};
	        	int erp[]={0,3,6,9,12,15,18,21};

	        	// Sample routine for testing if number of column indices is equal to number of matrix elements	        
	        	/*
	        	if(y.length==cindx.length)
	        		System.out.println("YES");
	        	else
	        		System.out.println("NO");
	            */
	        	int erpcount, erpold;//erpcount and erpold variable is used as references for identifying columnnumbers and rownumbers
	        	int[]rownumber=new int [cindx.length];//Stores the row number of the element
	        	List<Integer> columnnumber=new ArrayList<Integer>();//Stores the column number of the element
	        	int[] Col=new int[columnnumber.size()];
	        	int tdiagonal, tU;//Temporary diagonal and U elements for testing accuracy
	        	int[] tdiagonalarray =new int[erp.length];// Temporary Diagonal Array which stores diagonal Elements in an Array
	        	int k,i=0;
	        	int MinNode;//Variable for storing Min Node value
	        	int[] CindxU= new int [columnnumber.size()];
	        	int[] ERPU = new int[erp.length];
	        	int[] Switch= new int[erp.length];
	        	int[] Link = new int[erp.length];
	        	int ERPUCounter = 0;
	        	
	        	for(int j=1;j<erp.length;j++)
	        	{
	        		MinNode=1000;
	        		erpold=erp[j-1]+1;
	        		erpcount=erp[j];
	        		
	        		for(k=erpold+1;k<=erpcount;k++)
	        		{
	        			if((cindx[k]!=j)&& cindx[k]>j)
	        			{
	        				columnnumber.add(cindx[k]);
	        				
	        				//Updating Min Node Value
	        				if (MinNode ==1000 )
	                            MinNode = cindx[k];
	                        else if (cindx[k] < MinNode)
	                            MinNode = cindx[k];
	        				
	        				//Updating Switch Array
	        				Switch[cindx[k]] = j;
	        				
	        				//ERPU Update
	        				ERPU[j] = ++ERPUCounter;
	        			}
	        			
	        			
	        			

	        			System.out.println(columnnumber);
	        			for(int z:ERPU)
	        			System.out.println(z + "\t");
	        		}
	        		
	        		int tlink=j;
	        		int nexttlink;
	        	//while()
	        	
	        	}
	        		        
	        }
}