package cga;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import cga.data.Sensor;
import cga.ga.GANotifier;
import cga.ga.GeneticAlgorithm;
import cga.ui.AreaPanel;
import cga.ui.ParameterPanel;
import cga.utility.SensorUtility;

public class CoverageFrame extends JFrame implements GANotifier {
	private static final long serialVersionUID = 1L;

	public static void main(final String[] args) {
		new CoverageFrame();
	}

	private AreaPanel pnlArea;

	private ParameterPanel pnlParameter;

	public CoverageFrame() {
		initialize();
	}

	public void calculateArea() {
		JOptionPane.showMessageDialog(this,
				"Total Covered Area:" + SensorUtility.calculateArea(pnlArea.getAreaAsImage(), null, false));
	}

	private AreaPanel createPnlArea() {
		if (pnlArea == null) {
			pnlArea = new AreaPanel();
		}
		return pnlArea;
	}

	private ParameterPanel createPnlParameter() {
		if (pnlParameter == null) {
			pnlParameter = new ParameterPanel(this);
		}
		return pnlParameter;
	}

	@Override
	public void finished() {
		pnlParameter.enableRunButton();
	}

	private void initialize() {
		this.setSize(615, 430);

		final JSplitPane jsp = new JSplitPane();

		jsp.setLeftComponent(createPnlParameter());
		jsp.setRightComponent(createPnlArea());
		jsp.setDividerSize(0);
		jsp.setDividerLocation(201);

		this.setContentPane(jsp);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void load() {
		pnlArea.load();
	}

	public void loadSensors(final Vector<Sensor> sensors) {
		pnlArea.loadSensors(sensors);
	}

	@Override
	public void notify(final String message) {
		pnlParameter.info(message);
		pnlArea.repaint();
	}

	private void runGA() {
		new GeneticAlgorithm(this).runGA();
	}

	public void startGAThread() {
		new Thread() {
			@Override
			public void run() {
				runGA();
			}
		}.start();
	}
}
