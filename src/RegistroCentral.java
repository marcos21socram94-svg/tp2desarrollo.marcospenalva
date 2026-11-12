import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Partes 1, 2 y 4: Base de datos en memoria del sistema.
 * Solo guarda, busca y lista clientes, sucursales y envíos.
 */
public class RegistroCentral {

    // Parte 4: Los clientes se guardan en una ArrayList<Cliente>
    private final ArrayList<Cliente> clientes;

    // Parte 2: Los envíos se guardan en un HashMap<Integer, Envio> usando su código como clave
    private final HashMap<Integer, Envio> envios;

    // Parte 2 (Opcional) / CORRECCIÓN 4: HashMap para sucursales evitando códigos repetidos
    private final Map<String, Sucursal> sucursales;

    public RegistroCentral() {
        this.clientes = new ArrayList<>();
        this.envios = new HashMap<>();
        this.sucursales = new HashMap<>();
    }

    // =========================================================================
    // MÉTODOS PARA CLIENTES (ArrayList y Búsqueda Secuencial - Parte 1 y 4)
    // =========================================================================

    /**
     * CORRECCIÓN 1: Método auxiliar privado que verifica silenciosamente si un DNI ya está
     * en la lista, evitando imprimir el mensaje de error de buscarClienteSecuencial.
     */
    private boolean existeClienteConDni(String dni) {
        for (Cliente c : this.clientes) {
            if (c.getDNI().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parte 1: Registra un cliente validando que no se duplique su DNI.
     */
    public void registrarCliente(Cliente c) {
        Objects.requireNonNull(c, "El cliente no puede ser nulo");

        if (existeClienteConDni(c.getDNI())) {
            System.out.println("Aviso: El cliente con DNI " + c.getDNI() + " ya se encuentra registrado.");
        } else {
            this.clientes.add(c);
        }
    }

    /**
     * Parte 1: Devuelve la lista de todos los clientes registrados (copia defensiva).
     */
    public List<Cliente> listarClientes() {
        return new ArrayList<>(this.clientes);
    }

    /**
     * Parte 4.a: Firma exacta pedida en el PDF buscarClienteSecuencial(int dni).
     * Convierte el entero a texto y delega en la búsqueda secuencial.
     */
    public Cliente buscarClienteSecuencial(int dni) {
        return buscarClienteSecuencial(String.valueOf(dni));
    }

    /**
     * Parte 4.a: Recorre la ArrayList desde el principio hasta encontrar el cliente con ese DNI.
     * Si no está, lo informa por consola y devuelve null.
     */
    public Cliente buscarClienteSecuencial(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            System.out.println("Aviso: El DNI a buscar no puede estar vacío.");
            return null;
        }

        for (Cliente c : this.clientes) {
            if (c.getDNI().equals(dni.trim())) {
                return c;
            }
        }

        System.out.println("Aviso: No se encontró ningún cliente con el DNI " + dni);
        return null;
    }

    // =========================================================================
    // MÉTODOS PARA ENVÍOS (HashMap<Integer, Envio> - Parte 1 y 2)
    // =========================================================================

    /**
     * CORRECCIÓN 2: Firma exacta pedida en el PDF registrarEnvio(Envio e).
     * Toma el código de seguimiento directamente de e.getCodigo().
     * Si ya existe un envío con ese código, no lo registra y lo informa.
     */
    public void registrarEnvio(Envio e) {
        Objects.requireNonNull(e, "El envío no puede ser nulo");
        int codigo = e.getCodigo();

        if (this.envios.containsKey(codigo)) {
            System.out.println("Aviso: Ya existe un envío registrado con el código " + codigo + ". No se registró.");
        } else {
            this.envios.put(codigo, e);
        }
    }

    /**
     * Parte 2: Busca un envío de forma directa en el HashMap por su código numérico.
     * Si el código no existe, lo informa.
     */
    public Envio buscarEnvio(int codigo) {
        Envio envio = this.envios.get(codigo);
        if (envio == null) {
            System.out.println("Aviso: No existe ningún envío registrado con el código " + codigo);
        }
        return envio;
    }

    // =========================================================================
    // MÉTODOS PARA SUCURSALES (HashMap - Parte 1 y 2)
    // =========================================================================

    /**
     * CORRECCIÓN 4: Registra una sucursal controlando que no se repita su código.
     */
    public void registrarSucursal(Sucursal s) {
        Objects.requireNonNull(s, "La sucursal no puede ser nula");

        if (this.sucursales.containsKey(s.getId())) {
            System.out.println("Aviso: Ya existe una sucursal registrada con el código " + s.getId());
        } else {
            this.sucursales.put(s.getId(), s);
        }
    }

    /**
     * Busca una sucursal de forma directa usando su código identificador.
     */
    public Sucursal buscarSucursal(String idSucursal) {
        Sucursal s = this.sucursales.get(idSucursal);
        if (s == null) {
            System.out.println("Aviso: No existe ninguna sucursal con el código " + idSucursal);
        }
        return s;
    }

    public List<Sucursal> listarSucursales() {
        return new ArrayList<>(this.sucursales.values());
    }
}