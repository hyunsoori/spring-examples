package gs.spring.webfluxdemo.redis;


import gs.spring.webfluxdemo.conf.RedisConfigurer;
import gs.spring.webfluxdemo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisConfigurer.class)
public class RedisBasicTests {

    @Autowired
    private ReactiveRedisOperations<String, List<Person>> personRedisTemplate;

    @Test
    public void shouldWriteAndReadPerson() {

        Person person = new Person(1, "Tester");
        StepVerifier.create(personRedisTemplate.opsForValue().set("1", Arrays.asList(person))) //
                .expectNext(true) //
                .verifyComplete();

        Flux<String> get = personRedisTemplate.execute(conn -> conn.stringCommands().get(ByteBuffer.wrap("1".getBytes()))) //
                .map(ByteUtils::getBytes) //
                .map(String::new);

        StepVerifier.create(get) //
                .expectNext("[{\"id\":1,\"name\":\"Tester\"}]") //
                .verifyComplete();
    }
}
