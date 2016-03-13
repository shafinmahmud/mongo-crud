package shafin.web.mongocrud.crawler.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.nodes.Element;

import shafin.web.mongocrud.model.NewsPhoto;

public class DailystarParserImpl extends NewsParser {

	public DailystarParserImpl() {
	}

	public DailystarParserImpl(String pageHtml) {
		super.setPageHtml(pageHtml);
	}

	@Override
	public String parseSource() {
		return "The Daily Star";
	}

	@Override
	public String parseSourceLogo() {
		return "http://www.thedailystar.net/sites/all/themes/tds/logo.png";
	}

	@Override
	public String parseNewsUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> parseCateogryTags() throws IOException {
		JsoupParser jsoupParser = new JsoupParser();
		Iterator<Element> nodes = jsoupParser.parseDataIteratortFromHtml(getPageHtml(),
				"#block-system-main > div.full-width.detailed-page > div.container.detailed-header > "
						+ "div.panel-pane.pane-views-panes.pane-breadcrumb-panel-pane-1.no-title.block a");

		boolean keep = false;
		ArrayList<String> list = new ArrayList<>();
		while (nodes.hasNext()) {

			if (keep) {
				String tag = jsoupParser.stripHtmlTag(nodes.next().toString());
				list.add(tag);
			}
			keep = true;
		}
		return list;
	}

	@Override
	public ArrayList<String> parseKeyWords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String parseTitle() throws IOException {
		JsoupParser jsoupParser = new JsoupParser();
		try {
			return jsoupParser.stripHtmlTag(jsoupParser.parseDataFromHtml(getPageHtml(),
					"#block-system-main > div.full-width.detailed-page > " + "div.container.detailed-header > "
							+ "div.panel-pane.pane-views-panes.pane-news-details-panel-pane-10.no-title.block > div"));

		} catch (NullPointerException e) {
			return "";
		}
	}

	@Override
	public String parseAuthor() throws IOException {
		JsoupParser jsoupParser = new JsoupParser();
		try {
			return jsoupParser.stripHtmlTag(jsoupParser.parseDataFromHtml(getPageHtml(),
					"#block-system-main > div.full-width.detailed-page > "
							+ "div.container.mid-section.zero-bottom.clearfix > div > div >" + " div.middle-content > "
							+ "div.panel-pane.pane-views-panes.pane-news-details-panel-pane-2.push-left.no-title.block > "
							+ "div > span"));
		} catch (NullPointerException e) {
			return "";
		}

	}

	@Override
	public String parsePostTime() throws IOException {
		JsoupParser jsoupParser = new JsoupParser();
		try {
			return jsoupParser.stripHtmlTag(jsoupParser.parseDataFromHtml(getPageHtml(),
					"#block-system-main > div.full-width.detailed-page > "
							+ "div.container.detailed-header > div.panel-pane.pane-views-panes.pane-news-details-panel-pane-1.no-title.block > "
							+ "div > div"))
					.split("/")[0];
		} catch (NullPointerException e) {
			return "";
		}

	}

	@Override
	public String parseArticle() throws IOException {
		JsoupParser jsoupParser = new JsoupParser();
		try {
			return jsoupParser.stripHtmlTag(jsoupParser.parseDataFromHtml(getPageHtml(), "div.node-content"));
		} catch (NullPointerException e) {
			return "";
		}
		
	}

	@Override
	public ArrayList<NewsPhoto> parseNewsPhotos() throws  IOException {
		//
		JsoupParser jsoupParser = new JsoupParser();
		
		try {
			String html = jsoupParser.parseDataFromHtml(getPageHtml(),
					"#block-system-main > div.full-width.detailed-page > "
							+ "div.container.mid-section.zero-bottom.clearfix > div > div > "
							+ "div.middle-content > div.panel-pane.pane-views-panes.pane-news-details-panel-pane-4.push-left.feature-image-pane.no-title.block > div");
			String imgLink = jsoupParser.parseImgSource(jsoupParser.parseDataFromHtml(html, "img"));
			String caption = jsoupParser.stripHtmlTag(html);

			NewsPhoto newsPhoto = new NewsPhoto();
			newsPhoto.setImageUrl(imgLink);
			newsPhoto.setImageCaption(caption);

			ArrayList<NewsPhoto> list = new ArrayList<>();
			list.add(newsPhoto);
			
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<NewsPhoto>();
		}

	}

}
