package si.um.feri.jee.sample.service.ponudnik;

import jakarta.ejb.Remote;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

@Remote
public interface PonudnikServiceRemote {
    void createPonudnik(String ime, String naslov, List<ElektricnaPolnilnica> izbranePolnilnice);
    List<Ponudnik> getAllPonudniki();
    Optional<Ponudnik> getPonudnikByIme(String ime);
    void updatePonudnik(String ime, String newNaslov);
    void deletePonudnik(String ime);
}
