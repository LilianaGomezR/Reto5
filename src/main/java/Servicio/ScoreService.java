package Servicio;

import Repositorio.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ScoreService {
    
    @Autowired
    private ScoreRepository scoreRepository;
}
