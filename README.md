# Conversor de Monedas

Este proyecto implementa un conversor de monedas que utiliza la API Exchange Rate API (https://www.exchangerate-api.com/) para obtener los tipos de cambio en tiempo real.

## Descripción

La aplicación permite al usuario convertir entre las siguientes monedas y el dólar estadounidense (USD):

*   Peso Argentino (ARS)
*   Boliviano Boliviano (BOB)
*   Real Brasileño (BRL)
*   Peso Chileno (CLP)
*   Peso Colombiano (COP)
*   Peso Mexicano (MXN)

El usuario puede seleccionar la conversión deseada desde un menú interactivo y luego ingresar la cantidad a convertir.
La aplicación muestra el resultado de la conversión y guarda un historial de las mismas.

## Conceptos Aplicados

*   **API REST:** Se utiliza la API Exchange Rate API para obtener los tipos de cambio.
*   **Peticiones HTTP:** Se realizan peticiones HTTP para interactuar con la API.
*   **JSON:** Los datos de la API se reciben en formato JSON y se parsean utilizando la librería Gson de Google.
*   **Clases y Objetos:** Se utilizan clases para encapsular la lógica de la API (`ExchangeRateAPI`), la conversión (`CurrencyConverter`) y la interacción con el usuario (`Principal`).
*   **Excepciones:** Se manejan las posibles excepciones que pueden ocurrir durante la ejecución, como errores de conexión, errores en la API o entradas inválidas del usuario.
*   **Encapsulamiento:** Se utilizan modificadores de acceso (private, public) para controlar el acceso a los atributos y métodos de las clases.
*   **Inyección de dependencias:** La clase `CurrencyConverter` recibe una instancia de `ExchangeRateAPI` en su constructor.
*   **Records:** Se utilizan records para representar estructuras de datos inmutables, como la respuesta de la API.
*   **Colecciones (Listas):** Se utiliza una lista (`ArrayList`) para almacenar el historial de conversiones.
*   **Manejo de entrada/salida (I/O):** Se utiliza la clase `Scanner` para leer la entrada del usuario desde la consola.

## Cómo ejecutar

1.  **Obtener una API Key:** Registrarse en Exchange Rate API (https://www.exchangerate-api.com/) y obtener una API Key gratuita.
2.  **Configurar la variable de entorno:** Establecer la variable de entorno `EXCHANGE_RATE_API_KEY` con la API Key obtenida.
3.  En sistemas Linux/macOS, puedes hacerlo añadiendo la siguiente línea a tu archivo `~/.bashrc` o `~/.zshrc`:

    ```bash
    export EXCHANGE_RATE_API_KEY="tu_api_key"
    ```

    Y luego ejecutar `source ~/.bashrc` o `source ~/.zshrc`.

    En Windows, puedes configurarla desde las propiedades del sistema.
5.  **Compilar el código:** Compilar los archivos `.java` utilizando un compilador de Java (JDK). Por ejemplo:

    ```bash
    javac ExchangeRateAPI.java CurrencyConverter.java Principal.java
    ```

6.  **Ejecutar la aplicación:** Ejecutar la clase `Principal`:

    ```bash
    java Principal
    ```

## Estructura del proyecto
.
├── CurrencyConverter.java
├── ExchangeRateAPI.java
└── Principal.java
└── README.md


## Ejemplo de uso

Al ejecutar la aplicación, se mostrará un menú con las opciones de conversión.
El usuario puede seleccionar una opción ingresando el número correspondiente y luego ingresar la cantidad a convertir.
El resultado se mostrará en la consola.
