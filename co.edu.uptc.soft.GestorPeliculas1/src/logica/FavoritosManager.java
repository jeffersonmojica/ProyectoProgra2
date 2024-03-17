package logica;

import java.util.ArrayList;
import java.util.List;
import model.Pelicula;

public class FavoritosManager {
    private List<Pelicula> favoritos;
    private static FavoritosManager instancia = new FavoritosManager();

    public FavoritosManager() {
        this.favoritos = new ArrayList<>();
    }

    public void agregarFavorito(Pelicula pelicula) {
        favoritos.add(pelicula);
    }

    public void quitarFavorito(Pelicula pelicula) {
        favoritos.remove(pelicula);
    }

    public List<Pelicula> obtenerFavoritos() {
        return favoritos;
    }

    public static FavoritosManager getInstancia() {
        return instancia;
    }
}
