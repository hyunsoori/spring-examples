package gs.spring.webfluxdemo;



import gs.spring.webfluxdemo.model.Person;
import gs.spring.webfluxdemo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
public class DataLoader {

    private static Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private ReactiveRedisOperations<String, List<Person>> personOps;

    @Autowired
    private PersonRepository personRepository;

    @PostConstruct
    public void loadData() {

        List<Person> friendList = LongStream.range(1, 100)
                .boxed().map(id -> new Person(id, id.toString())).collect(Collectors.toList());

        personOps.opsForValue().set("friend:1", friendList)
                .subscribe(System.out::println);
        logger.info("Redis Data load end");

        personRepository.saveAll(friendList);
        logger.info("RDB Data load end");
    }
}
