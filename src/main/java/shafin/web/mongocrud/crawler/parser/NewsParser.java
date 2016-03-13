package shafin.web.mongocrud.crawler.parser;

import java.io.IOException;
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

	public  abstract String parseSource() throws IOException;

	public  abstract String parseSourceLogo() throws IOException;

	public  abstract String parseNewsUrl() throws IOException;

	public  abstract ArrayList<String> parseCateogryTags()throws IOException;

	public  abstract ArrayList<String> parseKeyWords();

	public  abstract String parseTitle() throws IOException;

	public  abstract String parseAuthor() throws IOException;

	public  abstract String parsePostTime() throws IOException;

	public  abstract String parseArticle() throws IOException;

	public  abstract ArrayList<NewsPhoto> parseNewsPhotos() throws IOException;
}
