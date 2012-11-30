import java.util.ArrayList;
import java.util.List;


public class Sub_Routines {
	
	int BusCount = 118;
    int BranchCount = 186;

    // Initialize matrix Arrays required to perform Power Flow
    int[] OriBusNo = new int[119];
    int[] BusType = new int[119];
    double[] Vmag = new double[119];
    double[] Vang = new double[119];
    double[] P_load = new double[119];
    double[] Q_load = new double[119];
    double[] P_gen = new double[119];
    double[] Vsh = new double[119];
    double[] QgenMax = new double[119];
    double[] QgenMin = new double[119];
    double[] Gsh = new double[119];
    double[] Bsh = { 0, 0, 0, 0, 0, -0.4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.14, 0, 0, -0.25, 0, 0, 0, 0, 0, 0, 0.1, 0.1, 0.1, 0, 0.15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.12, 0, 0, 0, 0, 0.2, 0, 0, 0.2, 0.1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.2, 0, 0.06, 0, 0, 0.06, 0, 0, 0, 0, 0, 0, 0, 0 };
    double[] Gself = new double[119];
    double[] Bself = new double[119];

    // Branch Data - Obtain after file read and connect each other
    // Use of raw elements only for test case
    int[] From_Bus = { 0, 1, 1, 4, 3, 5, 6, 8, 8, 9, 4, 5, 11, 2, 3, 7, 11, 12, 13, 14, 12, 15, 16, 17, 18, 19, 15, 20, 21, 22, 23, 23, 26, 25, 27, 28, 30, 8, 26, 17, 29, 23, 31, 27, 15, 19, 35, 35, 33, 34, 34, 38, 37, 37, 30, 39, 40, 40, 41, 43, 34, 44, 45, 46, 46, 47, 42, 42, 45, 48, 49, 49, 51, 52, 53, 49, 49, 54, 54, 55, 56, 50, 56, 51, 54, 56, 56, 55, 59, 59, 60, 60, 61, 63, 63, 64, 38, 64, 49, 49, 62, 62, 65, 66, 65, 47, 49, 68, 69, 24, 70, 24, 71, 71, 70, 70, 69, 74, 76, 69, 75, 77, 78, 77, 77, 79, 68, 81, 77, 82, 83, 83, 84, 85, 86, 85, 85, 88, 89, 89, 90, 89, 89, 91, 92, 92, 93, 94, 80, 82, 94, 80, 80, 80, 92, 94, 95, 96, 98, 99, 100, 92, 101, 100, 100, 103, 103, 100, 104, 105, 105, 105, 106, 108, 103, 109, 110, 110, 17, 32, 32, 27, 114, 68, 12, 75, 76 };
    int[] To_Bus = { 0, 2, 3, 5, 5, 6, 7, 9, 5, 10, 11, 11, 12, 12, 12, 12, 13, 14, 15, 15, 16, 17, 17, 18, 19, 20, 19, 21, 22, 23, 24, 25, 25, 27, 28, 29, 17, 30, 30, 31, 31, 32, 32, 32, 33, 34, 36, 37, 37, 36, 37, 37, 39, 40, 38, 40, 41, 42, 42, 44, 43, 45, 46, 47, 48, 49, 49, 49, 49, 49, 50, 51, 52, 53, 54, 54, 54, 55, 56, 56, 57, 57, 58, 58, 59, 59, 59, 59, 60, 61, 61, 62, 62, 59, 64, 61, 65, 65, 66, 66, 66, 67, 66, 67, 68, 69, 69, 69, 70, 70, 71, 72, 72, 73, 74, 75, 75, 75, 77, 77, 77, 78, 79, 80, 80, 80, 81, 80, 82, 83, 84, 85, 85, 86, 87, 88, 89, 89, 90, 90, 91, 92, 92, 92, 93, 94, 94, 95, 96, 96, 96, 97, 98, 99, 100, 100, 96, 97, 100, 100, 101, 102, 102, 103, 104, 104, 105, 106, 105, 106, 107, 108, 107, 109, 110, 110, 111, 112, 113, 113, 114, 115, 115, 116, 117, 118, 118 };
    int[] BranchType = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    double[] R = { 0, 0.030300, 0.012900, 0.001760, 0.024100, 0.011900, 0.004590, 0.002440, 0.000000, 0.002580, 0.020900, 0.020300, 0.005950, 0.018700, 0.048400, 0.008620, 0.022250, 0.021500, 0.074400, 0.059500, 0.021200, 0.013200, 0.045400, 0.012300, 0.011190, 0.025200, 0.012000, 0.018300, 0.020900, 0.034200, 0.013500, 0.015600, 0.000000, 0.031800, 0.019130, 0.023700, 0.000000, 0.004310, 0.007990, 0.047400, 0.010800, 0.031700, 0.029800, 0.022900, 0.038000, 0.075200, 0.002240, 0.011000, 0.041500, 0.008710, 0.002560, 0.000000, 0.032100, 0.059300, 0.004640, 0.018400, 0.014500, 0.055500, 0.041000, 0.060800, 0.041300, 0.022400, 0.040000, 0.038000, 0.060100, 0.019100, 0.071500, 0.071500, 0.068400, 0.017900, 0.026700, 0.048600, 0.020300, 0.040500, 0.026300, 0.073000, 0.086900, 0.016900, 0.002750, 0.004880, 0.034300, 0.047400, 0.034300, 0.025500, 0.050300, 0.082500, 0.080300, 0.047390, 0.031700, 0.032800, 0.002640, 0.012300, 0.008240, 0.000000, 0.001720, 0.000000, 0.009010, 0.002690, 0.018000, 0.018000, 0.048200, 0.025800, 0.000000, 0.022400, 0.001380, 0.084400, 0.098500, 0.000000, 0.030000, 0.002210, 0.008820, 0.048800, 0.044600, 0.008660, 0.040100, 0.042800, 0.040500, 0.012300, 0.044400, 0.030900, 0.060100, 0.003760, 0.005460, 0.017000, 0.029400, 0.015600, 0.001750, 0.000000, 0.029800, 0.011200, 0.062500, 0.043000, 0.030200, 0.035000, 0.028280, 0.020000, 0.023900, 0.013900, 0.051800, 0.023800, 0.025400, 0.009900, 0.039300, 0.038700, 0.025800, 0.048100, 0.022300, 0.013200, 0.035600, 0.016200, 0.026900, 0.018300, 0.023800, 0.045400, 0.064800, 0.017800, 0.017100, 0.017300, 0.039700, 0.018000, 0.027700, 0.012300, 0.024600, 0.016000, 0.045100, 0.046600, 0.053500, 0.060500, 0.009940, 0.014000, 0.053000, 0.026100, 0.053000, 0.010500, 0.039060, 0.027800, 0.022000, 0.024700, 0.009130, 0.061500, 0.013500, 0.016400, 0.002300, 0.000340, 0.032900, 0.014500, 0.016400 };
    double[] X = { 0, 0.099900, 0.042400, 0.007980, 0.108000, 0.054000, 0.020800, 0.030500, 0.026700, 0.032200, 0.068800, 0.068200, 0.019600, 0.061600, 0.160000, 0.034000, 0.073100, 0.070700, 0.244400, 0.195000, 0.083400, 0.043700, 0.180100, 0.050500, 0.049300, 0.117000, 0.039400, 0.084900, 0.097000, 0.159000, 0.049200, 0.080000, 0.038200, 0.163000, 0.085500, 0.094300, 0.038800, 0.050400, 0.086000, 0.156300, 0.033100, 0.115300, 0.098500, 0.075500, 0.124400, 0.247000, 0.010200, 0.049700, 0.142000, 0.026800, 0.009400, 0.037500, 0.106000, 0.168000, 0.054000, 0.060500, 0.048700, 0.183000, 0.135000, 0.245400, 0.168100, 0.090100, 0.135600, 0.127000, 0.189000, 0.062500, 0.323000, 0.323000, 0.186000, 0.050500, 0.075200, 0.137000, 0.058800, 0.163500, 0.122000, 0.289000, 0.291000, 0.070700, 0.009550, 0.015100, 0.096600, 0.134000, 0.096600, 0.071900, 0.229300, 0.251000, 0.239000, 0.215800, 0.145000, 0.150000, 0.013500, 0.056100, 0.037600, 0.038600, 0.020000, 0.026800, 0.098600, 0.030200, 0.091900, 0.091900, 0.218000, 0.117000, 0.037000, 0.101500, 0.016000, 0.277800, 0.324000, 0.037000, 0.127000, 0.411500, 0.035500, 0.196000, 0.180000, 0.045400, 0.132300, 0.141000, 0.122000, 0.040600, 0.148000, 0.101000, 0.199900, 0.012400, 0.024400, 0.048500, 0.105000, 0.070400, 0.020200, 0.037000, 0.085300, 0.036650, 0.132000, 0.148000, 0.064100, 0.123000, 0.207400, 0.102000, 0.173000, 0.071200, 0.188000, 0.099700, 0.083600, 0.050500, 0.158100, 0.127200, 0.084800, 0.158000, 0.073200, 0.043400, 0.182000, 0.053000, 0.086900, 0.093400, 0.108000, 0.206000, 0.295000, 0.058000, 0.054700, 0.088500, 0.179000, 0.081300, 0.126200, 0.055900, 0.112000, 0.052500, 0.204000, 0.158400, 0.162500, 0.229000, 0.037800, 0.054700, 0.183000, 0.070300, 0.183000, 0.028800, 0.181300, 0.076200, 0.075500, 0.064000, 0.030100, 0.203000, 0.061200, 0.074100, 0.010400, 0.004050, 0.140000, 0.048100, 0.054400 };
    double[] Bshx2 = { 0, 0.025400, 0.010820, 0.002100, 0.028400, 0.014260, 0.005500, 1.162000, 0.000000, 1.230000, 0.017480, 0.017380, 0.005020, 0.015720, 0.040600, 0.008740, 0.018760, 0.018160, 0.062680, 0.050200, 0.021400, 0.044400, 0.046600, 0.012980, 0.011420, 0.029800, 0.010100, 0.021600, 0.024600, 0.040400, 0.049800, 0.086400, 0.000000, 0.176400, 0.021600, 0.023800, 0.000000, 0.514000, 0.908000, 0.039900, 0.008300, 0.117300, 0.025100, 0.019260, 0.031940, 0.063200, 0.002680, 0.013180, 0.036600, 0.005680, 0.009840, 0.000000, 0.027000, 0.042000, 0.422000, 0.015520, 0.012220, 0.046600, 0.034400, 0.060680, 0.042260, 0.022400, 0.033200, 0.031600, 0.047200, 0.016040, 0.086000, 0.086000, 0.044400, 0.012580, 0.018740, 0.034200, 0.013960, 0.040580, 0.031000, 0.073800, 0.073000, 0.020200, 0.007320, 0.003740, 0.024200, 0.033200, 0.024200, 0.017880, 0.059800, 0.056900, 0.053600, 0.056460, 0.037600, 0.038800, 0.014560, 0.014680, 0.009800, 0.000000, 0.216000, 0.000000, 1.046000, 0.380000, 0.024800, 0.024800, 0.057800, 0.031000, 0.000000, 0.026820, 0.638000, 0.070920, 0.082800, 0.000000, 0.122000, 0.101980, 0.008780, 0.048800, 0.044440, 0.011780, 0.033680, 0.036000, 0.124000, 0.010340, 0.036800, 0.103800, 0.049780, 0.012640, 0.006480, 0.047200, 0.022800, 0.018700, 0.808000, 0.000000, 0.081740, 0.037960, 0.025800, 0.034800, 0.012340, 0.027600, 0.044500, 0.027600, 0.047000, 0.019340, 0.052800, 0.106000, 0.021400, 0.054800, 0.041400, 0.032680, 0.021800, 0.040600, 0.018760, 0.011100, 0.049400, 0.054400, 0.023000, 0.025400, 0.028600, 0.054600, 0.047200, 0.060400, 0.014740, 0.024000, 0.047600, 0.021600, 0.032800, 0.014640, 0.029400, 0.053600, 0.054100, 0.040700, 0.040800, 0.062000, 0.009860, 0.014340, 0.047200, 0.018440, 0.047200, 0.007600, 0.046100, 0.020200, 0.020000, 0.062000, 0.007680, 0.051800, 0.016280, 0.019720, 0.002760, 0.164000, 0.035800, 0.011980, 0.013560 };
    int[] ControlBus = new int[186];
    int[] T_side = new int[186];
    double[] Tp_ratio = { 0, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.985000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.960000, 0.000000, 0.000000, 0.000000, 0.960000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.935000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.960000, 0.000000, 0.985000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.935000, 0.000000, 0.000000, 0.000000, 0.000000, 0.935000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.935000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000 };
    double[] Tp_Min = new double[186];
    double[] Tp_Max = new double[186];
    
