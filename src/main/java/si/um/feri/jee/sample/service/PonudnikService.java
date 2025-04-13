package si.um.feri.jee.sample.service;

import si.um.feri.jee.sample.dao.ponudnik.PonudnikDAO;
import si.um.feri.jee.sample.dao.ponudnik.PonudnikDAOInterface;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

public class PonudnikService {
    private final PonudnikDAOInterface ponudnikDAO =  PonudnikDAO.getInstance();

    public void createPonudnik(String ime, String naslov, List<ElektricnaPolnilnica> izbranePolnilnice) {
        if (ime == null || ime.isEmpty() || naslov == null || naslov.isEmpty()) {
            throw new IllegalArgumentException("Ime ali naslov ne smeta biti prazna!");
        }

        Ponudnik ponudnik = new Ponudnik(ime, naslov);

        if (izbranePolnilnice != null) {
            for (ElektricnaPolnilnica polnilnica : izbranePolnilnice) {
                if (polnilnica.getPonudnik() == null) {
                    polnilnica.setPonudnik(ponudnik);
                    ponudnik.dodajPolnilnico(polnilnica);
                }
            }
        }

        System.out.println("Ponudnik: " + ponudnik);
        System.out.println(izbranePolnilnice);

        ponudnikDAO.insertPonudnik(ponudnik);
    }

    public List<Ponudnik> getAllPonudniki() {
        return ponudnikDAO.getAllPonudniki();
    }

    public Optional<Ponudnik> getPonudnikByIme(String ime) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti prazno!");
        }
        return ponudnikDAO.getPonudnikByIme(ime);
    }

    public void updatePonudnik(String ime, String newNaslov) {
        ponudnikDAO.updatePonudnik(ime, newNaslov);
    }

    public void deletePonudnik(String ime) {
        ponudnikDAO.deletePonudnik(ime);
    }
}
