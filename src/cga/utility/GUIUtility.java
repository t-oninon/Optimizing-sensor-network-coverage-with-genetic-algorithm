package cga.utility;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUIUtility {

	private static JFileChooser jfc;

	private static JFileChooser getFileChooser() {
		if (jfc == null) {
			jfc = new JFileChooser();
		}
		return jfc;
	}

	public static File selectFile(final String extension) {
		final JFileChooser _jfc = getFileChooser();
		_jfc.setFileFilter(new FileNameExtensionFilter("", extension));
		if (_jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return _jfc.getSelectedFile();
		return null;
	}
}
