
package cga.data.ga;

public class GAParameters {

	public float crossoverProbability;
	public int generationLimit;
	public float mutationProbability;
	public int populationSize;

	@Override
	public String toString() {
		return "PC:" + crossoverProbability + " PM:" + mutationProbability
				+ " NG:" + generationLimit + " NP:" + populationSize;
	}
}
