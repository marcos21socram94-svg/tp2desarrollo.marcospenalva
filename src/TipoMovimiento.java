/// Representa los diferentes tipos de movimientos que un envío puede registrar en su historial.
public enum TipoMovimiento {
    RECIBIDO_EN_SUCURSAL("Recibido en sucursal"), 
    DESPACHADO_DE_SUCURSAL("Despachado de sucursal"), 
    EN_DISTRIBUCION("En distribución"), 
    ENTREGADO("Entregado"), 
    DEVUELTO("Devuelto");

    private final String descripcion;

    TipoMovimiento(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}