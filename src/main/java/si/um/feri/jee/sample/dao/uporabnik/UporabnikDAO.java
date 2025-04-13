package si.um.feri.jee.sample.dao.uporabnik;

import si.um.feri.jee.sample.vao.Uporabnik;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UporabnikDAO implements UporabnikDAOInterface {
    private static volatile UporabnikDAO instance;
    private List<Uporabnik> uporabniki = Collections.synchronizedList(new ArrayList<>());

    private UporabnikDAO() {}

    public static UporabnikDAO getInstance() {
        if (instance == null) {
            synchronized (UporabnikDAO.class) {
                if (instance == null) {
                    instance = new UporabnikDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void insertUporabnik(Uporabnik uporabnik) {
        synchronized (uporabniki) {
            uporabniki.add(uporabnik);
        }
    }

    @Override
    public List<Uporabnik> getAllUporabniki() {
        synchronized (uporabniki) {
            return new ArrayList<>(uporabniki);
        }
    }

    @Override
    public Optional<Uporabnik> getUporabnikByEmail(String email) {
        synchronized (uporabniki) {
            return uporabniki.stream().filter(u -> u.getEmail().equals(email)).findFirst();
        }
    }

    @Override
    public void updateUporabnikStanje(String email, double novoStanje) {
        synchronized (uporabniki) {
            getUporabnikByEmail(email).ifPresent(u -> u.setStanje(novoStanje));
        }
    }

    @Override
    public void deleteUporabnik(String email) {
        synchronized (uporabniki) {
            uporabniki.removeIf(u -> u.getEmail().equals(email));
        }
    }
}
