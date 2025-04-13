package si.um.feri.jee.sample.iterators;


import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AktivnaPolnilnicaIterator implements Iterator<ElektricnaPolnilnica> {
    private final Iterator<ElektricnaPolnilnica> iterator;

    public AktivnaPolnilnicaIterator(List<ElektricnaPolnilnica> polnilnice) {
        List<ElektricnaPolnilnica> aktivni = new ArrayList<>();
        for (ElektricnaPolnilnica p : polnilnice) {
            if (p.isActive()) {
                aktivni.add(p);
            }
        }
        this.iterator = aktivni.iterator();
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