    int b_l = Bsh.length;

    //int[] N_Row_ele = new int[119];
    int[] ERP = new int[119];
    double[] Z = new double[187];
    double[] GB = new double[187];
    double[] BB = new double[187];
    double[] SB = new double[187];
    

    int[] Cindx = new int[492];//no of branches considered for size 
    int ERP_Counter = 0;
    int Mat_Counter = 0;
    int[] ele_added = new int[119];
    double[] Y_Mat_G = new double[492];// size justification = 2*(no of branches) + 118-> (diagonal elements)

    double[] Y_Mat_B = new double[492];

    /*
     * Variables for Performing LU Factorization , Copied frm Midterm 
     */
    
	int[] InitialRowPointer;
	int[] ColumnIndices;
	double[] MatrixElements;
	double[] y;
	//int[] Cindx;
	//int[] erp;
	
	int erp_length=0;
	int erpcount, erpold;//erpcount and erpold variable is used as references for identifying columnnumbers and rownumbers
	int[]rownumber;//Stores the row number of the element
	//List<Integer> columnnumber=new ArrayList<Integer>();//Stores the column number of the element
	//int[] Col=new int[columnnumber.size()];
	
	int tdiagonal, tU;//Temporary diagonal and U elements for testing accuracy
	int[] tdiagonalarray;// Temporary Diagonal Array which stores diagonal Elements in an Array
	//int k,i=0;
	
