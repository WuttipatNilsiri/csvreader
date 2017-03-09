package ku.util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;


public class CSVReader implements Iterator {
	InputStream in;
	String inputFile;
	BufferedReader br;
	String delimiter = ",";
	String line = null;
	
	/**
	 * initial with source
	 * @param inputFile source input file
	 */
	public CSVReader(String inputFile){
		try {
			in = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		br = new BufferedReader( new InputStreamReader(in) );
	}
	/**
	 * initial with InputStream
	 * @param in InputStream
	 * @throws FileNotFoundException if file not found
	 */
	public CSVReader(InputStream in) throws FileNotFoundException{
		this.in = in;
		br = new BufferedReader( new InputStreamReader(in) );
		
	}
	@Override
	/**
	 * test input there next line
	 * @return if no next line of input 
	 */
	public boolean hasNext() {
		try {
			line = br.readLine();
			return line != null;
		} catch (IOException e) {
			return false;
		}
	}
	/**
	 * split line and input to array of string
	 * @return array of string
	 */
	@Override
	public String[] next() {
			String[] strAr = null;
			try {
				if(line == null){
					line = br.readLine();
				}
				strAr = line.split(delimiter);
				for (String x : strAr){
					if (x == null){
						x = "";
					}
				}
			} 
			catch (IOException e) {
				return null;
			}
		return strAr;
	}
	/**
	 * close input stream
	 * @throws IOException
	 */
	public void close() throws IOException{
		in.close();
	}
	/**
	 * set delimiter
	 * @param delimiter spliter
	 */
	public void setDelimiter(String delimiter){
		this.delimiter = delimiter;
	}
	/**
	 * get delimiter
	 * @return delimiter
	 */
	public String getDelimiter(){
		return delimiter;
	}
	
}
