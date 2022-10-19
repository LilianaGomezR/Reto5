package Servicio;

import Modelo.Partyroom;
import Repositorio.PartyroomRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PartyroomService {
    
    @Autowired
    private PartyroomRepository partyroomRepository;

    
    public List<Partyroom> getAll() {
        return partyroomRepository.getAll();
    }

    public Optional<Partyroom> getPartyroom(int id) {
        return partyroomRepository.getPartyroom(id);
    }

    public Partyroom save(Partyroom partyroom) {
        if (partyroom.getId() == null) {
            return partyroomRepository.save(partyroom);
        } else {
            Optional<Partyroom> partyroom1 = partyroomRepository.getPartyroom(partyroom.getId());
            if (partyroom1.isEmpty()) {
                return partyroomRepository.save(partyroom);
            } else {
                return partyroom;
            }
        }
    }


    public Partyroom update(Partyroom partyroom){
        if(partyroom.getId()!=null){
            Optional<Partyroom>g= partyroomRepository.getPartyroom(partyroom.getId());
            if(!g.isEmpty()){
                if(partyroom.getDescription()!=null){                    
                    g.get().setDescription(partyroom.getDescription());
                }
                if(partyroom.getName()!=null){
                    g.get().setName(partyroom.getName());
                }
                return partyroomRepository.save(g.get());
            }
        }
        return partyroom;
    }
    public boolean deletePartyroom(int id){
        Boolean d= getPartyroom(id).map(partyroom -> {
                                                        partyroomRepository.delete(partyroom);
                                                        return true;
                                            }
                                      ).orElse(false);
        return d;
    }

}
