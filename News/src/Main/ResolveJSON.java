package Main;

import java.awt.Image;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResolveJSON {
	
	private Control ctrl;
	private News[] news;	
	
	public ResolveJSON(Control ctrl) {
		this.ctrl= ctrl;
	}

	public News[] resolve(String json) {
		
		json= json.replace("null", "not available");
		
			
		JSONObject obj = new JSONObject(json);
		
		JSONArray jsonArray = obj.getJSONArray("articles");
		JSONObject arr;
		
		news= new News[jsonArray.length()];
		
		for (int i = 0; i < jsonArray.length(); i++) {
			
			arr= jsonArray.getJSONObject(i);
			
			news[i]= new News(arr.getJSONObject("source").getString("id"), arr.getJSONObject("source").getString("name"),
					arr.getString("author"), arr.getString("title"), arr.getString("description"), arr.getString("url"),
					arr.getString("urlToImage"), arr.getString("publishedAt"), arr.getString("content"), replaceImgURL(arr.getString("urlToImage")));
			
			
		}

		return news;
	}
	
	private Image replaceImgURL(String news) {
		if(news.equals("not available"))
			news= "https://upload.wikimedia.org/wikipedia/en/d/d1/Image_not_available.png";
		
		return ctrl.getImageFromUrl(news);
	}

}
