package Repositorio;

import Interface.ClientCrudRepository;
import Modelo.Client;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class ClientRepository {

    @Autowired
    private ClientCrudRepository clienCrudRepository;
    
    
    public List<Client> getAll(){
        return (List<Client>) clienCrudRepository.findAll();
    }
    public Optional<Client> getClient(int id){
        return clienCrudRepository.findById(id);
    }
    
    public Client save(Client client){
        return clienCrudRepository.save(client);
    }

    public void delete(Client client){
        clienCrudRepository.delete(client);
    }

}
