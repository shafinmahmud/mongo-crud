package shafin.web.mongocrud.dto;

import java.util.ArrayList;

import shafin.web.mongocrud.model.NewsPhoto;

public class NewsDto {

	private String id;
	private String source;
	private String sourceLogo;
	private String newsUrl;
	private String categoryTags;
	private String keywords;
	private String title;
	private String author;
	private String postTime;
	private String article;
	private ArrayList<NewsPhoto> newsPhotos;

	public NewsDto() {
	}

	public NewsDto(String id, String source, String sourceLogo, String newsUrl, String categoryTags, String keywords,
			String title, String author, String postTime, String article, ArrayList<NewsPhoto> newsPhotos) {
		this.id = id;
		this.source = source;
		this.sourceLogo = sourceLogo;
		this.newsUrl = newsUrl;
		this.categoryTags = categoryTags;
		this.keywords = keywords;
		this.title = title;
		this.author = author;
		this.postTime = postTime;
		this.article = article;
		this.newsPhotos = newsPhotos;
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

	public String getCategoryTags() {
		return categoryTags;
	}

	public void setCategoryTags(String cat) {
		this.categoryTags = cat;
	}

	public void setCategoryTags(ArrayList<String> categoryTags, String delimeter) {
		this.categoryTags = convertListToString(categoryTags, delimeter);
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords, String delimeter) {
		this.keywords = convertListToString(keywords, delimeter);
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public ArrayList<NewsPhoto> getNewsPhotos() {
		return newsPhotos;
	}

	public void setNewsPhotos(ArrayList<NewsPhoto> newsPhotos) {
		this.newsPhotos = newsPhotos;
	}

	private String convertListToString(ArrayList<String> stringList, String delimeter) {
		String derivedString = "";
		if (stringList != null) {
			for (String s : stringList) {
				derivedString = derivedString.concat(s).concat(delimeter).concat(" ");
			}
		}
		return derivedString;
	}

	@Override
	public String toString() {
		return "NewsDto [id=" + id + ", source=" + source + ", sourceLogo=" + sourceLogo + ", newsUrl=" + newsUrl
				+ ", categoryTags=" + categoryTags + ", keywords=" + keywords + ", title=" + title + ", author="
				+ author + ", postTime=" + postTime + ", article=" + article + ", newsPhotos=" + newsPhotos + "]";
	}

}
