import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/////representa la clase de envio en la empresa/////
public abstract class Envio {
    /////atributos de la clase/////
    private final List<Paquete> paquetes = new ArrayList<>();
    private final List<Movimiento> historial = new ArrayList<>(); // Trazabilidad registrada por la Sucursal
    private final double distanceKm;
    private final String destino;
    private final Cliente cliente; /// Relación bidireccional obligatoria
///tarifas base por km y por kg de peso/////
    protected static final double TARIFA_BASE_KM = 100.0;
    protected static final double TARIFA_PESO_KG = 1.8;
///constructor de la clase con validacion de parametros/////
    public Envio(double distanciaKm, String destino, Cliente cliente) {
        Objects.requireNonNull(destino, "El destino no puede ser nulo");
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");

        if (distanciaKm <= 0) throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        if (destino.trim().isEmpty()) throw new IllegalArgumentException("El destino no puede estar vacío");

        this.distanceKm = distanciaKm;
        this.destino = destino;
        this.cliente = cliente;
    }
////agregar paquete al envio con validacion de estado y cantidad maxima de paquetes/////
    public void agregarPaquete(Paquete p) {
        Objects.requireNonNull(p, "El paquete no puede ser nulo");
        if (this.paquetes.size() >= 3) throw new IllegalStateException("Máximo 3 paquetes por envío.");
        if (p.getActualEstado() != Paquete.Estado.EN_PREPARACION) {
            throw new IllegalStateException("El paquete no está en preparación.");
        }
        this.paquetes.add(p);
    }

    // Métodos Tell, Don't Ask para que use la Sucursal, delegación de responsabilidades
    public boolean esVacio() {
        return this.paquetes.isEmpty();
    }

    public void iniciarViaje() {
        ejecutarSobrePaquetes(
                Paquete.Estado.EN_PREPARACION,
                Paquete::enviar,
                "Envío vacío.");
    }

    public void entregar() {
        ejecutarSobrePaquetes(
                Paquete.Estado.ENVIADO,
                Paquete::entregar,
                "Envío vacío.");
    }

    public void registrarDevolucion() {
        ejecutarSobrePaquetes(
                Paquete.Estado.ENVIADO,
                Paquete::registrarDevolucion,
                "Envío vacío.");
    }

    private void ejecutarSobrePaquetes(
            Paquete.Estado estadoEsperado,
            Consumer<Paquete> operacion,
            String mensajeEnvioVacio) {
        if (esVacio()) throw new IllegalStateException(mensajeEnvioVacio);

        for (Paquete paquete : paquetes) {
            if (paquete.getActualEstado() != estadoEsperado) {
                throw new IllegalStateException(
                        "El paquete no está en el estado requerido: " + estadoEsperado);
            }
        }

        for (Paquete paquete : paquetes) {
            operacion.accept(paquete);
        }
    }
    // Método para mostrar el historial de movimientos del envío
    public void mostrarHistorial() {
        System.out.println("--- Historial del Envío a " + destino + " ---");
        if (historial.isEmpty()) {
            System.out.println("Sin movimientos registrados.");
        } else {
            for (Movimiento m : historial) {
                System.out.println(m);
            }
        }
    }

    // Método para la trazabilidad de la Sucursal
    public void registrarMovimiento(String estado, String sucursalNombre) {
        this.historial.add(new Movimiento(estado, sucursalNombre));
    }
/// Método abstracto para calcular el costo total del envío, implementado por las subclases
    public abstract double calcularCostoTotal();
/// Método protegido para calcular el costo base compartido por las subclases
    protected double calcularCostoBase() {
        double costoCarga = 0;
        for (Paquete p : paquetes) { 
            costoCarga += p.getPeso() * TARIFA_PESO_KG; 
        }
        return (this.distanceKm * TARIFA_BASE_KM) + costoCarga;
    }
/// Getters para los atributos de la clase
    public String getDestino() { return destino; }
    public Cliente getCliente() { return cliente; }
    public List<Paquete> getPaquetes() { return new ArrayList<>(this.paquetes); }
    public List<Movimiento> getHistorial() { return new ArrayList<>(this.historial); }
}