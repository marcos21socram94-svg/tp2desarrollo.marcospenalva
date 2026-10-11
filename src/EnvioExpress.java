public class EnvioExpress extends Envio {
    public EnvioExpress(double distanciaKm, String destino, Cliente cliente) {
        super(distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase() * 1.5;
    }
}