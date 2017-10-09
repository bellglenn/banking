package viewmodel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.util.media.Media;

import com.opencsv.CSVReader;

public class FileUtil {

	public static List<String> readFile(String filename) {
		List<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			reader.close();
			return records;
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}
	}

	public static List<String[]> readCsvFile(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		List<String[]> lines = reader.readAll();
		reader.close();
		return lines;
	}

	public static File writeFile(Media media) throws IOException {
		File file = new File(media.getName());
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		Files.write(Paths.get(media.getName()), media.getStringData().trim().getBytes(),
					StandardOpenOption.APPEND);
		return file;
	}
}
