package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Filereader {

	public Filereader() {
		
	}
	
	public void saveFile(String location, String[] settings) throws FileNotFoundException, UnsupportedEncodingException {
		
		 File file = new File(location);
	        if(file.delete()){}
	        
	        PrintWriter writer = new PrintWriter(location, "UTF-8");
			writer.println("ApiKey NewsApi: \""+settings[0]+"\"");
			writer.println("Language: \""+settings[1]+"\"");
			writer.println("Darkmode: \""+settings[2]+"\"");
			writer.close();
	}
	
	public String[] readFile(String location) throws IOException {
		
		String[] settings = new String[3];
		int i=0;
		
		String fileName = location;
		File file = new File(fileName);
		
		if (file.createNewFile()) {
			createFile(location);
        }
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null){
			String []splitterString=line.split("\"");
			settings[i]= splitterString[1];
			i++;
		}
		br.close();
		return settings;
	}
	
	private void createFile(String location) throws FileNotFoundException, UnsupportedEncodingException {
		
		PrintWriter writer = new PrintWriter(location, "UTF-8");
		writer.println("ApiKey NewsApi: \"null\"");
		writer.println("Language: \"de\"");
		writer.println("Darkmode: \"true\"");
		writer.close();
	}
}
