
package cga.data;

public class Arc {

	private float alpha;
	private float beta;
	private int coverage = 1;

	public Arc(float alpha, float beta) {
		this.alpha = alpha;
		this.beta = beta;
	}

	public Arc mergeArcs(Arc arc) {
		float a, b;

		if (beta < arc.getAlpha())
			return null;

		if (alpha < arc.getAlpha())
			a = alpha;
		else
			a = arc.getAlpha();

		if (beta > arc.getBeta())
			b = beta;
		else
			b = arc.getBeta();

		return new Arc(a, b);
	}

	public Arc getIntersection(Arc arc) {
		Arc arcToReturn = null;

		if (alpha < arc.getAlpha()) {
			if (beta < arc.getAlpha()) {
				return null;
			} else if (beta > arc.getBeta()) {
				arcToReturn = new Arc(arc.getAlpha(), arc.getBeta());
			} else {
				arcToReturn = new Arc(arc.getAlpha(), beta);
			}
		} else {
			if ((alpha > arc.getBeta()) && (beta > arc.getBeta())) {
				return null;
			}
			if (beta > arc.getBeta()) {
				arcToReturn = new Arc(alpha, arc.getBeta());
			} else {
				arcToReturn = new Arc(alpha, beta);
			}
		}

		if (canBeDiscarded(arcToReturn))
			return null;
		else
			return arcToReturn;
	}

	public Arc[] getIntersectionDifference(Arc arc) {
		Arc[] difference = new Arc[2];

		difference[0] = null;
		difference[1] = null;

		Arc intersection = this.getIntersection(arc);

		// return original arc
		if (intersection == null) {
			difference[0] = new Arc(this.getAlpha(), this.getBeta());
			return difference;
		}

		// no difference
		if (this.isEqual(intersection))
			return null;

		if (alpha < intersection.getAlpha()) {
			if (beta > intersection.getBeta()) {
				difference[0] = new Arc(alpha, intersection.getAlpha());
				difference[1] = new Arc(intersection.getBeta(), beta);
			} else {
				difference[0] = new Arc(alpha, intersection.getAlpha());
				difference[1] = null;
			}
		} else {
			if (beta > intersection.getBeta()) {
				difference[0] = new Arc(intersection.getBeta(), beta);
				difference[1] = null;
			} else {
				return null;
			}
		}

		for (int i = 0; i < difference.length; i++) {
			if (difference[i] != null)
				if (canBeDiscarded(difference[i]))
					difference[i] = null;
		}

		return difference;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public float getBeta() {
		return beta;
	}

	public void setBeta(float beta) {
		this.beta = beta;
	}

	public String toString() {
		return new String("Alpha:" + (alpha / Math.PI) * 180 + " Beta:" + (beta / Math.PI) * 180 + " Coverage:"
				+ coverage + "\n");
	}

	public boolean isEqual(Arc arc) {

		if ((this.alpha == arc.getAlpha()) && (this.beta == arc.getBeta()))
			return true;

		return false;
	}

	public int getCoverage() {
		return coverage;
	}

	public void setCoverage(int coverage) {
		this.coverage = coverage;
	}

	private boolean canBeDiscarded(Arc arc) {
		return false;
	}

}
