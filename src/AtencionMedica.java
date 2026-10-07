import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AtencionMedica {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String recomendaciones;
    private String observaciones;

    private final List<MedicionSignosVitales> medicionesSignosVitales = new ArrayList<>();

    public AtencionMedica() {
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public MedicionSignosVitales getMedicionSignosVitales() {
        return medicionesSignosVitales.isEmpty() ? null : medicionesSignosVitales.get(0);
    }

    public void setMedicionSignosVitales(MedicionSignosVitales medicionSignosVitales) {
        medicionesSignosVitales.clear();
        if (medicionSignosVitales != null) {
            medicionesSignosVitales.add(medicionSignosVitales);
        }
    }

    public List<MedicionSignosVitales> getMedicionesSignosVitales() {
        return Collections.unmodifiableList(medicionesSignosVitales);
    }

    public void agregarMedicion(MedicionSignosVitales medicion) {
        medicionesSignosVitales.add(Objects.requireNonNull(medicion, "La medición no puede ser nula"));
    }
}
