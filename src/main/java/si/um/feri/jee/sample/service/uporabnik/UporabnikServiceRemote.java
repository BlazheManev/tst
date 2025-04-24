package si.um.feri.jee.sample.service.uporabnik;

import jakarta.ejb.Remote;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Uporabnik;

import java.util.List;
import java.util.Optional;

@Remote
public interface UporabnikServiceRemote {
    void dodajUporabnika(String ime, String email, double stanje, String tipVozila);
    List<Uporabnik> pridobiVseUporabnike();
    Optional<Uporabnik> pridobiUporabnikaPoEmailu(String email);
    void posodobiStanjeUporabnika(String email, double novoStanje);
    void posodobiUporabnika(Uporabnik uporabnik);
    void izbrisiUporabnika(String email);
    void zacniPolnjenje(Uporabnik uporabnik, ElektricnaPolnilnica polnilnica);
}