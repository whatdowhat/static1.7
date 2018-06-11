package statistics.ntels.coefficient;

public class CorrelationData implements Comparable<CorrelationData>{

	private Double x;
	private Double y;
	public Double getX() {
		return x;
	}
	public void setX(Object x) {
		this.x = Double.valueOf(String.valueOf(x));
	}
	public Double getY() {
		return y;
	}
	public void setY(Object y) {
		this.y = Double.valueOf(String.valueOf(y));
	}
	@Override
	public int compareTo(CorrelationData o) {
		// TODO Auto-generated method stub
		return y.compareTo(o.getY());
	}
	@Override
	public String toString() {
		return "CorrelationData [x=" + x + ", y=" + y + "]";
	}
	
	
}
