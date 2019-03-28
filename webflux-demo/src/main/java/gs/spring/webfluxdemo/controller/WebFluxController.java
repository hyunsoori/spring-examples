package gs.spring.webfluxdemo.controller;


import gs.spring.webfluxdemo.model.Person;
import gs.spring.webfluxdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class WebFluxController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ReactiveRedisOperations<String, List<Person>> personRedisTemplate;


    @GetMapping(value = "/person/rdb")
    public Mono<Object> findPersonListByRdb() {

        return Mono.fromCompletionStage(personRepository.findByIdLessThanEqual(100));
    }


    @GetMapping(value = "/person/redis")
    public Mono<List<Person>> findPersonListByRedis(){

        return personRedisTemplate.opsForValue().get("friend:1")
                .switchIfEmpty(Mono.just(Collections.emptyList()));
    }

    @GetMapping(value = "/person/redis/save")
    public Mono<Boolean> savePersonToRedis(){
        return personRedisTemplate.opsForValue().set("friend:1:list", Arrays.asList(new Person(1, "1")));
    }
}