	int MinNode;//Variable for storing Min Node value
	int[] CindxU; // An array which stores column indices of U Factor
	int[] RindxU;// An array which stores row indices of U Factor
	int[] CindxU_Ordered;// An array which stores ordered column indices values of U
	
	List<Integer> tCindxU =new ArrayList<Integer>();
	
	int[] ERPU;// An array which stores End of Row Pointers of U
	int[] ECPU;//An array which stores End of column Pointers of U
	int[] Switch;//Switch Array used in creating LU Data Matrix_sizeucture
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



	public void Data_Extract()
	{
	}
	
	public void Create_Y_Bus()

	{
		
        //****** make sure that data extractiona and Y bus formulation are separate routines.********
  


            // Self conductance and self susceptance calculated from Bus data calculated at 1 MVA base
            for (int i = 1; i < 119; i++)
            {
                Gself[i] = Gsh[i] * 100;// base = 100 MVA in both cases.
                Bself[i] = Bsh[i] * 100;
            }

            int Tp = 1;
            //double[] A= new double[187];
            double A = 1;

            // creating tap ratio for the modified transformer model
            for (int i = 1; i <= BranchCount; i++)
            {
                if (BranchType[i] != 0)
                {
                    if (Tp_ratio[i] != 0)
                        A = 1 / (Tp_ratio[i]);
                }
                else
                    A = 1 / Tp;

                Z[i] = R[i] * R[i] + X[i] * X[i];
                GB[i] = 100 * R[i] / Z[i] * A;

                BB[i] = 100 * (X[i] / Z[i]) * A;
                SB[i] = 0.5 * 100 * Bshx2[i];

                // computing the diagonal elements of the G and the B matrix


                Gself[From_Bus[i]] = Gself[From_Bus[i]] + A * GB[i];
                Bself[From_Bus[i]] = Bself[From_Bus[i]] + A * BB[From_Bus[i]] + SB[From_Bus[i]];

                Gself[To_Bus[i]] = Gself[To_Bus[i]] + GB[i] / A;
                Bself[To_Bus[i]] = Bself[To_Bus[i]] + BB[i] / A + SB[i];
            }


            // Creating Y bus structure

            for (int i = 1; i <= BusCount; i++)
            {
                Mat_Counter++;
                //update Diagonal element, Cindx for diag ele
                Y_Mat_G[Mat_Counter] = Gself[i];
                Y_Mat_B[Mat_Counter] = Bself[i];
                Cindx[Mat_Counter] = i;
                ele_added[i]++;

                for (int j = 1; j <= BranchCount; j++)
                {

                    if (From_Bus[j] == i)
                    {
                        Mat_Counter++;
                        Y_Mat_G[Mat_Counter] = -GB[j];
                        Y_Mat_B[Mat_Counter] = -BB[j];
                        Cindx[Mat_Counter] = To_Bus[j];
                        ele_added[i]++;

                        if (j < BranchCount)
                        {
                            if (To_Bus[j] == To_Bus[j + 1] && From_Bus[j + 1] == i)
                            {
                                Y_Mat_G[Mat_Counter] = -GB[j] - GB[j + 1];
                                Y_Mat_B[Mat_Counter] = -BB[j] - BB[j + 1];
                                Cindx[Mat_Counter] = To_Bus[j];
                                j++;
                               // ele_added[i]++;
                            }
                        }
                    }
                    if (To_Bus[j] == i)
                    {
                        Mat_Counter++;
                        Y_Mat_G[Mat_Counter] = -GB[j];
                        Y_Mat_B[Mat_Counter] = -BB[j];
                        Cindx[Mat_Counter] = From_Bus[j];
                        ele_added[i]++;

                        if (j < BranchCount)
                        {
                            if (From_Bus[j] == From_Bus[j + 1] && To_Bus[j + 1] == i)
                            {
                                Y_Mat_G[Mat_Counter] = -GB[j] - GB[j + 1];
                                Y_Mat_B[Mat_Counter] = -BB[j] - BB[j + 1];
                                Cindx[Mat_Counter] = To_Bus[j];
                                j++;
                                //ele_added[i]++;
                            }
                        }
                    }

                }


                
            }

            // Calculating ERP


            for (int i = 1; i <= BusCount; i++)
            {
                ERP[i]= ERP[i-1] + ele_added[i];
              
            }

System.out.println("END");


	}


