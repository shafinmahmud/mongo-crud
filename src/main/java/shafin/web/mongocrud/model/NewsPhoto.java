package shafin.web.mongocrud.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class NewsPhoto {

	@Field
	private String id;	
	@Field
	private String imageCaption;	
	@Field
	private String imageUrl;

	public NewsPhoto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageCaption() {
		return imageCaption;
	}

	public void setImageCaption(String caption) {
		this.imageCaption = caption;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "NewsPhoto [id=" + id + ", imageCaption=" + imageCaption + ", imageUrl=" + imageUrl + "]";
	}
	
	
	
}
