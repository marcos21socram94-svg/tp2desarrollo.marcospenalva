import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;

public class Sucursal {
    private String id;
    private String nombre;
    // La lista de "enviosRegistrados" ahora es una COLA FIFO
    private Queue<Envio> colaDespacho = new LinkedList<>();

    public Sucursal(String id, String nombre) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        
        if (id.trim().isEmpty() || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID y el nombre de la sucursal no pueden estar vacíos");
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    // El envío ingresa AL FINAL de la cola
    public void recibirEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.recibir(this);
        colaDespacho.add(envio); // add() lo pone al final en una LinkedList
    }

    // Despacha hasta un máximo de "cantidad" respetando el orden (FIFO)
    public void despachar(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a despachar debe ser mayor a 0");
        }
        if (colaDespacho.isEmpty()) {
            System.out.println("Aviso en " + this.nombre + ": La cola de despacho está vacía.");
            return;
        }

        int despachados = 0;
        // Sacamos envíos mientras haya en la cola y no superemos la capacidad del camión
        while (!colaDespacho.isEmpty() && despachados < cantidad) {
            Envio envio = colaDespacho.poll(); // poll() saca y devuelve el primero de la fila
            if (envio != null) {
                envio.despachar(this);
                despachados++;
            }
        }
        System.out.println("Se despacharon " + despachados + " envíos desde " + this.nombre);
    }

    public void entregarEnvio(Envio envio) {
        // En una entrega directa (llega el cliente al mostrador), solo validamos que exista
        // pero no usamos la cola de despacho de camiones
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.entregar(this);
    }

    public void procesarDevolucion(Envio envio, String motivoFallo) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.devolver(this, motivoFallo);
    }
}