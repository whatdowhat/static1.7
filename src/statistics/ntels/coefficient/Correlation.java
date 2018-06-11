package statistics.ntels.coefficient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Correlation {

	
	
	//this class gets coefficient correlation between element A and element B
	// r= 0.0 ~ 0.2 very low
	// r= 0.2 ~ 0.4 low
	// r= 0.4 ~ 0.6 have
	// r= 0.6 ~ 0.8 high
	// r= 0.8 ~ 1.0 very high
	
	private static List<CorrelationData> cList=null;
	
	// standard cuting low percent %
	private static float correctionFactorLow = 0.1f;
	
	// standard cuting low percent %
	private static float correctionFactorhigh = 0.1f;
	
	private static float processedCount=0.f;
	
	private static double r = 0;
	
	//90% (a,b)  : a = amount, b= dismissal station
	
	@SuppressWarnings("unchecked")
	private static Map<Integer, Double> distributionTable = new HashMap(){{
		
		put(3,1.638);
		put(4,1.533);
		put(5,1.476);
		put(6,1.440);
		put(7,1.415);
		put(8,1.397);
		put(9,1.383);
		
		put(10,1.372);
		put(11,1.363);
		put(12,1.356);
		put(13,1.350);
		put(14,1.345);
		put(15,1.341);
		put(16,1.337);
		put(17,1.333);
		put(18,1.330);
		put(19,1.328);
		
		put(20,1.325);
		put(21,1.323);
		put(22,1.321);
		put(23,1.319);
		put(24,1.318);
		put(25,1.316);
		put(26,1.315);
		put(27,1.314);
		put(28,1.313);
		put(29,1.311);
	
		put(30,1.310);
		
		put(40,1.303);
		
		put(60,1.296);
		
		put(120,1.289);
		
		put(999,1.282);
		
	}};
	
	
	
	
	public static float getCorrectionFactorLow() {
		return correctionFactorLow;
	}


	public static void setCorrectionFactorLow(float correctionFactorLow) {
		Correlation.correctionFactorLow = correctionFactorLow;
	}


	public static float getCorrectionFactorhigh() {
		return correctionFactorhigh;
	}


	public static void setCorrectionFactorhigh(float correctionFactorhigh) {
		Correlation.correctionFactorhigh = correctionFactorhigh;
	}


	public static double getProcessedCount() {
		return processedCount;
	}


	public static double getR() {
		return r;
	}


	//self test main
	public static void main(String[] args) {
		


	}
	
	
	public double getR(List<? extends Number > x , List<? extends Number> y) throws Exception {
		
		
		
		//long OriginalCount = y.stream().count();
		long OriginalCount = y.size();
		
		
		
		long deleteLowCount = (long) Math.ceil((OriginalCount*correctionFactorLow));
		long deleteHighCount = (long) Math.ceil((OriginalCount*correctionFactorhigh));
		
		
		List<CorrelationData> resultList = new ArrayList<>();
		
		if(y.size() != x.size()) throw new Exception("GET Correlation SIZE Matching Error ");
		
		//if(y.size() < 2) throw new Exception("GET Correlation not enough table size ERROR ");
		
		
		for (int i = 0; i < y.size(); i++) {
			
			CorrelationData data = new CorrelationData();
			data.setX(x.get(i));
			data.setY(y.get(i));
			
			resultList.add(data);
			
		}
		List<CorrelationData> resultListTem = new ArrayList<>();
		
		Collections.sort(resultList); //y_data_sorted by accending
//		System.out.println("orignal :: "+OriginalCount);
//		System.out.println("deleteHighCount :: "+deleteHighCount);
//		System.out.println("deleteLowCount :: "+deleteLowCount);
		//System.out.println((OriginalCount-deleteHighCount)-1);
		//cut data 
		for (int i = 0 + (int)(deleteLowCount) ; i < (OriginalCount-deleteHighCount) ; i++) {
			
			//System.out.println(i);
			resultListTem.add(resultList.get(i));
			
			
		}
		
		resultList = resultListTem;

		
		//resultList =  resultList.stream().sorted(comparing(CorrelationData::getY)).collect(toList()); //y_data sorted by accending
		//resultList =  resultList.stream().limit(OriginalCount-deleteHighCount).skip(deleteLowCount).collect(toList()); //cut data 
		
		if(resultList.size() < 5) throw new Exception("GET StatisticalHypothesisTest SIZE Matching Erro check the processedCount , it must big than 4 \n"
				+ "check setcorrectionFactorLow or setcorrectionFactorHigh");
		
		
		double avgX = 0;
		double sumX = 0;
		
		double avgY = 0;
		double sumY = 0;
		
		
		for (CorrelationData correlationData : resultListTem) {
			sumX += correlationData.getX();
			sumY += correlationData.getY();
		}
		avgX = sumX/resultList.size();
		avgY = sumY/resultList.size();
		
		
		//OptionalDouble avgX = resultList.stream().mapToDouble(CorrelationData::getX).average();
		//OptionalDouble avgY = resultList.stream().mapToDouble(CorrelationData::getY).average();
		
		double upper=0;
		double xBottom =0;
		double yBottom =0;
		
		for (CorrelationData correlationData : resultListTem) {
			
			upper += (correlationData.getX() - avgX ) * (correlationData.getY() - avgY);
			xBottom += Math.pow(correlationData.getX() - avgX , 2);
			yBottom += Math.pow(correlationData.getY() - avgY , 2);
			
		}
		
//		System.out.println(avgX);
//		System.out.println(avgY);
//		System.out.println(upper);
//		System.out.println(xBottom);
//		System.out.println(yBottom);
		
		
		double resultBottom = 0;
		resultBottom = Math.sqrt(xBottom*yBottom);
		
		//double upper = resultList.stream().mapToDouble(item -> (item.getX() - avgX.getAsDouble()) * (item.getY() - avgY.getAsDouble()) ).sum();
		
		
		//double xBottom = resultList.stream().mapToDouble(item -> Math.pow(item.getX() - avgX.getAsDouble() ,2) ).sum();
		//double yBottom = resultList.stream().mapToDouble(item -> Math.pow(item.getY() - avgY.getAsDouble() ,2) ).sum();
		
		
		double result = 0;
		
		//OptionalDouble resultBottom =OptionalDouble.of( Math.sqrt(xBottom*yBottom));
		
		if(resultBottom !=0 ) {
			
			result = upper/resultBottom;
		
		}else {
			
			
		}
		
		processedCount = resultList.size();
		r = result;
		
		
		return result;
		
	}
	
	public boolean getStatisticalHypothesisTest() throws Exception {
		
		double upper = this.r;
		
		double bottom_upper = 1 - Math.pow(upper,2);
		Double bottom_bottom = (double) (this.processedCount - 2);
		
		double bottom = Math.sqrt(bottom_upper/bottom_bottom);
		//OptionalDouble bottom = OptionalDouble.of(Math.sqrt(bottom_upper/bottom_bottom));
		
		
		double result = upper/bottom;
		
		double distributionValue = 0;
		
		if(bottom_bottom < 3) {
			throw new Exception("GET StatisticalHypothesisTest SIZE Matching Erro check the processedCount , it must big than 4 ");
		}else if(bottom_bottom < 31) {
			distributionValue = this.distributionTable.get(bottom_bottom.intValue());
		}else if(bottom_bottom < 50) {
			distributionValue = this.distributionTable.get(40);
		}else if(bottom_bottom < 100) {
			distributionValue = this.distributionTable.get(60);
		}else if(bottom_bottom < 150) {
			distributionValue = this.distributionTable.get(120);
		}else{
			distributionValue = this.distributionTable.get(999);
		}
			
		
		
		
		if(result<0) {
		
			if(result < -distributionValue ) {
				return true;
			}else {
				return false;
			}
			
			
		}else {
			
			if(result > distributionValue ) {
				return true;
			}else {
				return false;
			}
		}
		
		
		
	}
	
}
