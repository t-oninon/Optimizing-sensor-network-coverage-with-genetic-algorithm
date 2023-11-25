package cga.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import cga.data.SensorInfo;
import cga.ga.GANotifier;
import cga.ga.GeneticAlgorithm;
import cga.utility.JPGFileFilter;
import cga.utility.SensorXMLReader;

public class GATest implements GANotifier {

	public static void main(final String[] args) {
		new GATest().startTest();
	}

	@Override
	public void finished() {
		System.out.println("-----------------------------------");
	}

	@Override
	public void notify(final String _message) {
		System.out.println(_message);
	}

	private void redirectOutputToFile() {
		try {
			System.setOut(new PrintStream(new FileOutputStream("tests/log.txt")));
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void runGA(final float mp, final float cp, final int ps) {
		// load sensors from XML file
		SensorXMLReader.readSensorInfo(new File("sensorler2.xml"));
		final SensorInfo sensorInfo = SensorInfo.getInstance();
		// we are going to store elite images
		sensorInfo.saveEliteImage = true;
		// set GA parameters
		sensorInfo.gaParameter.mutationProbability = mp;
		sensorInfo.gaParameter.crossoverProbability = cp;
		sensorInfo.gaParameter.populationSize = ps;
		sensorInfo.gaParameter.generationLimit = 100;
		// display information
		System.out.println(sensorInfo.onlyParameters());
		// run GA
		new GeneticAlgorithm(this).runGA();
		// save results
		savePictures(mp + "_" + cp + "_" + ps);
	}

	private void savePictures(final String folderName) {
		final File folder = new File("tests/" + folderName);
		folder.mkdir();
		final File[] files = new File(".").listFiles(new JPGFileFilter());
		for (final File file : files) {
			file.renameTo(new File("tests/" + folderName + "/" + file.getName()));
		}
		new File("tests/log.txt").renameTo(new File("tests/" + folderName + "/log.txt"));
		redirectOutputToFile();
	}

	private void startTest() {
		redirectOutputToFile();
		final float[] mutationProbability = { 0, 0.05f, 0.1f };
		final float[] crossoverProbability = { 0.5f, 0.75f, 1f };
		final int[] populationSize = { 50, 100, 200 };
		float mp, cp;
		int ps;
		for (final float element : mutationProbability) {
			mp = element;
			for (final float element2 : crossoverProbability) {
				cp = element2;
				for (final int element3 : populationSize) {
					ps = element3;
					runGA(mp, cp, ps);
				}
			}
		}
	}

}
