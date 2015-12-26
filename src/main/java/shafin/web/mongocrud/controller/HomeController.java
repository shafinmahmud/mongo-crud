package shafin.web.mongocrud.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.service.NewsService;

@Controller
public class HomeController {
	
	@Autowired
	NewsService newsService;
	
	@RequestMapping("/")
	public String home(){
		
		Iterator<News> all = newsService.getAllNews();
		while(all.hasNext()){
			System.out.println(all.next());
		}
		return "home";	
	}
}

