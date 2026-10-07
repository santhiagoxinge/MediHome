public class Paciente extends Usuario implements INotificable{

    private String telefono;
    private String direccion;

    public Paciente() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println(mensaje);
    }
}
