////Clase Paquete con reglas de negocio////
public class Paquete {
    
    public enum Estado {
        EN_PREPARACION,
        ENVIADO,
        ENTREGADO,
        DEVUELTO   
    }
/////atributos de la clase////
    private final String id;
    private String descripcion;
    private double peso;
    private double alto;
    private double ancho;
    private double largo;
    private Estado actualEstado;
/////constructor de la clase////
    public Paquete(String id, String descripcion, double peso, double alto, double ancho, double largo) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("El ID no puede ser vacío.");
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.actualEstado = Estado.EN_PREPARACION; 
    }
/////constructor alternativo para crear un paquete sin dimensiones ni peso////
    public Paquete(String id, String descripcion) {
        this(id, descripcion, 0.0, 0.0, 0.0, 0.0);
    }

    // Reglas de negocio con Excepciones Defensivas////
    public void enviar() {
        if (actualEstado != Estado.EN_PREPARACION) {
            throw new IllegalStateException("Error: Solo se puede enviar un paquete en preparación.");
        }
        actualEstado = Estado.ENVIADO;
    }

    public void entregar() {
        if (actualEstado != Estado.ENVIADO) {
            throw new IllegalStateException("Error: Solo se puede entregar un paquete en viaje.");
        }
        actualEstado = Estado.ENTREGADO;
    }

    public void registrarDevolucion() {
        if (actualEstado != Estado.ENVIADO) {
            throw new IllegalStateException("Error: Solo se puede devolver un paquete en viaje.");
        }
        actualEstado = Estado.DEVUELTO;
    }

    public void actualizarPeso(double nuevoPeso) {
        if (this.actualEstado != Estado.EN_PREPARACION) {
            throw new IllegalStateException("Error: No se puede modificar el peso fuera de depósito.");
        }
        if (nuevoPeso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0.");
        }
        this.peso = nuevoPeso;
    }

    public void actualizarDimensiones(double alto, double ancho, double largo) {
        if (this.actualEstado != Estado.EN_PREPARACION) {
            throw new IllegalStateException("Error: Las dimensiones no pueden cambiar una vez enviado.");
        }
        if (alto <= 0 || ancho <= 0 || largo <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores a 0.");
        }
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
    }
/////getters y setters////
    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Estado getActualEstado() { return actualEstado; }
    public double getPeso() { return peso; }
    public double getAlto() { return alto; }
    public double getAncho() { return ancho; }
    public double getLargo() { return largo; }
}