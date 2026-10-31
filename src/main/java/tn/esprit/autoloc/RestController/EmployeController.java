package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Services.IEmploye;

@RestController
@RequestMapping("/api/Employe")
@RequiredArgsConstructor
public class EmployeController {
   private final IEmploye iEmploye;

    @PutMapping("/affecterEmploye/{idEmploye}/{idAgence}")
    public Employe affecterEmploy(@PathVariable Long idEmploye,
                                  @PathVariable Long idAgence){
        return iEmploye.affecterEmploye(idEmploye,idAgence);
    }

    @PutMapping("/desaffecterEmploye/{idEmploye}")
    public void desaffecterEmploy(@PathVariable Long idEmploye){
        iEmploye.desaffecterEmploye(idEmploye);
    }
}
