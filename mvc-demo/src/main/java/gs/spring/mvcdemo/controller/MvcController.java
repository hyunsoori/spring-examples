package gs.spring.mvcdemo.controller;


import gs.spring.mvcdemo.model.Person;
import gs.spring.mvcdemo.model.cache.PersonCache;
import gs.spring.mvcdemo.repository.PersonRepository;
import gs.spring.mvcdemo.repository.cache.PersonCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MvcController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonCacheRepository personCacheRepository;

    @GetMapping(value = "/person/rdb")
    public Iterable<Person> findFriendListByRdb(){
        return personRepository.findAll();
    }

    @GetMapping(value = "/person/redis")
    public List<Person> findFriendListByRedis(){
        Optional<PersonCache> personCache = personCacheRepository.findById(1L);
        if ( personCache.isPresent() ){
            return personCache.get().getPersonList();
        } else {
            return Collections.emptyList();
        }
    }
}
