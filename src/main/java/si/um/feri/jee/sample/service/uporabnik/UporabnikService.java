package si.um.feri.jee.sample.service.uporabnik;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import si.um.feri.jee.sample.chainOfResponsability.PolnjenjeHandler;
import si.um.feri.jee.sample.chainOfResponsability.PreveriKompatibilnost;
import si.um.feri.jee.sample.chainOfResponsability.PreveriRazpolozljivost;
import si.um.feri.jee.sample.chainOfResponsability.PreveriStanje;
import si.um.feri.jee.sample.dao.uporabnik.UporabnikDAO;
import si.um.feri.jee.sample.dao.uporabnik.UporabnikDAOInterface;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Uporabnik;

import java.util.List;
import java.util.Optional;

@Stateless
public class UporabnikService implements UporabnikServiceLocal, UporabnikServiceRemote {

    @Inject
    private UporabnikDAOInterface uporabnikDAO;
    public void dodajUporabnika(String ime, String email, double stanje, String tipVozila) {
        Uporabnik novUporabnik = new Uporabnik(ime, email, stanje, tipVozila);
        uporabnikDAO.insertUporabnik(novUporabnik);
    }

    public List<Uporabnik> pridobiVseUporabnike() {
        return uporabnikDAO.getAllUporabniki();
    }


    public Optional<Uporabnik> pridobiUporabnikaPoEmailu(String email) {
        return uporabnikDAO.getUporabnikByEmail(email);
    }

    public void posodobiStanjeUporabnika(String email, double novoStanje) {
        uporabnikDAO.updateUporabnikStanje(email, novoStanje);
    }

    public void posodobiUporabnika(Uporabnik uporabnik) {
        uporabnikDAO.updateUporabnik(uporabnik);
    }

    public void izbrisiUporabnika(String email) {
        uporabnikDAO.deleteUporabnik(email);
    }

    public void zacniPolnjenje(Uporabnik uporabnik, ElektricnaPolnilnica polnilnica) {
        // verigo preverjanj
        PolnjenjeHandler razpolozljivost = new PreveriRazpolozljivost();
        PolnjenjeHandler stanje = new PreveriStanje();
        PolnjenjeHandler kompatibilnost = new PreveriKompatibilnost();

        razpolozljivost.nastaviNaslednjega(stanje);
        stanje.nastaviNaslednjega(kompatibilnost);

        razpolozljivost.obdelaj(uporabnik, polnilnica);

        uporabnikDAO.insertUporabnik(uporabnik);
    }
}
