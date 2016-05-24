package shafin.web.mongocrud.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Connection.Response;

import shafin.web.mongocrud.crawler.parser.DailystarParserImpl;
import shafin.web.mongocrud.crawler.parser.JsoupParser;
import shafin.web.mongocrud.crawler.parser.NewsParser;
import shafin.web.mongocrud.crawler.parser.ProthomaloParserImpl;
import shafin.web.mongocrud.model.News;
import shafin.webmongocrud.exceptions.DomainSupportException;

public class NewsCrawler {

	JsoupParser jsoupParser;

	private String domain;
	private String url;

	private NewsParser newsParser;

	public NewsCrawler() {
		this.jsoupParser = new JsoupParser();
	}

	public NewsCrawler(String url) {
		try {
			this.jsoupParser = new JsoupParser();
			setUrl(url);
			setDomain(findDomain(url));
			setNewsParser(getDomain());
			
		} catch (MalformedURLException e) {
			throw new DomainSupportException(e.toString());
		}

	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public NewsParser getNewsParser() {
		return newsParser;
	}

	public void setNewsParser(NewsParser newsParser) {
		this.newsParser = newsParser;
	}

	public void setNewsParser(String domain) {
		if (domain.contains("prothom-alo.com")) {
			this.newsParser = new ProthomaloParserImpl();

		} else if (domain.contains("thedailystar.net")) {
			this.newsParser = new DailystarParserImpl();

		} else {
			throw new DomainSupportException(domain + " is not a supported domain for crawling");
		}
	}

	private String findDomain(String url) throws MalformedURLException {
		URL u = new URL(url);
		return u.getHost();
	}

	public News parseNews() {

		try {
			Response linkResponse = jsoupParser.getResponseFromGetRequest(url);
			this.newsParser.setPageHtml(linkResponse.parse().html());
			
			News news = new News();
			news.setArticle(newsParser.parseArticle());
			news.setTitle(newsParser.parseTitle());
			news.setAuthor(newsParser.parseAuthor());
			news.setPostTime(newsParser.parsePostTime());
			news.setCategoryTags(newsParser.parseCateogryTags());
			news.setSource(newsParser.parseSource());
			news.setSourceLogo(newsParser.parseSourceLogo());
			
			news.setNewsPhotos(newsParser.parseNewsPhotos());
			
			return news;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws MalformedURLException {

		String url = "http://www.thedailystar.net/sports/tamim-bats-his-way-the-top-790075";

		NewsCrawler newsCrawler = new NewsCrawler(url);

		News news = newsCrawler.parseNews();
		try {
			ArrayList<String> tags = news.getCategoryTags();
			System.out.println(tags.toString());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}
}
