package shafin.web.mongocrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shafin.web.mongocrud.model.News;

@Repository
public interface NewsRepository extends CrudRepository<News, String>{

	
}
