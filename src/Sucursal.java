import java.util.Objects;
////representa la sucursal de la empresa, encargada de recibir, despachar y entregar envíos/////
public class Sucursal {
    private final String id;
    private final String nombre;

    public Sucursal(String id, String nombre) { 
        Objects.requireNonNull(id, "El id no puede ser nulo");
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");

        if (id.trim().isEmpty() || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El id y el nombre no pueden estar vacíos");
        }

        this.id = id;
        this.nombre = nombre;
    }

    /// Recepción del paquete (origen o intermediario)
    public void recibirEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.registrarMovimiento("RECIBIDO EN SUCURSAL", this.nombre);
    }

    /// Envío o despacho del paquete (destino o intermediario)
    public void despacharEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.iniciarViaje(); // Cambia el estado de los paquetes internos
        envio.registrarMovimiento("DESPACHADO DESDE SUCURSAL", this.nombre);
    }

    /// Entrega del paquete al destinatario
    public void entregarEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.entregar(); // Cambia el estado de los paquetes internos
        envio.registrarMovimiento("ENTREGADO AL DESTINATARIO", this.nombre);
    }

    /// Registro de entrega fallida del paquete al destinatario
    public void registrarEnvioFallido(Envio envio, String motivo) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        Objects.requireNonNull(motivo, "El motivo no puede ser nulo");
        
        if (motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe especificar el motivo de la entrega fallida");
        }

        envio.registrarDevolucion(); // Registra la devolución en los paquetes
        envio.registrarMovimiento("ENTREGA FALLIDA (" + motivo + ")", this.nombre);
    }

    /// Procesa la entrega evaluando el resultado de la visita
    public void procesarEntrega(Envio envio, boolean fueExitosa, String motivoFallo) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        
        if (fueExitosa) {
            this.entregarEnvio(envio);
        } else {
            this.registrarEnvioFallido(envio, motivoFallo);
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }    
}