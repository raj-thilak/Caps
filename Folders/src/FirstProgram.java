import java.util.*;

public class FirstProgram 
{
	
	 public static void main(String args[])
     {
     	FirstProgram Data=new FirstProgram();
     	Data.Create_LU();
     	Data.rowtocolumn();
     	Data.columntorow();
     }
			
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
	int[] CindxU= new int [12]; // An array which stores column indices of U Factor
	int[] RindxU= new int[CindxU.length];// An array which stores row indices of U Factor
	int[] CindxU_Ordered = new int[CindxU.length];// An array which stores ordered column indices values of U
	int[] ERPU = new int[erp.length];// An array which stores End of Row Pointers of U
	int[] ECPU = new int[erp.length+1];//An array which stores End of column Pointers of U
	int[] Switch= new int[erp.length];//Switch Array used in creating LU Data structure
	int[] Link = new int[erp.length];// Self referential linked-list array which stores various links
	int[] nxtcolptr = new int[erp.length];// Next Column Pointer array used in ordering 
	int[] nxtrowptr = new int[erp.length];// Next row pointer array used in ordering
	int ERPUCounter = 1;
	int nextlink;
	//System.out.println(erp.length);
	

	public void Create_LU()
	        	{
	        	
	        	for(int j=1;j<erp.length-1;j++)
	        	{
	        		MinNode=1000;
	        		erpold=erp[j-1]+1;
	        		erpcount=erp[j];
	        		
	        		for(k=erpold;k<=erpcount;k++)
	        		{
	        			if(cindx[k]==j)
	        				continue;
	        			else if((cindx[k]!=j)&& cindx[k]>j)
	        			{
	        				//columnnumber.add(cindx[k]);
	        				//Updating CindxU
	        				CindxU[ERPUCounter]=cindx[k];
	        				
	        				//Updating Min Node Value
	        				if (MinNode ==1000 )
	                            MinNode = cindx[k];
	                        else if (cindx[k] < MinNode)
	                            MinNode = cindx[k];
	        				
	        				//Updating Switch Array
	        				Switch[CindxU[ERPUCounter]] = j;
	        				
	        				//ERPU Update
	        				ERPU[j] = ERPUCounter;
	        				ERPUCounter++;
	        			}
	        			
//	        			System.out.println(columnnumber);
//	        			for(int z:ERPU)
//	        			System.out.println(z + "\t");
	        		}
	        		
	        		int tlink=j;
	        		
	        	while((nextlink=Link[tlink])!=0)
	        	{
	        		for(int z=ERPU[nextlink-1]+1;z<=ERPU[nextlink];z++)
	        		{
	        			if(nextlink==CindxU[z])
	        				continue;
	        			
	        			if(MinNode==1000)
	        				MinNode=CindxU[z];
	        			else if((CindxU[z]<MinNode)&&(CindxU[z]>j))
	        				MinNode=CindxU[z];
	        			
	        			if((CindxU[z]>j)&&(Switch[CindxU[z]]!=j))
	        			{
	        				CindxU[ERPUCounter]=CindxU[z];
	        				
	        				Switch[CindxU[ERPUCounter]]=j;
	        				
	        				ERPU[j]=ERPUCounter;
	        				ERPUCounter++;
	        			}
	        			
	        		}
	        		
	        		tlink=nextlink;
	        	}
	        	
	        	tlink=MinNode;
	        	
	        	while((nextlink=Link[tlink])!=0)
	        	{
	        		if(Link[nextlink]==0)
	        		{	        			
	        			MinNode=nextlink;
	        			break;
	        		}
	        		
	        		tlink=nextlink;
	        	}
	        	
	        	Link[j]=0;
	        	Link[MinNode]=j;
	        }
	        	System.out.println("Pos");
	        	for(int q=0;q<13;q++)
	        		System.out.print("\t"+q);
	        	
	        	System.out.println("\n CindxU");
	        	for(int q=0;q<CindxU.length;q++)
	        		System.out.print("\t"+CindxU[q]);
	        	
	        	System.out.println("\n ERPU");
	        	for(int q=0;q<ERPU.length;q++)
	        		System.out.print("\t"+ERPU[q]);
	        	
	        	System.out.println("\n Switch");
	        	for(int q=0;q<Switch.length;q++)
	        		System.out.print("\t"+Switch[q]);
	        	
	        	System.out.println("\n Link");
	        	for(int q=0;q<Link.length;q++)
	        		System.out.print("\t"+Link[q]);
	        	
	        	
	      }

	public void rowtocolumn()
		{
			int tposition = 0;
            int[] t_nxcolpt = new int[erp.length];

            //Counting the no of elements in a row
            for (int i = 1; (i < CindxU.length - 1); i++)
            {
                if (CindxU[i] == 0)
                    break;
                tposition = CindxU[i];
                t_nxcolpt[tposition]++;
                
            }
            //Performing a running sum on the next column pointer 
            nxtcolptr[1] = 1;

            for (int i = 2; i < 8; i++)
            {
                nxtcolptr[i] = nxtcolptr[i - 1] + t_nxcolpt[i - 1];
                //System.out.println(nxtcolptr[i] + " ");
            }


            int tstoreelement;
            for (int i = 1; i < ERPU.length - 1; i++)
            {
                for (int j = ERPU[i - 1] + 1; j <= ERPU[i]; j++)
                {
                    tstoreelement = nxtcolptr[CindxU[j]];
                    RindxU[tstoreelement] = i;
                    nxtcolptr[CindxU[j]]++;
                }
            }

            for (int i = 1; i <nxtcolptr.length; i++)
                ECPU[i] = nxtcolptr[i] - 1;
            
        	System.out.println("\n RindxU");
        	for(int q=0;q<RindxU.length;q++)
        		System.out.print("\t"+RindxU[q]);
        	
        	System.out.println("\n ECPU");
        	for(int q=0;q<ECPU.length;q++)
        		System.out.print("\t"+ECPU[q]);
		}
	
	public void columntorow()
	{
		int tposition = 0;
        int[] t_nxtrowptr = new int[erp.length];


        //Counting the no of elements in a row
        for (int i = 1; i < RindxU.length - 1; i++)
        {
            if (RindxU[i] == 0)
                break;
            tposition = RindxU[i];
            t_nxtrowptr[tposition]++;
        }
        
        //Performing a running sum on the next row pointer 
        nxtrowptr[1] = 1;
        for (int i = 2; i < 8; i++)
            nxtrowptr[i] = nxtrowptr[i - 1] + t_nxtrowptr[i - 1];

        int tstore;
        for (int i = 1; i < ECPU.length - 1; i++)
        {
            for (int j = ECPU[i-1]+1; j <= ECPU[i]; j++)
            {
                tstore = nxtrowptr[RindxU[j]];
                CindxU_Ordered[tstore] = i;
                nxtrowptr[RindxU[j]]++;
            }
        }

        for (int i = 1; i < nxtrowptr.length-1; i++)
            ERPU[i] = nxtrowptr[i]-1;
        
        System.out.println("\n CindxU Ordered");
    	for(int q=0;q<CindxU_Ordered.length;q++)
    		System.out.print("\t"+CindxU_Ordered[q]);
    	
    	System.out.println("\n ERPU");
    	for(int q=0;q<ERPU.length;q++)
    		System.out.print("\t"+ERPU[q]);
		
	}

}
	        