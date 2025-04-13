package si.um.feri.jee.sample.iterators;

import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolnilnicaPoRegijiIterator implements Iterator<ElektricnaPolnilnica> {
    private final Iterator<ElektricnaPolnilnica> iterator;

    public PolnilnicaPoRegijiIterator(List<ElektricnaPolnilnica> polnilnice, String lokacija) {
        List<ElektricnaPolnilnica> filtrirani = new ArrayList<>();
        for (ElektricnaPolnilnica p : polnilnice) {
            if (p.getLokacija() != null && p.getLokacija().split(",")[0].equalsIgnoreCase(lokacija)) {
                filtrirani.add(p);
            }
        }
        this.iterator = filtrirani.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ElektricnaPolnilnica next() {
        return iterator.next();
    }
}
