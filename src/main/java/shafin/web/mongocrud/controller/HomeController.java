package shafin.web.mongocrud.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shafin.web.mongocrud.dto.NewsDto;
import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.service.NewsService;

@Controller
public class HomeController {

	@Autowired
	NewsService newsService;

	@ModelAttribute("newsList")
	public ArrayList<NewsDto> prepareNewsListModel() {
		ArrayList<News> all = newsService.getAllNewsList();
		ArrayList<NewsDto> dtos = new ArrayList<>();
		if (!all.isEmpty()) {
			for (News news : all) {		
				dtos.add(convertToDto(news));
			}
			return dtos;
		}
		return dtos;
	}
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		prepareNewsListModel();
		return "home";
	}
	
	@RequestMapping(value = "/news", params = { "id"}, method = RequestMethod.GET)
	public String news(@RequestParam("id") String id, Model model) {
		model.addAttribute("news", newsService.getNews(id));
		return "news";
	}
	
	@RequestMapping(value = "/news/edit", params = {"id"}, method = RequestMethod.GET)
	public String showEdit(@RequestParam("id") String id, Model model) {
		
		model.addAttribute("newsUpdate", convertToDto(newsService.getNews(id)));
		return "edit";
	}
	
	@RequestMapping(value = "/news/update", method = RequestMethod.POST)
	public String processNewsUpdate(@ModelAttribute(value="newsUpdate") NewsDto newsUpdate) {
		System.out.println(newsUpdate.toString());
		return "/news?id="+newsUpdate.getId();
	}
	
	private NewsDto convertToDto(News news){
		NewsDto dto = new NewsDto();
		dto.setId(news.getId());
		dto.setSource(news.getSource());
		dto.setSourceLogo(news.getSourceLogo());
		dto.setNewsUrl(news.getNewsUrl());
		dto.setCategoryTags(news.getCategoryTags(),";");
		dto.setTitle(news.getTitle());
		dto.setArticle(news.getArticle());
		dto.setAuthor(news.getAuthor());
		dto.setPostTime(news.getPostTime());
		dto.setKeywords(news.getKeywords(),";");
		dto.setNewsPhotos(news.getNewsPhotos());
		return dto;
	}
	
}
