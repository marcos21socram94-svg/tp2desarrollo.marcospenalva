public class Main {
    public static void main(String[] args) {
        // 1. Crear el Registro Central (Única instancia)
        RegistroCentral registro = new RegistroCentral();

        // 2. Crear y registrar datos
        Cliente cliente1 = new Cliente("CLI-01", "38123456", "Marcos", "Peñalva", "Calle Falsa 123", "2944000000");
        Cliente cliente2 = new Cliente("CLI-02", "40111222", "Juan", "Perez", "Av. Siempre Viva", "2944111111");
        
        registro.registrarCliente(cliente1);
        registro.registrarCliente(cliente2);

        // Probar Búsqueda Secuencial
        System.out.println("Buscando cliente por DNI 40111222:");
        Cliente cEncontrado = registro.buscarClienteSecuencial("40111222");
        if (cEncontrado != null) System.out.println("Encontrado: " + cEncontrado.getNombre());

        // 3. Probar Cola de Despacho en Sucursal
        Sucursal sucElBolson = new Sucursal("SUC-01", "El Bolsón");
        Envio envio1 = new EnvioEstandar(120, "Bariloche", cliente1);
        Envio envio2 = new EnvioExpress(120, "Bariloche", cliente2);
        
        envio1.agregarPaquete(new Paquete("PAQ-1", "Libro"));
        envio2.agregarPaquete(new Paquete("PAQ-2", "Ropa"));

        // Registrar envíos en el HashMap
        registro.registrarEnvio("ENV-001", envio1);
        registro.registrarEnvio("ENV-002", envio2);

        // Recibir envíos (entran a la cola de la sucursal en orden)
        sucElBolson.recibirEnvio(envio1);
        sucElBolson.recibirEnvio(envio2);

        // Despachar con límite de camión (capacidad = 1)
        System.out.println("\n--- Llegó un camión chico (capacidad 1) ---");
        sucElBolson.despachar(1); // Debería salir solo el envio1 (primero en llegar)
        
        System.out.println("\n--- Llegó un camión grande (capacidad 5) ---");
        sucElBolson.despachar(5); // Debería salir el envio2 (el único que quedaba en la cola)
    }
}