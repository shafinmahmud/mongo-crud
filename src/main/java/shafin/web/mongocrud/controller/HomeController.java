package shafin.web.mongocrud.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shafin.web.mongocrud.model.News;
import shafin.web.mongocrud.service.CrawlerService;
import shafin.web.mongocrud.service.NewsService;

@Controller
public class HomeController {

	@Autowired
	NewsService newsService;

	@Autowired
	CrawlerService crawlerService;

	private static final int PRODUCT_LIST_PAGE_SIZE = 10;
	private static final String PRODUCT_LIST_BASEURL = "/news/all/page/";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "redirect:/news/all/page/1";
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/news/all/page/{pageNumber}", method = RequestMethod.GET)
	public String pagedNewsList(HttpServletRequest request, @PathVariable Integer pageNumber, Model model) {

		PagedListHolder<News> pagedListHolder = (PagedListHolder<News>) request.getSession()
				.getAttribute("newsList");

		if (pagedListHolder == null) {
			pagedListHolder = new PagedListHolder<News>(newsService.getNewsbyList());
			pagedListHolder.setPageSize(PRODUCT_LIST_PAGE_SIZE);

		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
				pagedListHolder.setPage(goToPage);
			}
		}

		request.getSession().setAttribute("newsList", pagedListHolder);

		model.addAttribute("pager", currentPage(pagedListHolder));
		model.addAttribute("newsList", pagedListHolder.getPageList());

		return "home";
	}

	

	private Pager currentPage(PagedListHolder<?> pagedListHolder) {

		int currentIndex = pagedListHolder.getPage() + 1;
		int beginIndex = Math.max(1, currentIndex - PRODUCT_LIST_PAGE_SIZE);
		int endIndex = Math.min(beginIndex + 5, pagedListHolder.getPageCount());
		int totalPageCount = pagedListHolder.getPageCount();
		int totalItems = pagedListHolder.getNrOfElements();
		String baseUrl = PRODUCT_LIST_BASEURL;

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
