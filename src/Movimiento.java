import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/// Representa un movimiento realizado sobre un envío utilizando objetos de dominio estrictos.
public class Movimiento {
    private final LocalDateTime fechaHora;
    private final TipoMovimiento tipo;
    private final Sucursal sucursal;

    /// Constructor que inicializa un movimiento con el tipo de movimiento y el objeto Sucursal.
    public Movimiento(TipoMovimiento tipo, Sucursal sucursal) {
        this.tipo = Objects.requireNonNull(tipo, "El tipo de movimiento no puede ser nulo");
        this.sucursal = Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");
        this.fechaHora = LocalDateTime.now(); // Asignación automática de timestamp
    }

    // Getters actualizados (sin setters para mantener inmutabilidad)
    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    }
    
    public TipoMovimiento getTipo() { 
        return tipo; 
    }
    
    public Sucursal getSucursal() { 
        return sucursal; 
    }

    /// Método toString adaptado a los nuevos objetos de dominio
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + fechaHora.format(fmt) + "] - " + tipo + " en " + sucursal.getNombre();
    }
}