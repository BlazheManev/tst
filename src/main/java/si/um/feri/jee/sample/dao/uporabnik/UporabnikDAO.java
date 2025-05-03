package si.um.feri.jee.sample.dao.uporabnik;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.jee.sample.vao.Uporabnik;

import java.util.List;
import java.util.Optional;

@Stateless
public class UporabnikDAO implements UporabnikDAOInterface {

    @PersistenceContext(unitName = "ChargingPU")
    EntityManager em;

    @Override
    public void insertUporabnik(Uporabnik uporabnik) {
        em.persist(uporabnik);
    }

    @Override
    public List<Uporabnik> getAllUporabniki() {
        return em.createQuery("SELECT u FROM Uporabnik u", Uporabnik.class).getResultList();
    }

    @Override
    public Optional<Uporabnik> getUporabnikByEmail(String email) {
        List<Uporabnik> result = em.createQuery(
                        "SELECT u FROM Uporabnik u WHERE u.email = :email", Uporabnik.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findFirst();
    }

    @Override
    public void updateUporabnikStanje(String email, double novoStanje) {
        getUporabnikByEmail(email).ifPresent(u -> {
            u.setStanje(novoStanje);
            em.merge(u);
        });
    }

    @Override
    public void deleteUporabnik(String email) {
        getUporabnikByEmail(email).ifPresent(u -> {
            em.remove(em.contains(u) ? u : em.merge(u));
        });
    }

    public void updateUporabnik(Uporabnik updated) {
        em.merge(updated);
    }
}
