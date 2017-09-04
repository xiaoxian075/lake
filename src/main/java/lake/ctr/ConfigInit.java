package lake.ctr;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.util.RedisUtil;

import lake.entity.NRedis;

@Service
public class ConfigInit {

	@Autowired
	private NRedis redis;

	@PostConstruct
	public void init() {
		RedisUtil.init(redis);
	}

	@PreDestroy
	public void dostory() {
		//System.out.println("I'm  destory method  using  @PreDestroy....."+message);
	}
}
