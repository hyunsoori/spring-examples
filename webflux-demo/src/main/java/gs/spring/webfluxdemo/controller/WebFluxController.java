package gs.spring.webfluxdemo.controller;


import gs.spring.webfluxdemo.model.Person;
import gs.spring.webfluxdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RestController
public class WebFluxController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ReactiveRedisOperations<String, List<Person>> personRedisTemplate;


    @GetMapping(value = "/person/rdb")
    public Mono<Object> findFriendListByRdb(){

        return Mono.fromCompletionStage(personRepository.findAll());
    }

    @GetMapping(value = "/person/redis")
    public Mono<List<Person>> findFriendListByRedis(){

        return personRedisTemplate.opsForValue().get("friend:1")
                .switchIfEmpty(Mono.just(Collections.emptyList()));
    }
}
