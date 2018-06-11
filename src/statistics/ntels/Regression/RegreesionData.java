package statistics.ntels.Regression;

public class RegreesionData implements Comparable<RegreesionData> {

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
	public int compareTo(RegreesionData o) {
		// TODO Auto-generated method stub
		return y.compareTo(o.y);
	}
	
	
}
