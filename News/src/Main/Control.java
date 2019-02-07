package Main;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Control {
	
	private Filereader filereader;
	private Data data;
	private HTTP http;
	private ResolveJSON resolvejson;

	public Control(String[] args) {
		
		this.filereader= new Filereader();
		this.data= new Data();
		this.http= new HTTP();
		this.resolvejson= new ResolveJSON(this);
		
		checkArgs(args);
		loadSettings();
	}
	
	public String[] getSettings() {
		
		return new String[] {data.getLang(), data.isDarkMode()+"", data.getApiKey()};
	}
	
	public News[] callAPI(String call, String search) {
		String response= getHTTP(call, search);
		
		if(response!="") {
			News[] news= resolveResponse(response);
			
			return news;
		}else
			return null;
	}
	
	public Image getImageFromUrl(String url) {
		return http.getImageFromUrl(url);
	}
	
	private void checkArgs(String[] args) {
		if(args.length>=1) {
			if(!args[0].equals("") && !args[0].isEmpty()) {
				this.data.setLocation(args[0]);
			}
		}
	}
	
	private News[] resolveResponse(String json) {
		
		return resolvejson.resolve(json);
	}
	
	private String getHTTP(String news, String search) {
		
		switch(news) {
			case "headlines": return this.http.executeGET("https://newsapi.org/v2/top-headlines?country="+this.data.getLang(), this.data.getApiKey());
			case "everything": return this.http.executeGET("https://newsapi.org/v2/everything?q="+search, this.data.getApiKey());
			case "sources": return this.http.executeGET("https://newsapi.org/v2/sources?language="+this.data.getLang(), this.data.getApiKey());
			case "sourcesall": return this.http.executeGET("https://newsapi.org/v2/sources", this.data.getApiKey());
			default: return "";
		}
	}
	
	public void saveData(String apiKey,	String lang, boolean darkMode) {
		this.data.setApiKey(apiKey);
		this.data.setLang(lang);
		this.data.setDarkMode(darkMode);
		
		saveSettings();
	}
	
	public void saveSettings() {
		try {
			this.filereader.saveFile(data.getLocation(), new String[] {data.getApiKey()+"", data.getLang()+"", data.isDarkMode()+""});
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadSettings() {
		String location= data.getLocation();
		String[] settings = new String[3];
		try {
			settings= filereader.readFile(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.data.setApiKey(settings[0]);
		this.data.setLang(settings[1]);
		this.data.setDarkMode(Boolean.valueOf(settings[2]));
	}
}
