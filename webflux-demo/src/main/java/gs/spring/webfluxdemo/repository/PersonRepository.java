package gs.spring.webfluxdemo.repository;

import gs.spring.webfluxdemo.model.Person;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PersonRepository extends Repository<Person, Long> {

    @Async
    CompletableFuture<List<Person>> findAll();


    @Async
    CompletableFuture<List<Person>> saveAll(Iterable<Person> entities);
}
