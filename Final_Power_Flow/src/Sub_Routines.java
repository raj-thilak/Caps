import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class Sub_Routines {

	int BusCount = 118;
	int BranchCount = 186;
	int Base=100;
	// Initialize matrix Arrays required to perform Power Flow
	int[] Bus_Number = new int[119];
	int[] BusType = new int[119];
	double[] V_Mag = new double[119];
	double[] V_Ang = new double[119];
	//	double[] P_load = new double[119];
	//	double[] Q_load = new double[119];
	double[] P_load = { 0, 51, 20, 39, 30, 0, 52, 19, 0, 0, 0, 70, 47, 34, 14, 90, 25, 11, 60, 45, 18, 14, 10, 7, 0, 0, 0, 62, 17, 24, 0, 43, 59, 23, 59, 33, 31, 0, 0, 27, 20, 37, 37, 18, 16, 53, 28, 34, 20, 87, 17, 17, 18, 23, 113, 63, 84, 12, 12, 277, 78, 0, 77, 0, 0, 0, 39, 28, 0, 0, 66, 0, 0, 0, 68, 47, 68, 61, 71, 39, 130, 0, 54, 20, 11, 24, 21, 0, 48, 0, 78, 0, 65, 12, 30, 42, 38, 15, 34, 0, 37, 22, 5, 23, 38, 31, 43, 28, 2, 8, 39, 0, 25, 0, 8, 22, 0, 20, 33 };
	double[] Q_load = { 0, 27, 9, 10, 12, 0, 22, 2, 0, 0, 0, 23, 10, 16, 1, 30, 10, 3, 34, 25, 3, 8, 5, 3, 0, 0, 0, 13, 7, 4, 0, 27, 23, 9, 26, 9, 17, 0, 0, 11, 23, 10, 23, 7, 8, 22, 10, 0, 11, 30, 4, 8, 5, 11, 32, 22, 18, 3, 3, 113, 3, 0, 14, 0, 0, 0, 18, 7, 0, 0, 20, 0, 0, 0, 27, 11, 36, 28, 26, 32, 26, 0, 27, 10, 7, 15, 10, 0, 10, 0, 42, 0, 10, 7, 16, 31, 15, 9, 8, 0, 18, 15, 3, 16, 25, 26, 16, 12, 1, 3, 30, 0, 13, 0, 3, 7, 0, 8, 15 };

	//double[] P_gen = new double[119];

	double[] P_gen = { 0, 0, 0, 0, -9, 0, 0, 0, -28, 0, 450, 0, 85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -13, 220, 314, -9, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, -46, 0, -59, 0, 0, 0, 19, 0, 0, 204, 0, 0, 0, 0, 48, 0, 0, 0, 0, 155, 0, 160, 0, 0, 0, 391, 392, 0, 0, 5.164000e+002, 0, 0, -12, -6, 0, 0, 0, 0, 0, 0, 477, 0, 0, 0, 0, 0, 0, 4, 0, 607, -85, -10, 0, 0, 0, 0, 0, 0, 0, -42, 252, 0, 0, 40, 0, 0, 0, -22, 0, 0, 0, 36, -43, -6, 0, 0, -184, 0, 0 };
	double[] Q_gen= new double[119];
	double[] Vsh = new double[119];
	double[] QgenMax = { 0,15 ,  0 ,  0 ,  300 ,  0 ,  50 ,  0 ,  300 ,  0 ,  200 ,  0 ,  120 ,  0 ,  0 ,  30 ,  0 ,  0 ,  50 ,  24 ,  0 ,  0 ,  0 ,  0 ,  300 ,  140 ,  1000 ,  300 ,  0 ,  0 ,  0 ,  300 ,  42 ,  0 ,  24 ,  0 ,  24 ,  0 ,  0 ,  0 ,  300 ,  0 ,  300 ,  0 ,  0 ,  0 ,  100 ,  0 ,  0 ,  210 ,  0 ,  0 ,  0 ,  0 ,  300 ,  23 ,  15 ,  0 ,  0 ,  180 ,  0 ,  300 ,  20 ,  0 ,  0 ,  200 ,  200 ,  0 ,  0 ,  300 ,  32 ,  0 ,  100 ,  100 ,  9 ,  0 ,  23 ,  70 ,  0 ,  0 ,  280 ,  0 ,  0 ,  0 ,  0 ,  23 ,  0 ,  1000 ,  0 ,  300 ,  300 ,  100 ,  9 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  100 ,  155 ,  0 ,  0 ,  40 ,  23 ,  23 ,  0 ,  200 ,  0 ,  0 ,  23 ,  1000 ,  1000 ,  200 ,  0 ,  0 ,  1000 ,  0 ,  0 };
	double[] QgenMin = { 0,-5 ,  0 ,  0 ,  -300 ,  0 ,  -13 ,  0 ,  -300 ,  0 ,  -147 ,  0 ,  -35 ,  0 ,  0 ,  -10 ,  0 ,  0 ,  -16 ,  -8 ,  0 ,  0 ,  0 ,  0 ,  -300 ,  -47 ,  -1000 ,  -300 ,  0 ,  0 ,  0 ,  -300 ,  -14 ,  0 ,  -8 ,  0 ,  -8 ,  0 ,  0 ,  0 ,  -300 ,  0 ,  -300 ,  0 ,  0 ,  0 ,  -100 ,  0 ,  0 ,  -85 ,  0 ,  0 ,  0 ,  0 ,  -300 ,  -8 ,  -8 ,  0 ,  0 ,  -60 ,  0 ,  -100 ,  -20 ,  0 ,  0 ,  -67 ,  -67 ,  0 ,  0 ,  -300 ,  -10 ,  0 ,  -100 ,  -100 ,  -6 ,  0 ,  -8 ,  -20 ,  0 ,  0 ,  -165 ,  0 ,  0 ,  0 ,  0 ,  -8 ,  0 ,  -100 ,  0 ,  -210 ,  -300 ,  -100 ,  -3 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  -100 ,  -50 ,  0 ,  0 ,  -15 ,  -8 ,  -8 ,  0 ,  -200 ,  0 ,  0 ,  -8 ,  -100 ,  -100 ,  -100 ,  0 ,  0 ,  -1000 ,  0 ,  0 , };

	double[] Gsh = new double[119];
	double[] Bsh = { 0, 0, 0, 0, 0, -0.4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.14, 0, 0, -0.25, 0, 0, 0, 0, 0, 0, 0.1, 0.1, 0.1, 0, 0.15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.12, 0, 0, 0, 0, 0.2, 0, 0, 0.2, 0.1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.2, 0, 0.06, 0, 0, 0.06, 0, 0, 0, 0, 0, 0, 0, 0 };
	double[] Gself = new double[119];
	double[] Bself = new double[119];
	double[] P_Calc = new double[119];
	double[] Q_Calc = new double[119];

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
	double[] Tp_ratio = { 0.000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.985000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.960000, 0.000000, 0.000000, 0.000000, 0.960000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.935000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.960000, 0.000000, 0.985000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.935000, 0.000000, 0.000000, 0.000000, 0.000000, 0.935000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.935000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000 };
	double[] Tp_Min = new double[186];
	double[] Tp_Max = new double[186];
	double[] Desired_Voltage = {0, 0.955000 ,  0.000000 ,  0.000000 ,  0.998000 ,  0.000000 ,  0.990000 ,  0.000000 ,  1.015000 ,  0.000000 ,  1.050000 ,  0.000000 ,  0.990000 ,  0.000000 ,  0.000000 ,  0.970000 ,  0.000000 ,  0.000000 ,  0.973000 ,  0.962000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.992000 ,  1.050000 ,  1.015000 ,  0.968000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.967000 ,  0.963000 ,  0.000000 ,  0.984000 ,  0.000000 ,  0.980000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.970000 ,  0.000000 ,  0.985000 ,  0.000000 ,  0.000000 ,  0.000000 ,  1.005000 ,  0.000000 ,  0.000000 ,  1.025000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.955000 ,  0.952000 ,  0.954000 ,  0.000000 ,  0.000000 ,  0.985000 ,  0.000000 ,  0.995000 ,  0.998000 ,  0.000000 ,  0.000000 ,  1.005000 ,  1.050000 ,  0.000000 ,  0.000000 ,  1.035000 ,  0.984000 ,  0.000000 ,  0.980000 ,  0.991000 ,  0.958000 ,  0.000000 ,  0.943000 ,  1.006000 ,  0.000000 ,  0.000000 ,  1.040000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.985000 ,  0.000000 ,  1.015000 ,  0.000000 ,  1.005000 ,  0.985000 ,  0.980000 ,  0.990000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.000000 ,  0.000000 ,  1.010000 ,  1.017000 ,  0.000000 ,  0.000000 ,  1.010000 ,  0.971000 ,  0.965000 ,  0.000000 ,  0.952000 ,  0.000000 ,  0.000000 ,  0.973000 ,  0.980000 ,  0.975000 ,  0.993000 ,  0.000000 ,  0.000000 ,  1.005000 ,  0.000000 ,  0.000000 };
	int[] Bus_Type={0, 2 ,  0 ,  0 ,  2 ,  0 ,  2 ,  0 ,  2 ,  0 ,  2 ,  0 ,  2 ,  0 ,  0 ,  2 ,  0 ,  0 ,  2 ,  2 ,  0 ,  0 ,  0 ,  0 ,  2 ,  2 ,  2 ,  2 ,  0 ,  0 ,  0 ,  2 ,  2 ,  0 ,  2 ,  0 ,  2 ,  0 ,  0 ,  0 ,  2 ,  0 ,  2 ,  0 ,  0 ,  0 ,  2 ,  0 ,  0 ,  2 ,  0 ,  0 ,  0 ,  0 ,  2 ,  2 ,  2 ,  0 ,  0 ,  2 ,  0 ,  2 ,  2 ,  0 ,  0 ,  2 ,  2 ,  0 ,  0 ,  3 ,  2 ,  0 ,  2 ,  2 ,  2 ,  0 ,  2 ,  2 ,  0 ,  0 ,  2 ,  0 ,  0 ,  0 ,  0 ,  2 ,  0 ,  2 ,  0 ,  2 ,  2 ,  2 ,  2 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  2 ,  2 ,  0 ,  0 ,  2 ,  2 ,  2 ,  0 ,  2 ,  0 ,  0 ,  2 ,  2 ,  2 ,  2 ,  0 ,  0 ,  2 ,  0 ,  0 , };
	float[] Q_Max_Var={0, 15 ,  0 ,  0 ,  300 ,  0 ,  50 ,  0 ,  300 ,  0 ,  200 ,  0 ,  120 ,  0 ,  0 ,  30 ,  0 ,  0 ,  50 ,  24 ,  0 ,  0 ,  0 ,  0 ,  300 ,  140 ,  1000 ,  300 ,  0 ,  0 ,  0 ,  300 ,  42 ,  0 ,  24 ,  0 ,  24 ,  0 ,  0 ,  0 ,  300 ,  0 ,  300 ,  0 ,  0 ,  0 ,  100 ,  0 ,  0 ,  210 ,  0 ,  0 ,  0 ,  0 ,  300 ,  23 ,  15 ,  0 ,  0 ,  180 ,  0 ,  300 ,  20 ,  0 ,  0 ,  200 ,  200 ,  0 ,  0 ,  300 ,  32 ,  0 ,  100 ,  100 ,  9 ,  0 ,  23 ,  70 ,  0 ,  0 ,  280 ,  0 ,  0 ,  0 ,  0 ,  23 ,  0 ,  1000 ,  0 ,  300 ,  300 ,  100 ,  9 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  100 ,  155 ,  0 ,  0 ,  40 ,  23 ,  23 ,  0 ,  200 ,  0 ,  0 ,  23 ,  1000 ,  1000 ,  200 ,  0 ,  0 ,  1000 ,  0 ,  0 };
	float[] Q_Min_Var ={0, -5 ,  0 ,  0 ,  -300 ,  0 ,  -13 ,  0 ,  -300 ,  0 ,  -147 ,  0 ,  -35 ,  0 ,  0 ,  -10 ,  0 ,  0 ,  -16 ,  -8 ,  0 ,  0 ,  0 ,  0 ,  -300 ,  -47 ,  -1000 ,  -300 ,  0 ,  0 ,  0 ,  -300 ,  -14 ,  0 ,  -8 ,  0 ,  -8 ,  0 ,  0 ,  0 ,  -300 ,  0 ,  -300 ,  0 ,  0 ,  0 ,  -100 ,  0 ,  0 ,  -85 ,  0 ,  0 ,  0 ,  0 ,  -300 ,  -8 ,  -8 ,  0 ,  0 ,  -60 ,  0 ,  -100 ,  -20 ,  0 ,  0 ,  -67 ,  -67 ,  0 ,  0 ,  -300 ,  -10 ,  0 ,  -100 ,  -100 ,  -6 ,  0 ,  -8 ,  -20 ,  0 ,  0 ,  -165 ,  0 ,  0 ,  0 ,  0 ,  -8 ,  0 ,  -100 ,  0 ,  -210 ,  -300 ,  -100 ,  -3 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  -100 ,  -50 ,  0 ,  0 ,  -15 ,  -8 ,  -8 ,  0 ,  -200 ,  0 ,  0 ,  -8 ,  -100 ,  -100 ,  -100 ,  0 ,  0 ,  -1000 ,  0 ,  0 };

	int b_l = Bsh.length;
	double tolerence= 10E-6;
	//int[] N_Row_ele = new int[119];

	int[] ERP = new int[119];
	double[] Z = new double[187];
	double[] GB = new double[187];// ****Change 
	double[] BB = new double[187];
	double[] SB = new double[187];

	int[] Cindx = new int[477];//no of branches considered for size 
	int ERP_Counter = 0;
	int Y_Counter = 0;
	int[] ele_added = new int[119];
	double[] Y_Mat_G = new double[477];// size justification = 2*(no of branches) + 118-> (diagonal elements)

	double[] Y_Mat_B = new double[477];// ****Change Y_Mat_G, B
	double[] G_ik = new double[187];
	double[] B_ik = new double[187];
	double[] G_ki = new double[187];
	double[] B_ki = new double[187];


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

	int[] nxtcolptr;// Next Column Pointer array used in ordering 
	int[] nxtrowptr;// Next row pointer array used in ordering

	int ERPUCounter = 1;
	int Rindx=0;
	int nextlink;


	//Tinney Ordering

	//	double[] BB_Tinney;
	//	double[] XX_Tinney;
	//	double[] ZZ_Tinney;
	int[] LRO;
	int[] Matrix_size;

	//Variables for Bus Switching

	int [] Bus_Type_Switch;
	//	float[] Q_max_Bus;
	//	float[] Q_min_Bus;
	int [] Q_max_Bus = new int[BusCount + 1];
	int [] Q_min_Bus = new int[BusCount + 1];
	int bus_switch;

	// Variables for Mismatch Calculation

	double Max_P_Mismatch,Max_Q_Mismatch;

	double[] P_Mismatch;
	double[] Q_Mismatch;

	//Variables of Jacobian

	double[] H;
	double[] J;
	double[] N;
	double[] L;

	//Factorize Jacobian Variables
	int[] Link_LU;
	int[] ICPL;
	double[] ExAccum_H;
	double[] ExAccum_J;
	double[] ExAccum_N;
	double[] ExAccum_L;
	double[] URO_H;
	double[] URO_J;
	double[] URO_N;
	double[] URO_L ;
	double[] LCO_H;
	double[] LCO_J;
	double[] LCO_N;
	double[] LCO_L;
	double[] Diag_H;
	double[] Diag_J;
	double[] Diag_N;
	double[] Diag_L;

	// Forward Backward Variables

	double[] delta_V_Ang;
	double[] delta_V_Mag ;
	int solve_variable = 0;
	// Line flow 
	double[] P_ik;
	double[] Q_ik;
	double[] P_ki;
	double[] Q_ki;

	int tester;
	
	public void Data_Extract()
	{
		FileRead test = new FileRead();
		
		tester = test.test;
		
		System.out.println(tester);
	}

	/*
	 ********************************************************************************
	 * Function Name : Create_Y_Bus()
	 * Function Type : Sub- Routine

	 * Summary:
	 * This sub routine is used to create the Y-Bus Matrix using the data extracted
	 * from the previous step.
	 * ******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :
	 *  
	 *  Gsh			: Shunt Conductance
	 *  BranchCount	: No. of Branches 
	 *  Bsh			: Shunt Suseptance
	 *  BusCount	: No. of Buses 
	 *  From_Bus	: Array that stores the numbers of 'From' Bus
	 *  R			: Array that contains the values of resistances
	 *  To_Bus		: Array that contains the numbers of 'To' Bus 
	 *  X			: Array that stores the values of reactances
	 * ******************************************************************************
	 * Output Elements :

	 * Cindx[]		: An array that stores the values of Column Indices of the Elements
	 * ERP[]		: An array that stores the values of End of Row Pointers
	 * Y_Mat_B[]	: An array that stores the values of Suseptance
	 * Y_Mat_G[]	: An array that stores the values of Conductance
	 * ******************************************************************************
	 * Internal Elements:
	 * BB[]			: Array that stores Branch Suseptances
	 * Bself[]		: Array that stores Self Suseptance
	 * GB[]			: Array that stores Branch Conductance
	 * Gself[]		: Array that stores Self conductance
	 * SB[]			: Array that stores Line Charging Capacitance
	 * Tap_Multiplier: A scalar multiplier used as a tap ratio for transformer
	 * Y_Counter	: A scalar counter element that is used to G and B matrices
	 * Z			: An array that stores the values of impedances
	 */

	public void Create_Y_Bus()

	{

		// Write this loop into Data Extract as Initialization
		for (int i = 1; i <= 118; i++)
		{
			P_load[i] = P_load[i] / Base;
			P_gen[i] = P_gen[i] / Base;
			Q_gen[i] = Q_gen[i] / Base;
			Q_load[i] = Q_load[i] / Base;
			QgenMax[i] = QgenMax[i] / Base;
			QgenMin[i] = QgenMin[i] / Base;
		}


		// Calculate Self Suseptance and Conductances
		for (int i = 1; i < 119; i++)
		{
			Gself[i] = Gsh[i] ;
			Bself[i] = Bsh[i] ;
		}

		double Tap_Multiplier = 1;

		// Create tap ratio for the modified transformer model
		// Preapre Data for Y Bus Formulation
		for (int i = 1; i <= BranchCount; i++)
		{
			if (BranchType[i] == 0)
				Tap_Multiplier = 1 ;
			else
				Tap_Multiplier = 1 / Tp_ratio[i];

			// Compute Branch Impedance
			Z[i] =( R[i]*R[i]) + (X[i] * X[i]);
			// Compute Branch Conductance
			GB[i] = (R[i] / Z[i]) * Tap_Multiplier;

			//Compute Branch Suseptance
			BB[i] = -(X[i] / Z[i]) * Tap_Multiplier;

			// Compute Branch Line Charging Capacitance
			SB[i] = 0.5* Bshx2[i];


			// Calculate the diagonal elements of the G and the B matrix

			Gself[From_Bus[i]] = Gself[From_Bus[i]] + GB[i]*Tap_Multiplier;
			Bself[From_Bus[i]] = Bself[From_Bus[i]] + BB[i]*Tap_Multiplier + SB[i];

			Gself[To_Bus[i]] = Gself[To_Bus[i]] + GB[i] / Tap_Multiplier;
			Bself[To_Bus[i]] = Bself[To_Bus[i]] + BB[i] / Tap_Multiplier + SB[i];
		}


		// For Loop for creating Ybus Structure

		// Outer 'for' loop accounts for all the buses
		for (int i = 1; i <= BusCount; i++)
		{
			Y_Counter++;
			Y_Mat_G[Y_Counter] = Gself[i];
			Y_Mat_B[Y_Counter] = Bself[i];
			Cindx[Y_Counter] = i;
			ele_added[i]++;

			//Inner 'for' loop accounts for branches in From Bus  
			for (int j = 1; j <= BranchCount; j++)
			{

				if (From_Bus[j] == i)
				{
					//Update Diagonal Elements
					Y_Counter++;
					Y_Mat_G[Y_Counter] = -GB[j];
					Y_Mat_B[Y_Counter] = -BB[j];
					Cindx[Y_Counter] = To_Bus[j];
					ele_added[i]++;

					// Check condition for double circuit
					if (j < BranchCount)
					{
						if (To_Bus[j] == To_Bus[j + 1] && From_Bus[j + 1] == i)
						{
							Y_Mat_G[Y_Counter] = -GB[j] - GB[j + 1];
							Y_Mat_B[Y_Counter] = -BB[j] - BB[j + 1];
							Cindx[Y_Counter] = To_Bus[j];
							j++;
						}
					}
				}

				// Update Diagonal element from  
				if (To_Bus[j] == i)
				{
					Y_Counter++;
					Y_Mat_G[Y_Counter] = -GB[j];
					Y_Mat_B[Y_Counter] = -BB[j];
					Cindx[Y_Counter] = From_Bus[j];
					ele_added[i]++;

					// Check condition for double circuit
					if (j < BranchCount)
					{
						if (From_Bus[j] == From_Bus[j + 1] && To_Bus[j + 1] == i)
						{
							Y_Mat_G[Y_Counter] = -GB[j] - GB[j + 1];
							Y_Mat_B[Y_Counter] = -BB[j] - BB[j + 1];
							Cindx[Y_Counter] = From_Bus[j];
							j++;
						}
					}
				}

			}
		}


		// for loop to calculate ERP
		for (int i = 1; i <= BusCount; i++)
		{
			ERP[i]= ERP[i-1] + ele_added[i];
		}

		//System.out.println("End of Routine - Create_Y_Bus");


	}

	/*
	 **********************************************************************************
	 * Function Name : Create_LU()
	 * Function Type : Sub- Routine

	 * Summary:
	 * The method Create_LU obtains the data processed by Tinney_Ordering and works 
	 * on it to develop a robust and an efficient data structure to perform 
	 * Numerical Factorization  of the Jacobian Matrix. The data output hence obtained 
	 * should be ordered in order to get RRCU type of data set. 
	 * 
	 * The symbolic portion of the Lower (L), Upper (U) and Diagonal(D) 
	 * triangular matrices are formed.
	 * ********************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ********************************************************************************
	 * Input Elements :

	 * Cindx[]	:	An Array that contains Column Indices of the Elements
	 * ERP[]	:	An Array that contains end of row pointers
	 * y[]		:	An Array that contains Matrix elements
	 * *********************************************************************************
	 * Output Elements :

	 * CindxU	: 	An array which stores column indices of U Factor
	 * ERPU		: 	An array that stores end of row pointers of U
	 * Fillins	: 	Variable that stores the fill-in counts
	 * **********************************************************************************
	 * Internal Variables

	 * rownumber	:	An Array that Stores the row number of the element
	 * tdiagonal	:	Temporary diagonal value store variable
	 * Link			:	Self – referential linked list used in creating LU
	 * Switch		:	Switch Array used in creation of LU structure
	 * tCindU		:	Temporary list which dynamically creates CindxU
	 * MinNode		:	Variable for storing Min Node value
	 * k,i,z		:	For Loop Variables
	 * tlink		:	temporary link varible
	 * ERPUCounter	:	Counter to keep track of EPRU
	 * nextlink		:	Link Variable used in Linked list handling
	 * CindxU_Counter:	Counter to add tCindxU list back into CindxU
	 * ***********************************************************************************
	 * Step-by-Step Procedure[General]
	 * Step 0:
	 * Zero linked list (LINK(NoBus)) (which will be used to store all rows which contribute 
	 * fill to each row, i.) We will use the Fill-In Corollary: e.g., if row 15 is used when 
	 * accounting for fill to row 21 then it need not be used when accounting for fill to 
	 * any other row.)Zero switch array (Switch(NoBus)). Set i=1.
	 * Step 1:
	 * For row i, allocate space in data structure of URU for original non-zero’s and copy 
	 * column indices into this space. (Use switch array to track which column indices are 
	 * added to row i of URU.) Updating row pointer (ERPU) and column indices (CIndx) as 
	 * each col. index is added. Keep track of lowest numbered off-diagonal non-zero entry to 
	 * the right of the diagonal (MinNod).
	 * Step 2:
	 * Enter “fill linked list” (LINK) in ith position. For each row j, linked to row i, find 
	 * all non-zeros to the right of the ith column, merge those column indices with native 
	 * indices (using switch array (Switch)). Update column indices (CIndx) and row pointers 
	 * (ERPU). Update lowest numbered off-diagonal non-zero entry to the right of the 
	 * diagonal (MinNod).
	 * Step 3:
	 * Add to the “fill linked list” (LINK) associating row i with the row number to which it 
	 * contributes fill (MinNod). That row number is found using Fill-In Theorem.
	 * Step 4:
	 * If this i<NOBS-1,i:=i+1 go to Step 1. Else End
	 * ***************************************************************************************
	 */

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

		// The ERPUCounter variable acts as a counter for updating EPRU
		for(int j=1;j<ERP.length;j++)
		{
			/*Step 0:* Initialize Minode* Zero the Switch Array * Zero the link Array */

			MinNode=100000;//Initializing Min Node at the start of each node

			//Pl Note: The MinNode is set to a very high value to make the comparison
			//and updating of the link-list more efficient

			erpold=ERP[j-1]+1;
			//erpold is a local varible used for initializing the for loop

			erpcount=ERP[j];
			// Determine the local varible to set the limit for for-loop

			/* Step 1:* Copy the original matrix using Switch,ERP * Update MinNode, ERPU*/

			for(k=erpold;k<=erpcount;k++)
			{

				/* * Perform a check if the element is Diagonal 
				 *  If the element is diagonal, perform no operation*/
				if(Cindx[k]==j)
				{
					//					System.out.println(y[k]);
					//					System.out.println(counter);
					//					counter++;
					continue;
				}
				else if((Cindx[k]!=j)&& Cindx[k]>j)
				{
					//Updating tCindxUby adding the cindx of the element
					tCindxU.add(Cindx[k]);

					//Updating Min Node Value
					/*The If-else loops checks if the element is a MinNode 
					 * and updates corresponding to the cindx value of element*/
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
			/* Step 2:Using Link to adjust fill, 109.Update Minnode , 
			 * ERPU <Everthing with help of steering for-loop 
			 * Step 3: 111. * Add corresponding row to the linked list*/  
			//Initialize nextlink , which is the link associated with tlink

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

					//Check if the corresponding index of tCindxU is
					//greater than row being processed, if Yes. Do not process
					// similarly check if the switch contains the row number.

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
			//Finally,
			//Add the row processed to the linked list and zero the linked list array
			//Identify the next position by searching for the Link with indices Zero
			//Simultaneously, Update the Minnode value with the nextlink value

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

			// Processing the data stored in Intger Arraylist, tCindxU
			//to int[] array CindxU to enable easy access for the future
			int CindxCounter=0;
			CindxU=new int[tCindxU.size()];

			// For-loop to copy all elements of tCindxU to CindxU
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
		//				System.out.println("\n Link");
		//	        	for(int q=0;q<Link.length;q++)
		//	        		System.out.print("\t"+Link[q]);

		//System.out.println("End of Routine - Create_LU");
	}

	/*
	 ********************************************************************************
	 * Function Name : rowtocolumn()
	 * Function Type : Sub- Routine

	 * Summary:
 		The CIndxU obtained by the LU Symbolic factorization is unordered in nature. 
 		In LU Numerical factorization it would be much more convenient if the data-
 		structure CIndxU is ordered. This subroutine is the first part of this 
 		ordering process and it would convert ERPU to ECPU.

	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :

	 * CindxU	: 	An array which stores column indices of U Factor
	 * ERPU		: 	An array that stores end of row pointers of U
	 * *******************************************************************************
	 * Output Elements :

	 * CindxU_Ordered	: 	An array which stores column indices of U Factor in order
	 * ECPU 			:	An array that stores end of column pointers of U

	 * ********************************************************************************
	 * Internal Variables

	 * tstoreelement	:	Temporary store variable
	 * tposition 		:	Temporary position identifier variable
	 * nxtcolptr 		:	Next column Pointer array used in column-row conversion
	 * RindxU 			:	An Array that stores row indices of U
	 * i,j 				:	For loop elements
	 * *********************************************************************************
	 * Step-by-Step Procedure[General]

	 * Row to Cols. Storage Conversion Routine
	 * Step 1:  
	 * Copy existing row pointers as new column pointers.
	 * Count number of elements in each column.  
	 * Set up pointers to the beginning of each column by forming evolving sum.  
	 * Add 1 to each element and perform a right shift on the results
	 * Step 2:  
	 * Scan row I.  Store each element of matrix and appropriate row indices in first 
	 * available and appropriate column position.
	 * Step 3:  
	 * Increment ‘NxColPt’ for each column in which entries were made.
	 * Step 4:  
	 * If I<N, continue, else repeat for next row (i.e.,  I:=I+1,and go to step 2.)
	 * Step 5:  
	 * Perform left shift on ‘NxColPt’ to get ECP 
	 * *********************************************************************************
	 */

	public void rowtocolumn()
	{
		// initializing the local variable tposition to zero 
		// tposition helps in counting number of elements in each column
		int tposition = 0;

		// Create a temporary nextcol pointer array that stores the no. of elements
		nxtcolptr = new int[ERP.length];

		//Initialize nxtcolptr and RindxU to respective sizes
		int[] t_nxcolpt = new int[ERP.length];
		RindxU= new int[CindxU.length];

		/*
		 * step 0 
		 * Accumulate the number of elements in each column 
		 * Using a For-Loop , count the elements using tposition 
		 * and store the number to t_nxcoltpt and increment the counter
		 */
		for (int i = 1; (i < CindxU.length-1); i++)
		{
			if (CindxU[i] == 0)
				break;
			tposition = CindxU[i];
			t_nxcolpt[tposition]++;

		}

		/*
		Step 1 : Perform Running Sum to Obtain Nxtcolptr 
		Pl Note, the first element is always 1, 
		 */
		nxtcolptr[1] = 1;

		/*
		 * Step 2,3 & 4 
		 * For Each row, enter row index i, in appropriate pos 
		 * Update the nxtcolptr
		 */
		for (int i = 2; i < ERP.length; i++)
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

		/*
		 * Step 5 
		 * Subtract 1 from nxtcolptr to obtain ECPU 
		 * This is performed using a simple for loop
		 */

		for (int i = 1; i <nxtcolptr.length; i++)
			ECPU[i] = nxtcolptr[i] - 1;

		//		System.out.println("\n RindxU");
		//		for(int q=0;q<RindxU.length;q++)
		//			System.out.print("\t"+RindxU[q]);
		//
		//		System.out.println("\n ECPU");
		//		for(int q=0;q<ECPU.length;q++)
		//			System.out.print("\t"+ECPU[q]);

		//System.out.println("End of Routine - rowtocolumn ");
	}

	/*
	 ********************************************************************************
	 * Function Name : columntorow()
	 * Function Type : Sub- Routine

	 * Summary:
 		The data structure stored by columns after the rowtocolumn() conversion is 
 		again converted to row representation and the data structure obtained after 
 		this is an ordered data structure which would be convenient for LU Numerical 
 		factorization. The ouput of this routine is CindxU_Ordered and ERPU arrays 
 		used for Numerical Factorization of the Y matrix.

	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :

	 * CindxU_Ordered	: 	An array which stores column indices of U Factor in order
	 * ECPU 			:	An array that stores end of column pointers of U

	 * *******************************************************************************
	 * Output Elements :

	 * CindxU_Ordered	: 	An array which stores column indices of U Factor in order
	 * ERPU 			:	An array that stores end of row pointers of U

	 * ********************************************************************************
	 * Internal Variables

	 * tstoreelement	: 	Temporary store variable
	 * tposition 		:	Temporary position identifier variable
	 * nxtrowptr 		:	Next column Pointer array used in column-row conversion
	 * i,j 				:	For loop elements
	 * *********************************************************************************
	 * Step-by-Step Procedure[General]

	 * Col to Row. Storage Conversion Routine
	 * Step 1:  
	 * Copy existing col pointers as new row pointers.
	 * Count number of elements in each row.  
	 * Set up pointers to the beginning of each row by forming evolving sum.  
	 * Add 1 to each element and perform a right shift on the results
	 * Step 2:  
	 * Scan row I.  Store each element of matrix and appropriate row indices in first 
	 * available and appropriate row position.
	 * Step 3:  
	 * Increment ‘NxRowPt’ for each column in which entries were made.
	 * Step 4:  
	 * If I<N, continue, else repeat for next row (i.e.,  I:=I+1,and go to step 2.)
	 * Step 5:  
	 * Perform left shift on ‘NxRowPt’ to get ERPU 
	 * *********************************************************************************
	 */

	public void columntorow()
	{

		//* The sub-routine is similar to the rowtocolumn()conversion

		// initializing the local variable tposition to zero 
		// tposition helps in counting number of elements in each row

		int tposition = 0;

		// Create a temporary nextrow pointer array that stores the no. of elements
		nxtrowptr = new int[ERP.length];
		int[] t_nxtrowptr = new int[ERP.length];

		CindxU_Ordered = new int[CindxU.length];

		/*
		step 0  
		 * Accumulate the number of elements in each row 
		 * Using a For-Loop , count the elements using tposition
		 * and store the number to t_nxtrowtpt and increment the counter
		 */
		for (int i = 1; i < RindxU.length - 1; i++)
		{
			if (RindxU[i] == 0)
				break;
			tposition = RindxU[i];
			t_nxtrowptr[tposition]++;
		}

		/*
		 * Step 1 : 
		 * Perform Running Sum to Obtain Nxtrowptr 
		 * Pl Note, the first element is always 1,
		 * Now perform running sum and right shift elements by 1 position
		 */
		//Initializing first position with 1
		nxtrowptr[1] = 1;
		for (int i = 2; i < ERP.length; i++)
			nxtrowptr[i] = nxtrowptr[i - 1] + t_nxtrowptr[i - 1];

		/*
		 * Step 2,3 & 4 
		 * For Each row, enter row index i, in appropriate pos 
		 * Update the nxtrowptr
		 */

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

		/*
		 * Step 5 
		 * Subtract 1 from nxtrowptr to obtain ECPU 
		 * This is performed using a simple for loop
		 */

		for (int i = 1; i < nxtrowptr.length-1; i++)
			ERPU[i] = nxtrowptr[i]-1;


		//Print ERPU and CindxU_ordered as markers to check the accuracy of the code
		//		System.out.println("\n CindxU Ordered");
		//		for(int q=0;q<CindxU_Ordered.length;q++)
		//			System.out.print("\t"+CindxU_Ordered[q]);
		//
		//		System.out.println("\n ERPU");
		//		for(int q=0;q<ERPU.length;q++)
		//			System.out.print("\t"+ERPU[q]);

		//System.out.println("End of Routine - columntorow ");

	}

	/*
	 ********************************************************************************
	 * Function Name : initialize_voltage()
	 * Function Type : Sub- Routine

	 * Summary:
 		This function  initializes the voltages to 1 P.U and angles to 0 depending 
 		on type of the bus.  
	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :

	 * Bus_Type			:	An array that stores the type of Bus 
	 * Desired_Voltage	:	An array that stores the specified volatges on the Buses
	 * *******************************************************************************
	 * Output Elements :

	 * V_Mag		:	An array that stores the voltage magnitude of the buses	
	 * V_Ang 		:	An array that stores the angles of the bus
	 * ********************************************************************************
	 * Internal Variables
	 * 
	 * i	: For loop iterator 
	 * *********************************************************************************
	 */

	public void initialize_voltage()
	{
		for(int i =1; i<=BusCount; i++)
		{
			if (Bus_Type[i]==2) // Check if Generator Bus
				V_Mag[i]=Desired_Voltage[i];
			else if (Bus_Type[i]==3)// Check if Slack Bus 
				V_Mag[i]=Desired_Voltage[i];
			else
				V_Mag[i]=1;

			V_Ang[i]=0;// Set angles to zero

		}

		//System.out.println("End of Routine - initialize_voltage ");
	}

	/*
	 ********************************************************************************
	 * Function Name : injections()
	 * Function Type : Sub- Routine

	 * Summary:
		The objective of this routine is to calculate the injected power in each bus.
		The data from the YBus matrix is used to calculate the injected power  
	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :

	 * Bus_Type			:	An array that stores the type of Bus 
	 * Desired_Voltage	:	An array that stores the specified volatges on the Buses
	 * *******************************************************************************
	 * Output Elements :

	 * P_Calc	:	Real Power injected in each bus
	 * Q_Calc	:	Reactive Power injected in each bus 

	 * ********************************************************************************
	 * Internal Variables

	 * i				: For loop iterator 
	 * K				: Temporary variable used to store Cindx
	 * G_Multiplier		: Multiplier that stores math operation on G for P 	
	 * B_Multiplier		: Multiplier that stores math operation on B for P
	 * G_Q_Multiplier	: Multiplier that stores math operation on G for Q 
	 * B_Q_Multiplier	: Multiplier that stores math operation on B for Q

	 * *********************************************************************************
	 * Step-by-Step Procedure

	 	Initialize the real power and the reactive power injections to zero
	 	Using ERP , Cindx Calculate the injections at each bus
	 *********************************************************************************
	 */

	public void injections()
	{
		double G_Multiplier,B_Multiplier,G_Q_Multiplier,B_Q_Multiplier;

		for (int i=1; i<=BusCount;i++)
		{
			P_Calc[i]=0;
			Q_Calc[i]=0;// Initializing P_Calc and Q_calc to zero	

			erpold=ERP[i-1]+1;
			erpcount=ERP[i];
			int k=0;

			for(int j=erpold;j<=erpcount;j++)
			{
				k=Cindx[j];

				G_Multiplier=Y_Mat_G[j]*Math.cos(V_Ang[i]-V_Ang[k]);
				B_Multiplier=Y_Mat_B[j]*Math.sin(V_Ang[i]-V_Ang[k]);		
				G_Q_Multiplier=Y_Mat_G[j]*Math.sin(V_Ang[i]-V_Ang[k]);
				B_Q_Multiplier=Y_Mat_B[j]*Math.cos(V_Ang[i]-V_Ang[k]);

				P_Calc[i]= P_Calc[i]+(G_Multiplier + B_Multiplier)*V_Mag[k];
				Q_Calc[i]= Q_Calc[i]+(G_Q_Multiplier - B_Q_Multiplier)*V_Mag[k];
			}

			P_Calc[i]=P_Calc[i]*V_Mag[i]; // Caclulate and update P_Calc and Q_Calc using
			Q_Calc[i]=Q_Calc[i]*V_Mag[i];// Formulas given in the Lecture slides
		}

		//System.out.println("End of Routine - injections ");
	}

	/*
	 ********************************************************************************
	 * Function Name : power_mismatch()
	 * Function Type : Sub- Routine

	 * Summary:
  		The objective of the function is to calculate Power Mismatches at each bus 
	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :

	 * Bus_Type	:	The array specifying the type of the Bus
	 * P_Calc	:	Real Power injected in each bus
	 * P_Gen	:	The array containing generated power at each Generator Bus
	 * P_Load	:	The array containing real power supplied to the Load
	 * Q_Calc	:	Reactive Power injected in each bus
	 * Q_Gen 	:	The array containing generated reactive power at each Generator Bus
	 * Q_Load	:	The array containing reactive power supplied to the Load
	 * *******************************************************************************
	 * Output Elements :

	 * Max_P_Mismatch	:	Array that contains values of  mismatches in real power
	 * Max_Q_Mismatch 	:	Array that contains values of  mismatches in reactive power
	 * P_Mismatch		:	maximum value of the mismatch in real power
	 * Q_Mismatch		:	maximum value of the mismatch in reactive power

	 * ********************************************************************************
	 * Internal Variables

	 * i				: For loop iterator 
	 * K				: Temporary variable used to store Cindx
	 * G_Multiplier		: Multiplier that stores math operation on G for P 	
	 * B_Multiplier		: Multiplier that stores math operation on B for P
	 * G_Q_Multiplier	: Multiplier that stores math operation on G for Q 
	 * B_Q_Multiplier	: Multiplier that stores math operation on B for Q

	 * *********************************************************************************
	 */
	public void power_mismatch()
	{
		Max_P_Mismatch = 0;
		Max_Q_Mismatch = 0;

		// Initialize sizes of P and Q Mismatch Arrays
		P_Mismatch = new double[BusCount + 1];
		Q_Mismatch = new double[BusCount + 1];

		// 'for' loop for calculation of the mismatches
		for (int i = 1; i <= BusCount;i++)
		{
			P_Mismatch[i] = P_gen[i] - P_load[i] - P_Calc[i];
			Q_Mismatch[i] = Q_gen[i] - Q_load[i] - Q_Calc[i];


			//Update the mismatches if the bus type is notPQ/Load bus.

			// Check for generator bus
			if (Bus_Type[i] == 2)
			{
				Q_Mismatch[i] = 0;

			}

			// Check for Slack bus 
			else if (Bus_Type[i] == 3)
			{
				P_Mismatch[i] = 0;
				Q_Mismatch[i] = 0;

			}

			//Calculate Maximum Real and reactive power mismatches.
			else		
			{
				if (Math.abs(P_Mismatch[i]) > Max_P_Mismatch)
					Max_P_Mismatch = Math.abs(P_Mismatch[i]);
				if (Math.abs(Q_Mismatch[i]) > Max_Q_Mismatch)
					Max_Q_Mismatch = Math.abs(Q_Mismatch[i]);

			}
		}

		//System.out.println("End of Routine - power_mismatch ");
	}

	/*
	 ********************************************************************************
	 * Function Name : Tinney_Ordering()
	 * Function Type : Sub- Routine

	 * Summary:
	   The main objective of this program is to reorder the obtained Y Bus Matrix from 
	   the previous function
	   Tinney Ordering for the YBus Matrix obtained from Input Routine
	   The ordering scheme used is Tinney 1
	 * ******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :
	 *  
	 * Bus_Type[i] 		: Array that stores Bus types
	 * Desired_Voltage[i]: Array that stores Specified voltages
	 * P_gen[i] 		: Array that stores Generator real power
	 * P_load[i] 		: Array that stores real power supplied at load bus
	 * Q_gen[i] 		: Array that stores reactive power /var on generator
	 * Q_load[i] 		: Array that stores reactive power at laod bus 
	 * QgenMax[i] 		: Maximum Var Limit on generator array
	 * QgenMin[i] 		: Minimum Var limit on the generator array
	 * V_Ang[i] 		: Voltage angles
	 * V_Mag[i] 		: Voltage Magnitudes
	 * ******************************************************************************
	 * Output Elements :
	 
	 * Bus_Type[i] 		: Array that stores Bus types
	 * Desired_Voltage[i]: Array that stores Specified voltages
	 * P_gen[i] 		: Array that stores Generator real power
	 * P_load[i] 		: Array that stores real power supplied at load bus
	 * Q_gen[i] 		: Array that stores reactive power /var on generator
	 * Q_load[i] 		: Array that stores reactive power at laod bus 
	 * QgenMax[i] 		: Maximum Var Limit on generator array
	 * QgenMin[i] 		: Minimum Var limit on the generator array
	 * V_Ang[i] 		: Voltage angles
	 * V_Mag[i] 		: Voltage Magnitudes

	 * ******************************************************************************
	 * Internal Elements:
	 
	 * tinney_Bus_Type[i] 		: Array that stores Bus types
	 * tinney_Desired_Voltage[i]: Array that stores Specified voltages
	 * tinney_P_gen[i] 			: Array that stores Generator real power
	 * tinney_P_load[i] 		: Array that stores real power supplied at load bus
	 * tinney_Q_gen[i] 			: Array that stores reactive power /var on generator
	 * tinney_Q_load[i] 		: Array that stores reactive power at laod bus 
	 * tinney_QgenMax[i] 		: Maximum Var Limit on generator array
	 * tinney_QgenMin[i] 		: Minimum Var limit on the generator array
	 * tinney_V_Ang[i] 			: Voltage angles
	 * tinney_V_Mag[i] 			: Voltage Magnitudes
	 * 
	 * ********************************************************************************
	 * Procedure 
	  
	 * Step 1
	 * Compute the valency of each element using for loop 
	 * Step 2 
	 * Reorder Values in ascending order and sort them according to valencies
	 * Step 3
	 * Now , copy the values of buses into temporary arrays to perform ordering 
	 * Step 4 
	 * Finally , copy all the temporaray arrays back into original array
	 * 
	 * This Tinney ordering significantly reduces the number of fillins
	 */
	public void Tinney_Ordering()
	{
		int temp,temp1,temp2;

		int r;


		double[] tinney_G = new double[492];
		double[] tinney_B = new double[492];
		int[] tinney_BusType = new int[119];
		double[] tinney_Vmag = new double[119];
		double[] tinney_Vang = new double[119];
		double[] tinney_P_load = new double[119];
		double[] tinney_Q_load = new double[119];
		double[] tinney_Pgen = new double[119];
		double[] tinney_Qgen = new double[119];
		double[] tinney_Desired_Voltage = new double[119];
		double[] tinney_Qgen_Max = new double[119];
		double[] tinney_Qgen_Min = new double[119];


		//Initialize the temporarary tinney arrays
		int[] tinney_Cindx = new int[477];
		int[] valency= new int[ERP.length];
		Matrix_size= new int[ERP.length];

		/*
		 * Step 1
		 * Compute the valency of each element using for loop 
		 */
		for(int i=1; i<=BusCount;i++)
		{
			temp=0;

			for(int j=ERP[i-1]+1;j<=ERP[i];j++)
			{
				if(i!=Cindx[j])
					temp=temp+1;
			}
			valency[i]=temp;
		}

		//Creating Incremental Bus Number Array
		for(int i=1;i<=BusCount;i++)
			Matrix_size[i]=i;
		
		 
		 /*
		  * Step 2 
		  * Reorder Values in ascending order 
		  * Arrange them according to valencies
		 */
		for(int i=1;i<=BusCount;i++)
		{
			for(int j=1;j<BusCount;j++)
			{
				if(valency[j+1]<valency[j])
				{
					temp1=valency[j+1];
					temp2=Matrix_size[j+1];

					valency[j+1]=valency[j];
					Matrix_size[j+1]=Matrix_size[j];

					valency[j]= temp1;
					Matrix_size[j]= temp2;
				}
			}
		}



		int tinney_erp[] = new int[ERP.length];


		tinney_erp[1]=1;

		int k=1,t=0;
		int v1,p;

		/* Using a nested for loop ,
		 * Perform Tinney Ordering 
		 * If the column index is that of a diagonal  
		 * Element the corresponding non zero element
		 * from the input matrix to the new position and set 
		 * that element as the Diagonal  element of that row. 
		 */
		
		for(int i=1;i<=BusCount;i++)
		{
			v1=Matrix_size[i];
			//	int j=erp[i-1]+1;j<=erp[i];j++
			for(int j=ERP[v1-1]+1;j<=ERP[v1];j++)
			{
				p=Cindx[j];
				if(p==v1)
				{
					tinney_G[k] = Y_Mat_G[j];
					tinney_B[k] = Y_Mat_B[j];
					tinney_Cindx[k]=i;

					k=k+1;
					t=t+1;
				}
				else
				{
					for(int m=1;m<=BusCount;m++)
					{
						if(Matrix_size[m]==p)
						{
							tinney_G[k] = Y_Mat_G[j];
							tinney_B[k] = Y_Mat_B[j];
							r = m;
							tinney_Cindx[k] = r;
							k=k+1;
							t=t+1;
							break;
						}
					}
				}
			}

			tinney_erp[i]=t;
		}

		//Copy all the temporary variables using a for loop
		for (int i = 1; i <= BusCount; i++)
		{
			tinney_BusType[i] = Bus_Type[Matrix_size[i]];
			tinney_Vmag[i] = V_Mag[Matrix_size[i]];
			tinney_Vang[i] = V_Ang[Matrix_size[i]];
			tinney_P_load[i] = P_load[Matrix_size[i]];
			tinney_Q_load[i] = Q_load[Matrix_size[i]];
			tinney_Pgen[i] = P_gen[Matrix_size[i]];
			tinney_Qgen[i] = Q_gen[Matrix_size[i]];
			tinney_Desired_Voltage[i] = Desired_Voltage[Matrix_size[i]];
			tinney_Qgen_Max[i] = QgenMax[Matrix_size[i]];
			tinney_Qgen_Min[i] = QgenMin[Matrix_size[i]];
		}


		for(int i=1;i<ERP.length;i++)
			ERP[i]=tinney_erp[i];

		for(int j=1;j<Cindx.length;j++)
			Cindx[j]=tinney_Cindx[j];

		for(int l=1;l<Y_Mat_G.length;l++)
		{
			Y_Mat_G[l]=tinney_G[l];
			Y_Mat_B[l] =tinney_B[l];
		}

		// Copy back elements into original arrays afer Tinney ordering 
		for (int i = 1; i <= BusCount; i++)
		{
			Bus_Type[i] = tinney_BusType[i];
			V_Mag[i] = tinney_Vmag[i];
			V_Ang[i] = tinney_Vang[i];
			P_load[i] = tinney_P_load[i];
			Q_load[i] = tinney_Q_load[i];
			P_gen[i] = tinney_Pgen[i];
			Q_gen[i] = tinney_Qgen[i];
			Desired_Voltage[i] = tinney_Desired_Voltage[i];
			QgenMax[i] = tinney_Qgen_Max[i];
			QgenMin[i] = tinney_Qgen_Min[i];
		}

		//System.out.println("End of Routine- TinneyOrdering ()");
	}

	int bus_switch()
	{
		int i = 0;
		int k = 0;
		int z = 0;// Change variables name

		Bus_Type_Switch = new int[BusCount + 1];


		int[] t_Q_max_Bus = new int[BusCount+1];
		int[] t_Q_min_Bus = new int[BusCount+1];


		for (i = 1; i <= BusCount; i++)
		{
			if (Bus_Type[i] == 2)
			{
				if (QgenMax[i] < (Q_load[i] + Q_Calc[i]))
				{
					bus_switch = 1;
					Bus_Type[i] = 5;
					Q_gen[i] = QgenMax[i];
					t_Q_max_Bus[k++] = i;
				}
				if (QgenMin[i] > (Q_load[i] + Q_Calc[i]))
				{
					bus_switch = 1;
					Bus_Type[i] = 4;
					Q_gen[i] = QgenMin[i];
					t_Q_min_Bus[z++] = i;
				}

				if (Bus_Type[i] == 4)
				{
					if (V_Mag[i] < Desired_Voltage[i])
					{
						bus_switch = 2;
						V_Mag[i] = Desired_Voltage[i];
						Bus_Type[i] = 2;

					}
				}
				if (Bus_Type[i] == 5)
				{
					if (V_Mag[i] > Desired_Voltage[i])
					{
						bus_switch = 2;
						V_Mag[i] = Desired_Voltage[i];
						Bus_Type[i] = 2;
					}
				}

			}
		}

		for(int j=0;j<t_Q_min_Bus.length;j++)
		{
			if(t_Q_min_Bus[j]!=0)
				Q_min_Bus[j]=t_Q_min_Bus[j];
		}

		for(int j=0;j<t_Q_max_Bus.length;j++)
		{
			if(t_Q_max_Bus[j]!=0)
				Q_max_Bus[j]=t_Q_max_Bus[j];
		}

		return bus_switch;

	}

	/*
	 ********************************************************************************
	 * Function Name :  create_jacobian()
	 * Function Type : Sub- Routine

	 * Summary:
  		The objective of the function is to calcuulate the Jacobian entries 
  		according to the given formulae. This routine updates the diagonal as well 
  		as off-diagonal elements of the Jacobian Matrix in special cases like PV Bus 
  		or slack bus.
	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :

	 * Bus_Type	:	The array specifying the type of the Bus
	 * BusCount	:	Bus Count is used as an iterator and is equal to No. of Buses
	 * Cindx	:	Array that stores column indices of Y matrix elements
	 * ERP		:	Array that stores End of Row pointers of the elements
	 * V_Ang	: 	Array that stores Voltage angles of the bus
	 * V_Mag	:	Array that stores Voltage Magnitude of the bus
	 * Y_Mat_B	: 	Y-Matrix Array that contains the values  of suseptances
	 * Y_Mat_G	: 	Y-Matrix array that stores values of conductances 

	 * *******************************************************************************
	 * Output Elements :

	 * H	: H entries of the Jacobian
	 * J	: J entries of the Jacobian
	 * L	: L entries of the Jacobian
	 * N	: N entries of the Jacobian

	 * ********************************************************************************
	 * Internal Variables

	 * i				: For loop iterator 
	 * K				: Temporary variable used to store Cindx
	 * *********************************************************************************
	 */

	void create_jacobian()
	{

		H = new double[477];
		J = new double[477];
		N = new double[477];
		L = new double[477];
		int k = 0;

		for (int i = 1; i <= BusCount; i++)
		{
			for (int j = ERP[i - 1] + 1; j <= ERP[i]; j++)
			{
				// Check if the index is of non-Diagonal element
				// Compute jacobian entries for non-diagonal elements
				k = Cindx[j];
				if (i != k)
				{
					H[j] = V_Mag[i] * V_Mag[k] * (Y_Mat_G[j] * (Math.sin(V_Ang[i] - V_Ang[k])) - Y_Mat_B[j] * (Math.cos(V_Ang[i] - V_Ang[k])));
					J[j] = -V_Mag[i] * V_Mag[k] * (Y_Mat_G[j] * (Math.cos(V_Ang[i] - V_Ang[k])) + Y_Mat_B[j] * (Math.sin(V_Ang[i] - V_Ang[k])));
					N[j] = V_Mag[i] * (Y_Mat_G[j] * (Math.cos(V_Ang[i] - V_Ang[k])) + Y_Mat_B[j] * (Math.sin(V_Ang[i] - V_Ang[k])));
					L[j] = V_Mag[i] * (Y_Mat_G[j] * (Math.sin(V_Ang[i] - V_Ang[k])) - Y_Mat_B[j] * (Math.cos(V_Ang[i] - V_Ang[k])));

					/*
					The above values of the Jacobian are good for a normal load 
					and a PQ Bus.However, if the Bus type is other than P-Q Load bus 
				 	we need to make certain modifications to the Jacobian entries. 
				 	The other types of buses are Type2 : PV Bus and Type3: Slack Bus
					 */

					// Case : slack bus
					// All H=J=L=N= 0
					if (Bus_Type[i] == 3)
						H[j] = J[j] = N[j] = L[j] = 0;

					// Case : Bus Type 2
					// The Entries J=L =0; 
					if (Bus_Type[i] == 2)
						J[j] = L[j] = 0;
				}

				//Calculation of diagonal entries of the Jacobian 
				else 
				{

					H[j] = -Q_Calc[i] - (V_Mag[i] * V_Mag[i]) * Y_Mat_B[j];
					J[j] = P_Calc[i] - (V_Mag[i] * V_Mag[i]) * Y_Mat_G[j];
					N[j] = (P_Calc[i] / V_Mag[i]) + (V_Mag[i] * Y_Mat_G[j]);
					L[j] = (Q_Calc[i] / V_Mag[i]) - (V_Mag[i] * Y_Mat_B[j]);

					// The same condition holds true as the case of non-diagonal
					//elements 

					// Case : Slack Bus 
					if (Bus_Type[i] == 3)
					{
						H[j] = L[j] = 1;
						J[j] = N[j] = 0;
					}

					//Case : Type 2 Bus or PV bus 
					if (Bus_Type[i] == 2)
					{
						J[j] = 0;
						L[j] = 1;
					}

				}
			}
		}

		//System.out.println("End of Routine - create_Jacobian ");
	}

	/*
	 ********************************************************************************
	 * Function Name :  Factorize_jacobian()
	 * Function Type : Sub- Routine

	 * Summary:
  		The ordered data structure stored by rows obtained after the columntorow( ) 
  		is used to perform the Numerical factorization to get the L/U matrix for the 
  		Jacobian matrix. This routine factorizes the A matrix to corresponding L,
  		D and U factors.L Matrix is stored by Columns and U Matrix is stored by rows.

	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :

	 * Bus_Type	:	The array specifying the type of the Bus
	 * BusCount	:	Bus Count is used as an iterator and is equal to No. of Buses
	 * Cindx	:	Array that stores column indices of Y matrix elements
	 * ERP		:	Array that stores End of Row pointers of the elements
	 * V_Ang	: 	Array that stores Voltage angles of the bus
	 * V_Mag	:	Array that stores Voltage Magnitude of the bus
	 * Y_Mat_B	: 	Y-Matrix Array that contains the values  of suseptances
	 * Y_Mat_G	: 	Y-Matrix array that stores values of conductances 
	 * H		: 	H entries of the Jacobian
	 * J		: 	J entries of the Jacobian
	 * L		: 	L entries of the Jacobian
	 * N		: 	N entries of the Jacobian

	 * *******************************************************************************
	 * Output Elements :

	 * Diag_H 	:	An array that stores the diagonal elemnts of H
	 * Diag_J 	:	An array that stores the diagonal elemnts of J
	 * Diag_L 	:	An array that stores the diagonal elemnts of N
	 * Diag_N 	:	An array that stores the diagonal elemnts of L
	 * LCO_H 	:	Array that stores lower diagonal elements of the H+Fill Elements 
	 * LCO_J 	:	Array that stores lower diagonal elements of the J+Fill Elements
	 * LCO_L 	:	Array that stores lower diagonal elements of the L+Fill Elements
	 * LCO_N 	:	Array that stores lower diagonal elements of the N+Fill Elements
	 * URO_H 	:	Array that stores upperr diagonal elements of the H+Fill Elements 
	 * URO_J 	:	Array that stores upperr diagonal elements of the J+Fill Elements 
	 * URO_L 	:	Array that stores upperr diagonal elements of the N+Fill Elements 
	 * URO_N 	:	Array that stores upperr diagonal elements of the L+Fill Elements 
	 * 
	 * ********************************************************************************
	 * Internal Variables:

	 * Diag_Multiplier	: Scalar that stores the value of Diag
	 * divider			: Scalar that stores denominator of Ex Accum Product
	 * ExAccum_H 		: Array that stores Exaccumulator values of H 
	 * ExAccum_J 		: Array that stores Exaccumulator values of J 
	 * ExAccum_L 		: Array that stores Exaccumulator values of L 
	 * ExAccum_N 		: Array that stores Exaccumulator values of N 
	 * i 				: for loop iterator
	 * ICPL 			: An array that stores Initial Column Pointers of L
	 * Link_LU 			: Self–referential linked list used in Numerical Factorization
	 * next_r_link		: temporary link variable used to handle linked list
	 * node 			: temporary link variable used to handle linked list
	 * R_index 			: Row Indices variable
	 * rx 				: Variable used in updating and creating linked lists
	 * rxlink 			: Temporary link variable used in linked list
	 * templink			: Temporary link variable used in linked list 
	 * URO_Counter		: Counter used for keeping track of URO elements

	 * *********************************************************************************
	 * Step by Step Procedure :
	Step 0:
	Initialization.
	Set RIndx=1
	Setup ICPL array to point to the beginning of all columns of L (rows of U).
	For each row (RIndx):
	Step 1:
	Load ExAcum with elements from row RIndx.
	Step 2:
	Perform update of elements to the right of the diagonal (ROD) and 
	left of diagonal (LOD) using expanded accumulator method[ H/J/N/L]. Update Link 
	after each row(used in updating row Rindx) is used. (Use Link to determine col. indices 
	of elements LOD, i.e., row numbers used when performing update on elements ROD. After 
	each row is used for update operation, associate its index in Link with the row number
	for which it will next be needed when performing an update operation.
	Step 3:
	Zero all links in Link associated with row RIndx. 
	(Since factorization of row is complete, links not needed.)
	Step 4:
	Invert diagonal element and store in Diag H/J/N/L(RIndx).
	Step 5:
	Store elements to the right of the diagonal.
	Step 6:
	Add RIndx to appropriate location in linked list Link.
	 */

	void Factorize_jacobian()
	{

		int ERPU_Shifter = 0;
		//Local Variable used to right shift ERPU

		int tlink = 0;// Temporary link used in Linked List operation

		// Initializing Link_LU, ICPL, ExAccum, URO, LCO, Diag arrays 
		Link_LU = new int[BusCount + 1];
		ICPL = new int[BusCount];
		ExAccum_H = new double[BusCount + 1];
		ExAccum_J = new double[BusCount + 1];
		ExAccum_N = new double[BusCount + 1];
		ExAccum_L = new double[BusCount + 1];
		URO_H = new double[CindxU.length];
		URO_J = new double[CindxU.length];
		URO_N = new double[CindxU.length];
		URO_L = new double[CindxU.length];
		LCO_H = new double[CindxU.length];
		LCO_J = new double[CindxU.length];
		LCO_N = new double[CindxU.length];
		LCO_L = new double[CindxU.length];
		Diag_H = new double[BusCount + 1];
		Diag_J = new double[BusCount + 1];
		Diag_N = new double[BusCount + 1];
		Diag_L = new double[BusCount + 1];

		int R_index = 0;
		double Diag_Multiplier = 0;

		double H1=0, H2=0;
		double L1=0,L2=0;
		double N1=0, N2=0;
		double J1=0,J2=0;

		/* 
		 * Step 0: 
		 * Initialize 
		 * Rindx -> Update the row to be processed into Rindx  
		 * Link-> Zero Link Array 28. 
		 * ICPL -> Right Shift and add 1 to the this array 
		 */

		for (int i = 1; i <BusCount; i++)
		{
			ERPU_Shifter = ERPU[i - 1];
			ICPL[i] = ERPU_Shifter + 1;
		}

		//Initialize an URO_Counter element which is used to update URO
		int URO_Counter = 1;

		/* 
		 * Step 1: 
		 * a,b Zero Ex-Accum using Link, CindxU_ordered
		 * c Using Cindx , link Update Ex-Accum values
		 */

		/*
		 * Using a nested for loop and erp as arguments
		 * Access each element of ERPU and zero the Ex-accum.
		 * Pl Note: The Ex-accum isn't required to be zeroed for 
		 * Rindx-1 iteration. Where, 
		 * Rindx this row index being processed 
		 */


		for (R_index = 1; R_index <= BusCount; R_index++) // loop for counting rows
		{
			if (R_index < BusCount)
			{
				for (int j = ERPU[R_index - 1] + 1; j <= ERPU[R_index]; j++)
				{

					ExAccum_H[CindxU_Ordered[j]] = 0;
					ExAccum_J[CindxU_Ordered[j]] = 0;
					ExAccum_N[CindxU_Ordered[j]] = 0;
					ExAccum_L[CindxU_Ordered[j]] = 0;
				}
			}

			/*  
			 * Using a While loop, 
			 * Traverse the Link Array Link_LU, and 
			 * Zero the Ex-accum of the corresponding Linkptr to zero
			 */

			int node = R_index, templink;
			while ((templink = Link_LU[node]) != 0)
			{
				ExAccum_H[templink] = 0;
				ExAccum_J[templink] = 0;
				ExAccum_N[templink] = 0;
				ExAccum_L[templink] = 0;
				node = templink;
			}


			for (int j = ERP[R_index - 1] + 1; j <= ERP[R_index]; j++)
			{
				ExAccum_H[Cindx[j]] = H[j];
				ExAccum_J[Cindx[j]] = J[j];
				ExAccum_N[Cindx[j]] = N[j];
				ExAccum_L[Cindx[j]] = L[j];
			}
			double multiplier_H = 0;
			double multiplier_N = 0;
			double multiplier_J = 0;
			double multiplier_L = 0;
			//Multiplier variable to be used in URO operation in EXAccum

			List<Integer> Node_rx = new ArrayList<Integer>();
			// List of Nodes is used for adding rx to linkedlists dynamically

			int rx1;
			tlink = R_index;

			/*
			 * Step2 
			 * Initilaize rx=0;
			 * Search Link_LU for Next greater value
			 * If there is no element , Proceed to step 3 
			 */ 
			/* 
			 * Using a While Loop , Perform 
			 * Traversing , Updating and adding links 
			 */

			while ((Link_LU[R_index]) != 0)
			{

				while (true)
				{
					if ((rx1 = Link_LU[tlink]) != 0)
					{
						Link_LU[tlink] = 0;
						Node_rx.add(rx1);
						tlink = rx1;
					}
					else
						break;
				}

				int rxlink = 0, nextnod;

				if(R_index == 118)
					System.out.println();

				for (Integer rx:Node_rx)
				{

					multiplier_H = ExAccum_H[rx];
					multiplier_N = ExAccum_N[rx];
					multiplier_J = ExAccum_J[rx];
					multiplier_L = ExAccum_L[rx];

					// Update active Exaccum by subtracting the product of 
					//multiplier and URO 
					// Remember, Multiplier is the ExAccum[rx] 
					//of corresponding rx.

					for (int z = ERPU[rx - 1] + 1; z <= ERPU[rx]; z++)
					{
						ExAccum_H[CindxU_Ordered[z]] = ExAccum_H[CindxU_Ordered[z]] - multiplier_H * URO_H[z] - multiplier_N * URO_J[z];
						ExAccum_J[CindxU_Ordered[z]] = ExAccum_J[CindxU_Ordered[z]] - multiplier_J * URO_H[z] - multiplier_L * URO_J[z];
						ExAccum_N[CindxU_Ordered[z]] = ExAccum_N[CindxU_Ordered[z]] - multiplier_H * URO_N[z] - multiplier_N * URO_L[z];
						ExAccum_L[CindxU_Ordered[z]] = ExAccum_L[CindxU_Ordered[z]] - multiplier_J * URO_N[z] - multiplier_L * URO_L[z];

					}

					//Update LCO Vales and increment next 
					//LCO Pointers accordingly

					LCO_H[ICPL[rx]] = ExAccum_H[rx] * Diag_H[rx] + ExAccum_N[rx] * Diag_J[rx];
					LCO_N[ICPL[rx]] = ExAccum_H[rx] * Diag_N[rx] + ExAccum_N[rx] * Diag_L[rx];
					LCO_J[ICPL[rx]] = ExAccum_J[rx] * Diag_H[rx] + ExAccum_L[rx] * Diag_J[rx];
					LCO_L[ICPL[rx]] = ExAccum_J[rx] * Diag_N[rx] + ExAccum_L[rx] * Diag_L[rx];

					ICPL[rx]++;
					//Simultaneously, update the rxlink variable

					//Initializing Link Array using rx 
					//Update rx only if it is greater than ICPl of 
					//the corresponding index
					if (ICPL[rx] < CindxU_Ordered.length)
						rxlink = CindxU_Ordered[ICPL[rx]];

					//Traverse the list , searching for 
					//the next non-zero position

					if (ICPL[rx] <= ERPU[rx])
					{ 
						while ((nextnod = Link_LU[rxlink]) != 0)
						{
							rxlink = Link_LU[nextnod];
							if (rxlink == 0)
							{
								rxlink = nextnod;
								break;
							}
						}

						Link_LU[rxlink] = rx;
					}
				}
			}

			/*
			 * Step 4 
			 * Invert ExAccum product and store in Diagonal Array 
			 */
			double divider ;

			divider  = (ExAccum_H[R_index] * ExAccum_L[R_index] - ExAccum_N[R_index] * ExAccum_J[R_index]);

			Diag_Multiplier = 1 / divider ;

			Diag_H[R_index] = ExAccum_L[R_index] * Diag_Multiplier;
			Diag_N[R_index] = -ExAccum_N[R_index] * Diag_Multiplier;
			Diag_J[R_index] = -ExAccum_J[R_index] * Diag_Multiplier;
			Diag_L[R_index] = ExAccum_H[R_index] * Diag_Multiplier;

			/*
			 * Step 5 
			 * Multiply active Exaccum by Diag and store in URO 
			 */ 

			// Updating URO Value
			if (R_index < BusCount)
			{
				for (int j = ERPU[R_index - 1] + 1; j <= ERPU[R_index]; j++)
				{

					H1= Diag_H[R_index] * ExAccum_H[CindxU_Ordered[j]];
					H2= Diag_N[R_index] * ExAccum_J[CindxU_Ordered[j]];

					N1=Diag_H[R_index] * ExAccum_N[CindxU_Ordered[j]];	
					N2=Diag_N[R_index] * ExAccum_L[CindxU_Ordered[j]];

					J1 = Diag_J[R_index] * ExAccum_H[CindxU_Ordered[j]];
					J2= Diag_L[R_index] * ExAccum_J[CindxU_Ordered[j]];

					L1 = Diag_J[R_index] * ExAccum_N[CindxU_Ordered[j]];
					L2 = Diag_L[R_index] * ExAccum_L[CindxU_Ordered[j]];

					URO_H[URO_Counter] = H1 + H2;
					URO_N[URO_Counter] = N1 + N2;
					URO_J[URO_Counter] = J1 + J2;
					URO_L[URO_Counter] = L1 + L2;
					URO_Counter++;
				}

				// Adding Rindx to Linked List

				int next_r_link, NextNode;
				next_r_link = CindxU_Ordered[ICPL[R_index]];
				
				/* 
				* Step 6 
				* Add Rindx to linked list associated with 
				* CindxU_Ordered(ICPL(Rindx))
				*/

				while ((NextNode = Link_LU[next_r_link]) != 0)
				{
					next_r_link = Link_LU[NextNode];
					
					/*
					* Check if the link , nextrlink is zero,
					* If Yes, update it to point the LinkNode
					* Continue iteration until while loop fails
					*/
					if (next_r_link == 0)
					{
						next_r_link = NextNode;
						break;
					}
				}
				Link_LU[next_r_link] = R_index;
			}
		}
		
		//System.out.println("End of Routine - create_Jacobian ");
	}

	
	/*
	 ********************************************************************************
	 * Function Name : Forward_solve()
	 * Function Type : Sub- Routine

	 * Summary:
  		Forward_solve(); performs Forward, Diagonal Substitution for the sparse matrix. 
  		The data required is given by previous operations. 
  		For forward substitution L is considered to be stored by columns. 

	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :
	 * 
	 * BusCount	: Bus Count is used as an iterator and is equal to No. of Buses
	 * CindxU_Ordered: An array that stores col indices after ordering 
	 * 	Diag_H 	:	An array that stores the diagonal elemnts of H
	 * Diag_J 	:	An array that stores the diagonal elemnts of J
	 * Diag_L 	:	An array that stores the diagonal elemnts of N
	 * Diag_N 	:	An array that stores the diagonal elemnts of L
	 * LCO_H 	:	Array that stores lower diagonal elements of the H+Fill Elements 
	 * LCO_J 	:	Array that stores lower diagonal elements of the J+Fill Elements
	 * LCO_L 	:	Array that stores lower diagonal elements of the L+Fill Elements
	 * LCO_N 	:	Array that stores lower diagonal elements of the N+Fill Elements
	  
	 * *******************************************************************************
	 * Output Elements :

	 * delta_V_Ang		: Difference in Voltage angles which stores P Mismatch
	 * delta_V_Mag		: Difference in Voltage angles which stores Q Mismatch
	  
	 * ********************************************************************************
	 * Internal Variables:
	  
	 * i,j				:	for loop variables 
	 * solve_variable	:	Solve variable that stores value of CindxU after ordering 
	 * Temp_P			:	Temporary variable for storing P Mismatch
	 * Temp_Q			:	Temporary varible for storing Q mismatch
	
	 * *********************************************************************************
	 */
	
	void Forward_solve()
	{
		

		delta_V_Ang = new double[BusCount + 1];
		delta_V_Mag = new double[BusCount + 1];

		double Temp_P = 0, Temp_Q=0;

		// Outer for loop is for each Bus 
		// Iner for loop is for accessing each element
		for (int i = 1; i <= BusCount; i++)
		{
			for (int j = ERPU[i - 1] + 1; j <= ERPU[i]; j++)
			{
				solve_variable = CindxU_Ordered[j];
				//stores column index in a scalar variable;
				
				//initialization of the variables P_Mismatch and Q_Mismatch
				//updated values of the calculated powers
				Temp_P = P_Mismatch[solve_variable] - LCO_H[j] * P_Mismatch[i] - LCO_N[j] * Q_Mismatch[i];
				Temp_Q = Q_Mismatch[solve_variable] - LCO_J[j] * P_Mismatch[i] - LCO_L[j] * Q_Mismatch[i];

				P_Mismatch[solve_variable] = Temp_P;
				Q_Mismatch[solve_variable] = Temp_Q;

			}
		}

		//Multiplication of the P_Mismatch and Q_Mismatch with the diagonal elements;
		for (int i = 1; i <= BusCount; i++)
		{
			Temp_P = Diag_H[i] * P_Mismatch[i] + Diag_N[i] * Q_Mismatch[i];
			Temp_Q = Diag_J[i] * P_Mismatch[i] + Diag_L[i] * Q_Mismatch[i];
			P_Mismatch[i] = Temp_P;
			Q_Mismatch[i] = Temp_Q;

		}
		
		//Copy Arrays back 
		delta_V_Ang = P_Mismatch;
		delta_V_Mag = Q_Mismatch;
		
		//System.out.println("End of Routine - Forward_Solve ");
	}

	

	/*
	 ********************************************************************************
	 * Function Name : Backward_solve()
	 * Function Type : Sub- Routine

	 * Summary:
  		Backward_solve() Backward Substitution for the sparse matrix. 
  		The data required is given by previous operations. 
  		The backward substitution is used to arrive at the power flow solution

	 * *******************************************************************************
	 * Characteristics:
	 * 
	 * Language			:	JAVA
	 * Development Kit	: 	Eclipse -JUNO
	 * Java Library		: 	JAVASE-1.7
	 * CPU				: 	Intel Core i5 [TM]
	 * Author			: 	Rajthilak Murugesan
	 * Reference		: 	Prof. Daniel Tylavsky Lectures, EEE 574 , ASU, Fall 2012
	 * ******************************************************************************
	 * Input Elements :
	 * 
	 * BusCount	: Bus Count is used as an iterator and is equal to No. of Buses
	 * CindxU_Ordered: An array that stores col indices after ordering 
	 * Diag_H 	:	An array that stores the diagonal elemnts of H
	 * Diag_J 	:	An array that stores the diagonal elemnts of J
	 * Diag_L 	:	An array that stores the diagonal elemnts of N
	 * Diag_N 	:	An array that stores the diagonal elemnts of L
	 * URO_H 	:	Array that stores upperr diagonal elements of the H+Fill Elements 
	 * URO_J 	:	Array that stores upperr diagonal elements of the J+Fill Elements 
	 * URO_L 	:	Array that stores upperr diagonal elements of the N+Fill Elements 
	 * URO_N 	:	Array that stores upperr diagonal elements of the L+Fill Elements 
	  
	 * *******************************************************************************
	 * Output Elements :

	 * delta_V_Ang		: Difference in Voltage angles which stores P Mismatch
	 * delta_V_Mag		: Difference in Voltage angles which stores Q Mismatch
	  
	 * ********************************************************************************
	 * Internal Variables:
	  
	 * i,j				:	for loop variables 
	 * solve_variable	:	Solve variable that stores value of CindxU after ordering 
	 * p_temp			:	Temporary variable for storing P Mismatch
	 * q_temp			:	Temporary varible for storing Q mismatch
	
	 * *********************************************************************************
	 */
	
	void Backward_solve()
	{
		double p_temp = 0;
		double q_temp = 0;

		//Apply the backward substitution routine using URO
		for (int i = BusCount-1; i >= 1; i--)
		{
			for (int j = ERPU[i]; j >= ERPU[i - 1] + 1; j--)
			{
				solve_variable = CindxU_Ordered[j];
				p_temp = P_Mismatch[i] - URO_H[j] * P_Mismatch[solve_variable] - URO_N[j] * Q_Mismatch[solve_variable];
				q_temp = Q_Mismatch[i] - URO_J[j] * P_Mismatch[solve_variable] - URO_L[j] * Q_Mismatch[solve_variable];
				P_Mismatch[i] = p_temp;
				Q_Mismatch[i] = q_temp;

			}
		}

		delta_V_Ang = P_Mismatch;
		delta_V_Mag = Q_Mismatch;
		
		//System.out.println("End of Routine - Backward_solve() ");
	}

	

	void compute_lineflows()
	{


		int k, l, m;

		for (int i = 1; i <BusCount; i++)
		{
			k = From_Bus[i];
			l = To_Bus[i];
			//******** multiply by base if needed
			P_ik[i] = V_Mag[i] * V_Mag[i] * (G_ik[i] + GB[i]) - V_Mag[i] * V_Mag[k] * (GB[i] * Math.cos(V_Ang[i] - V_Ang[k]) + BB[i] * Math.sin(V_Ang[i] - V_Ang[k]));
			Q_ik[i] = V_Mag[i] * V_Mag[i] * (B_ik[i] + BB[i]) - V_Mag[i] * V_Mag[k] * (GB[i] * Math.sin(V_Ang[i] - V_Ang[k]) + BB[i] * Math.cos(V_Ang[i] - V_Ang[k]));
			P_ki[i] = V_Mag[k] * V_Mag[k] * (G_ki[i] + GB[i]) - V_Mag[i] * V_Mag[k] * (GB[i] * Math.cos(V_Ang[k] - V_Ang[i]) + BB[i] * Math.sin(V_Ang[k] - V_Ang[i]));
			Q_ki[i] = V_Mag[k] * V_Mag[k] * (B_ki[i] + BB[i]) - V_Mag[i] * V_Mag[k] * (GB[i] * Math.sin(V_Ang[k] - V_Ang[i]) - BB[i] * Math.cos(V_Ang[k] - V_Ang[i]));
		}



	}

	
	
	void Solve()
	{
		float[] Rmax= new float[120]; 
		float[] Rmin=new float[120];

		double[] temp_VMag = new double[119];
		double[] temp_VAng = new double[119];

		Create_Y_Bus();
		Tinney_Ordering();
		Create_LU();
		rowtocolumn();
		columntorow();
		initialize_voltage();

		int flip=0;
		int count=0;
		int bus_switch_count=0;
		int iteration=1;
		for(iteration=1;iteration<=10;iteration++)
		{

			injections();

			if (bus_switch_count==0)
			{
				if (iteration>3)
				{
					flip = bus_switch();

					if(flip==2)
						injections();	

					bus_switch_count=-1;
				}
			}

			if(bus_switch_count==-1)
				flip =0;

			power_mismatch();

			if(flip==0)
			{
				if(Max_P_Mismatch<tolerence || Max_Q_Mismatch<tolerence)
				{
					iteration=iteration-1;
					break;
				}
			}
			else
			{
				for(int k=0;k<Q_max_Bus.length;k++)
				{
					Rmax[k]= Q_max_Bus[k];
					Rmin[k] = Q_min_Bus[k];
				}

			}

			create_jacobian();
			Factorize_jacobian();
			Forward_solve();
			Backward_solve();

			for(int i=1;i<=BusCount;i++)
			{
				V_Mag[i] =V_Mag[i] + delta_V_Mag[i];
				V_Ang[i] =V_Ang[i] + delta_V_Ang[i];
			}

			count++;
			System.out.println(count);

		}

		ReOrdering();

		System.out.println("The Powerflow successfully converges after "+(iteration+1)+" Newton-Raphson iterations ");

		for(double v:V_Mag)
			System.out.println(v);

		for(double q:V_Ang)
			System.out.println(q);

		//	compute_lineflows();
	}

	

	private void ReOrdering() {

		int count = 0;

		double[] t_V_Mag = new double[119];
		double[] t_V_Ang = new double[119];

		int[] t_Q_max_Bus = new int[BusCount+1];
		int[] t_Q_min_Bus = new int[BusCount+1];

		for(int i=1;i<=BusCount;i++)
		{
			for(int j=1;j<=BusCount;j++)
			{
				if(Matrix_size[j]==i)
				{
					t_V_Mag[i]= V_Mag[j];
					t_V_Ang[i]= V_Ang[j];
				}
			}
		}


		// Identifying Bus on Limits
		for(int k=0; k<Q_max_Bus.length;k++)
		{
			if(Q_max_Bus[k]!=0)
				t_Q_max_Bus[count] =  Matrix_size[Q_max_Bus[k]];
		}

		for(int k=0; k<Q_min_Bus.length;k++)
		{
			if(Q_min_Bus[k]!=0)
			{
				t_Q_min_Bus[count] =  Matrix_size[Q_min_Bus[k]];
				count++;
			}
		}

		V_Mag = t_V_Mag;
		V_Ang = t_V_Ang;
		Q_max_Bus=t_Q_max_Bus;
		Q_min_Bus=t_Q_min_Bus;
	}
}
