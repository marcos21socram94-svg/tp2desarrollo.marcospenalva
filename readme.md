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

   ## 💻 Instrucciones de Ejecución
1. Clonar el repositorio en tu equipo.
2. Compilar los archivos fuente desde la terminal ubicados en la carpeta `src` forzando UTF-8:
   ```bash
   javac -encoding UTF-8 -d ../out *.java
   java -Dfile.encoding=UTF-8 -cp ../out Main