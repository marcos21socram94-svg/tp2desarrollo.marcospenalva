public enum EstadoEnvio {
    EN_PREPARACION, 
    DESPACHADO, 
    EN_TRANSITO, 
    ENTREGADO, 
    DEVUELTO;

    /// La regla de transición queda encapsulada dentro del propio enum
    public boolean esTransicionValida(EstadoEnvio nuevoEstado) {
        switch (this) {
            case EN_PREPARACION:
                return nuevoEstado == DESPACHADO;
            case DESPACHADO:
                return nuevoEstado == EN_TRANSITO || nuevoEstado == ENTREGADO;
            case EN_TRANSITO:
                return nuevoEstado == ENTREGADO || nuevoEstado == DEVUELTO;
            default:
                return false; // Estados terminales
        }
    }
}