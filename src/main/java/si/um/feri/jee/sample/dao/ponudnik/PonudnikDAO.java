package si.um.feri.jee.sample.dao.ponudnik;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

@Stateless
public class PonudnikDAO implements PonudnikDAOInterface {

    @PersistenceContext(unitName = "ChargingPU")
    EntityManager em;

    @Override
    public void insertPonudnik(Ponudnik ponudnik) {
        em.merge(ponudnik); // 👈 Use merge instead of persist
    }

    @Override
    public List<Ponudnik> getAllPonudniki() {
        return em.createQuery("SELECT p FROM Ponudnik p", Ponudnik.class).getResultList();
    }

    @Override
    public Optional<Ponudnik> getPonudnikByIme(String ime) {
        List<Ponudnik> result = em.createQuery("SELECT p FROM Ponudnik p WHERE p.ime = :ime", Ponudnik.class)
                .setParameter("ime", ime)
                .getResultList();
        return result.stream().findFirst();
    }

    @Override
    public void updatePonudnik(String ime, String newNaslov) {
        getPonudnikByIme(ime).ifPresent(p -> {
            p.setNaslov(newNaslov);
            em.merge(p);
        });
    }

    @Override
    public void deletePonudnik(String ime) {
        getPonudnikByIme(ime).ifPresent(p -> {
            em.remove(em.contains(p) ? p : em.merge(p));
        });
    }
}
