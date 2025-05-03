package si.um.feri.jee.sample.service.elektricnaPolnilnica;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import si.um.feri.jee.sample.dao.polnilnica.ElektricnaPolnilnicaDAOInterface;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Stateless
public class ElektricnaPolnilnicaService implements ElektricnaPolnilnicaServiceLocal, ElektricnaPolnilnicaServiceRemote {

    @Inject
    private ElektricnaPolnilnicaDAOInterface polnilnicaDAO;

    @Override
    public void addElektricnaPolnilnica(String ime, String lokacija, double hitrostPolnjenja, boolean active, double cenaPolnenja, String[] kompatibilnaVozila) {
        if (ime == null || ime.isEmpty() || lokacija == null || lokacija.isEmpty()) {
            throw new IllegalArgumentException("Ime in lokacija ne smeta biti prazna!");
        }

        ElektricnaPolnilnica polnilnica = new ElektricnaPolnilnica(
                ime,
                lokacija,
                null,
                hitrostPolnjenja,
                active,
                null,
                cenaPolnenja,
                Arrays.asList(kompatibilnaVozila)
        );

        polnilnicaDAO.insertElektricnaPolnilnica(polnilnica);
    }

    @Override
    public List<ElektricnaPolnilnica> getAllElektricnaPolnilnici() {
        return polnilnicaDAO.getAllElektricnaPolnilnici();
    }

    @Override
    public Optional<ElektricnaPolnilnica> getElektricnaPolnilnicaByLokacija(String lokacija) {
        return polnilnicaDAO.getElektricnaPolnilnicaByLokacija(lokacija);
    }

    @Override
    public void deleteElektricnaPolnilnica(String lokacija) {
        polnilnicaDAO.deleteElektricnaPolnilnica(lokacija);
    }

    @Override
    public void updateElektricnaPolnilnica(ElektricnaPolnilnica polnilnica) {
        polnilnicaDAO.updateElektricnaPolnilnica(polnilnica);
    }

    @Override
    public boolean lahkoPolni(String lokacija, String tipVozila) {
        Optional<ElektricnaPolnilnica> optional = getElektricnaPolnilnicaByLokacija(lokacija);
        return optional.map(p -> {
            List<String> types = p.getKompatibilnaVozila();
            return types != null && types.contains(tipVozila);
        }).orElse(false);
    }
}
