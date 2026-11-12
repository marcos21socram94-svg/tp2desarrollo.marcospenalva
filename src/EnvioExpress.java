public class EnvioExpress extends Envio {
    public EnvioExpress(int codigo, double distanciaKm, String destino, Cliente cliente) {
        super(codigo, distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase() * 1.5;
    }
}