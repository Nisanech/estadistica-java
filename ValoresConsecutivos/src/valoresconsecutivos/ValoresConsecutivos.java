/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package valoresconsecutivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Nisanech
 */
public class ValoresConsecutivos {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    /* Ingresar la ruta donde se encuentre los archivos para los cálculos respectivos */
    double[] datos = leerArchivo("D:\\DesarrolloWeb-SW\\Proyectos\\EstadisticaDatos\\ArchivosTXT\\consec-250.txt");
    if (datos != null) {
      System.out.println("Los datos del archivo son:");
      for (double dato : datos) {
        System.out.print(dato + " ");
      }
      System.out.println();
      Scanner scanner = new Scanner(System.in);
      System.out.print("Ingrese un valor entero para buscar una serie consecutiva de ese tamaño: ");
      int n = scanner.nextInt();
      if (n > datos.length) {
        System.out.println("El valor ingresado es mayor que el número de datos en el archivo.");
      } else {
        boolean serieEncontrada = false;
        int repeticiones = 0;
        double serieActual = datos[0];
        for (int i = 0; i <= datos.length - n; i++) {
          boolean serieActualEncontrada = true;
          for (int j = 1; j < n; j++) {
            if (datos[i + j] != datos[i]) {
              serieActualEncontrada = false;
              break;
            }
          }
          if (serieActualEncontrada) {
            serieEncontrada = true;
            if (datos[i] == datos[i + n - 1]) {
              repeticiones++;
            }
            serieActual = datos[i];
          }
        }
        if (serieEncontrada) {
          System.out.println("Serie consecutiva de " + n + " valores iguales encontrados. El valor es " + serieActual + " y se repite " + repeticiones + " veces en la serie.");
        } else {
          System.out.println("No se encontró una serie consecutiva de " + n + " valores iguales.");
        }
      }
    }
  }

  public static double[] leerArchivo(String nombreArchivo) {
    double[] datos = null;
    try {
      BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
      String linea;
      if ((linea = br.readLine()) != null) {
        int n = Integer.parseInt(linea.trim());
        datos = new double[n];
        int i = 0;
        while ((linea = br.readLine()) != null) {
          linea = linea.trim();
          if (linea.isEmpty()) {
            continue; // Ignorar líneas vacías
          }
          String[] substrings = linea.split("\\s+");
          for (int j = 0; j < substrings.length; j++) {
            if (!substrings[j].isEmpty()) {
              datos[i++] = Double.parseDouble(substrings[j]);
            }
          }
        }
      }
      br.close();
    } catch (IOException e) {
      System.out.println("Error al leer el archivo: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println("Error al convertir a número: " + e.getMessage());
    }
    return datos;
  }

}
