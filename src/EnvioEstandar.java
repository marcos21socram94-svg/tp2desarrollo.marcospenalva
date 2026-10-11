public class EnvioEstandar extends Envio {
    public EnvioEstandar(double distanciaKm, String destino, Cliente cliente) {
        super(distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase();
    }
}