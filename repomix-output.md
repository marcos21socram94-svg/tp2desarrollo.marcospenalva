This file is a merged representation of the entire codebase, combined into a single document by Repomix.

# File Summary

## Purpose
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  a. A header with the file path (## File: path/to/file)
  b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)

# Directory Structure
````
src/
  Cliente.java
  Envio.java
  EnvioEstandar.java
  EnvioExpress.java
  EnvioInternacional.java
  EstadoEnvio.java
  Main.java
  Movimiento.java
  Paquete.java
  Sucursal.java
  TipoMovimiento.java
.$tp2desarrollo.drawio.bkp
.editorconfig
.gitignore
consultaHistorialTrazabilidad.drawio.png
diagramClases.png
readme.md
secuencia a SucursalEnvioPaquete.png
````

# Files

## File: src/Cliente.java
````java
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
````

## File: src/EnvioInternacional.java
````java
///representa un envío internacional, que hereda de la clase Envio y calcula el costo total del envío con un recargo fijo.
public class EnvioInternacional extends Envio {
    public EnvioInternacional(double distanciaKm, String destino, Cliente cliente) {
        super(distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase() + 7500.0; 
    }
}
````

## File: .$tp2desarrollo.drawio.bkp
````
<mxfile host="Electron">
  <diagram name="Página-1" id="kSCjxaXDDdfD-kVE_JcC">
    <mxGraphModel dx="858" dy="602" grid="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" page="1" pageScale="1" pageWidth="827" pageHeight="1169" math="0" shadow="0">
      <root>
        <mxCell id="0" />
        <mxCell id="1" parent="0" />
        <mxCell id="piqGELF_AoF7AHO_TCfO-2" parent="1" style="verticalAlign=top;align=left;overflow=fill;html=1;whiteSpace=wrap;" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;font style=&quot;font-size: 16px; background-color: light-dark(#ffffff, var(--ge-dark-color, #121212));&quot;&gt;Cliente&lt;/font&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-id:String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-DNI: String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-nombre : String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-apellido: String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-direccion : String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-telefono: String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-envios: List&amp;lt;Envio&amp;gt;&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+registrarEnvio(envio: Envio)&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getID(): String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getDNI(): String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getNombre(): String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getApellido(): String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getDireccion(): String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getTelefono(): String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getEnvios(): List&amp;lt;envio&amp;gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;br&gt;&lt;br&gt;&lt;div&gt;&lt;br&gt;&lt;/div&gt;" vertex="1">
          <mxGeometry height="270" width="240" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-4" parent="1" style="verticalAlign=top;align=left;overflow=fill;html=1;whiteSpace=wrap;" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;span style=&quot;background-color: light-dark(#ffffff, var(--ge-dark-color, #121212));&quot;&gt;&lt;font style=&quot;font-size: 16px;&quot;&gt;Movimiento&lt;/font&gt;&lt;/span&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-fechaHora: LocalDateTime&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-tipo:TipoMovimiento&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-sucursal:Sucursal&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getFechaHora():LocalDateTime&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getTipo():TipoMovimiento&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+getSucursal(): Sucursal&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+toString(): String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;" vertex="1">
          <mxGeometry height="170" width="190" x="430" y="1090" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-5" parent="1" style="verticalAlign=top;align=left;overflow=fill;html=1;whiteSpace=wrap;" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;font style=&quot;font-size: 16px; background-color: light-dark(#ffffff, var(--ge-dark-color, #121212));&quot;&gt;&lt;i&gt;Envio&lt;/i&gt;&lt;/font&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;#TARIFA_BASE_KM: double = 100.0 {readOnly}&lt;br&gt;#TARIFA_PESO_KG: double = 1.8{readOnly}&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- estadoActual: EstadoEnvio&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-distanciaKM: double&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-destino:String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-cliente: Cliente&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-paquetes: List&amp;lt;Paquete&amp;gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-historial: List&amp;lt;Movimiento&amp;gt;&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ agregarPaquete(paquete: Paquete): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ registrarMovimiento(tipo: TipoMovimiento, ...): void&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ cambiarEstado(nuevoEstado: EstadoEnvio): void&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getEstadoActual(): EstadoEnvio&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ obtenerUltimoMovimiento(): Optional&amp;lt;Movimiento&amp;gt;&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ obtenerHistorialCompleto(): List&amp;lt;Movimiento&amp;gt;&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ obtenerSucursalesVisitadas(): List&amp;lt;Sucursal&amp;gt;&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ {abstract} calcularCostoTotal(): double&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;# calcularCostoBase(): double&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getPaquetes(): List&amp;lt;Paquete&amp;gt;&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getCliente(): Cliente&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;" vertex="1">
          <mxGeometry height="340" width="370" x="310" y="190" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-6" edge="1" parent="1" source="piqGELF_AoF7AHO_TCfO-5" style="html=1;verticalAlign=bottom;endArrow=blockThin;rounded=0;edgeStyle=orthogonalEdgeStyle;endFill=1;exitX=-0.004;exitY=0.471;exitDx=0;exitDy=0;exitPerimeter=0;entryX=1.005;entryY=0.321;entryDx=0;entryDy=0;entryPerimeter=0;" target="piqGELF_AoF7AHO_TCfO-2" value="dispatch">
          <mxGeometry relative="1" width="80" as="geometry">
            <mxPoint x="190" y="340" as="sourcePoint" />
            <mxPoint x="250" y="150" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-7" parent="1" style="text;html=1;whiteSpace=wrap;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;rounded=0;" value="1cliente-asociacion" vertex="1">
          <mxGeometry height="30" width="60" x="230" y="50" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-9" parent="1" style="text;html=1;whiteSpace=wrap;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;rounded=0;" value="0..*" vertex="1">
          <mxGeometry height="30" width="60" x="440" y="1050" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-10" parent="1" style="verticalAlign=top;align=left;overflow=fill;html=1;whiteSpace=wrap;" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;EnvioEstandar&lt;/b&gt;&lt;/p&gt;&lt;hr&gt;+calcularCostoTotal():double" vertex="1">
          <mxGeometry height="60" width="160" y="600" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-11" parent="1" style="verticalAlign=top;align=left;overflow=fill;html=1;whiteSpace=wrap;" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;EnvioExpress&lt;/b&gt;&lt;/p&gt;&lt;hr&gt;+calcularCostoTotal():double" vertex="1">
          <mxGeometry height="60" width="170" x="310" y="600" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-12" parent="1" style="verticalAlign=top;align=left;overflow=fill;html=1;whiteSpace=wrap;" value="&lt;blockquote style=&quot;margin: 0px 0px 0px 40px; border-width: medium; border-style: none; border-color: currentcolor; border-image: none; padding: 0px;&quot;&gt;&lt;p style=&quot;margin: 4px 0px 0px;&quot;&gt;&lt;b&gt;EnvioInternacional&lt;/b&gt;&lt;/p&gt;&lt;hr&gt;+calcularCostoTotal():double&lt;/blockquote&gt;" vertex="1">
          <mxGeometry height="60" width="200" x="590" y="600" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-19" parent="1" style="text;html=1;whiteSpace=wrap;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;rounded=0;" value="(herencia)" vertex="1">
          <mxGeometry height="30" width="60" x="50" y="520" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-20" parent="1" style="text;html=1;whiteSpace=wrap;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;rounded=0;" value="(herencia)" vertex="1">
          <mxGeometry height="30" width="60" x="710" y="540" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-21" parent="1" style="text;html=1;whiteSpace=wrap;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;rounded=0;" value="(herencia)" vertex="1">
          <mxGeometry height="30" width="60" x="400" y="560" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-23" parent="1" style="text;html=1;whiteSpace=wrap;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;rounded=0;" value="0..* paquetes" vertex="1">
          <mxGeometry height="30" width="60" x="80" y="680" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-27" parent="1" style="verticalAlign=top;align=left;overflow=fill;html=1;whiteSpace=wrap;" value="&lt;p style=&quot;margin: 4px 0px 0px; text-align: center;&quot;&gt;&lt;b&gt;Paquete&lt;/b&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:8px;&quot;&gt;-id:String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:8px;&quot;&gt;-descripcion:String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:8px;&quot;&gt;-peso:double&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:8px;&quot;&gt;-alto:double&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:8px;&quot;&gt;-ancho:double&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:8px;&quot;&gt;-largo:double&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:8px;&quot;&gt;-actualEstado:Estado&lt;/p&gt;&lt;hr&gt;+Paquete(id:String,descripcion:String&lt;div&gt;&lt;div&gt;+Paquete(id:String,desc:String,P:double&lt;/div&gt;&lt;div&gt;,alto:double,anc:double,lar:double)&lt;/div&gt;&lt;div&gt;+enviar():void&lt;div&gt;+entregar():void&lt;/div&gt;&lt;div&gt;+registrarDevolucion():void&lt;/div&gt;&lt;div&gt;+actualizarPeso(w:double):void&lt;/div&gt;&lt;div&gt;+actualizarDimensiones(...):void&lt;/div&gt;&lt;div&gt;+getId():String&lt;/div&gt;&lt;div&gt;+getDescripcion():String&lt;/div&gt;&lt;div&gt;+getActualEstado():String&lt;/div&gt;&lt;div&gt;+getPeso():double&lt;/div&gt;&lt;div&gt;+getAlto():double&lt;/div&gt;&lt;div&gt;+getAncho():double&lt;/div&gt;&lt;div&gt;+getLargo():double&lt;/div&gt;&lt;div&gt;&lt;br&gt;&lt;p style=&quot;margin:0px;margin-left:8px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;/div&gt;&lt;/div&gt;&lt;/div&gt;" vertex="1">
          <mxGeometry height="370" width="220" x="10" y="750" as="geometry" />
        </mxCell>
        <mxCell id="piqGELF_AoF7AHO_TCfO-28" parent="1" style="verticalAlign=top;align=left;overflow=fill;html=1;whiteSpace=wrap;" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;Sucursal&lt;/b&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-id:String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;-nombre:String&lt;span style=&quot;white-space: pre;&quot;&gt;&#x9;&lt;/span&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot; style=&quot;border-style:solid;&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ Sucursal(id: String, nombre: String)&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getId(): String&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getNombre(): String&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ recibirEnvio(envio: Envio): void&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ despacharEnvio(envio: Envio): void&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ procesarEntrega(envio: Envio, fueExitosa: boolean,&amp;nbsp;&lt;span style=&quot;background-color: transparent; color: light-dark(rgb(0, 0, 0), rgb(255, 255, 255));&quot;&gt;motivoFallo: String): void&amp;nbsp; &amp;nbsp;&lt;/span&gt;&lt;/p&gt;" vertex="1">
          <mxGeometry height="200" width="410" x="410" y="780" as="geometry" />
        </mxCell>
        <mxCell id="s7Mxcf-n1sYTeIR6XKpj-2" edge="1" parent="1" source="piqGELF_AoF7AHO_TCfO-5" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=0.964;entryY=-0.005;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0;exitY=0.778;exitDx=0;exitDy=0;exitPerimeter=0;" target="piqGELF_AoF7AHO_TCfO-27" value="">
          <mxGeometry relative="1" width="160" as="geometry">
            <mxPoint x="77.51999999999998" y="585" as="sourcePoint" />
            <mxPoint x="240" y="554" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="s7Mxcf-n1sYTeIR6XKpj-6" edge="1" parent="1" source="piqGELF_AoF7AHO_TCfO-10" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;entryX=0;entryY=0.667;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.458;exitY=0;exitDx=0;exitDy=0;exitPerimeter=0;" target="piqGELF_AoF7AHO_TCfO-5" value="Extends">
          <mxGeometry relative="1" width="160" as="geometry">
            <mxPoint x="70" y="580" as="sourcePoint" />
            <mxPoint x="230" y="580" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="s7Mxcf-n1sYTeIR6XKpj-7" edge="1" parent="1" source="piqGELF_AoF7AHO_TCfO-12" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;entryX=0.694;entryY=0.996;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.832;exitY=0.013;exitDx=0;exitDy=0;exitPerimeter=0;" target="piqGELF_AoF7AHO_TCfO-5" value="Extends">
          <mxGeometry relative="1" width="160" as="geometry">
            <mxPoint x="520" y="763" as="sourcePoint" />
            <mxPoint x="757" y="580" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="s7Mxcf-n1sYTeIR6XKpj-8" edge="1" parent="1" source="piqGELF_AoF7AHO_TCfO-11" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;entryX=0.538;entryY=1.006;entryDx=0;entryDy=0;entryPerimeter=0;" target="piqGELF_AoF7AHO_TCfO-5" value="Extends">
          <mxGeometry relative="1" width="160" as="geometry">
            <mxPoint x="510" y="873" as="sourcePoint" />
            <mxPoint x="747" y="690" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="s7Mxcf-n1sYTeIR6XKpj-9" edge="1" parent="1" source="piqGELF_AoF7AHO_TCfO-28" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;entryX=0.642;entryY=1.011;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.382;exitY=-0.012;exitDx=0;exitDy=0;exitPerimeter=0;" target="piqGELF_AoF7AHO_TCfO-5" value="">
          <mxGeometry relative="1" width="160" as="geometry">
            <mxPoint x="220" y="720" as="sourcePoint" />
            <mxPoint x="380" y="720" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-1" edge="1" parent="1" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;exitX=0;exitY=0.25;exitDx=0;exitDy=0;" value="">
          <mxGeometry relative="1" width="160" as="geometry">
            <Array as="points">
              <mxPoint x="435" y="1000" />
            </Array>
            <mxPoint x="430" y="1142.5000000000002" as="sourcePoint" />
            <mxPoint x="491.11" y="980" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-3" parent="1" style="shape=table;startSize=0;container=1;collapsible=0;childLayout=tableLayout;fixedHeader=1;" value="" vertex="1">
          <mxGeometry height="108" width="180" x="610" as="geometry" />
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-4" parent="yZGrojGmkkd2ZfzUg6dT-3" style="shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;strokeColor=inherit;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fixedHeader=1;" value="" vertex="1">
          <mxGeometry height="20" width="180" as="geometry" />
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-5" parent="yZGrojGmkkd2ZfzUg6dT-4" style="shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;strokeColor=inherit;overflow=hidden;fillColor=none;top=0;left=0;bottom=0;right=0;pointerEvents=1;" value="EstadoEnvio&lt;div&gt;&lt;br&gt;&lt;/div&gt;" vertex="1">
          <mxGeometry height="20" width="180" as="geometry">
            <mxRectangle height="20" width="180" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-6" parent="yZGrojGmkkd2ZfzUg6dT-3" style="shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;strokeColor=inherit;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fixedHeader=1;" value="" vertex="1">
          <mxGeometry height="88" width="180" y="20" as="geometry" />
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-7" parent="yZGrojGmkkd2ZfzUg6dT-6" style="shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;strokeColor=inherit;overflow=hidden;fillColor=none;top=0;left=0;bottom=0;right=0;pointerEvents=1;align=left;" value="En_Preparación&lt;div&gt;Despachado&lt;/div&gt;&lt;div&gt;En_Transito&lt;/div&gt;&lt;div&gt;Entregado&lt;/div&gt;&lt;div&gt;Devuelto&lt;/div&gt;" vertex="1">
          <mxGeometry height="88" width="180" as="geometry">
            <mxRectangle height="88" width="180" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-8" parent="1" style="shape=table;startSize=0;container=1;collapsible=0;childLayout=tableLayout;fixedHeader=1;" value="" vertex="1">
          <mxGeometry height="100" width="186" x="384" y="14" as="geometry" />
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-9" parent="yZGrojGmkkd2ZfzUg6dT-8" style="shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;strokeColor=inherit;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fixedHeader=1;" value="" vertex="1">
          <mxGeometry height="20" width="186" as="geometry" />
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-10" parent="yZGrojGmkkd2ZfzUg6dT-9" style="shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;strokeColor=inherit;overflow=hidden;fillColor=none;top=0;left=0;bottom=0;right=0;pointerEvents=1;" value="TipoMovimiento" vertex="1">
          <mxGeometry height="20" width="186" as="geometry">
            <mxRectangle height="20" width="186" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-11" parent="yZGrojGmkkd2ZfzUg6dT-8" style="shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;strokeColor=inherit;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fixedHeader=1;" value="" vertex="1">
          <mxGeometry height="80" width="186" y="20" as="geometry" />
        </mxCell>
        <mxCell id="yZGrojGmkkd2ZfzUg6dT-12" parent="yZGrojGmkkd2ZfzUg6dT-11" style="shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;strokeColor=inherit;overflow=hidden;fillColor=none;top=0;left=0;bottom=0;right=0;pointerEvents=1;align=left;" value="Recibido_En_Sucursal&lt;div&gt;Despachado_De_Sucursal&lt;/div&gt;&lt;div&gt;En_Distribución&lt;/div&gt;&lt;div&gt;Entregado&lt;/div&gt;&lt;div&gt;Develto&lt;/div&gt;" vertex="1">
          <mxGeometry height="80" width="186" as="geometry">
            <mxRectangle height="80" width="186" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
      </root>
    </mxGraphModel>
  </diagram>
</mxfile>
````

## File: .editorconfig
````
[*]
cpp_indent_braces=false
cpp_indent_multi_line_relative_to=innermost_parenthesis
cpp_indent_within_parentheses=indent
cpp_indent_preserve_within_parentheses=false
cpp_indent_case_labels=false
cpp_indent_case_contents=true
cpp_indent_case_contents_when_block=false
cpp_indent_lambda_braces_when_parameter=true
cpp_indent_goto_labels=one_left
cpp_indent_preprocessor=leftmost_column
cpp_indent_access_specifiers=false
cpp_indent_namespace_contents=true
cpp_indent_preserve_comments=false
cpp_new_line_before_open_brace_namespace=ignore
cpp_new_line_before_open_brace_type=ignore
cpp_new_line_before_open_brace_function=ignore
cpp_new_line_before_open_brace_block=ignore
cpp_new_line_before_open_brace_lambda=ignore
cpp_new_line_scope_braces_on_separate_lines=false
cpp_new_line_close_brace_same_line_empty_type=false
cpp_new_line_close_brace_same_line_empty_function=false
cpp_new_line_before_catch=true
cpp_new_line_before_else=true
cpp_new_line_before_while_in_do_while=false
cpp_space_before_function_open_parenthesis=remove
cpp_space_within_parameter_list_parentheses=false
cpp_space_between_empty_parameter_list_parentheses=false
cpp_space_after_keywords_in_control_flow_statements=true
cpp_space_within_control_flow_statement_parentheses=false
cpp_space_before_lambda_open_parenthesis=false
cpp_space_within_cast_parentheses=false
cpp_space_after_cast_close_parenthesis=false
cpp_space_within_expression_parentheses=false
cpp_space_before_block_open_brace=true
cpp_space_between_empty_braces=false
cpp_space_before_initializer_list_open_brace=false
cpp_space_within_initializer_list_braces=true
cpp_space_preserve_in_initializer_list=true
cpp_space_before_open_square_bracket=false
cpp_space_within_square_brackets=false
cpp_space_before_empty_square_brackets=false
cpp_space_between_empty_square_brackets=false
cpp_space_group_square_brackets=true
cpp_space_within_lambda_brackets=false
cpp_space_between_empty_lambda_brackets=false
cpp_space_before_comma=false
cpp_space_after_comma=true
cpp_space_remove_around_member_operators=true
cpp_space_before_inheritance_colon=true
cpp_space_before_constructor_colon=true
cpp_space_remove_before_semicolon=true
cpp_space_after_semicolon=false
cpp_space_remove_around_unary_operator=true
cpp_space_around_binary_operator=insert
cpp_space_around_assignment_operator=insert
cpp_space_pointer_reference_alignment=left
cpp_space_around_ternary_operator=insert
cpp_wrap_preserve_blocks=one_liners
````

## File: .gitignore
````
*.class
out/
bin/
.idea/
.vscode/
````

## File: src/EnvioEstandar.java
````java
public class EnvioEstandar extends Envio {
    public EnvioEstandar(double distanciaKm, String destino, Cliente cliente) {
        super(distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase();
    }
}
````

## File: src/EnvioExpress.java
````java
public class EnvioExpress extends Envio {
    public EnvioExpress(double distanciaKm, String destino, Cliente cliente) {
        super(distanciaKm, destino, cliente);
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoBase() * 1.5;
    }
}
````

## File: src/EstadoEnvio.java
````java
public enum EstadoEnvio {
    EN_PREPARACION, 
    DESPACHADO, 
    EN_TRANSITO, 
    ENTREGADO, 
    DEVUELTO
}
````

## File: src/Movimiento.java
````java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/// Representa un movimiento realizado sobre un envío utilizando objetos de dominio estrictos.
public class Movimiento {
    private final LocalDateTime fechaHora;
    private final TipoMovimiento tipo;
    private final Sucursal sucursal;

    /// Constructor que inicializa un movimiento con el tipo de movimiento y el objeto Sucursal.
    public Movimiento(TipoMovimiento tipo, Sucursal sucursal) {
        this.tipo = Objects.requireNonNull(tipo, "El tipo de movimiento no puede ser nulo");
        this.sucursal = Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");
        this.fechaHora = LocalDateTime.now(); // Asignación automática de timestamp
    }

    // Getters actualizados (sin setters para mantener inmutabilidad)
    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    }
    
    public TipoMovimiento getTipo() { 
        return tipo; 
    }
    
    public Sucursal getSucursal() { 
        return sucursal; 
    }

    /// Método toString adaptado a los nuevos objetos de dominio
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + fechaHora.format(fmt) + "] - " + tipo + " en " + sucursal.getNombre();
    }
}
````

## File: src/Paquete.java
````java
public class Paquete {
    
    public enum Estado {
        EN_PREPARACION,
        ENVIADO,
        ENTREGADO,
        DEVUELTO   
    }

    private final String id;
    private String descripcion;
    private double peso;
    private double alto;
    private double ancho;
    private double largo;
    private Estado actualEstado;

    public Paquete(String id, String descripcion, double peso, double alto, double ancho, double largo) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo ni vacío.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula ni vacía.");
        }
        
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.actualEstado = Estado.EN_PREPARACION; 
    }

