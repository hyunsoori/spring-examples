package gs.spring.mvcdemo.model.cache;

import gs.spring.mvcdemo.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("friend:cache")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonCache {

    @Id
    private long id;
    private List<Person> personList;
}
