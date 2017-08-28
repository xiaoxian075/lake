package lake.db;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface IBaseDB<T> {
	List<T> selectList(Map<String,Object> params);
}
