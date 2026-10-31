package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Services.IClient;

import java.util.List;

@RestController
@Tag(name="Gestion Client")
@RequestMapping("/api/Client")
@RequiredArgsConstructor
public class ClientController {
    private final IClient iClient;
    @Operation(description="ajouter un client dans la base ")
    @PostMapping("/AddClient")
    public Client ajouterClient(@RequestBody Client client) {
        return iClient.ajouterClient(client);

    }
    @Operation(description="récuperer tous les clients")
    @GetMapping("/GetAll")
    public List<Client> getAll() {
        return iClient.recupererClients();
    }

    @PutMapping("/updateClient")
    public Client updateClient(@RequestBody Client client) {
        return iClient.updateClient(client);
    }
    @DeleteMapping("/deleteClient/{idclient}")
    public void deleteClient(@PathVariable("idclient") Long id)
    {
        iClient.supprimerClient(id);
    }
}