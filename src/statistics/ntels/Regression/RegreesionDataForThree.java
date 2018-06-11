package statistics.ntels.Regression;

import statistics.ntels.coefficient.CorrelationData;

public class RegreesionDataForThree implements Comparable<RegreesionDataForThree>{

	private Double x1;
	private Double y;
	private Double x2;
	
	
	public Double getX2() {
		return x2;
	}
	public void setX2(Object x2) {
		this.x2 = Double.valueOf(String.valueOf(x2));
	}
	public Double getX1() {
		return x1;
	}
	public void setX1(Object x1) {
		this.x1 = Double.valueOf(String.valueOf(x1));
	}
	public Double getY() {
		return y;
	}
	public void setY(Object y) {
		this.y = Double.valueOf(String.valueOf(y));
	}
	@Override
	public int compareTo(RegreesionDataForThree o) {
		// TODO Auto-generated method stub
		return y.compareTo(o.y);
	}
	
}
