package tn.esprit.autoloc.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Repositories.AgenceRepo;
import tn.esprit.autoloc.Repositories.EmployeRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgence {
   private final AgenceRepo agenceRepo;

    @Override
    public Agence addAgence(Agence agence) {
        return agenceRepo.save(agence);
    }

    @Override
    public void deleteAgence(long id) {
        agenceRepo.deleteById(id);
    }

    @Override
    public Set<Agence> retrieveAgence() {
       // return agenceRepo.findAll().stream().collect(Collectors.toSet());
        return new HashSet<>(agenceRepo.findAll());
    }

    @Override
    public List<Agence> retrieveAgences() {
        return agenceRepo.findAll();
    }

    @Override
    public Agence retrieveById(long id) {
        return agenceRepo.findById(id).orElseThrow(null);
    }

    @Override
    public Agence updateAgence(Agence agence) {
        return agenceRepo.save(agence);
    }

    // ===== Partie I : Recherches simples =====

    @Override
    public List<Agence> rechercherParVille(String ville) {
        return agenceRepo.findByVille(ville);
    }

    // ===== Partie II : Recherches partielles =====

    @Override
    public List<Agence> rechercherNomFinitPar(String suffixe) {
        return agenceRepo.findByNomEndingWithIgnoreCase(suffixe);
    }
}
