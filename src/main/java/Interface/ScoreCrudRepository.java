package Interface;

import Modelo.Score;
import org.springframework.data.repository.CrudRepository;


public interface ScoreCrudRepository extends CrudRepository<Score,Integer> {
    
}
