package shafin.web.mongocrud.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shafin.web.mongocrud.model.News;

@Repository
public interface NewsRepository extends MongoRepository<News, String>{
	
	Page<News> findAll(Pageable pageable);
	
	 @Query("{\"$or\" : [ { \"title\" : { \"$regex\" : ?0, \"$options\" : \"i\"}} , " +
		        "{ \"source\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}, " +
		        "{ \"article\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}, " +
		        "{ \"categoryTags\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}]}")
	public ArrayList<News> findByTitleleOrSourceOrArticleOrCateogryTagsLike(@Param("query") String query);

}
