package shafin.web.mongocrud.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.repository.NewsRepository;

@Service("newsService")
public class NewsService {

	@Autowired
	NewsRepository newsRepository;
	
	public News postNews(News news){	
			return newsRepository.save(news);
	}
	
	public Iterator<News> getAllNews(){
		return newsRepository.findAll().iterator();
	}
	
}
