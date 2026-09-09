///representa un envío express, que hereda de la clase Envio y calcula el costo total del envío con un recargo del 50%.
public class EnvioExpress extends Envio {
    public EnvioExpress(double distanciaKm, String destino, Cliente cliente) {
        super(distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase() * 1.5; 
    }
}