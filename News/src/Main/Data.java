package Main;

public class Data {

	private String apiKey;
	private String lang;
	private boolean darkMode;
	private String location= "settings.txt";
	
	public Data() {
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public boolean isDarkMode() {
		return darkMode;
	}

	public void setDarkMode(boolean darkMode) {
		this.darkMode = darkMode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
