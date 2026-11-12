public class EnvioEstandar extends Envio {
    public EnvioEstandar(int codigo, double distanciaKm, String destino, Cliente cliente) {
        super(codigo, distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase();
    }
}