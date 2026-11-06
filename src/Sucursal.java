import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Sucursal {
    private String id;
    private String nombre;
    private List<Envio> enviosRegistrados = new ArrayList<>();

    public Sucursal(String id, String nombre) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    public void recibirEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.recibir(this);
        if (!enviosRegistrados.contains(envio)) {
            enviosRegistrados.add(envio);
        }
    }

    public void despacharEnvio(Envio envio) {
        validarEnvioRegistrado(envio);
        envio.despachar(this);
    }

    public void entregarEnvio(Envio envio) {
        validarEnvioRegistrado(envio);
        envio.entregar(this);
    }

    public void procesarDevolucion(Envio envio, String motivoFallo) {
        validarEnvioRegistrado(envio);
        envio.devolver(this, motivoFallo);
    }

    // 4. Excepción: Envío inexistente en el registro de la sucursal
    private void validarEnvioRegistrado(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        if (!enviosRegistrados.contains(envio)) {
            throw new NoSuchElementException("Envío inexistente: el envío no se encuentra registrado en la sucursal " + this.nombre);
        }
    }

    public List<Envio> getEnviosRegistrados() {
        return new ArrayList<>(enviosRegistrados);
    }
}