package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Entities.Paiement;

@Repository
public interface PaymentRepo extends JpaRepository<Paiement,Long> {
}
