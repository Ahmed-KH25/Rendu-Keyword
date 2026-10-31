package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Client;

import java.util.List;
import java.util.Set;

public interface IClient {
    Client ajouterClient(Client cl);
    public void supprimerClient(long idClient);
    List<Client> recupererClients();
    Set<Client> findClients();
    Client recupererClientByid(long idClient);
    Client updateClient(Client cl);
}
