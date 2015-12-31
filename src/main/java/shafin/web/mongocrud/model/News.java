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
	private String sourceLogo;
	@Field
	private String newsUrl;
	@Field
    private ArrayList<String> categoryTags;
	@Field
	private ArrayList<String> keywords;
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

	public String getSourceLogo() {
		return sourceLogo;
	}

	public void setSourceLogo(String sourceLogo) {
		this.sourceLogo = sourceLogo;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArrayList<String> getCategoryTags() {
		return categoryTags;
	}

	public void setCategoryTags(ArrayList<String> categoryTags) {
		this.categoryTags = categoryTags;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
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
