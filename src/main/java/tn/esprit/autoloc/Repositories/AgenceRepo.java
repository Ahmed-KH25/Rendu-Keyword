package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Agence;

import java.util.List;


@Repository
public interface AgenceRepo extends JpaRepository<Agence,Long> {

    // ===== Partie I : Recherches simples =====

    // Scénario 4 : agences situées dans une ville donnée (attribut "ville")
    List<Agence> findByVille(String ville);

    // ===== Partie II : Recherches partielles =====

    // Scénario 7 : nom se terminant par une chaîne (LIKE %suffixe)
    List<Agence> findByNomEndingWithIgnoreCase(String suffixe);
}
