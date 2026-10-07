import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EquipoAtencion {
    private String codigo;
    private String nombre;
    private String zonaCobertura;
    private final List<ProfesionalSalud> profesionales = new ArrayList<>();

    public EquipoAtencion() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    public void agregarProfesional(ProfesionalSalud profesional) {
        Objects.requireNonNull(profesional, "El profesional no puede ser nulo");
        if (profesionales.contains(profesional)) {
            return;
        }

        EquipoAtencion equipoAnterior = profesional.getEquipoAtencion();
        if (equipoAnterior != null && equipoAnterior != this) {
            equipoAnterior.retirarProfesional(profesional);
        }

        profesionales.add(profesional);
        profesional.setEquipoAtencion(this);
    }

    public void retirarProfesional(ProfesionalSalud profesional) {
        if (profesionales.remove(profesional) && profesional != null
                && profesional.getEquipoAtencion() == this) {
            profesional.setEquipoAtencion(null);
        }
    }
}
