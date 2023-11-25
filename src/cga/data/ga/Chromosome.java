
package cga.data.ga;

import cga.data.SensorInfo;
import cga.ga.GeneticAlgorithm;

public class Chromosome {

	//fitness value of this chromosome
	private int fitness;
	// we are stroing movements as genes of chromosome
	private final Movement[] genes;

	public Chromosome(final int sensorCount) {
		genes = new Movement[sensorCount];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Movement(0, 0);
		}
	}

	/**
	 * generate random genes
	 * 
	 * @param sensorCount
	 */
	public Chromosome(final int sensorCount, final boolean random) {
		genes = new Movement[sensorCount];
		if (random) {
			for (int i = 0; i < genes.length; i++) {
				genes[i] = new Movement(generateRandomMovement(),
						generateRandomMovement());
			}
		}
	}

	@Override
	public Object clone() {
		final Chromosome chr = new Chromosome(genes.length, false);
		for (int i = 0; i < genes.length; i++) {
			chr.setGene(i, genes[i]);
		}
		chr.setFitness(fitness);
		return chr;
	}

	public int generateRandomMovement() {
		final int high = SensorInfo.getInstance().parameter.movementLimit;
		final int low = -SensorInfo.getInstance().parameter.movementLimit;
		return GeneticAlgorithm.RANDOM_NUMBER_GENERATOR.nextInt(high - low + 1) + low;
	}

	public int getFitness() {
		return fitness;
	}

	public Movement getGene(final int i) {
		return genes[i];
	}

	public int getGeneCount() {
		return genes.length;
	}

	public Movement[] getGenes() {
		return genes;
	}

	public void setFitness(final int fitness) {
		this.fitness = fitness;
	}

	public void setGene(final int i, final Movement gene) {
		genes[i] = gene;
	}

	@Override
	public String toString() {
		return "F:" + fitness;
	}
}
