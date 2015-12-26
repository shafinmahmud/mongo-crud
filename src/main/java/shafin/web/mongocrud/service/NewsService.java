package shafin.web.mongocrud.service;

import java.util.ArrayList;
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
	
	public ArrayList<News> getAllNewsList(){
		return makeCollection(newsRepository.findAll());
	}

	public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
	    ArrayList<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    return list;
	}
}
