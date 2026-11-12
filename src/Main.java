public class Main {
    public static void main(String[] args) {
        // =====================================================================
        // PARTE 1: Crear la única instancia de RegistroCentral
        // =====================================================================
        RegistroCentral registro = new RegistroCentral();

        // 1. Registrar sucursales y probar control de duplicados (Corrección 4)
        System.out.println("=== 1. REGISTRO DE SUCURSALES ===");
        Sucursal sucElBolson = new Sucursal("SUC-01", "El Bolsón");
        Sucursal sucBariloche = new Sucursal("SUC-02", "Bariloche");
        Sucursal sucViedma = new Sucursal("SUC-03", "Viedma");

        registro.registrarSucursal(sucElBolson);
        registro.registrarSucursal(sucBariloche);
        registro.registrarSucursal(sucViedma);
        registro.registrarSucursal(sucElBolson); // Debe avisar que SUC-01 ya existe

        // 2. Registrar clientes sin falsos carteles de error y listarlos (Corrección 1)
        System.out.println("\n=== 2. REGISTRO Y LISTADO DE CLIENTES ===");
        Cliente cliente1 = new Cliente("CLI-01", "38123456", "Marcos", "Peñalva", "Calle Falsa 123", "2944000000");
        Cliente cliente2 = new Cliente("CLI-02", "40111222", "Juan", "Pérez", "Av. Siempre Viva 742", "2944111111");

        registro.registrarCliente(cliente1);
        registro.registrarCliente(cliente2);
        registro.registrarCliente(cliente1); // Prueba de DNI duplicado

        System.out.println("Clientes actualmente en el RegistroCentral:");
        for (Cliente c : registro.listarClientes()) {
            System.out.println(" - " + c.getNombre() + " " + c.getApellido() + " (DNI: " + c.getDNI() + ")");
        }

        // =====================================================================
        // PARTE 4: Búsqueda Secuencial de Clientes por DNI
        // =====================================================================
        System.out.println("\n=== 3. BÚSQUEDA SECUENCIAL DE CLIENTES ===");
        Cliente buscado1 = registro.buscarClienteSecuencial(38123456);
        if (buscado1 != null) {
            System.out.println("Encontrado por int DNI: " + buscado1.getNombre() + " " + buscado1.getApellido());
        }
        // Buscar un DNI inexistente
        registro.buscarClienteSecuencial(99999999);

        // =====================================================================
        // PARTE 2: HashMap<Integer, Envio> - registrarEnvio(Envio e) y buscarEnvio(int codigo)
        // =====================================================================
        System.out.println("\n=== 4. REGISTRO Y BÚSQUEDA DE ENVÍOS (HASHMAP) ===");
        Envio envio1 = new EnvioEstandar(1001, 120, "Bariloche", cliente1);
        Envio envio2 = new EnvioExpress(1002, 120, "Bariloche", cliente2);
        Envio envio3 = new EnvioInternacional(1003, 950, "Viedma", cliente1);

        envio1.agregarPaquete(new Paquete("PAQ-1", "Libros", 2.0, 20, 15, 10));
        envio2.agregarPaquete(new Paquete("PAQ-2", "Ropa de montaña", 1.5, 30, 25, 15));
        envio3.agregarPaquete(new Paquete("PAQ-3", "Notebook", 3.0, 40, 30, 10));

        // Se registran pasando únicamente el objeto Envio (Corrección 2)
        registro.registrarEnvio(envio1);
        registro.registrarEnvio(envio2);
        registro.registrarEnvio(envio3);
        registro.registrarEnvio(envio1); // Debe avisar que el código 1001 ya existe

        // Buscar un envío inexistente
        registro.buscarEnvio(9999);

        // =====================================================================
        // PARTE 3: Cola de Despacho (FIFO) y Capacidad del Camión
        // =====================================================================
        System.out.println("\n=== 5. COLA DE DESPACHO (FIFO) EN SUCURSAL ORIGEN ===");
        // Llegan los 3 envíos a El Bolsón en orden: 1001, 1002 y 1003
        sucElBolson.recibirEnvio(envio1);
        sucElBolson.recibirEnvio(envio2);
        sucElBolson.recibirEnvio(envio3);

        System.out.println("Envíos esperando en la cola de El Bolsón: " + sucElBolson.obtenerCantidadEnCola());

        // Llega un camión con capacidad para 2 envíos (deben salir 1001 y 1002 por orden de llegada)
        System.out.println("\n-> Despachando camión con capacidad para 2 envíos:");
        sucElBolson.despachar(2);
        System.out.println("Estado envío 1001: " + envio1.getEstadoActual()); // DESPACHADO
        System.out.println("Estado envío 1002: " + envio2.getEstadoActual()); // DESPACHADO
        System.out.println("Estado envío 1003: " + envio3.getEstadoActual()); // Sigue EN_TRANSITO esperando

        // Llega un camión grande con capacidad para 5 envíos (solo queda el 1003 en la cola)
        System.out.println("\n-> Despachando camión con capacidad para 5 envíos:");
        sucElBolson.despachar(5);
        System.out.println("Estado envío 1003: " + envio3.getEstadoActual()); // DESPACHADO

        // Intentar despachar con la cola vacía
        System.out.println("\n-> Intentando despachar otro camión cuando la cola ya está vacía:");
        sucElBolson.despachar(3);

        // =====================================================================
        // CORRECCIONES 3 y 5: Escala intermedia, entrega y retiro de la cola
        // =====================================================================
        System.out.println("\n=== 6. RECEPCIÓN EN DESTINO, ENTREGA Y ESCALA INTERMEDIA ===");
        // Buscamos los envíos en el RegistroCentral y ejecutamos las acciones en el dominio
        Envio envBuscado1 = registro.buscarEnvio(1001);
        Envio envBuscado3 = registro.buscarEnvio(1003);

        // Ambos llegan a la sucursal Bariloche (entran a la cola de Bariloche)
        sucBariloche.recibirEnvio(envBuscado1);
        sucBariloche.recibirEnvio(envBuscado3);
        System.out.println("En cola de Bariloche tras recibir 1001 y 1003: " + sucBariloche.obtenerCantidadEnCola());

        // El envío 1001 era para Bariloche: se entrega en mostrador y sale automáticamente de la cola (Corrección 3)
        sucBariloche.entregarEnvio(envBuscado1);
        System.out.println("Estado envío 1001 tras entregarse: " + envBuscado1.getEstadoActual());
        System.out.println("En cola de Bariloche luego de entregar 1001: " + sucBariloche.obtenerCantidadEnCola());

        // El envío 1003 sigue viaje hacia Viedma: se despacha desde Bariloche sin fallar (Corrección 5)
        sucBariloche.despachar(2);
        sucViedma.recibirEnvio(envBuscado3);
        sucViedma.entregarEnvio(envBuscado3);
        System.out.println("Estado final envío 1003 en Viedma: " + envBuscado3.getEstadoActual());
        System.out.println("Sucursales visitadas por envío 1003: " + envBuscado3.obtenerSucursalesVisitadas().size());
    }
}