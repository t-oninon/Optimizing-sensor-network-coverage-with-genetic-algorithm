package cga.utility;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class JPGFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File file, String name) {
		return Pattern.matches(".*\\.jpg", name);
	}
}
