package si.um.feri.jee.sample.observers.user;

import si.um.feri.jee.sample.observers.polnilnica.ElektricnaPolnilnicaObserver;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

public class UserNotifier implements ElektricnaPolnilnicaObserver {
    @Override
    public void update(Ponudnik ponudnik, ElektricnaPolnilnica polnilnica, String action) {
        if ("occupied".equals(action)) {
            System.out.println("📩 [EMAIL] Od: noreply@chargingstations.com");
            System.out.println("📩 Za: " + polnilnica.getCurrentUserEmail());
            System.out.println("📩 Zadeva: Polnjenje se je začelo! ⚡\n");
            System.out.println("Pozdravljeni,\n");
            System.out.println("Vaše polnjenje na polnilnici **" + polnilnica.getLokacija() + "** se je začelo.");
            System.out.println("🚗 Moč polnjenja: " + polnilnica.getHitrostPolnjenja() + " kW\n");
            System.out.println("Lep pozdrav,\n" + ponudnik.getIme());
        } else if ("free".equals(action)) {
            System.out.println("📩 [EMAIL] Od: noreply@chargingstations.com");
            System.out.println("📩 Za: " + polnilnica.getCurrentUserEmail());
            System.out.println("📩 Zadeva: Polnjenje končano! ✅\n");
            System.out.println("Pozdravljeni,\n");
            System.out.println("Vaše polnjenje na polnilnici **" + polnilnica.getLokacija() + "** je končano.");
            System.out.println("🔌\n");
            System.out.println("Lep pozdrav,\n" + ponudnik.getIme());
        }
    }
}
