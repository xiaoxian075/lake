package lake;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.RedisUtil;

import lake.entity.RedisConfig;

@Service
public class ConfigInit {

	@Autowired
	private RedisConfig redisConfig;

	@PostConstruct
	public void init() {
		RedisUtil.init(redisConfig);
	}

	@PreDestroy
	public void dostory() {
		//System.out.println("I'm  destory method  using  @PreDestroy....."+message);
	}
}
