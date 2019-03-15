package gs.spring.mvcdemo.repository;

import gs.spring.mvcdemo.model.Person;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person, Long> {}
