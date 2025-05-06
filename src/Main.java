import java.util.Scanner;

// --- SALUDO ---

class SaludoModel {
  private String nombreUsuario;
  public void setNombre(String nombre) {
    this.nombreUsuario = nombre;
  }
  public String getNombre() {
    return nombreUsuario;
  }
}

class SaludoView {
  public void pedirNombre() {
    System.out.print("Introduce tu nombre: ");
  }
  public void mostrarSaludo(String nombre) {
    System.out.println("Hola, " + nombre + "!");
  }
}

class SaludoController {
  private SaludoModel modelo;
  private SaludoView vista;
  private Scanner scanner;

  public SaludoController(SaludoModel modelo, SaludoView vista, Scanner scanner) {
    this.modelo = modelo;
    this.vista = vista;
    this.scanner = scanner;
  }

  public void iniciar() {
    vista.pedirNombre();
    String nombre = scanner.nextLine();
    modelo.setNombre(nombre);
    vista.mostrarSaludo(modelo.getNombre());
  }
}

// --- SUMA ---

class SumaModel {
  public double sumar(double... numeros) {
    double suma = 0;
    for (double numero : numeros) {
      suma += numero;
    }
    return suma;
  }
}

class SumaView {
  public void pedirNumeros() {
    System.out.print("Introduce los números a sumar, separados por espacio: ");
  }

  public void mostrarResultado(double suma) {
    // Aquí mostramos solo el resultado como una "alerta"
    System.out.println("\nEl resultado de tu suma es: " + suma + "\n");
  }

  public void mostrarError(String mensaje) {
    System.err.println("Error: " + mensaje);
  }
}

class SumaController {
  private SumaModel modelo;
  private SumaView vista;
  private Scanner scanner;

  public SumaController(SumaModel modelo, SumaView vista, Scanner scanner) {
    this.modelo = modelo;
    this.vista = vista;
    this.scanner = scanner;
  }

  public void iniciar() {
    try {
      vista.pedirNumeros();
      String input = scanner.nextLine();
      String[] numerosStr = input.split("\\s+");  // Separar los números por espacios
      double[] numeros = new double[numerosStr.length];

      // Convertir cada número ingresado en un double
      for (int i = 0; i < numerosStr.length; i++) {
        numeros[i] = Double.parseDouble(numerosStr[i].replace(",", "."));
      }

      double resultado = modelo.sumar(numeros);
      vista.mostrarResultado(resultado);  // Solo mostramos el resultado de la suma

    } catch (NumberFormatException e) {
      vista.mostrarError("Entrada inválida. Debes introducir números válidos.");
    }
  }
}

// --- MAIN ---

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Saludo
    SaludoModel saludoModel = new SaludoModel();
    SaludoView saludoView = new SaludoView();
    SaludoController saludoController = new SaludoController(saludoModel, saludoView, scanner);
    saludoController.iniciar();

    // Suma interactiva
    SumaModel sumaModel = new SumaModel();
    SumaView sumaView = new SumaView();
    SumaController sumaController = new SumaController(sumaModel, sumaView, scanner);

    while (true) {
      System.out.println("\n¿Qué deseas hacer?");
      System.out.println("- Escribe 'sumar' para sumar varios números.");
      System.out.println("- Escribe 'salir' para terminar el programa.");
      System.out.print("> ");
      String opcion = scanner.nextLine().trim().toLowerCase();

      if (opcion.equals("sumar")) {
        sumaController.iniciar();
      } else if (opcion.equals("salir")) {
        System.out.println("¡Hasta luego!");
        break;
      } else {
        System.out.println("Opción no válida. Escribe 'sumar' o 'salir'.");
      }
    }

    scanner.close();
  }
}
