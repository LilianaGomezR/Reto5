package Servicio;

import Modelo.Client;
import Repositorio.ClientRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> client1 = clientRepository.getClient(client.getIdClient());
            if (client1.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }


    
    // Con optional se requiere get() porque es algo asi como
    // un arraylist con un elemento. Puede que lo tenga o no 
    // Y por tanto con el get() se extrae el elemento de Client
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> e= clientRepository.getClient(client.getIdClient());
            if(!e.isEmpty()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                clientRepository.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }


    // getClient(id) devuelve un optional de Client
    //.map es uan funcion de Optional
    // Client es un parametro de funcion que recibe el objeto de quien lo llama
    // se llama la funcion de repositorioClient delete enviandole el objeto a borrar
    // el orElse es de la funcion map ( cuando no encuentra objeto para hacer map, osea getClient(id)
    // no devolvio nada)
    // public boolean deleteClient(int clientId) {
    //     Boolean aBoolean = getClient(clientId).map(client -> {
    //         repositoryClient.delete(client);
    //         return true;
    //     }).orElse(false);
    //     return aBoolean;
    // }
    public boolean deleteClient(int clientId) {
        boolean flag=false;
        Optional<Client> c= clientRepository.getClient(clientId);
        if(c.isPresent()){
            clientRepository.delete(c.get());
            flag=true;
        }
        return flag;
    }
    
}
