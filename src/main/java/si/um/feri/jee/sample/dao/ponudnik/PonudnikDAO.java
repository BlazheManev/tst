package si.um.feri.jee.sample.dao.ponudnik;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PonudnikDAO implements PonudnikDAOInterface {
    private static volatile PonudnikDAO instance;
    private List<Ponudnik> ponudniki = Collections.synchronizedList(new ArrayList<>());

    private PonudnikDAO() {}


    // Singleton metoda
    public static PonudnikDAO getInstance() {
        if (instance == null) {
            synchronized (PonudnikDAO.class) {
                if (instance == null) {
                    instance = new PonudnikDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void insertPonudnik(Ponudnik ponudnik) {
        synchronized (ponudniki) {
            ponudniki.add(ponudnik);
        }
    }

    @Override
    public List<Ponudnik> getAllPonudniki() {
        synchronized (ponudniki) {
            return new ArrayList<>(ponudniki);
        }
    }

    @Override
    public Optional<Ponudnik> getPonudnikByIme(String ime) {
        synchronized (ponudniki) {
            return ponudniki.stream().filter(p -> p.getIme().equals(ime)).findFirst();
        }
    }

    @Override
    public void updatePonudnik(String ime, String newNaslov) {
        synchronized (ponudniki) {
            getPonudnikByIme(ime).ifPresent(p -> p.setNaslov(newNaslov));
        }
    }

    @Override
    public void deletePonudnik(String ime) {
        synchronized (ponudniki) {
            ponudniki.removeIf(p -> p.getIme().equals(ime));
        }
    }
}
