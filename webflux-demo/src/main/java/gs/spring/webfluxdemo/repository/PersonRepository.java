package gs.spring.webfluxdemo.repository;

import gs.spring.webfluxdemo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface PersonRepository extends Repository<Person, Long> {

    @Async
    CompletableFuture<List<Person>> findByIdLessThanEqual(long id);


    @Async
    CompletableFuture<List<Person>> saveAll(Iterable<Person> entities);

    @Async
    CompletableFuture<Person> save(Person person);

    @Async
    CompletableFuture<Optional<Person>> findById(long id);
}
