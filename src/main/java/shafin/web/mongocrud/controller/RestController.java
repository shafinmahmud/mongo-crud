package shafin.web.mongocrud.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shafin.web.mongocrud.dto.NewsDto;
import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.service.CrawlerService;
import shafin.web.mongocrud.service.NewsService;

public class RestController {

	@Autowired
	NewsService newsService;

	@Autowired
	CrawlerService crawlerService;
	
	@RequestMapping(value = "/news/all", params = { "page", "size" }, method = RequestMethod.GET)
	public String showNewsTable(@RequestParam("page") int page, @RequestParam("size") int size, Model model) {

		ArrayList<News> all = newsService.getNewsbyPagination(page, size);
		ArrayList<NewsDto> dtos = new ArrayList<>();
		if (!all.isEmpty()) {
			for (News news : all) {
				dtos.add(convertToNewsDto(news));
			}
			model.addAttribute("newsList", dtos);
		}

		return "home";
	}
	
	private NewsDto convertToNewsDto(News news) {
		NewsDto dto = new NewsDto();
		dto.setId(news.getId());
		dto.setSource(news.getSource());
		dto.setSourceLogo(news.getSourceLogo());
		dto.setNewsUrl(news.getNewsUrl());
		dto.setCategoryTags(news.getCategoryTags(), ";");
		dto.setTitle(news.getTitle());
		dto.setArticle(news.getArticle());
		dto.setAuthor(news.getAuthor());
		dto.setPostTime(news.getPostTime());
		dto.setKeywords(news.getKeywords(), ";");
		dto.setNewsPhotos(news.getNewsPhotos());
		return dto;
	}
}
