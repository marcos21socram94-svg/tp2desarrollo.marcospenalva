public enum EstadoEnvio {
    EN_PREPARACION("En preparación"), 
    DESPACHADO("Despachado"), 
    EN_TRANSITO("En tránsito"), 
    ENTREGADO("Entregado"), 
    DEVUELTO("Devuelto");

    private final String descripcion;

    EstadoEnvio(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}