package statistics.ntels.coefficient;

import java.util.Arrays;

public class mAIN {

		public static void main(String[] args) {
			
			Correlation correlation = new Correlation();
			try {
				
				
				System.out.println(correlation.getR(Arrays.asList(190,190,190,190,190,190,190), Arrays.asList(190,190,190,190,190,190,190)));
				System.out.println("---");
				System.out.println(correlation.getProcessedCount());
				System.out.println(correlation.getStatisticalHypothesisTest());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
