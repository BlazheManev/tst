package si.um.feri.jee.sample.dao.polnilnica;


import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ElektricnaPolnilnicaDAO implements ElektricnaPolnilnicaDAOInterface {
    private static volatile ElektricnaPolnilnicaDAO instance;
    private final List<ElektricnaPolnilnica> polnilnice = Collections.synchronizedList(new ArrayList<>());

   private ElektricnaPolnilnicaDAO() {}

    // Singleton metoda
    public static ElektricnaPolnilnicaDAO getInstance() {
        if (instance == null) {
            synchronized (ElektricnaPolnilnicaDAO.class) {
                if (instance == null) {
                    instance = new ElektricnaPolnilnicaDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void insertElektricnaPolnilnica(ElektricnaPolnilnica polnilnica) {
        synchronized (polnilnice) {
            polnilnice.add(polnilnica);
        }
    }

    @Override
    public List<ElektricnaPolnilnica> getElektricnaPolnilniceByPonudnik(String ponudnikIme) {
        synchronized (polnilnice) {
            return polnilnice.stream().filter(e -> e.getPonudnik().getIme().equals(ponudnikIme))
                    .toList();
        }
    }

    @Override
    public Optional<ElektricnaPolnilnica> getElektricnaPolnilnicaByLokacija(String lokacija) {
        synchronized (polnilnice) {
            return polnilnice.stream().filter(e -> e.getLokacija().equals(lokacija)).findFirst();
        }
    }

    @Override
    public void updateElektricnaPolnilnica(String lokacija, Ponudnik newPonudnik) {
       synchronized (polnilnice) {
           getElektricnaPolnilnicaByLokacija(lokacija).ifPresent(e -> e.setPonudnik(newPonudnik));
       }
    }

    @Override
    public void deleteElektricnaPolnilnica(String lokacija) {
        synchronized (polnilnice) {
            polnilnice.removeIf(e -> e.getLokacija().equals(lokacija));
        }
    }

    @Override
    public List<ElektricnaPolnilnica> getAllElektricnaPolnilnici() {
        synchronized (polnilnice) {
            return new ArrayList<>(polnilnice);
        }
   }
}
