package viewmodel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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
		Files.write(Paths.get(media.getName()), media.getStringData().trim().getBytes(), StandardOpenOption.APPEND);
		return file;
	}

	private static final String NEW_LINE_SEPARATOR = "\n";

	public static File write(String name, String[] header, List<List<String>> content) throws IOException {
		File file = new File(name);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			fileWriter = new FileWriter(file);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(header);
			for (List<String> row : content) {
				csvFilePrinter.printRecord(row.toArray());
			}
			return file;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}
}