	public void Create_LU()
	{

		rownumber=new int [Cindx.length];
		tdiagonalarray =new int[119];
		ERPU = new int[119];
		ECPU = new int[120];
		Switch= new int[119];
		Link = new int[119];
		int ERPUCounter = 1;
		int Fillins=0;
		int k=0;
		tCindxU.add(0); 
		
		for(int j=1;j<ERP.length-1;j++)
		{
			MinNode=100000;
			erpold=ERP[j-1]+1;
			erpcount=ERP[j];


			for(k=erpold;k<=erpcount;k++)
			{
				if(Cindx[k]==j)
				{
					//					System.out.println(y[k]);
					//					System.out.println(counter);
					//					counter++;
					continue;
				}
				else if((Cindx[k]!=j)&& Cindx[k]>j)
				{
					//columnnumber.add(Cindx[k]);
					//Updating CindxU

					tCindxU.add(Cindx[k]);

					//Updating Min Node Value

					if (MinNode ==100000 )
						MinNode = Cindx[k];
					else if (Cindx[k] < MinNode)
						MinNode = Cindx[k];

					//Updating Switch Array
					int t_switch;
					t_switch=tCindxU.get(ERPUCounter);
					
					Switch[t_switch] = j;

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
					if(nextlink==tCindxU.get(z))
						continue;

					//					if(MinNode==00000)
					//						MinNode=CindxU[z];
					//					else if((CindxU[z]<MinNode)&&(CindxU[z]>j))
					//						MinNode=CindxU[z];

					if((tCindxU.get(z)>j)&&(Switch[tCindxU.get(z)]!=j))
					{
						tCindxU.add(ERPUCounter,tCindxU.get(z));

						Switch[tCindxU.get(ERPUCounter)]=j;

						ERPU[j]=ERPUCounter;
						ERPUCounter++;
						Fillins++;

						if(MinNode==100000)
							MinNode=tCindxU.get(z);
						else if((tCindxU.get(z)<MinNode)&&(tCindxU.get(z)>j))
							MinNode=tCindxU.get(z);
					}

				}

				tlink=nextlink;
			}

			tlink=MinNode;

			if(MinNode != 100000){
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
			
			int CindxCounter=0;
			CindxU=new int[tCindxU.size()];
			for(int i=0;i<tCindxU.size();i++)
			{
				CindxU[i]=tCindxU.get(i);
				CindxCounter++;
			}
		}
					System.out.println(Fillins);

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



}
