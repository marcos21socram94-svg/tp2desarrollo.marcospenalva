///representa un envío estándar, que hereda de la clase Envio y calcula el costo total del envío sin aplicar descuentos adicionales.
public class EnvioEstandar extends Envio {
    public EnvioEstandar(double distanciaKm, String destino, Cliente cliente) {
        super(distanciaKm, destino, cliente);
    }
    
    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase(); 
    }
}   