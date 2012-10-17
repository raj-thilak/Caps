
import java.io.*;
import java.util.*;

//File_read is used to read the data file and write it line by line.

public class Matrix {

	/*
	 * Declare InitialRowPointer , Column Indices and Matrix Elements arrays as Class Variables
	 * Class variables are those variable that can be accessed by any method
	 */

	int[] InitialRowPointer;
	int[] ColumnIndices;
	double[] MatrixElements;
	double[] y;
	int[] cindx;
	int[] erp;
	int erpcount, erpold;//erpcount and erpold variable is used as references for identifying columnnumbers and rownumbers
	int[]rownumber;//Stores the row number of the element
	//List<Integer> columnnumber=new ArrayList<Integer>();//Stores the column number of the element
	//int[] Col=new int[columnnumber.size()];
	int tdiagonal, tU;//Temporary diagonal and U elements for testing accuracy
	int[] tdiagonalarray;// Temporary Diagonal Array which stores diagonal Elements in an Array
	//int k,i=0;
	int MinNode;//Variable for storing Min Node value
	int[] CindxU= new int [8500]; // An array which stores column indices of U Factor
	int[] RindxU= new int[CindxU.length];// An array which stores row indices of U Factor
	int[] CindxU_Ordered = new int[CindxU.length];// An array which stores ordered column indices values of U
	int[] ERPU;// An array which stores End of Row Pointers of U
	int[] ECPU;//An array which stores End of column Pointers of U
	int[] Switch;//Switch Array used in creating LU Data structure
	int[] Link ;// Self referential linked-list array which stores various links
	int[] Link_LU;
	int[] nxtcolptr;// Next Column Pointer array used in ordering 
	int[] nxtrowptr;// Next row pointer array used in ordering

	int[] ICPL;
	double[]ExAccum;
	double[] URO;
	double[] LCO;
	double[] Diagonal;

	int ERPUCounter = 1;
	int Rindx=0;
	int nextlink;

	
	double[] B;
	double[] X;
	double[] W;
	int[] LRO;
	
	
	// Sample routine for testing if number of column indices is equal to number of matrix elements	        
	/*
	if(y.length==cindx.length)
		System.out.println("YES");
	else
		System.out.println("NO");
	 */


	//System.out.println(erp.length);	

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


			/*
			 **Routine for Identifying Initial Row Pointers,Column Indices and Matrix Elemnts*
			 *Compare the data read and check if it is equal to the String value "Initial Row Pointer"
			 *If the string is equal update the vale of corresponding variable 
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



			/*
			 * *Routine for converting objects into array elements 
			 * asList
			public static <T> List<T> asList(T... a)
			Returns a fixed-size list backed by the specified array. (Changes to the returned list "write through" to the array.) 
			This method acts as bridge between array-based and collection-based APIs, in combination with Collection.toArray(). 
			The returned list is serializable and implements RandomAccess. 
			 * 
			 */
			List<String> tlist= new ArrayList<String>();

			for(int i=irp+1;i<ci-1;i++)
			{
				String SplitChar = "[ ]+";
				tlist = Arrays.asList(data.get(i).trim().split(SplitChar));
				tInitialRowPointer.addAll(tlist);

			}

			/*
			 *** Routine to Convert String into an Integer value***
			 *public static int parseInt(String s)
                    throws NumberFormatException
			Parses the string argument as a signed decimal integer. 
			The characters in the string must all be decimal digits,  
			The resulting integer value is returned, exactly as if the argument and 
			the radix 10 were given as arguments to the parseInt(java.lang.String, int) method.

			 */
			InitialRowPointer = new int[tInitialRowPointer.size()];

			for(int i=0;i<tInitialRowPointer.size();i++)
			{
				InitialRowPointer[i]=Integer.parseInt(tInitialRowPointer.get(i));
			}

			/*
			 * Following is a sample addition code to check if the obtained intgers have been parsed.
			 */

			/*int sum=0;
			sum=InitialRowPointer[0]+InitialRowPointer[1];
			System.out.println(sum);
			 */


