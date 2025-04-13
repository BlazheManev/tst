package si.um.feri.jee.sample.observers.user;

import si.um.feri.jee.sample.observers.polnilnica.ElektricnaPolnilnicaObserver;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

public class ProviderNotifier implements ElektricnaPolnilnicaObserver {
    @Override
    public void update(Ponudnik ponudnik, ElektricnaPolnilnica polnilnica, String action) {
        if ("occupied".equals(action)) {
            System.out.println("🏢 Ponudnik obveščen: Polnilnica " + polnilnica.getLokacija() +
                    " pri ponudniku " + ponudnik.getIme() + " je zdaj zasedena.");
        } else if ("free".equals(action)) {
            System.out.println("🏢 Ponudnik obveščen: Polnilnica " + polnilnica.getLokacija() +
                    " pri ponudniku " + ponudnik.getIme() + " je zdaj prosta.");
        }
    }
}
