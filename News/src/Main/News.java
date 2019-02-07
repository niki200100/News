package Main;

import java.awt.Image;

public class News {

	private String id;
	private String name;
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImg;
	private String publishedAt;
	private String content;
	private Image img;
	
	public News(String id, String name, String author, String title, String description,
				String url, String urlToImg, String publishedAt, String content, Image img) {
		
		this.id= id;
		this.name= name;
		this.author= author;
		this.title= title;
		this.description= description;
		this.url= url;
		this.urlToImg= urlToImg;
		this.publishedAt= publishedAt;
		this.content= content;
		this.img= img;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAuthor() {
		return author;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getUrl() {
		return url;
	}
	public String getUrlToImg() {
		return urlToImg;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public String getContent() {
		return content;
	}
	public void setUrlToImg(String urlToImg) {
		this.urlToImg= urlToImg;
	}
	public Image getImage() {
		return img;
	}
}