package shafin.web.mongocrud.model;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "news")
public class News {

	@Field
	private String id;
	@Field
	private String source;
	@Field
	private String category;
	@Field
	private String title;
	@Field
	private String writter;
	@Field
	private String postTime;
	@Field
	private String article;
	@Field
	private ArrayList<NewsPhoto> newsPhoto;

	public News() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWritter() {
		return writter;
	}

	public void setWritter(String writter) {
		this.writter = writter;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public ArrayList<NewsPhoto> getNewsPhoto() {
		return newsPhoto;
	}

	public void setNewsPhoto(ArrayList<NewsPhoto> newsPhoto) {
		this.newsPhoto = newsPhoto;
	}

}
