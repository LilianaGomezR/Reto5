package Interface;

import Modelo.Message;
import org.springframework.data.repository.CrudRepository;



public interface MessageCrudRepository extends CrudRepository<Message,Integer>{

    
}
