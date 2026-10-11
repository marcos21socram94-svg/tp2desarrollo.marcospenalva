import java.util.Objects;

public class Sucursal {
    private String id;
    private String nombre;

    public Sucursal(String id, String nombre) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        
        // Si el envío está despachado, al recibirlo en destino pasa a EN_TRANSITO de forma válida
        if (envio.getEstadoActual() == EstadoEnvio.DESPACHADO) {
            envio.cambiarEstado(EstadoEnvio.EN_TRANSITO);
        }
        
        envio.registrarMovimiento(TipoMovimiento.RECIBIDO_EN_SUCURSAL, this);
    }

    public void despacharEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.cambiarEstado(EstadoEnvio.DESPACHADO);
        envio.registrarMovimiento(TipoMovimiento.DESPACHADO_DE_SUCURSAL, this);
    }

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