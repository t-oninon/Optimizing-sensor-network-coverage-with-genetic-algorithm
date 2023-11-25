package cga.utility;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cga.data.Hotspot;
import cga.data.Sensor;
import cga.data.SensorInfo;

public class SensorXMLReader {

	private static int getAttributeValue(final Element element, final String attName) {
		return Integer.parseInt(element.getAttribute(attName));
	}

	public static void readSensorInfo(final File sensorFile) {
		SensorInfo.getInstance().reset();
		final SensorInfo si = SensorInfo.getInstance();
		try {
			final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			final Document doc = builder.parse(sensorFile);

			final Element sensorProblem = (Element) doc.getElementsByTagName("SensorProblem").item(0);

			si.parameter.setAreaWidth(getAttributeValue(sensorProblem, "areaWidth"));
			si.parameter.setAreaHeight(getAttributeValue(sensorProblem, "areaHeight"));

			// read sensors
			si.parameter.sensorRadius = getAttributeValue((Element) doc.getElementsByTagName("Sensors").item(0),
					"radius");

			final NodeList sensors = doc.getElementsByTagName("Sensor");
			for (int i = 0; i < sensors.getLength(); i++) {
				final Element element = (Element) sensors.item(i);
				final Sensor sensor = new Sensor(i, getAttributeValue(element, "x"), getAttributeValue(element, "y"));
				si.addSensor(sensor);
			}

			// Hotspots
			si.parameter.kCovered = getAttributeValue((Element) doc.getElementsByTagName("Hotspots").item(0),
					"kCovered");

			final NodeList hotspots = doc.getElementsByTagName("Hotspot");
			for (int i = 0; i < hotspots.getLength(); i++) {
				final Element element = (Element) hotspots.item(i);
				final Hotspot hs = new Hotspot(i, getAttributeValue(element, "x"), getAttributeValue(element, "y"));
				si.addHotspot(hs);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
