package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TodoRepository extends  CrudRepository<Todo, Long>{
	List<Todo> findByName(String Name);
	
}
