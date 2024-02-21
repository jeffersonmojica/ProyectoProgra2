package persistence;

import logic.Pelicula;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistenciaDatos {

    private static final String RutaPeliculas = "C:\\Users\\SERGIO\\IdeaProjects\\co.edu.uptc.GestorPeliculas\\src\\peliculas.txt";
    private static final String RutaFavoritos = "C:\\Users\\SERGIO\\IdeaProjects\\co.edu.uptc.GestorPeliculas\\src\\favoritos.txt";


    public static Map<String, Pelicula> cargarPeliculas() {
        Map<String, Pelicula> peliculas = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RutaPeliculas))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                if (datos.length == 4) {
                    String nombre = datos[0];
                    String clasificacion = datos[1];
                    String director = datos[2];
                    String estado = datos[3];
                    peliculas.put(nombre, new Pelicula(nombre, clasificacion, director, estado));
                }
            }
            System.out.println("Datos cargados exitosamente desde " + RutaPeliculas);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de películas. Se creará uno nuevo al guardar los datos.");
        } catch (IOException e) {
            System.err.println("Error al cargar los datos de películas: " + e.getMessage());
        }
        return peliculas;
    }

    public static List<Pelicula> cargarFavoritos() {
        List<Pelicula> peliculasFavoritas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RutaFavoritos))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String nombre = line.trim(); // Asume que cada línea es el nombre de una película favorita
                peliculasFavoritas.add(new Pelicula(nombre, "", "", ""));
            }
            System.out.println("Favoritos cargados exitosamente desde " + RutaFavoritos);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de favoritos. Se creará uno nuevo al guardar los datos.");
        } catch (IOException e) {
            System.err.println("Error al cargar los datos de favoritos: " + e.getMessage());
        }
        return peliculasFavoritas;
    }

    public static void guardarPeliculas(Map<String, Pelicula> peliculas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RutaPeliculas))) {
            for (Pelicula pelicula : peliculas.values()) {
                writer.write(pelicula.getNombre() + ";" + pelicula.getClasificacion() + ";" + pelicula.getDirector() + ";" + pelicula.getEstado());
                writer.newLine();
            }
            System.out.println("Datos de películas guardados exitosamente en " + RutaPeliculas);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos de películas: " + e.getMessage());
        }
    }

    public static void guardarFavoritos(List<Pelicula> peliculasFavoritas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RutaFavoritos))) {
            for (Pelicula pelicula : peliculasFavoritas) {
                writer.write(pelicula.getNombre());
                writer.newLine();
            }
            System.out.println("Favoritos guardados exitosamente en " + RutaFavoritos);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos de favoritos: " + e.getMessage());
        }
    }
}
