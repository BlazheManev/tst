package si.um.feri.jee.sample;


import si.um.feri.jee.sample.observers.polnilnica.ElektricnaPolnilnicaObserver;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.util.List;

public class ChargingStationDisplay implements ElektricnaPolnilnicaObserver {
    private List<ElektricnaPolnilnica> polnilnice;

    public ChargingStationDisplay(List<ElektricnaPolnilnica> polnilnice) {
        this.polnilnice = polnilnice;
    }

    @Override
    public void update(Ponudnik ponudnik, ElektricnaPolnilnica polnilnica, String action) {
        System.out.println("\n📟 [Zaslon polnilne postaje] Trenutno stanje polnilnic:");

        System.out.print("✅ Proste polnilnice: ");
        polnilnice.stream()
                .filter(p -> p.getCurrentUserEmail() == null)
                .forEach(p -> System.out.print(p.getLokacija() + ", "));
        System.out.println();

        System.out.print("⛔ Zasedene polnilnice: ");
        polnilnice.stream()
                .filter(p -> p.getCurrentUserEmail() != null)
                .forEach(p -> System.out.print(p.getLokacija() + ", "));
        System.out.println();

        System.out.println("🔄 Ažuriranje zaslona... Polnilnica " + polnilnica.getLokacija() + " je sada " +
                (action.equals("occupied") ? "zasedena" : "prosta") + ".");
    }

}
