package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Repositories.AgenceRepo;
import tn.esprit.autoloc.Repositories.EmployeRepo;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeServiceImpl implements IEmploye {
    private final EmployeRepo employeRepo;
    private final AgenceRepo agenceRepo;
    @Override
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = employeRepo.findById(idEmploye).get();

        Agence agence = employe.getAgence();
        employe.setAgence(null);
        return employeRepo.save(employe);
    }

    @Override
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = employeRepo.findById(idEmploye)
                .orElseThrow();
        Agence agence = agenceRepo.findById(idAgence)
                .orElseThrow();

        employe.setAgence(agence);
        return employeRepo.save(employe);
    }
}
