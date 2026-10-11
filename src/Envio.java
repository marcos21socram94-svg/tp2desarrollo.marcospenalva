import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

public abstract class Envio {
    protected double distanciaKm;
    protected String destino;
    protected Cliente cliente;
    protected List<Paquete> paquetes = new ArrayList<>();
    protected List<Movimiento> historial = new ArrayList<>();
    protected EstadoEnvio estadoActual = EstadoEnvio.EN_PREPARACION;

    protected static final double TARIFA_BASE_KM = 50.0;
    protected static final double TARIFA_PESO_KG = 100.0;

    public Envio(double distanciaKm, String destino, Cliente cliente) {
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }
        this.distanciaKm = distanciaKm;
        this.destino = Objects.requireNonNull(destino, "El destino no puede ser nulo");
        this.cliente = Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
    }

    public abstract double calcularCostoTotal();

    protected double calcularCostoBase() {
        double costoCarga = 0;
        for (Paquete p : paquetes) {
            costoCarga += p.getPeso() * TARIFA_PESO_KG;
        }
        return (this.distanciaKm * TARIFA_BASE_KM) + costoCarga;
    }

    public void agregarPaquete(Paquete paquete) {
        Objects.requireNonNull(paquete, "El paquete no puede ser nulo");
        this.paquetes.add(paquete);
    }

    public void registrarMovimiento(TipoMovimiento tipo, Sucursal sucursal) {
        Movimiento mov = new Movimiento(tipo, sucursal);
        this.historial.add(mov);
    }

    public void cambiarEstado(EstadoEnvio nuevoEstado) {
        Objects.requireNonNull(nuevoEstado, "El nuevo estado no puede ser nulo");
        if (!this.estadoActual.esTransicionValida(nuevoEstado)) {
            throw new IllegalStateException("Transición inválida de " + this.estadoActual + " a " + nuevoEstado);
        }
        this.estadoActual = nuevoEstado;
    }

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