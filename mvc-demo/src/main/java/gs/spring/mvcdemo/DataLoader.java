package gs.spring.mvcdemo;

import gs.spring.mvcdemo.model.Person;
import gs.spring.mvcdemo.model.cache.PersonCache;
import gs.spring.mvcdemo.repository.PersonRepository;
import gs.spring.mvcdemo.repository.cache.PersonCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
public class DataLoader {

    private static Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private PersonCacheRepository personCacheRepository;

    @Autowired
    private PersonRepository personRepository;

    @PostConstruct
    public void loadData() {


        PersonCache personCache = new PersonCache();
        personCache.setId(1);
        personCache.setPersonList(
                LongStream.range(1, 100).boxed().map(id -> new Person(id, id.toString())).collect(Collectors.toList())
        );

        personCacheRepository.save(personCache);
        logger.info("Redis Data load end");

        List<Person> personList = personCache.getPersonList().stream().map(p -> new Person(p.getId(), p.getName())).collect(Collectors.toList());
        personRepository.saveAll(personList);
        logger.info("RDB Data load end");
    }
}
