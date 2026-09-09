import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/////representa un cliente de la empresa de envios/////
public class Cliente {  
    ///atributos de la clase/////
    private final String id;
    private final String DNI;
    private final List<Envio> envios;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    ///constructor de la clase/////
    public Cliente(String id, String DNI, String nombre, String apellido, String direccion, String telefono) {
    /////programacion defensiva - validacion contra objetos nulos////////////
    Objects.requireNonNull(id, "El id no puede ser nulo");
    Objects.requireNonNull(DNI, "El DNI no puede ser nulo");
    Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
    Objects.requireNonNull(apellido, "El apellido no puede ser nulo");    
    Objects.requireNonNull(direccion, "La direccion no puede ser nulo");    
    Objects.requireNonNull(telefono, "El telefono no puede ser nulo");    
    //// validacion contra textos vacios////////
    if(id.trim().isEmpty()|| DNI.trim().isEmpty() || nombre.trim().isEmpty() || apellido.trim().isEmpty() || direccion.trim().isEmpty() || telefono.trim().isEmpty()) {
        throw new IllegalArgumentException("El id, DNI, nombre, apellido, direccion y telefono no pueden estar vacíos");
    }
    //////// asignacion de variables//////////
        this.id = id;
        this.DNI = DNI; 
        this.nombre = nombre;   
        this.apellido = apellido;   
        this.direccion = direccion;  
        this.telefono = telefono;   
        this.envios = new ArrayList<>();

    
}

/////metodo de negocio////////
public void registrarEnvio(Envio envio) {
    Objects.requireNonNull(envio, "El envio no puede ser nulo");
    this.envios.add(envio);
}

////getters y setters/////
public String getId(){return id;}   
public String getDNI(){return DNI;}
public String getNombre(){return nombre;}
public String getApellido(){return apellido;}   
public String getDireccion(){return direccion;}   
public String getTelefono(){return telefono;}   
//copia defensiva de la lista de envios
public List<Envio> getEnvios() { return new ArrayList<>(this.envios); }}