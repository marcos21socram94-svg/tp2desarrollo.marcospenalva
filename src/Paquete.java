public class Paquete {
    
    public enum Estado {
        EN_PREPARACION("En preparación"),
        ENVIADO("Enviado"),
        ENTREGADO("Entregado"),
        DEVUELTO("Devuelto");
        
        private final String descripcion;

        Estado(String descripcion) {
            this.descripcion = descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }

    private final String id;
    private String descripcion;
    private double peso;
    private double alto;
    private double ancho;
    private double largo;
    private Estado actualEstado;

    public Paquete(String id, String descripcion, double peso, double alto, double ancho, double largo) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo ni vacío.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula ni vacía.");
        }
        
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.actualEstado = Estado.EN_PREPARACION; 
    }

    public Paquete(String id, String descripcion) {
        this(id, descripcion, 0.0, 0.0, 0.0, 0.0);
    }

    // Reglas de negocio con Excepciones Defensivas
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

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Estado getActualEstado() { return actualEstado; }
    public double getPeso() { return peso; }
    public double getAlto() { return alto; }
    public double getAncho() { return ancho; }
    public double getLargo() { return largo; }
}