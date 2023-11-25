
package cga.data;

public class Hotspot extends Circle {

	public Hotspot(final int _id, final float x, final float y) {
		super(_id, x, y);
	}

	@Override
	public String toString() {
		return "Hotspot " + super.toString();
	}
}
