package si.um.feri.jee.sample.dao.polnilnica;

import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

public interface ElektricnaPolnilnicaDAOInterface {
    List<ElektricnaPolnilnica> getElektricnaPolnilniceByPonudnik(String ponudnikIme);
    Optional<ElektricnaPolnilnica> getElektricnaPolnilnicaByLokacija(String lokacija);
    void deleteElektricnaPolnilnica(String lokacija);
    List<ElektricnaPolnilnica> getAllElektricnaPolnilnici();
    void updateElektricnaPolnilnica(String lokacija, Ponudnik newPonudnik);
    void insertElektricnaPolnilnica(ElektricnaPolnilnica polnilnica);
}