			for(int i=ci+2;i<me-2;i++)
			{
				String SplitChar = "[ ]+";
				tlist = Arrays.asList(data.get(i).trim().split(SplitChar));
				tColumnIndices.addAll(tlist);
			}

			ColumnIndices = new int[tColumnIndices.size()];

			for(int i=0;i<tColumnIndices.size();i++)
			{
				ColumnIndices[i]=Integer.parseInt(tColumnIndices.get(i));
			}


			//			int sum=0;
			//			sum=ColumnIndices[0]+ColumnIndices[1];
			//			System.out.println(sum);

			for(int i=me+2;i<data.size()-1;i++)
			{
				String SplitChar = "[ ]+";
				tlist = Arrays.asList(data.get(i).trim().split(SplitChar));
				tMatrixElements.addAll(tlist);
			}

			/*
			 * Print the values of the matriz elements to check if they match the data file.
			 */
			//			for(int i=0;i<tMatrixElements.size();i++)
			//				System.out.println(tMatrixElements.get(i));


			MatrixElements = new double[tMatrixElements.size()];// Double is used for matrix elements because they are not integers

			//			System.out.println(tMatrixElements.size());

			for(int l=0;l<tMatrixElements.size();l++)
			{
				String str=tMatrixElements.get(l);
				if(str!=null)
					MatrixElements[l]=Double.parseDouble(str);// parseDouble is similar to parseInt and performs the same operation on doubles
			}

			/*
			 * Following is a sample addition code to check if the obtained doubles have been parsed.
			 */
			/*
			double sum=0;
			sum=MatrixElements[0]+ MatrixElements[1];
			System.out.println(sum);
			 */

			y= new double[MatrixElements.length+1];
			cindx= new int[ColumnIndices.length+1];
			erp= new int[InitialRowPointer.length];


			for(int l=0;l<MatrixElements.length;l++)
			{
				y[l+1]=MatrixElements[l];
			}


			for(int l=0; l<ColumnIndices.length;l++)
				cindx[l+1] =ColumnIndices[l];


			for(int l=1;l<=InitialRowPointer.length;l++)
			{
				erp[l-1]=InitialRowPointer[l-1]-1;
			}

