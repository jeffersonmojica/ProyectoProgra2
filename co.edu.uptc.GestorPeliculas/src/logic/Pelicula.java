package logic;

public class Pelicula {
    private String nombre, clasificacion, director, estado;

    public Pelicula(String nombre, String clasificacion, String director, String estado) {
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.director = director;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }


    public String getClasificacion() {
        return clasificacion;
    }


    public String getDirector() {
        return director;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pelicula : " +
                "nombre='" + nombre + '\'' +
                ", clasificacion='" + clasificacion + '\'' +
                ", director='" + director + '\'' +
                ", estado='" + estado + '\''+"\n";
    }
}
