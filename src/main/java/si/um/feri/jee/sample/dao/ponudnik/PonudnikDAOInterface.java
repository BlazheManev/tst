package si.um.feri.jee.sample.dao.ponudnik;

import si.um.feri.jee.sample.vao.Ponudnik;
import java.util.List;
import java.util.Optional;

public interface PonudnikDAOInterface {
    void insertPonudnik(Ponudnik ponudnik);
    List<Ponudnik> getAllPonudniki();
    Optional<Ponudnik> getPonudnikByIme(String ime);
    void updatePonudnik(String ime, String newNaslov);
    void deletePonudnik(String ime);
}
