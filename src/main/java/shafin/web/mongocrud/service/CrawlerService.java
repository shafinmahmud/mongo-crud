package shafin.web.mongocrud.service;

import org.springframework.stereotype.Service;

import shafin.web.mongocrud.crawler.NewsCrawler;
import shafin.web.mongocrud.model.News;

@Service("crawlerService")
public class CrawlerService {

	public News parseNewsUrl(String URL){
		
		NewsCrawler newsCrawler = new NewsCrawler(URL);
		return newsCrawler.parseNews();
	}
}
