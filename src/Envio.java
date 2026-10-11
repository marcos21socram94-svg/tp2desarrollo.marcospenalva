import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

/// Representa un envío genérico con atributos y métodos comunes a todos los tipos de envíos.
public abstract class Envio {
    protected double distanciaKm;
    protected String destino;
    protected Cliente cliente;
    protected List<Paquete> paquetes = new ArrayList<>();
    protected List<Movimiento> historial = new ArrayList<>();
    protected EstadoEnvio estadoActual = EstadoEnvio.EN_PREPARACION;

    protected static final double TARIFA_BASE_KM = 50.0;
    protected static final double TARIFA_PESO_KG = 100.0;

    /// Constructor que inicializa un envío con la distancia, destino y cliente.
    public Envio(double distanciaKm, String destino, Cliente cliente) {
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }
        this.distanciaKm = distanciaKm;
        this.destino = Objects.requireNonNull(destino, "El destino no puede ser nulo");
        this.cliente = Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
    }

    /// Método abstracto que debe ser implementado por las subclases para calcular el costo total del envío.
    public abstract double calcularCostoTotal();

    protected double calcularCostoBase() {
        double costoCarga = 0;
        for (Paquete p : paquetes) {
            costoCarga += p.getPeso() * TARIFA_PESO_KG;
        }
        return (this.distanciaKm * TARIFA_BASE_KM) + costoCarga;
    }
/// Agrega un paquete al envío, asegurando que no sea nulo.
    public void agregarPaquete(Paquete paquete) {
        Objects.requireNonNull(paquete, "El paquete no puede ser nulo");
        this.paquetes.add(paquete);
    }
/// Registra un movimiento en el historial del envío, asegurando que el tipo y la sucursal no sean nulos.
    public void registrarMovimiento(TipoMovimiento tipo, Sucursal sucursal) {
        Movimiento mov = new Movimiento(tipo, sucursal);
        this.historial.add(mov);
    }
/// Cambia el estado actual del envío, asegurando que la transición sea válida según las reglas de negocio.
    public void cambiarEstado(EstadoEnvio nuevoEstado) {
        Objects.requireNonNull(nuevoEstado, "El nuevo estado no puede ser nulo");
        if (!this.estadoActual.esTransicionValida(nuevoEstado)) {
            throw new IllegalStateException("Transición inválida de " + this.estadoActual + " a " + nuevoEstado);
        }
        this.estadoActual = nuevoEstado;
    }
/// Getters y métodos de utilidad para acceder a los atributos del envío y su historial.
    public EstadoEnvio getEstadoActual() {
        return estadoActual;
    }

    public Optional<Movimiento> obtenerUltimoMovimiento() {
        if (historial.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(historial.get(historial.size() - 1));
    }

    public List<Movimiento> obtenerHistorialCompleto() {
        return new ArrayList<>(this.historial);
    }

    public List<Sucursal> obtenerSucursalesVisitadas() {
        List<Sucursal> lista = new ArrayList<>();
        for (Movimiento m : historial) {
            if (m.getSucursal() != null && !lista.contains(m.getSucursal())) {
                lista.add(m.getSucursal());
            }
        }
        return lista;
    }
    
    protected boolean estaFinalizado() {
        return this.estadoActual == EstadoEnvio.ENTREGADO || this.estadoActual == EstadoEnvio.DEVUELTO;
    }

    protected boolean todaviaNoSalio() {
        return this.estadoActual == EstadoEnvio.EN_PREPARACION;
    }

    protected boolean tienePaquetesCargados() {
        return !this.paquetes.isEmpty();
    }

    public List<Paquete> getPaquetes() {
        return new ArrayList<>(paquetes);
    }

    public Cliente getCliente() {
        return cliente;
    }
}