package shafin.web.mongocrud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shafin.web.mongocrud.model.News;

@Repository
public interface NewsRepository extends MongoRepository<News, String>{

	
}
