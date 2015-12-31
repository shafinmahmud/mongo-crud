package shafin.web.mongocrud.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.service.NewsService;

@Controller
public class HomeController {

	@Autowired
	NewsService newsService;

	@ModelAttribute("newsList")
	public ArrayList<News> prepareNewsDataModel() {
		ArrayList<News> all = newsService.getAllNewsList();
		if (!all.isEmpty()) {
			//System.out.println(all.get(1).getArticle());
			return all;
		}
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		prepareNewsDataModel();
		return "home";
	}
}
