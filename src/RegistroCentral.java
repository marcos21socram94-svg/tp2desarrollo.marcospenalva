import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RegistroCentral {
    // Lista para búsqueda secuencial de clientes
    private List<Cliente> clientes;
    // HashMap para búsqueda directa de envíos (Clave: Hash/ID en String, Valor: Envio)
    private Map<String, Envio> envios;
    private List<Sucursal> sucursales;

    public RegistroCentral() {
        this.clientes = new ArrayList<>();
        this.envios = new HashMap<>();
        this.sucursales = new ArrayList<>();
    }

    // --- MÉTODOS PARA CLIENTES (Lista / Búsqueda Secuencial) ---
    
    public void registrarCliente(Cliente c) {
        Objects.requireNonNull(c, "El cliente no puede ser nulo");
        // Evitar duplicados por DNI
        if (buscarClienteSecuencial(c.getDNI()) == null) {
            this.clientes.add(c);
        } else {
            System.out.println("El cliente con DNI " + c.getDNI() + " ya está registrado.");
        }
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(this.clientes);
    }

    public Cliente buscarClienteSecuencial(String dni) {
        if (dni == null || dni.trim().isEmpty()) return null;
        
        // Recorrido secuencial elemento por elemento
        for (Cliente c : clientes) {
            if (c.getDNI().equals(dni)) {
                return c;
            }
        }
        System.out.println("No se encontró ningún cliente con DNI: " + dni);
        return null;
    }

    // --- MÉTODOS PARA ENVÍOS (HashMap / Búsqueda Directa) ---

    // Nota: Como tu clase Envio no tiene un id numérico explícito en el diseño anterior,
    // usamos la referencia al primer paquete o creamos una clave basada en su cliente y destino.
    // Para simplificar según el TP, asumiremos que usamos un String "codigoSeguimiento" que le pasamos al registrar.
    public void registrarEnvio(String codigoSeguimiento, Envio e) {
        Objects.requireNonNull(e, "El envío no puede ser nulo");
        if (codigoSeguimiento == null || codigoSeguimiento.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de seguimiento no puede estar vacío");
        }
        
        if (this.envios.containsKey(codigoSeguimiento)) {
            System.out.println("Error: Ya existe un envío registrado con el código " + codigoSeguimiento);
        } else {
            this.envios.put(codigoSeguimiento, e);
        }
    }

    public Envio buscarEnvio(String codigoSeguimiento) {
        Envio e = this.envios.get(codigoSeguimiento);
        if (e == null) {
            System.out.println("Aviso: No existe ningún envío con el código " + codigoSeguimiento);
        }
        return e;
    }

    // --- MÉTODOS PARA SUCURSALES ---

    public void registrarSucursal(Sucursal s) {
        Objects.requireNonNull(s, "La sucursal no puede ser nula");
        this.sucursales.add(s);
    }
}