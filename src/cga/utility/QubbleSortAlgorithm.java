package cga.utility;

import cga.data.ga.Chromosome;

public class QubbleSortAlgorithm {

	private static QubbleSortAlgorithm instance = null;

	public static QubbleSortAlgorithm getInstance() {
		if (instance == null) {
			instance = new QubbleSortAlgorithm();
		}
		return instance;
	}

	private QubbleSortAlgorithm() {

	}

	private void bsort(final Chromosome[] a, final int lo, final int hi) {
		for (int j = hi; j > lo; j--) {
			for (int i = lo; i < j; i++) {
				if (a[i].getFitness() > a[i + 1].getFitness()) {
					swap(a, i, i + 1);
				}
			}
		}
	}

	public void sort(final Chromosome[] a) {
		sort(a, 0, a.length - 1);
	}

	private void sort(final Chromosome[] a, final int lo0, final int hi0) {
		int lo = lo0;
		int hi = hi0;

		/*
		 * Bubble sort if the number of elements is less than 6
		 */
		if ((hi - lo) <= 6) {
			bsort(a, lo, hi);

			return;
		}

		/*
		 * Pick a pivot and move it out of the way
		 */
		final Chromosome pivot = a[(lo + hi) / 2];
		a[(lo + hi) / 2] = a[hi];
		a[hi] = pivot;

		while (lo < hi) {
			/*
			 * Search forward from a[lo] until an element is found that is
			 * greater than the pivot or lo >= hi
			 */
			while ((a[lo].getFitness() <= pivot.getFitness()) && (lo < hi)) {
				lo++;
			}

			/*
			 * Search backward from a[hi] until element is found that is less
			 * than the pivot, or hi <= lo
			 */
			while ((pivot.getFitness() <= a[hi].getFitness()) && (lo < hi)) {
				hi--;
			}

			/*
			 * Swap elements a[lo] and a[hi]
			 */
			if (lo < hi) {
				swap(a, lo, hi);
			}
		}

		/*
		 * Put the median in the "center" of the list
		 */
		a[hi0] = a[hi];
		a[hi] = pivot;

		/*
		 * Recursive calls, elements a[lo0] to a[lo-1] are less than or equal to
		 * pivot, elements a[hi+1] to a[hi0] are greater than pivot.
		 */
		sort(a, lo0, lo - 1);
		sort(a, hi + 1, hi0);
	}

	private void swap(final Chromosome[] a, final int i, final int j) {
		final Chromosome temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
