package shafin.web.mongocrud.crawler.parser;

import java.util.ArrayList;

import shafin.web.mongocrud.model.NewsPhoto;

public abstract class NewsParser {
	
	private String pageHtml;
	
	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public  abstract String parseSource();

	public  abstract String parseSourceLogo();

	public  abstract String parseNewsUrl();

	public  abstract ArrayList<String> parseCateogryTags();

	public  abstract ArrayList<String> parseKeyWords();

	public  abstract String parseTitle();

	public  abstract String parseAuthor();

	public  abstract String parsePostTime();

	public  abstract String parseArticle();

	public  abstract ArrayList<NewsPhoto> parseNewsPhotos();
}
