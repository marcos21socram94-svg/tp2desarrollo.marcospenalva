import java.util.Objects;

/// Representa una sucursal de la empresa de envíos con un identificador y un nombre.
public class Sucursal {
    private String id;
    private String nombre;

    public Sucursal(String id, String nombre) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
    }
/// Getters para acceder a los atributos de la sucursal
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
/// Recibe un envío en la sucursal, cambiando su estado a EN_TRANSITO si estaba DESPACHADO y registrando el movimiento correspondiente.
    public void recibirEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        
        // Si el envío está despachado, al recibirlo en destino pasa a EN_TRANSITO de forma válida
        if (envio.getEstadoActual() == EstadoEnvio.DESPACHADO) {
            envio.cambiarEstado(EstadoEnvio.EN_TRANSITO);
        }
        
        envio.registrarMovimiento(TipoMovimiento.RECIBIDO_EN_SUCURSAL, this);
    }
/// Despacha un envío desde la sucursal, cambiando su estado a DESPACHADO y registrando el movimiento correspondiente.
    public void despacharEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.cambiarEstado(EstadoEnvio.DESPACHADO);
        envio.registrarMovimiento(TipoMovimiento.DESPACHADO_DE_SUCURSAL, this);
    }
/// Procesa la entrega de un envío, cambiando su estado a ENTREGADO o DEVUELTO según el resultado y registrando el movimiento correspondiente.
    public void procesarEntrega(Envio envio, boolean fueExitosa, String motivoFallo) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        if (fueExitosa) {
            envio.cambiarEstado(EstadoEnvio.ENTREGADO);
            envio.registrarMovimiento(TipoMovimiento.ENTREGADO, this);
        } else {
            envio.cambiarEstado(EstadoEnvio.DEVUELTO);
            envio.registrarMovimiento(TipoMovimiento.DEVUELTO, this);
        }
    }
}