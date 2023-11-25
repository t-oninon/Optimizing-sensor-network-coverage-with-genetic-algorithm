
package cga.data.ga;

public class Movement {

	private int xInc;
	private int yInc;

	public Movement(final int inc, final int inc2) {
		super();
		xInc = inc;
		yInc = inc2;
	}

	public int getXInc() {
		return xInc;
	}

	public int getYInc() {
		return yInc;
	}

	public void setXInc(final int x) {
		xInc = x;
	}

	public void setYInc(final int y) {
		yInc = y;
	}

	public String toString() {
		return xInc + " " + yInc;
	}
}
