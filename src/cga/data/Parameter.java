
package cga.data;

public class Parameter {

	private int areaHeight;
	private int areaWidth;
	public int kCovered;
	public int sensorRadius;

	public int totalArea;
	public int movementLimit = 3;

	public int getAreaHeight() {
		return areaHeight;
	}

	public int getAreaWidth() {
		return areaWidth;
	}

	public int getTotalArea() {
		return totalArea;
	}

	public void setAreaHeight(final int _areaHeight) {
		areaHeight = _areaHeight;
		totalArea = areaWidth * areaHeight;
	}

	public void setAreaWidth(final int _areaWidth) {
		areaWidth = _areaWidth;
		totalArea = areaWidth * areaHeight;
	}

	@Override
	public String toString() {
		return "A(" + areaWidth + "," + areaHeight + ")=" + totalArea
				+ " kCovered:" + kCovered + " Radius:" + sensorRadius
				+ " movementLimit:" + movementLimit;
	}
}
