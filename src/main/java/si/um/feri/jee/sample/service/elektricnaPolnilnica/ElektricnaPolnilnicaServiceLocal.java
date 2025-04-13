package si.um.feri.jee.sample.service.elektricnaPolnilnica;

import jakarta.ejb.Local;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;

import java.util.List;
import java.util.Optional;

@Local
public interface ElektricnaPolnilnicaServiceLocal {
    void addElektricnaPolnilnica(String ime, String lokacija, double hitrostPolnjenja, boolean active, double cenaPolnenja, String[] kompatibilnaVozila);

    List<ElektricnaPolnilnica> getAllElektricnaPolnilnici();

    Optional<ElektricnaPolnilnica> getElektricnaPolnilnicaByLokacija(String lokacija);

    void deleteElektricnaPolnilnica(String lokacija);

    void updateElektricnaPolnilnica(ElektricnaPolnilnica polnilnica);

    boolean lahkoPolni(String lokacija, String tipVozila);
}
