import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Sucursal {
    private final String id;
    private final String nombre;

    // Parte 3: Cola de envíos pendientes de despacho (FIFO: First In, First Out)
    private final Queue<Envio> colaDespacho = new LinkedList<>();

    public Sucursal(String id, String nombre) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");

        if (id.trim().isEmpty() || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID y el nombre de la sucursal no pueden estar vacíos");
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    /**
     * Parte 3: Recibe un envío en la sucursal y lo agrega al final de la cola de espera.
     */
    public void recibirEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.recibir(this);      // El propio envío cambia su estado y registra el movimiento
        colaDespacho.add(envio);  // Entra al final de la cola FIFO
    }

    /**
     * Parte 3: Despacha hasta "cantidad" envíos respetando el orden de llegada.
     * Si hay menos envíos que la capacidad, salen los que haya. Si está vacía, lo informa.
     */
    public void despachar(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a despachar debe ser mayor a 0");
        }
        if (colaDespacho.isEmpty()) {
            System.out.println("Aviso en sucursal " + this.nombre + ": La cola de despacho está vacía.");
            return;
        }

        int despachados = 0;
        while (!colaDespacho.isEmpty() && despachados < cantidad) {
            Envio envio = colaDespacho.poll(); // Saca el primero que llegó a la fila
            if (envio != null) {
                envio.despachar(this);
                despachados++;
            }
        }
        System.out.println("Sucursal " + this.nombre + ": Se despacharon " + despachados + " envíos.");
    }

    /**
     * CORRECCIÓN 3: Entrega el envío en destino y lo retira de la cola de despacho
     * para que no intente subirse a otro camión más adelante.
     */
    public void entregarEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.entregar(this);
        colaDespacho.remove(envio); // Se quita de la fila de envíos pendientes de despacho
    }

    /**
     * CORRECCIÓN 3: Procesa la devolución y retira el envío de la cola de despacho.
     */
    public void procesarDevolucion(Envio envio, String motivoFallo) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.devolver(this, motivoFallo);
        colaDespacho.remove(envio); // Se quita de la fila de envíos pendientes de despacho
    }

    public int obtenerCantidadEnCola() {
        return colaDespacho.size();
    }
}