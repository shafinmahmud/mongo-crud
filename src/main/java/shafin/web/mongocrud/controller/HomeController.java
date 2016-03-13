package shafin.web.mongocrud.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shafin.web.mongocrud.dto.NewsDto;
import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.model.NewsPhoto;
import shafin.web.mongocrud.service.CrawlerService;
import shafin.web.mongocrud.service.NewsService;

@Controller
public class HomeController {

	@Autowired
	NewsService newsService;
	
	@Autowired
	CrawlerService crawlerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "redirect:/news/all?page=0&size=10";
	}
	
	@RequestMapping(value = "/news/search", params = {"q"}, method = RequestMethod.GET)
	public String search(@RequestParam("q") String query, Model model) {
		
		ArrayList<News> all = newsService.getNewsbySearch(query);
		ArrayList<NewsDto> dtos = new ArrayList<>();
		if (!all.isEmpty()) {
			for (News news : all) {
				dtos.add(convertToNewsDto(news));
			}
			model.addAttribute("newsList", dtos);
		}

		return "home";
	}

	@RequestMapping(value = "/pagableblabla", method = RequestMethod.GET)
    public String blog(Model uiModel, Pageable pageable) {
		
        PageWrapper<News> page = new PageWrapper<News>
        	(newsService.getNewsbyPageable(pageable), "/news");
        uiModel.addAttribute("news", page);
        
        return "home";
    }

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

	@RequestMapping(value = "/news", params = { "id" }, method = RequestMethod.GET)
	public String news(@RequestParam("id") String id, Model model) {
		model.addAttribute("news", newsService.getNews(id));
		return "news";
	}

	@RequestMapping(value = "/news/edit", params = { "id" }, method = RequestMethod.GET)
	public String showEdit(@RequestParam("id") String id, Model model) {
		NewsDto dto = convertToNewsDto(newsService.getNews(id));
		// System.out.println(dto.toString());
		model.addAttribute("newsUpdate", dto);
		return "edit";
	}

	@RequestMapping(value = "/news/update", method = RequestMethod.POST)
	public String processNewsUpdate(@ModelAttribute(value = "newsUpdate") NewsDto newsUpdate) {
		// System.out.println(newsUpdate.toString());
		return "redirect:/news?id=" + newsService.updateNews(convertToNews(newsUpdate)).getId();
	}

	@RequestMapping(value = "/news/add", method = RequestMethod.GET)
	public String showAdd(Model model) {
		NewsDto addNews = new NewsDto();
		ArrayList<NewsPhoto> photos = new ArrayList<>();

		NewsPhoto photo = new NewsPhoto();
		photo.setImageUrl("");
		photo.setImageCaption("");
		photos.add(photo);

		addNews.setNewsPhotos(photos);
		model.addAttribute("newsAdd", addNews);
		return "add";
	}

	@RequestMapping(value = "/news/insert", method = RequestMethod.POST, params="action=save")
	public String processNewsAdd(@ModelAttribute(value = "newsAdd") NewsDto newsAdd) {
		// System.out.println(newsAdd.toString());
		return "redirect:/news?id=" + newsService.insertNews(convertToNews(newsAdd)).getId();
	}
	
	@RequestMapping(value = "/news/insert", method = RequestMethod.POST, params="action=crawl")
	public String processNewsCrawl(@ModelAttribute(value = "newsAdd") NewsDto newsAdd, Model model) {
		
		News news = crawlerService.parseNewsUrl(newsAdd.getNewsUrl());
		
		newsAdd.setSource(news.getSource());
		newsAdd.setSourceLogo(news.getSourceLogo());
		
		newsAdd.setCategoryTags(news.getCategoryTags(), ";");
		newsAdd.setTitle(news.getTitle());
		newsAdd.setPostTime(news.getPostTime());
		newsAdd.setAuthor(news.getAuthor());
		
		newsAdd.setArticle(news.getArticle());
		
		newsAdd.setNewsPhotos(news.getNewsPhotos());
		
		/*NewsDto addNews = new NewsDto();
		ArrayList<NewsPhoto> photos = new ArrayList<>();

		NewsPhoto photo = new NewsPhoto();
		photo.setImageUrl("");
		photo.setImageCaption("");
		photos.add(photo);

		addNews.setNewsPhotos(photos);*/
		model.addAttribute("newsAdd", newsAdd);
		return "add";
	}

	@RequestMapping(value = "/news/delete", params = { "id" }, method = RequestMethod.GET)
	public String deleteNews(@RequestParam("id") String id) {
		newsService.deleteNews(id);
		return "redirect:/";
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

	private News convertToNews(NewsDto dto) {
		News news = new News();
		news.setId(dto.getId());
		news.setSource(dto.getSource());
		news.setSourceLogo(dto.getSourceLogo());
		news.setNewsUrl(dto.getNewsUrl());
		news.setCategoryTags(dto.getCategoryTags(), ";");
		news.setTitle(dto.getTitle());
		news.setArticle(dto.getArticle());
		news.setAuthor(dto.getAuthor());
		news.setPostTime(dto.getPostTime());
		news.setKeywords(dto.getKeywords(), ";");
		news.setNewsPhotos(dto.getNewsPhotos());
		return news;
	}

}
