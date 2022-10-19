package Controlador;

import Servicio.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ScoreController {
    
    @Autowired
    private ScoreService scoreService;
}
