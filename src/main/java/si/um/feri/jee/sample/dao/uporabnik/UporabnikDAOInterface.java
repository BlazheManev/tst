package si.um.feri.jee.sample.dao.uporabnik;

import si.um.feri.jee.sample.vao.Uporabnik;

import java.util.List;
import java.util.Optional;

public interface UporabnikDAOInterface {
    void insertUporabnik(Uporabnik uporabnik);
    List<Uporabnik> getAllUporabniki();
    Optional<Uporabnik> getUporabnikByEmail(String email);
    void updateUporabnikStanje(String email, double novoStanje);
    void deleteUporabnik(String email);
}
