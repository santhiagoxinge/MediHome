import java.time.LocalDateTime;

public class MedicionSignosVitales {
    private LocalDateTime fechaHora;
    private double temperatura;
    private int frecuenciaCardiaca;
    private int frecuenciaSistolica;
    private int presionDiastolica;
    private double saturacionOxigeno;

    public MedicionSignosVitales() {

    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getFrecuenciaSistolica() {
        return frecuenciaSistolica;
    }

    public void setFrecuenciaSistolica(int frecuenciaSistolica) {
        this.frecuenciaSistolica = frecuenciaSistolica;
    }

    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(int presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public double getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(double saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public void realizarMedicion() {
        System.out.printf(
                "Medición de signos vitales: temperatura %.1f °C, frecuencia cardíaca %d lpm, "
                        + "presión arterial %d/%d mmHg, saturación de oxígeno %.1f %%."
                        + "%n",
                temperatura,
                frecuenciaCardiaca,
                frecuenciaSistolica,
                presionDiastolica,
                saturacionOxigeno
        );
    }
}
