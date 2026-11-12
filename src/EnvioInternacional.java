
///representa un envío internacional, que hereda de la clase Envio y calcula el costo total del envío con un recargo fijo.
public class EnvioInternacional extends Envio {
    public EnvioInternacional(int codigo, double distanciaKm, String destino, Cliente cliente) {
        super(codigo, distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase() + 7500.0; 
    }
}