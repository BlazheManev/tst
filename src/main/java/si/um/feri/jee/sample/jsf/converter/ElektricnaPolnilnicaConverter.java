package si.um.feri.jee.sample.jsf.converter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import si.um.feri.jee.sample.service.elektricnaPolnilnica.ElektricnaPolnilnicaServiceLocal;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;

@FacesConverter(value = "polnilnicaConverter", managed = true)
@ApplicationScoped
public class ElektricnaPolnilnicaConverter implements Converter<ElektricnaPolnilnica> {

    @Inject
    private ElektricnaPolnilnicaServiceLocal polnilnicaService;

    @Override
    public ElektricnaPolnilnica getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("🔁 Converting string to object: " + value);
        if (value == null || value.isBlank()) return null;
        return polnilnicaService.getElektricnaPolnilnicaByLokacija(value).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ElektricnaPolnilnica object) {
        System.out.println("🔁 Converting string to object: " + object);
        if (object == null || object.getLokacija() == null) return "";
        return object.getLokacija();
    }
}
