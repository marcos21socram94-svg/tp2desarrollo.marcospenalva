import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class Envio {
    // CORRECCIÓN 2: Código de seguimiento único para usar como clave en el HashMap<Integer, Envio>
    protected final int codigo;
    protected double distanciaKm;
    protected String destino;
    protected Cliente cliente;
    protected List<Paquete> paquetes = new ArrayList<>();
    protected List<Movimiento> historial = new ArrayList<>();
    protected EstadoEnvio estadoActual = EstadoEnvio.EN_PREPARACION;

    protected static final double TARIFA_BASE_KM = 50.0;
    protected static final double TARIFA_PESO_KG = 100.0;
    private static final int LIMITE_PAQUETES = 3;

    public Envio(int codigo, double distanciaKm, String destino, Cliente cliente) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código de seguimiento debe ser un número positivo");
        }
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }
        this.destino = Objects.requireNonNull(destino, "El destino no puede ser nulo");
        this.cliente = Objects.requireNonNull(cliente, "El cliente no puede ser nulo");

        if (this.destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío");
        }

        this.codigo = codigo;
        this.distanciaKm = distanciaKm;
    }

    // Getter del código de seguimiento para que RegistroCentral lo obtenga directamente del objeto
    public int getCodigo() {
        return codigo;
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
        if (!todaviaNoSalio()) {
            throw new IllegalStateException("No se pueden agregar paquetes: el envío ya salió de la etapa de preparación.");
        }
        if (this.paquetes.size() >= LIMITE_PAQUETES) {
            throw new IllegalStateException("No se pueden agregar más de " + LIMITE_PAQUETES + " paquetes al envío.");
        }
        this.paquetes.add(paquete);
    }

    private void registrarMovimiento(TipoMovimiento tipo, Sucursal sucursal) {
        Objects.requireNonNull(tipo, "El tipo de movimiento no puede ser nulo");
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");
        this.historial.add(new Movimiento(tipo, sucursal));
    }

    // --- ACCIONES DEL NEGOCIO ---

    public void recibir(Sucursal sucursal) {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");

        if (!tienePaquetesCargados()) {
            throw new IllegalStateException("Envío sin paquetes: no se puede recibir un envío vacío.");
        }
        if (estaFinalizado()) {
            throw new IllegalStateException("Etapa inválida: el envío ya se encuentra finalizado (" + estadoActual + ").");
        }

        if (todaviaNoSalio()) {
            // Primer ingreso en la sucursal de origen
            this.estadoActual = EstadoEnvio.EN_TRANSITO;
            registrarMovimiento(TipoMovimiento.RECIBIDO_EN_SUCURSAL, sucursal);
        } else if (this.estadoActual == EstadoEnvio.DESPACHADO) {
            // Ingreso en una sucursal de escala o destino tras haber sido despachado
            Optional<Movimiento> ultimo = obtenerUltimoMovimiento();
            if (ultimo.isPresent() && ultimo.get().getSucursal().equals(sucursal)) {
                throw new IllegalStateException("Movimiento incoherente: no se puede recibir en la misma sucursal desde la que se despachó.");
            }
            this.estadoActual = EstadoEnvio.EN_TRANSITO;
            registrarMovimiento(TipoMovimiento.RECIBIDO_EN_SUCURSAL, sucursal);
        } else {
            throw new IllegalStateException("Movimiento incoherente: no se puede recibir dos veces seguidas sin despacho previo.");
        }
    }

    public void despachar(Sucursal sucursal) {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");

        if (!tienePaquetesCargados()) {
            throw new IllegalStateException("Envío sin paquetes: no se puede despachar un envío vacío.");
        }
        if (estaFinalizado() || this.estadoActual != EstadoEnvio.EN_TRANSITO) {
            throw new IllegalStateException("Movimiento incoherente: el envío debe estar recibido en la sucursal antes de despacharse.");
        }

        Optional<Movimiento> ultimo = obtenerUltimoMovimiento();
        if (ultimo.isPresent() && !ultimo.get().getSucursal().equals(sucursal)) {
            throw new IllegalStateException("Movimiento incoherente: no se puede despachar desde una sucursal distinta a donde se recibió.");
        }

        this.estadoActual = EstadoEnvio.DESPACHADO;
        registrarMovimiento(TipoMovimiento.DESPACHADO_DE_SUCURSAL, sucursal);

        for (Paquete p : paquetes) {
            p.enviar();
        }
    }

    public void entregar(Sucursal sucursal) {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");

        if (!tienePaquetesCargados()) {
            throw new IllegalStateException("Envío sin paquetes: no se puede entregar.");
        }
        if (estaFinalizado() || this.estadoActual != EstadoEnvio.EN_TRANSITO) {
            throw new IllegalStateException("Etapa inválida: el envío debe haber sido recibido en la sucursal de destino para entregarse.");
        }

        this.estadoActual = EstadoEnvio.ENTREGADO;
        registrarMovimiento(TipoMovimiento.ENTREGADO, sucursal);

        for (Paquete p : paquetes) {
            p.entregar();
        }
    }

    public void devolver(Sucursal sucursal, String motivoFallo) {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");
        if (motivoFallo == null || motivoFallo.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo de fallo no puede estar vacío.");
        }
        if (estaFinalizado() || this.estadoActual != EstadoEnvio.EN_TRANSITO) {
            throw new IllegalStateException("Etapa inválida: solo se puede devolver un envío que está en tránsito en la sucursal.");
        }

        this.estadoActual = EstadoEnvio.DEVUELTO;
        registrarMovimiento(TipoMovimiento.DEVUELTO, sucursal);

        for (Paquete p : paquetes) {
            p.registrarDevolucion();
        }
    }

    // --- CONSULTAS INTERNAS Y GETTERS ---

    protected boolean estaFinalizado() {
        return this.estadoActual == EstadoEnvio.ENTREGADO || this.estadoActual == EstadoEnvio.DEVUELTO;
    }

    protected boolean todaviaNoSalio() {
        return this.estadoActual == EstadoEnvio.EN_PREPARACION;
    }

    protected boolean tienePaquetesCargados() {
        return !this.paquetes.isEmpty();
    }

    public EstadoEnvio getEstadoActual() { return estadoActual; }
    public String getDestino() { return destino; }

    public Optional<Movimiento> obtenerUltimoMovimiento() {
        if (historial.isEmpty()) return Optional.empty();
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

    public List<Paquete> getPaquetes() { return new ArrayList<>(paquetes); }
    public Cliente getCliente() { return cliente; }
}