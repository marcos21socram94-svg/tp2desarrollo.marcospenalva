public class Main {
    public static void main(String[] args) {
        // 1. Configuración inicial
        Cliente cliente1 = new Cliente("CLI-01", "38123456", "Marcos", "Peñalva", "Calle Falsa 123", "2944000000");
        Sucursal sucElBolson = new Sucursal("SUC-01", "El Bolsón");
        Sucursal sucBariloche = new Sucursal("SUC-02", "Bariloche");

        System.out.println("=========================================");
        System.out.println(" CASO 1: FLUJO EXITOSO (CAMINO FELIZ)");
        System.out.println("=========================================");
        
        Paquete p1 = new Paquete("PAQ-01", "Notebook", 2.5, 5, 30, 40);
        Envio envioExitoso = new EnvioEstandar(120, "Bariloche", cliente1);
        
        envioExitoso.agregarPaquete(p1);
        cliente1.registrarEnvio(envioExitoso);

        // Flujo coherente de estados
        sucElBolson.recibirEnvio(envioExitoso);      // Queda EN_TRANSITO (ingreso a la red)
        sucElBolson.despacharEnvio(envioExitoso);    // Queda DESPACHADO
        sucBariloche.recibirEnvio(envioExitoso);     // Queda EN_TRANSITO (llegó a destino)
        sucBariloche.entregarEnvio(envioExitoso);    // Queda ENTREGADO

        System.out.println("Historial del envío exitoso:");
        for (Movimiento m : envioExitoso.obtenerHistorialCompleto()) {
            System.out.println(m);
        }
        System.out.println("Estado del envío: " + envioExitoso.getEstadoActual());
        // Aquí el paquete también debe decir ENTREGADO por la coordinación
        System.out.println("Estado del paquete coordinado: " + p1.getActualEstado()); 


        System.out.println("\n=========================================");
        System.out.println(" CASO 2: PRUEBA DE EXCEPCIONES DEL DOMINIO");
        System.out.println("=========================================");

        // Prueba A: Envío sin paquetes
        System.out.println("\n--- Intentando recibir un envío sin paquetes ---");
        try {
            Envio envioVacio = new EnvioEstandar(50, "Bariloche", cliente1);
            sucElBolson.recibirEnvio(envioVacio);
        } catch (IllegalStateException e) {
            System.out.println("[ÉXITO] Excepción capturada: " + e.getMessage());
        }

        // Prueba B: Envío inexistente (no registrado en la sucursal)
        System.out.println("\n--- Intentando despachar un envío no registrado en Bariloche ---");
        try {
            Paquete p2 = new Paquete("PAQ-02", "Libro", 1.0, 5, 20, 15);
            Envio envioFantasma = new EnvioEstandar(80, "Bariloche", cliente1);
            envioFantasma.agregarPaquete(p2);
            
            // Lo recibe El Bolsón, pero intentamos despacharlo desde Bariloche
            sucElBolson.recibirEnvio(envioFantasma);
            sucBariloche.despacharEnvio(envioFantasma); 
        } catch (java.util.NoSuchElementException e) {
            System.out.println("[ÉXITO] Excepción capturada: " + e.getMessage());
        }

        // Prueba C: Movimiento incoherente (Recibir dos veces seguidas en la misma sucursal)
        System.out.println("\n--- Intentando recibir el mismo envío dos veces seguidas ---");
        try {
            Paquete p3 = new Paquete("PAQ-03", "Documentos", 0.5, 2, 10, 10);
            Envio envioIncoherente = new EnvioEstandar(100, "Bariloche", cliente1);
            envioIncoherente.agregarPaquete(p3);
            
            sucElBolson.recibirEnvio(envioIncoherente); // Primera recepción OK
            sucElBolson.recibirEnvio(envioIncoherente); // Segunda recepción sin despacho previo -> FALLA
        } catch (IllegalStateException e) {
            System.out.println("[ÉXITO] Excepción capturada: " + e.getMessage());
        }

        // Prueba D: Etapa inválida (Intentar agregar un paquete cuando ya salió)
        System.out.println("\n--- Intentando agregar un paquete a un envío ya despachado ---");
        try {
            Paquete p4 = new Paquete("PAQ-04", "Ropa", 2.0, 10, 30, 20);
            Envio envioApurado = new EnvioEstandar(200, "Bariloche", cliente1);
            envioApurado.agregarPaquete(p4);
            
            sucElBolson.recibirEnvio(envioApurado);
            sucElBolson.despacharEnvio(envioApurado);
            
            Paquete p5 = new Paquete("PAQ-05", "Zapatillas", 1.0, 10, 20, 15);
            envioApurado.agregarPaquete(p5); // Ya fue despachado, debería fallar
        } catch (IllegalStateException e) {
            System.out.println("[ÉXITO] Excepción capturada: " + e.getMessage());
        }
    }
}