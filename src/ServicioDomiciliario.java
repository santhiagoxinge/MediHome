import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ServicioDomiciliario {
    public static final String ESTADO_SOLICITADO = "solicitado";
    public static final String ESTADO_PROGRAMADO = "programado";
    public static final String ESTADO_EN_ATENCION = "en atención";
    public static final String ESTADO_FINALIZADO = "finalizado";
    public static final String ESTADO_CANCELADO = "cancelado";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String codigo;
    private LocalDateTime fechaProgramada;
    private String direccionAtencion;
    private String motivo;
    private String estado = ESTADO_SOLICITADO;

    private Paciente paciente;
    private ProfesionalSalud profesionalSalud;

    private AtencionMedica atencionMedica;

    public ServicioDomiciliario() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getDireccionAtencion() {
        return direccionAtencion;
    }

    public void setDireccionAtencion(String direccionAtencion) {
        this.direccionAtencion = direccionAtencion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ProfesionalSalud getProfesionalSalud() {
        return profesionalSalud;
    }

    public void setProfesionalSalud(ProfesionalSalud profesionalSalud) {
        if (this.profesionalSalud == profesionalSalud) {
            return;
        }
        if (this.profesionalSalud != null) {
            this.profesionalSalud.retirarServicio(this);
        }
        this.profesionalSalud = profesionalSalud;
        if (profesionalSalud != null) {
            profesionalSalud.registrarServicio(this);
        }
    }

    public AtencionMedica getAtencionMedica() {
        return atencionMedica;
    }

    public void setAtencionMedica(AtencionMedica atencionMedica) {
        this.atencionMedica = atencionMedica;
    }

    public void programar(LocalDateTime fecha) {
        Objects.requireNonNull(fecha, "La fecha programada no puede ser nula");
        if (ESTADO_CANCELADO.equals(estado) || ESTADO_FINALIZADO.equals(estado)
                || ESTADO_EN_ATENCION.equals(estado)) {
            throw new IllegalStateException("No se puede programar un servicio en estado " + estado);
        }
        if (profesionalSalud != null && !profesionalSalud.estaDisponible(fecha, this)) {
            throw new IllegalStateException("El profesional ya tiene un servicio activo en esa fecha");
        }

        fechaProgramada = fecha;
        estado = ESTADO_PROGRAMADO;
    }

    public void asignarProfesional(ProfesionalSalud profesional) {
        Objects.requireNonNull(profesional, "El profesional no puede ser nulo");
        if (ESTADO_CANCELADO.equals(estado) || ESTADO_FINALIZADO.equals(estado)
                || ESTADO_EN_ATENCION.equals(estado)) {
            throw new IllegalStateException("No se puede asignar un profesional en estado " + estado);
        }
        if (fechaProgramada != null && !profesional.estaDisponible(fechaProgramada, this)) {
            throw new IllegalStateException("El profesional ya tiene un servicio activo en esa fecha");
        }

        setProfesionalSalud(profesional);
    }

    public void iniciarAtencion() {
        if (!ESTADO_PROGRAMADO.equals(estado) || paciente == null || profesionalSalud == null) {
            throw new IllegalStateException(
                    "Para iniciar la atención, el servicio debe estar programado y tener paciente y profesional asignados"
            );
        }
        if (atencionMedica == null) {
            atencionMedica = new AtencionMedica();
        }
        atencionMedica.setFechaHoraInicio(LocalDateTime.now());
        estado = ESTADO_EN_ATENCION;
    }

    public void finalizar() {
        if (!ESTADO_EN_ATENCION.equals(estado) || atencionMedica == null) {
            throw new IllegalStateException("Solo se puede finalizar un servicio que está en atención");
        }
        atencionMedica.setFechaHoraFin(LocalDateTime.now());
        estado = ESTADO_FINALIZADO;
    }

    public void cancelar() {
        if (ESTADO_EN_ATENCION.equals(estado) || ESTADO_FINALIZADO.equals(estado)) {
            throw new IllegalStateException("No se puede cancelar un servicio en estado " + estado);
        }
        estado = ESTADO_CANCELADO;
    }

    public String generarReporte() {
        if (paciente == null || profesionalSalud == null || atencionMedica == null) {
            throw new IllegalStateException("El servicio debe tener paciente, profesional y atención para generar el reporte");
        }

        StringBuilder reporte = new StringBuilder()
                .append("========== REPORTE DE ATENCIÓN DOMICILIARIA ==========\n")
                .append("Servicio: ").append(valor(codigo)).append('\n')
                .append("Estado: ").append(valor(estado)).append('\n')
                .append("Paciente: ").append(valor(paciente.getNombre()))
                .append(" (").append(valor(paciente.getIdentificacion())).append(")\n")
                .append("Profesional: ").append(valor(profesionalSalud.getNombre()))
                .append(" - ").append(valor(profesionalSalud.getEspecialidad())).append('\n')
                .append("Fecha programada: ").append(formatear(fechaProgramada)).append('\n')
                .append("Dirección: ").append(valor(direccionAtencion)).append('\n')
                .append("Motivo: ").append(valor(motivo)).append('\n')
                .append("Inicio de atención: ").append(formatear(atencionMedica.getFechaHoraInicio())).append('\n')
                .append("Fin de atención: ").append(formatear(atencionMedica.getFechaHoraFin())).append('\n')
                .append("Observaciones: ").append(valor(atencionMedica.getObservaciones())).append('\n')
                .append("Recomendaciones: ").append(valor(atencionMedica.getRecomendaciones())).append('\n')
                .append("Mediciones de signos vitales: ")
                .append(atencionMedica.getMedicionesSignosVitales().size()).append('\n');

        for (MedicionSignosVitales medicion : atencionMedica.getMedicionesSignosVitales()) {
            reporte.append("  - ").append(formatear(medicion.getFechaHora()))
                    .append(": temperatura ").append(medicion.getTemperatura()).append(" °C, ")
                    .append("frecuencia cardíaca ").append(medicion.getFrecuenciaCardiaca()).append(" lpm, ")
                    .append("presión arterial ").append(medicion.getFrecuenciaSistolica()).append('/')
                    .append(medicion.getPresionDiastolica()).append(" mmHg, ")
                    .append("saturación de oxígeno ").append(medicion.getSaturacionOxigeno()).append(" %.\n");
        }

        return reporte.append("======================================================\n").toString();
    }

    private static String formatear(LocalDateTime fecha) {
        return fecha == null ? "No registrada" : fecha.format(FORMATO_FECHA);
    }

    private static String valor(String texto) {
        return texto == null || texto.isBlank() ? "No registrado" : texto;
    }
}
