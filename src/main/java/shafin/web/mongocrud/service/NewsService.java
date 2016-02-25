package shafin.web.mongocrud.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	public Iterator<News> getNewsbyIterator(){
		return newsRepository.findAll().iterator();
	}
	
	public ArrayList<News> getNewsbyArrayList(){
		return makeCollection(newsRepository.findAll());
	}
	
	public ArrayList<News> getNewsbyPagination(int p, int s){
		Page<News> newsPage = newsRepository.findAll(new PageRequest(p, s));
		ArrayList<News> newsList = new ArrayList<>();
		for(News news: newsPage){
			newsList.add(news);
		}
		return newsList;
	}

	public News insertNews(News news){
		
		return newsRepository.save(news);
	}
	
	public News getNews(String _id){
		return newsRepository.findOne(_id);
	}
	
	public News updateNews(News news){
		return newsRepository.save(news);
	}
	
	public void deleteNews(String _id){
		newsRepository.delete(getNews(_id));
	}
	
	public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
	    ArrayList<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    return list;
	}
}
