package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Employe;

public interface IEmploye {
    public Employe desaffecterEmploye(Long idEmploye);
    public Employe affecterEmploye(Long idEmploye, Long idAgence);
}
