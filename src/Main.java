public class Main {
    public static void main(String[] args) {
        // 1. Cliente y Sucursales
        Cliente cliente1 = new Cliente("CLI-01", "38123456", "Marcos", "Peñalva", "Calle Falsa 123", "2944000000");
        Sucursal sucElBolson = new Sucursal("SUC-01", "El Bolsón");
        Sucursal sucBariloche = new Sucursal("SUC-02", "Bariloche");

        //  Envío Exito
        Paquete p1 = new Paquete("PAQ-01", "Notebook", 2.5, 5, 30, 40);
        Envio envioExitoso = new EnvioEstandar(120, "Bariloche", cliente1);
        envioExitoso.agregarPaquete(p1);
        cliente1.registrarEnvio(envioExitoso);

        sucElBolson.recibirEnvio(envioExitoso);
        sucElBolson.despacharEnvio(envioExitoso);
        sucBariloche.recibirEnvio(envioExitoso);

        // Simulamos que la entrega FUE EXITOSA (true, y null en motivo)
        sucBariloche.procesarEntrega(envioExitoso, true, null);

        System.out.println("--- HISTORIAL ENVÍO EXITOSO ---");
        for (Movimiento m : envioExitoso.obtenerHistorialCompleto()) {
            System.out.println(m);
        }
        System.out.println("Estado final paquete: " + p1.getActualEstado() + "\n");

        // Envío Fallido
        Paquete p2 = new Paquete("PAQ-02", "Documentos Urgentísimos", 0.5, 2, 15, 20);
        Envio envioFallido = new EnvioExpress(120, "Bariloche", cliente1);
        envioFallido.agregarPaquete(p2);
        cliente1.registrarEnvio(envioFallido);

        sucElBolson.recibirEnvio(envioFallido);
        sucElBolson.despacharEnvio(envioFallido);
        sucBariloche.recibirEnvio(envioFallido);

        // Simulamos que la entrega fallo
        sucBariloche.procesarEntrega(envioFallido, false, "Domicilio cerrado / Destinatario ausente");

        System.out.println("--- HISTORIAL ENVÍO FALLIDO (DEVOLUCIÓN) ---");
        for (Movimiento m : envioFallido.obtenerHistorialCompleto()) {
            System.out.println(m);
        }
        System.out.println("Estado final paquete: " + p2.getActualEstado());
    }
}