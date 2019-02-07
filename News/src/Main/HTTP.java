package Main;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTP {
	
	public HTTP() {
		
	}

	public String executeGET(String targetURL, String apiKey) {
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url(targetURL)
		  .get()
		  .addHeader("X-Api-Key", apiKey)
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response response;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
		}
		return "";
	}
	
	public Image getImageFromUrl(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (MalformedURLException e) {
			try {
				return ImageIO.read(new URL( "https://upload.wikimedia.org/wikipedia/en/d/d1/Image_not_available.png"));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		} catch (IOException e) {
			try {
				return ImageIO.read(new URL( "https://upload.wikimedia.org/wikipedia/en/d/d1/Image_not_available.png"));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}
}
