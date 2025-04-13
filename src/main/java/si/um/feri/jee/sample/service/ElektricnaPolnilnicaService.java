package si.um.feri.jee.sample.service;
import si.um.feri.jee.sample.dao.polnilnica.ElektricnaPolnilnicaDAO;
import si.um.feri.jee.sample.dao.polnilnica.ElektricnaPolnilnicaDAOInterface;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

public class ElektricnaPolnilnicaService {

    private final ElektricnaPolnilnicaDAOInterface polnilnicaDAO = ElektricnaPolnilnicaDAO.getInstance();

    public void addElektricnaPolnilnica(String ime, String lokacija, double hitrostPolnjenja, boolean active, double cenaPolnenja, String[] kompatibilnaVozila) {
        if (ime == null || ime.isEmpty() || lokacija == null || lokacija.isEmpty()) {
            throw new IllegalArgumentException("Ime in lokacija ne smeta biti prazna!");
        }

        ElektricnaPolnilnica polnilnica = new ElektricnaPolnilnica(
                ime,
                lokacija,
                null, // No ponudnik at creation time
                hitrostPolnjenja,
                active,
                null, // No user charging yet
                cenaPolnenja,
                kompatibilnaVozila
        );

        polnilnicaDAO.insertElektricnaPolnilnica(polnilnica);
    }

    public List<ElektricnaPolnilnica> getAllElektricnaPolnilnici() {
        return polnilnicaDAO.getAllElektricnaPolnilnici();
    }

    public Optional<ElektricnaPolnilnica> getElektricnaPolnilnicaByLokacija(String lokacija) {
        return polnilnicaDAO.getElektricnaPolnilnicaByLokacija(lokacija);
    }

    public void deleteElektricnaPolnilnica(String lokacija) {
        polnilnicaDAO.deleteElektricnaPolnilnica(lokacija);
    }
}