			System.out.println(erp.length);
			System.out.println(cindx.length);
			System.out.println(y.length);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Create_LU()
	{

		rownumber=new int [cindx.length];
		tdiagonalarray =new int[erp.length];
		ERPU = new int[erp.length];
		ECPU = new int[erp.length+1];
		Switch= new int[erp.length];
		Link = new int[erp.length];
		int ERPUCounter = 1;
		int counter=1;
		int k=0;

		for(int j=1;j<erp.length-1;j++)
		{
			MinNode=100000;
			erpold=erp[j-1]+1;
			erpcount=erp[j];


			for(k=erpold;k<=erpcount;k++)
			{
				if(cindx[k]==j)
				{
					//					System.out.println(y[k]);
					//					System.out.println(counter);
					//					counter++;
					continue;
				}
				else if((cindx[k]!=j)&& cindx[k]>j)
				{
					//columnnumber.add(cindx[k]);
					//Updating CindxU

					CindxU[ERPUCounter]=cindx[k];

					//Updating Min Node Value

					if (MinNode ==100000 )
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

					//					if(MinNode==00000)
					//						MinNode=CindxU[z];
					//					else if((CindxU[z]<MinNode)&&(CindxU[z]>j))
					//						MinNode=CindxU[z];

					if((CindxU[z]>j)&&(Switch[CindxU[z]]!=j))
					{
						CindxU[ERPUCounter]=CindxU[z];

						Switch[CindxU[ERPUCounter]]=j;

						ERPU[j]=ERPUCounter;
						ERPUCounter++;

						if(MinNode==100000)
							MinNode=CindxU[z];
						else if((CindxU[z]<MinNode)&&(CindxU[z]>j))
							MinNode=CindxU[z];
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

		//	        	System.out.println("Pos");
		//	        	for(int q=0;q<13;q++)
		//	        		System.out.print("\t"+q);
		//	        	
		//	        	System.out.println("\n CindxU");
		//	        	for(int q=0;q<CindxU.length;q++)
		//	        		System.out.print("\t"+CindxU[q]);
		//	        	
		//	        	System.out.println("\n ERPU");
		//	        	for(int q=0;q<ERPU.length;q++)
		//	        		System.out.print("\t"+ERPU[q]);
		//	        	
		//	        	System.out.println("\n Switch");
		//	        	for(int q=0;q<Switch.length;q++)
		//	        		System.out.print("\t"+Switch[q]);
		//	        	
		//       	System.out.println("\n Link");
		//	        	for(int q=0;q<Link.length;q++)
		//	        		System.out.print("\t"+Link[q]);
	}

	public void rowtocolumn()
	{
		int tposition = 0;
		nxtcolptr = new int[erp.length];
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

		for (int i = 2; i < erp.length; i++)
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

		//		System.out.println("\n RindxU");
		//		for(int q=0;q<RindxU.length;q++)
		//			System.out.print("\t"+RindxU[q]);
		//
		//		System.out.println("\n ECPU");
		//		for(int q=0;q<ECPU.length;q++)
		//			System.out.print("\t"+ECPU[q]);
	}

	public void columntorow()
	{
		int tposition = 0;
		nxtrowptr = new int[erp.length];
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
		for (int i = 2; i < erp.length; i++)
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

		//		System.out.println("\n CindxU Ordered");
		//		for(int q=0;q<CindxU_Ordered.length;q++)
		//			System.out.print("\t"+CindxU_Ordered[q]);
		//
		//		System.out.println("\n ERPU");
		//		for(int q=0;q<ERPU.length;q++)
		//			System.out.print("\t"+ERPU[q]);

	}

	public void Factorize_LU() 
	{
		//int rx = 0;
		int ERPU_Shifter=0;
		int nodelink = 0;
		int tlink = 0;
		int compare=0;
		int comparelink=0;

		Link_LU= new int[erp.length];
		ICPL = new int[erp.length];
		ExAccum= new double[erp.length];
		URO = new double[CindxU.length];
		LCO = new double[CindxU.length];
		Diagonal=new double[erp.length];

		//Initializing ICPL using ERPU
		for (int i = 1; i < ERPU.length-1; i++)
		{
			ERPU_Shifter= ERPU[i - 1];
			ICPL[i] = ERPU_Shifter + 1;
			//System.out.print(ICPL[i] + " ");
		}

		//		for(int x=1;x<8169;x++)
		//			System.out.println(CindxU_Ordered[x]);

		int URO_Counter= 1;

		for (Rindx = 1; Rindx<=erp.length-1; Rindx++)
		{
			if(Rindx<erp.length-1)
			{
				for (int j = ERPU[Rindx - 1] + 1; j <= ERPU[Rindx]; j++)
				{
					//Initializing ExAccum using CindxU ordered.
					ExAccum[CindxU_Ordered[j]] = 0.00;
				}
			}

			//Initializing ExAccum to 0 using link_1
			int node = Rindx, templink;
			while ((templink = Link_LU[node]) != 0)
			{
				ExAccum[templink] = 0.00;
				node=templink;
			}

			// Copying matrix elements in the ExAccum
			for (int j =erp[Rindx-1]+ 1; j <= erp[Rindx]; j++)
				ExAccum[cindx[j]] = y[j];

			double multiplier=0;

			List<Integer> Nodes = new ArrayList<Integer>();
			int rx1;

			while((Link_LU[Rindx])!=0){
				nodelink = Rindx;
				while(true){
					if((rx1 = Link_LU[nodelink])!= 0)
					{
						Link_LU[nodelink] = 0;
						Nodes.add(rx1);
						nodelink = rx1;
					}
					else
						break;
				}

				int rxlink=0, nextnod;

				for(Integer rx:Nodes)
				{
					multiplier = ExAccum[rx];

					for(int z=ERPU[rx-1]+1;z<=ERPU[rx];z++)
					{
						ExAccum[CindxU_Ordered[z]]=ExAccum[CindxU_Ordered[z]] - (multiplier*URO[z]);
					}

					if(ExAccum[rx] == 0.0)
						System.out.println(rx + "\t"+ Rindx+"Stupidity");

					if(ICPL[rx] < 8169)
						LCO[ICPL[rx]] = ExAccum[rx] * Diagonal[rx];

					ICPL[rx]++;

					if(ICPL[rx] < 8169)
						rxlink = CindxU_Ordered[ICPL[rx]];


					if(ICPL[rx]<=ERPU[rx])
					{
						while((nextnod = Link_LU[rxlink])!= 0){
							rxlink = Link_LU[nextnod];
							if(rxlink == 0){
								rxlink = nextnod;
								break;
							}
						}

						Link_LU[rxlink] = rx;
					}
				}
			}
			// Updating Diagonal Value 
			Diagonal[Rindx] = 1 / ExAccum[Rindx];

			// Updating URO Value
			if(Rindx < 662){
				for (int j = ERPU[Rindx - 1] + 1; j <= ERPU[Rindx]; j++)
				{
					URO[URO_Counter] = Diagonal[Rindx] * ExAccum[CindxU_Ordered[j]];
					URO_Counter++;            
				}

				// Adding Rindx to Linked List
				int linkrownumber, NextNode;

				linkrownumber = CindxU_Ordered[ICPL[Rindx]];

				while((NextNode = Link_LU[linkrownumber])!= 0){
					linkrownumber = Link_LU[NextNode];
					if(linkrownumber == 0){
						linkrownumber = NextNode;
						break;
					}
				}
				Link_LU[linkrownumber] = Rindx;
			}

		}

		//
		//		System.out.println("\n Position");
		//		for(int q=0;q<15;q++)
		//			System.out.print("\t"+ q);
		//
		//		System.out.println("\n Diagonal");
		//		for(int q=0;q<Diagonal.length;q++)
		//			System.out.println("\t"+Diagonal[q]);
		//
		//		System.out.println("\n ERPU");
		//		for(int q=0;q<ERPU.length;q++)
		//			System.out.print("\t"+ERPU[q]);
		//
		//		System.out.println("\n URO");
		//		for(int q=1;q<2000;q++)
		//			System.out.println("\t"+URO[q]);
		//
		//		System.out.println("\n CindxU_Ordered");
		//		for(int q=0;q<CindxU_Ordered.length;q++)
		//			System.out.print("\t"+CindxU_Ordered[q]);
		//
		//		System.out.println("\n LCO");
		//		for(int q=0;q<LCO.length;q++)
		//			System.out.print("\t"+LCO[q]);
		//
		//		System.out.println("\n ICPL");
		//		for(int q=0;q<ICPL.length;q++)
		//			System.out.print("\t"+ICPL[q]);
		//
		//		System.out.println("\n LINK-LU");
		//		for(int q=0;q<Link_LU.length;q++)
		//			System.out.println("\t"+Link_LU[q]);
		//
		//		System.out.println("\n Ex-Accum");
		//		for(int q=0;q<ExAccum.length;q++)
		//			System.out.print("\t"+ExAccum[q]);
	}

	public void Forward_Substitue()
	{

		 B = new double[erp.length];
		 X = new double[erp.length];
		 W =new double[erp.length];
		 LRO = new int[CindxU_Ordered.length];
		
		double B_initial = y[1] ;
		
		B[1] = B_initial;
		
		for(int l=0;l<erp.length;l++)
			W[l]=B[l];
		
		for(int l=1;l<CindxU_Ordered.length;l++)
			LRO[l]=CindxU_Ordered[l];
		
		int temp;		
		for(int i = 1;  i <=662; i++)
		{
			
			for(int j = ERPU[i -1]+1; j <= ERPU[i] ; j++)
			{
		//		temp=LRO[j];
				W[LRO[j]] = W[LRO[j]] - LCO[j]*W[i];
			}
		}

		
		for(int b=1; b< 663; b++)
			X[b] = W[b] * Diagonal[b];
		
		
		for(int k=662; k >= 1; k--)
		{
			
			for(int j = ERPU[k];j >= ERPU[k-1]+1;j--)
			{
				X[k] = X[k] - X[k+1]*URO[j];
			}
		}
	
		System.out.println("THE END");
		for(double i:X)
			System.out.println(i);
	}

}
