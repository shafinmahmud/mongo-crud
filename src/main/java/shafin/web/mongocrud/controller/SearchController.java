package shafin.web.mongocrud.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shafin.web.mongocrud.dto.NewsDto;
import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.service.CrawlerService;
import shafin.web.mongocrud.service.NewsService;

@Controller
public class SearchController {

	@Autowired
	NewsService newsService;

	@Autowired
	CrawlerService crawlerService;

	private static final int PRODUCT_LIST_PAGE_SIZE = 10;

	@RequestMapping(value = "/news/search", params = { "q" }, method = RequestMethod.GET)
	public String home(@RequestParam("q") String q, Model model) {
		return "redirect:/news/search/" + q + "/page/1";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/news/search/{q}/page/{pageNumber}", method = RequestMethod.GET)
	public String pagedNewsList(HttpServletRequest request, @PathVariable String q, @PathVariable Integer pageNumber,
			Model model) {

		ArrayList<News> all = newsService.getNewsbySearch(q);
		ArrayList<NewsDto> dtos = new ArrayList<>();
		if (!all.isEmpty()) {
			for (News news : all) {
				dtos.add(convertToNewsDto(news));
			}
		}

		PagedListHolder<NewsDto> pagedListHolder = (PagedListHolder<NewsDto>) request.getSession()
				.getAttribute("newsSearchList");

		if (pagedListHolder == null) {
			pagedListHolder = new PagedListHolder<NewsDto>(dtos);
			pagedListHolder.setPageSize(PRODUCT_LIST_PAGE_SIZE);

		} else {
			if(request.getSession().getAttribute("q").equals(q)){
				final int goToPage = pageNumber - 1;
				if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
					pagedListHolder.setPage(goToPage);
				}
			}else{
				pagedListHolder = new PagedListHolder<NewsDto>(dtos);
				pagedListHolder.setPageSize(PRODUCT_LIST_PAGE_SIZE);
			}
			
		}

		request.getSession().setAttribute("newsSearchList", pagedListHolder);
		request.getSession().setAttribute("q",q);

		model.addAttribute("q", q);
		model.addAttribute("pager", currentPage(pagedListHolder, "/news/search/" + q + "/page/"));
		model.addAttribute("newsSearchList", pagedListHolder.getPageList());

		return "search";
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

	private Pager currentPage(PagedListHolder<?> pagedListHolder, String baseUri) {

		int currentIndex = pagedListHolder.getPage() + 1;
		int beginIndex = Math.max(1, currentIndex - PRODUCT_LIST_PAGE_SIZE);
		int endIndex = Math.min(beginIndex + 5, pagedListHolder.getPageCount());
		int totalPageCount = pagedListHolder.getPageCount();
		int totalItems = pagedListHolder.getNrOfElements();
		String baseUrl = baseUri;

		Pager pager = new Pager();
		pager.setBeginIndex(beginIndex);
		pager.setEndIndex(endIndex);
		pager.setCurrentIndex(currentIndex);
		pager.setTotalPageCount(totalPageCount);
		pager.setTotalItems(totalItems);
		pager.setBaseUrl(baseUrl);
		return pager;
	}

}
