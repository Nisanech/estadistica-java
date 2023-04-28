/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package estadistica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Nisanech
 */
public class EstadisticaDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[] datos = leerArchivo("D:\\DesarrolloWeb-SW\\Proyectos\\EstadisticaDatos\\ArchivosTXT\\datos-1000-20-10.txt");
        if (datos != null) {
            double media = calcularMedia(datos);
            double valorMinimo = calcularMinimo(datos);
            double valorMaximo = calcularMaximo(datos);
            double rango = calcularRango(datos);
            double mediana = calcularMediana(datos);
            String sesgo = determinarSesgo(datos);
            System.out.println("Media: " + media);
            System.out.println("Valor mínimo: " + valorMinimo);
            System.out.println("Valor máximo: " + valorMaximo);
            System.out.println("Rango: " + rango);
            System.out.println("Mediana: " + mediana);
            System.out.println("Sesgo: " + sesgo);
        } else {
            System.out.println("No se pudieron leer los datos del archivo");
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

    public static double calcularMedia(double[] datos) {
        double suma = 0.0;
        for (int i = 0; i < datos.length; i++) {
            suma += datos[i];
        }
        return suma / datos.length;
    }

    public static double calcularMinimo(double[] datos) {
        return Arrays.stream(datos).min().getAsDouble();
    }

    public static double calcularMaximo(double[] datos) {
        return Arrays.stream(datos).max().getAsDouble();
    }

    public static double calcularRango(double[] datos) {
        return calcularMaximo(datos) - calcularMinimo(datos);
    }

    public static double calcularMediana(double[] datos) {
        Arrays.sort(datos);
        int n = datos.length;
        if (n % 2 == 0) {
            return (datos[n / 2 - 1] + datos[n / 2]) / 2.0;
        } else {
            return datos[n / 2];
        }
    }

    public static String determinarSesgo(double[] datos) {
        double media = calcularMedia(datos);
        double mediana = calcularMediana(datos);
        if (media > mediana) {
            return "Sesgo a la derecha";
        } else if (media < mediana) {
            return "Sesgo a la izquierda";
        } else {
            return "Sesgo cero";
        }
    }

}
