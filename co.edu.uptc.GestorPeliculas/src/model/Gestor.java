package model;

import logic.Pelicula;
import persistence.PersistenciaDatos;

import java.util.ArrayList;
import java.util.HashMap;

public class Gestor {
    private HashMap<String, Pelicula> peliculas;
    private ArrayList<Pelicula> peliculasFavoritas;

    public Gestor() {
        // Cargar películas y favoritos al inicializar el Gestor
        this.peliculas = (HashMap<String, Pelicula>) PersistenciaDatos.cargarPeliculas();
        this.peliculasFavoritas = (ArrayList<Pelicula>) PersistenciaDatos.cargarFavoritos();
    }

    public void agregarPelicula(Pelicula p) {
        peliculas.put(p.getNombre(), new Pelicula(p.getNombre(), p.getClasificacion(), p.getDirector(), p.getEstado()));
        PersistenciaDatos.guardarPeliculas(peliculas);
    }

    public String eliminar(String nombre) {
        Pelicula pelicula = peliculas.get(nombre);
        if (pelicula != null) {
            peliculas.remove(nombre);
            PersistenciaDatos.guardarPeliculas(peliculas);
            return "Pelicula eliminada";
        } else {
            return "La película no existe";
        }
    }

    public String mostrarPorCategoria(String clasificacion) {
        StringBuilder result = new StringBuilder();
        for (Pelicula pelicula : peliculas.values()) {
            if (pelicula.getClasificacion().equals(clasificacion)) {
                result.append(pelicula.toString()).append("\n");
            }
        }
        return result.toString();
    }

    public String mostrarPorEstado(String estado) {
        StringBuilder result = new StringBuilder();
        for (Pelicula pelicula : peliculas.values()) {
            if (pelicula.getEstado().equals(estado)) {
                result.append(pelicula.toString()).append("\n");
            }
        }
        return result.toString();
    }

    public String buscar(String nombre) {
        Pelicula pelicula = peliculas.get(nombre);
        if (pelicula != null) {
            return pelicula.toString();
        } else {
            return "La película no existe.";
        }
    }

    public void agregarAFavoritos(String nombre) {
        Pelicula pelicula = peliculas.get(nombre);
        if (pelicula != null) {
            peliculasFavoritas.add(pelicula);
            PersistenciaDatos.guardarFavoritos(peliculasFavoritas);
        }
    }

    public void agregarAVistas(String nombre) {
        Pelicula pelicula = peliculas.get(nombre);
        if (pelicula != null) {
            peliculas.get(nombre).setEstado("vista");
            PersistenciaDatos.guardarPeliculas(peliculas);
        }
    }

    public String mostrarPeliculasFavoritas() {
        StringBuilder result = new StringBuilder();
        for (Pelicula pelicula : peliculasFavoritas) {
            result.append(pelicula.toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "GestionPeliculas:\n{" + peliculas + '}';
    }
}

//arreglos
//mas arreglos
//otro
