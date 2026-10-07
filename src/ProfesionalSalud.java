import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProfesionalSalud extends Usuario implements INotificable{

    private String numeroRegistroProfesional;
    private String especialidad;
    private EquipoAtencion equipoAtencion;
    private final List<ServicioDomiciliario> serviciosAsignados = new ArrayList<>();

    public ProfesionalSalud() {
    }

    public String getNumeroRegistroProfesional() {
        return numeroRegistroProfesional;
    }

    public void setNumeroRegistroProfesional(String numeroRegistroProfesional) {
        this.numeroRegistroProfesional = numeroRegistroProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public EquipoAtencion getEquipoAtencion() {
        return equipoAtencion;
    }

    public void setEquipoAtencion(EquipoAtencion equipoAtencion) {
        this.equipoAtencion = equipoAtencion;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println(mensaje);
    }

    public boolean estaDisponible(LocalDateTime fecha) {
        if (fecha == null) {
            return false;
        }

        return serviciosAsignados.stream()
                .filter(servicio -> servicio.getEstado() != null)
                .filter(servicio -> !ServicioDomiciliario.ESTADO_CANCELADO.equals(servicio.getEstado()))
                .filter(servicio -> !ServicioDomiciliario.ESTADO_FINALIZADO.equals(servicio.getEstado()))
                .map(ServicioDomiciliario::getFechaProgramada)
                .noneMatch(fechaAsignada -> fechaAsignada != null
                        && fechaAsignada.toLocalDate().equals(fecha.toLocalDate()));
    }

    boolean estaDisponible(LocalDateTime fecha, ServicioDomiciliario servicioIgnorado) {
        if (fecha == null) {
            return false;
        }

        return serviciosAsignados.stream()
                .filter(servicio -> servicio != servicioIgnorado)
                .filter(servicio -> servicio.getEstado() != null)
                .filter(servicio -> !ServicioDomiciliario.ESTADO_CANCELADO.equals(servicio.getEstado()))
                .filter(servicio -> !ServicioDomiciliario.ESTADO_FINALIZADO.equals(servicio.getEstado()))
                .map(ServicioDomiciliario::getFechaProgramada)
                .noneMatch(fechaAsignada -> fechaAsignada != null
                        && fechaAsignada.toLocalDate().equals(fecha.toLocalDate()));
    }

    void registrarServicio(ServicioDomiciliario servicio) {
        if (!serviciosAsignados.contains(servicio)) {
            serviciosAsignados.add(servicio);
        }
    }

    void retirarServicio(ServicioDomiciliario servicio) {
        serviciosAsignados.remove(servicio);
    }
}
