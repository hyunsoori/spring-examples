package gs.spring.mvcdemo.repository.cache;

import gs.spring.mvcdemo.model.cache.PersonCache;
import org.springframework.data.repository.CrudRepository;

public interface PersonCacheRepository extends CrudRepository<PersonCache, Long> {}
