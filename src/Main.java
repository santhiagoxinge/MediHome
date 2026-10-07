import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Paciente paciente = new Paciente();
        paciente.setIdentificacion("1020304050");
        paciente.setNombre("Laura Gómez");
        paciente.setCorreo("laura.gomez@example.com");
        paciente.setTelefono("3001234567");
        paciente.setDireccion("Calle 10 # 20-30");

        ProfesionalSalud profesional = new ProfesionalSalud();
        profesional.setIdentificacion("987654321");
        profesional.setNombre("Andrés Pérez");
        profesional.setCorreo("andres.perez@example.com");
        profesional.setNumeroRegistroProfesional("RM-12345");
        profesional.setEspecialidad("Medicina general");

        EquipoAtencion equipo = new EquipoAtencion();
        equipo.setCodigo("EQ-01");
        equipo.setNombre("Equipo Norte");
        equipo.setZonaCobertura("Zona norte");
        equipo.agregarProfesional(profesional);

        ServicioDomiciliario servicio = new ServicioDomiciliario();
        servicio.setCodigo("SD-001");
        servicio.setPaciente(paciente);
        servicio.setDireccionAtencion(paciente.getDireccion());
        servicio.setMotivo("Examen médico domiciliario");
        servicio.programar(LocalDateTime.now());
        servicio.asignarProfesional(profesional);
        paciente.notificar("Se ha programado su servicio domiciliario " + servicio.getCodigo() + ".");
        profesional.notificar("Tiene asignado el servicio domiciliario " + servicio.getCodigo() + ".");

        AtencionMedica atencion = new AtencionMedica();
        atencion.setObservaciones("Paciente estable. Sin hallazgos clínicos relevantes.");
        atencion.setRecomendaciones("Mantener hidratación y continuar los controles indicados.");
        servicio.setAtencionMedica(atencion);
        servicio.iniciarAtencion();

        MedicionSignosVitales medicion = new MedicionSignosVitales();
        medicion.setFechaHora(LocalDateTime.now());
        medicion.setTemperatura(36.7);
        medicion.setFrecuenciaCardiaca(72);
        medicion.setFrecuenciaSistolica(120);
        medicion.setPresionDiastolica(80);
        medicion.setSaturacionOxigeno(98.5);
        atencion.agregarMedicion(medicion);
        medicion.realizarMedicion();

        servicio.finalizar();
        System.out.println(servicio.generarReporte());
    }
}
