
package cga.data;

import java.util.Vector;

public class Sensor extends Circle {

	public static final int BLACK = 1;
	public static final int GRAY = 2;
	public static final int WHITE = 0;
	private int connectedComponentNumber;
	private Vector<Sensor> neighbours;
	private int status = WHITE;

	public Sensor(final int _id, final float x, final float y) {
		super(_id, x, y);
	}

	public void addNeighbour(final Sensor neighbour) {
		if (neighbours == null)
			neighbours = new Vector<Sensor>();
		neighbours.add(neighbour);
	}

	public void clearNeighbours() {
		neighbours = null;
	}

	public boolean connectedTo(final Circle _sensor) {
		// Can these two sensors communicate with each other?
		final int diameter = SensorInfo.getInstance().parameter.sensorRadius * 2;
		final float distance = super.distance(_sensor);
		if (distance <= diameter)
			return true;
		return false;
	}

	public int getConnectedComponentNumber() {
		return connectedComponentNumber;
	}

	public Vector<Sensor> getNeighbours() {
		return neighbours;
	}

	public int getStatus() {
		return status;
	}

	public void setConnectedComponentNumber(final int connectionSetNumber) {
		connectedComponentNumber = connectionSetNumber;
	}

	public void setStatus(final int _status) {
		status = _status;
	}

	@Override
	public String toString() {
		return "Sensor " + super.toString();
	}
}