    public Paquete(String id, String descripcion) {
        this(id, descripcion, 0.0, 0.0, 0.0, 0.0);
    }

    // Reglas de negocio con Excepciones Defensivas
    public void enviar() {
        if (actualEstado != Estado.EN_PREPARACION) {
            throw new IllegalStateException("Error: Solo se puede enviar un paquete en preparación.");
        }
        actualEstado = Estado.ENVIADO;
    }

    public void entregar() {
        if (actualEstado != Estado.ENVIADO) {
            throw new IllegalStateException("Error: Solo se puede entregar un paquete en viaje.");
        }
        actualEstado = Estado.ENTREGADO;
    }

    public void registrarDevolucion() {
        if (actualEstado != Estado.ENVIADO) {
            throw new IllegalStateException("Error: Solo se puede devolver un paquete en viaje.");
        }
        actualEstado = Estado.DEVUELTO;
    }

    public void actualizarPeso(double nuevoPeso) {
        if (this.actualEstado != Estado.EN_PREPARACION) {
            throw new IllegalStateException("Error: No se puede modificar el peso fuera de depósito.");
        }
        if (nuevoPeso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0.");
        }
        this.peso = nuevoPeso;
    }

    public void actualizarDimensiones(double alto, double ancho, double largo) {
        if (this.actualEstado != Estado.EN_PREPARACION) {
            throw new IllegalStateException("Error: Las dimensiones no pueden cambiar una vez enviado.");
        }
        if (alto <= 0 || ancho <= 0 || largo <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores a 0.");
        }
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Estado getActualEstado() { return actualEstado; }
    public double getPeso() { return peso; }
    public double getAlto() { return alto; }
    public double getAncho() { return ancho; }
    public double getLargo() { return largo; }
}
````

## File: src/TipoMovimiento.java
````java
/// Representa los diferentes tipos de movimientos que un envío puede registrar en su historial.
public enum TipoMovimiento {
    RECIBIDO_EN_SUCURSAL, 
    DESPACHADO_DE_SUCURSAL, 
    EN_DISTRIBUCION, 
    ENTREGADO, 
    DEVUELTO
}
````

## File: readme.md
````markdown
# Sistema de Gestión de Envíos y Distribución (TP2 y TP2.1)

Proyecto desarrollado para la **Tecnicatura Superior en Desarrollo de Software Full Stack (IPAP)**.

## 🚀 Descripción del Proyecto
Este sistema modela la operación logística de una empresa de envíos, permitiendo administrar clientes, sucursales, paquetes y envíos (estándar, express e internacionales). En esta versión (TP2/TP2.1), el foco principal estuvo puesto en la **aplicación de principios sólidos de Orientación a Objetos (POO)**, tales como:
* **Encapsulamiento estricto:** Los objetos protegen su estado interno y son responsables de sus propias transiciones.
* **Patrón "Tell, Don't Ask":** Las clases colaboran enviándose mensajes con comportamiento en lugar de manipular datos ajenos desde afuera.
* **Objetos de dominio:** El historial y los estados se gestionan mediante clases dedicadas (`Movimiento`) y enumeraciones (`EstadoEnvio`, `TipoMovimiento`) en lugar de textos libres.
* **Programación defensiva:** Validación de reglas de negocio y control de excepciones ante flujos inválidos.

---

## 🛠️ Estructura de Clases Principales
* **`Cliente`**: Administra la información de contacto y la lista de envíos asociados.
* **`Sucursal`**: Representa los puntos físicos de control y procesa operaciones ordenando al envío mediante mensajes.
* **`Envio` (Clase Abstracta)**: Coordina la lógica central de costos, paquetes y su propia máquina de estados, extendida por `EnvioEstandar`, `EnvioExpress` e `EnvioInternacional`.
* **`Paquete`**: Gestiona su propio ciclo de vida, peso y dimensiones con validaciones de depósito.
* **`Movimiento` & `TipoMovimiento`**: Registran de forma inmutable cada acontecimiento en la trazabilidad del envío.
* **`EstadoEnvio`**: Encapsula las reglas de transición válidas mediante una máquina de estados.

---

## 💻 Instrucciones de Ejecución
1. Clonar el repositorio en tu equipo.
2. Compilar los archivos fuente desde la terminal ubicados en la carpeta `src`:
   ```bash
   javac -d ../out *.java
````

## File: src/Main.java
````java
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
````

## File: src/Envio.java
````java
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

public abstract class Envio {
    protected double distanciaKm;
    protected String destino;
    protected Cliente cliente;
    protected List<Paquete> paquetes = new ArrayList<>();
    protected List<Movimiento> historial = new ArrayList<>();
    protected EstadoEnvio estadoActual = EstadoEnvio.EN_PREPARACION;

    protected static final double TARIFA_BASE_KM = 50.0;
    protected static final double TARIFA_PESO_KG = 100.0;
    private static final int LIMITE_PAQUETES = 3;

    public Envio(double distanciaKm, String destino, Cliente cliente) {
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }
        this.destino = Objects.requireNonNull(destino, "El destino no puede ser nulo");
        this.cliente = Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        
        // Validación agregada para evitar textos vacíos
        if (this.destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío");
        }
        this.distanciaKm = distanciaKm;
    }

    public abstract double calcularCostoTotal();

    protected double calcularCostoBase() {
        double costoCarga = 0;
        for (Paquete p : paquetes) {
            costoCarga += p.getPeso() * TARIFA_PESO_KG;
        }
        return (this.distanciaKm * TARIFA_BASE_KM) + costoCarga;
    }

    public void agregarPaquete(Paquete paquete) {
        Objects.requireNonNull(paquete, "El paquete no puede ser nulo");
        if (!todaviaNoSalio()) {
            throw new IllegalStateException("No se pueden agregar paquetes: el envío ya salió de la etapa de preparación.");
        }
        if (this.paquetes.size() >= LIMITE_PAQUETES) {
            throw new IllegalStateException("No se pueden agregar más de " + LIMITE_PAQUETES + " paquetes al envío.");
        }
        this.paquetes.add(paquete);
    }

    private void registrarMovimiento(TipoMovimiento tipo, Sucursal sucursal) {
        Objects.requireNonNull(tipo, "El tipo de movimiento no puede ser nulo");
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");
        this.historial.add(new Movimiento(tipo, sucursal));
    }

    // --- ACCIONES DEL DOMINIO CON LAS EXCEPCIONES INTEGRADAS ---

    public void recibir(Sucursal sucursal) {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");
        
        // 1. Excepción: Envío sin paquetes
        if (!tienePaquetesCargados()) {
            throw new IllegalStateException("Envío sin paquetes: no se puede recibir un envío vacío.");
        }
        // 2. Excepción: Recibir en otra etapa (ej. cuando ya está finalizado)
        if (estaFinalizado()) {
            throw new IllegalStateException("Etapa inválida: el envío ya se encuentra finalizado (" + estadoActual + ").");
        }

        // 3. Excepción: Movimiento incoherente (recibir dos veces o en otra sucursal sin despacho previo)
        if (todaviaNoSalio()) {
            // Primer ingreso en la sucursal de origen
            this.estadoActual = EstadoEnvio.EN_TRANSITO;
            registrarMovimiento(TipoMovimiento.RECIBIDO_EN_SUCURSAL, sucursal);
        } else if (this.estadoActual == EstadoEnvio.DESPACHADO) {
            // Llega a una nueva sucursal tras haber sido despachado
            Optional<Movimiento> ultimo = obtenerUltimoMovimiento();
            if (ultimo.isPresent() && ultimo.get().getSucursal().equals(sucursal)) {
                throw new IllegalStateException("Movimiento incoherente: no se puede recibir en la misma sucursal desde la que se despachó.");
            }
            this.estadoActual = EstadoEnvio.EN_TRANSITO;
            registrarMovimiento(TipoMovimiento.RECIBIDO_EN_SUCURSAL, sucursal);
        } else {
            // Si ya estaba EN_TRANSITO (recibido) y quieren recibirlo de nuevo sin despachar
            throw new IllegalStateException("Movimiento incoherente: no se puede recibir dos veces o en otra sucursal sin despacho previo.");
        }
    }

    public void despachar(Sucursal sucursal) {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");

        if (!tienePaquetesCargados()) {
            throw new IllegalStateException("Envío sin paquetes: no se puede despachar un envío vacío.");
        }
        if (estaFinalizado() || this.estadoActual != EstadoEnvio.EN_TRANSITO) {
            throw new IllegalStateException("Movimiento incoherente: el envío debe estar recibido en la sucursal antes de despacharse.");
        }

        Optional<Movimiento> ultimo = obtenerUltimoMovimiento();
        if (ultimo.isPresent() && !ultimo.get().getSucursal().equals(sucursal)) {
            throw new IllegalStateException("Movimiento incoherente: no se puede despachar desde una sucursal distinta a donde se recibió.");
        }

        this.estadoActual = EstadoEnvio.DESPACHADO;
        registrarMovimiento(TipoMovimiento.DESPACHADO_DE_SUCURSAL, sucursal);

        for (Paquete p : paquetes) {
            p.enviar();
        }
    }

    public void entregar(Sucursal sucursal) {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");

        if (!tienePaquetesCargados()) {
            throw new IllegalStateException("Envío sin paquetes: no se puede entregar.");
        }
        if (estaFinalizado() || this.estadoActual != EstadoEnvio.EN_TRANSITO) {
            throw new IllegalStateException("Etapa inválida: el envío debe haber sido recibido en la sucursal de destino para entregarse.");
        }

        this.estadoActual = EstadoEnvio.ENTREGADO;
        registrarMovimiento(TipoMovimiento.ENTREGADO, sucursal);

        for (Paquete p : paquetes) {
            p.entregar();
        }
    }

    public void devolver(Sucursal sucursal, String motivoFallo) {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser nula");
        if (motivoFallo == null || motivoFallo.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo de fallo no puede estar vacío.");
        }
        if (estaFinalizado() || this.estadoActual != EstadoEnvio.EN_TRANSITO) {
            throw new IllegalStateException("Etapa inválida: solo se puede devolver un envío que está en tránsito en la sucursal.");
        }

        this.estadoActual = EstadoEnvio.DEVUELTO;
        registrarMovimiento(TipoMovimiento.DEVUELTO, sucursal);

        for (Paquete p : paquetes) {
            p.registrarDevolucion();
        }
    }

    // --- REGLAS ENCAPSULADAS (Ahora sí se usan todas) ---

    protected boolean estaFinalizado() {
        return this.estadoActual == EstadoEnvio.ENTREGADO || this.estadoActual == EstadoEnvio.DEVUELTO;
    }

    protected boolean todaviaNoSalio() {
        return this.estadoActual == EstadoEnvio.EN_PREPARACION;
    }

    protected boolean tienePaquetesCargados() {
        return !this.paquetes.isEmpty();
    }

    public EstadoEnvio getEstadoActual() { return estadoActual; }

    public Optional<Movimiento> obtenerUltimoMovimiento() {
        if (historial.isEmpty()) return Optional.empty();
        return Optional.of(historial.get(historial.size() - 1));
    }

    public List<Movimiento> obtenerHistorialCompleto() {
        return new ArrayList<>(this.historial);
    }

    public List<Sucursal> obtenerSucursalesVisitadas() {
        List<Sucursal> lista = new ArrayList<>();
        for (Movimiento m : historial) {
            if (m.getSucursal() != null && !lista.contains(m.getSucursal())) {
                lista.add(m.getSucursal());
            }
        }
        return lista;
    }

    public List<Paquete> getPaquetes() { return new ArrayList<>(paquetes); }
    public Cliente getCliente() { return cliente; }
}
````

## File: src/Sucursal.java
````java
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Sucursal {
    private String id;
    private String nombre;
    private List<Envio> enviosRegistrados = new ArrayList<>();

    public Sucursal(String id, String nombre) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        
        if (id.trim().isEmpty() || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID y el nombre de la sucursal no pueden estar vacíos");
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    public void recibirEnvio(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        envio.recibir(this);
        if (!enviosRegistrados.contains(envio)) {
            enviosRegistrados.add(envio);
        }
    }

    public void despacharEnvio(Envio envio) {
        validarEnvioRegistrado(envio);
        envio.despachar(this);
    }

    public void entregarEnvio(Envio envio) {
        validarEnvioRegistrado(envio);
        envio.entregar(this);
    }

    public void procesarDevolucion(Envio envio, String motivoFallo) {
        validarEnvioRegistrado(envio);
        envio.devolver(this, motivoFallo);
    }

    private void validarEnvioRegistrado(Envio envio) {
        Objects.requireNonNull(envio, "El envío no puede ser nulo");
        if (!enviosRegistrados.contains(envio)) {
            throw new NoSuchElementException("Envío inexistente: el envío no se encuentra registrado en la sucursal " + this.nombre);
        }
    }

    public List<Envio> getEnviosRegistrados() {
        return new ArrayList<>(enviosRegistrados);
    }
}
````
