package shafin.web.mongocrud.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.service.NewsService;

@Controller
public class HomeController {
	
	@Autowired
	NewsService newsService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model){
		
		Iterator<News> all = newsService.getAllNews();
		while(all.hasNext()){
			System.out.println(all.next().getTitle());
		}
		return "home";	
	}
}

