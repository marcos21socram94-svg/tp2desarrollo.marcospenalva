public class Main {
    public static void main(String[] args) {
        // 1. Cliente y Sucursales
        Cliente cliente1 = new Cliente("CLI-01", "38123456", "Marcos", "Peñalva", "San Martín 450", "2944123456");
        Sucursal sucElBolson = new Sucursal("SUC-01", "El Bolsón");
        Sucursal sucBariloche = new Sucursal("SUC-02", "Bariloche");

        // --- CASO 1: Envío Exitoso ---
        Paquete p1 = new Paquete("PAQ-01", "Notebook", 2.5, 5, 30, 20);
        Envio envioExitoso = new EnvioEstandar(120, "Bariloche", cliente1);
        envioExitoso.agregarPaquete(p1);
        cliente1.registrarEnvio(envioExitoso);

        sucElBolson.recibirEnvio(envioExitoso);
        sucElBolson.despacharEnvio(envioExitoso);
        sucBariloche.recibirEnvio(envioExitoso);
        
        // Simulamos que la entrega FUE EXITOSA (true)
        sucBariloche.procesarEntrega(envioExitoso, true, null);
        
        envioExitoso.mostrarHistorial();
        System.out.println("Estado final paquete: " + p1.getActualEstado() + "\n");

        // --- CASO 2: Envío Fallido (Devolución) ---
        Paquete p2 = new Paquete("PAQ-02", "Documentos Urgentísimos", 0.5, 2, 10, 15);
        Envio envioFallido = new EnvioExpress(120, "Bariloche", cliente1);
        envioFallido.agregarPaquete(p2);
        cliente1.registrarEnvio(envioFallido);

        sucElBolson.recibirEnvio(envioFallido);
        sucElBolson.despacharEnvio(envioFallido);
        sucBariloche.recibirEnvio(envioFallido);
        
        // Simulamos que la entrega FALLÓ (false)
        sucBariloche.procesarEntrega(envioFallido, false, "Domicilio cerrado / Cliente ausente");
        
        envioFallido.mostrarHistorial();
        System.out.println("Estado final paquete: " + p2.getActualEstado());
    }
}