package exam.lawnmower;

public class Position {

	private int cx;
	private int cy;
	private int degree;

	public Position(int cx, int cy, int degree) {
		this.cx = cx;
		this.cy = cy;
		this.degree = degree;
	}

	public int getCx() {
		return this.cx;
	}

	public int getCy() {
		return this.cy;
	}

	public int getDegree() {
		return this.degree;
	}
}
