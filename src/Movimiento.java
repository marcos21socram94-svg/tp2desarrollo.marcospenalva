import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
/// Representa un movimiento realizado sobre un envío, incluyendo la fecha y hora, la acción realizada y la sucursal involucrada.
public class Movimiento {
    private final LocalDateTime fechaHora;
    private final String accion;
    private final String nombreSucursal;
/// Constructor que inicializa un movimiento con la acción y el nombre de la sucursal, asignando automáticamente la fecha y hora actual.
    public Movimiento(String accion, String nombreSucursal) {
        Objects.requireNonNull(accion, "La acción no puede ser nula");
        Objects.requireNonNull(nombreSucursal, "El nombre de la sucursal no puede ser nulo");
/// Validación de campos
        if (accion.trim().isEmpty() || nombreSucursal.trim().isEmpty()) {
            throw new IllegalArgumentException("La acción y la sucursal no pueden estar vacías");
        }
// Asignación de fecha y hora actual
        this.fechaHora = LocalDateTime.now(); // Asignación automática de timestamp
        this.accion = accion;
        this.nombreSucursal = nombreSucursal;
    }

    // Getters únicamente (sin setters para mantener inmutabilidad)
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getAccion() { return accion; }
    public String getNombreSucursal() { return nombreSucursal; }

/// Método toString para representar el movimiento de manera legible
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + fechaHora.format(fmt) + "] - " + accion + " en " + nombreSucursal;
    }
}