package si.um.feri.jee.sample.dao.polnilnica;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

@Stateless
public class ElektricnaPolnilnicaDAO implements ElektricnaPolnilnicaDAOInterface {

    @PersistenceContext(unitName = "ChargingPU")
    private EntityManager em;

    @Override
    public void insertElektricnaPolnilnica(ElektricnaPolnilnica polnilnica) {
        em.persist(polnilnica);
    }

    @Override
    public List<ElektricnaPolnilnica> getElektricnaPolnilniceByPonudnik(String ponudnikIme) {
        return em.createQuery(
                        "SELECT e FROM ElektricnaPolnilnica e WHERE e.ponudnik.ime = :ime", ElektricnaPolnilnica.class)
                .setParameter("ime", ponudnikIme)
                .getResultList();
    }

    @Override
    public Optional<ElektricnaPolnilnica> getElektricnaPolnilnicaByLokacija(String lokacija) {
        List<ElektricnaPolnilnica> result = em.createQuery(
                        "SELECT e FROM ElektricnaPolnilnica e WHERE e.lokacija = :lokacija", ElektricnaPolnilnica.class)
                .setParameter("lokacija", lokacija)
                .getResultList();
        return result.stream().findFirst();
    }

    @Override
    public void updateElektricnaPolnilnica(String lokacija, Ponudnik newPonudnik) {
        getElektricnaPolnilnicaByLokacija(lokacija).ifPresent(polnilnica -> {
            polnilnica.setPonudnik(newPonudnik);
            em.merge(polnilnica);
        });
    }

    @Override
    public void deleteElektricnaPolnilnica(String lokacija) {
        getElektricnaPolnilnicaByLokacija(lokacija).ifPresent(polnilnica -> {
            em.remove(em.contains(polnilnica) ? polnilnica : em.merge(polnilnica));
        });
    }

    @Override
    public List<ElektricnaPolnilnica> getAllElektricnaPolnilnici() {
        return em.createQuery("SELECT e FROM ElektricnaPolnilnica e", ElektricnaPolnilnica.class).getResultList();
    }

    @Override
    public void updateElektricnaPolnilnica(ElektricnaPolnilnica updatedPolnilnica) {
        em.merge(updatedPolnilnica);
    }
}
