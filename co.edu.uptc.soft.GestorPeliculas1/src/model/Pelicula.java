package model;

public class Pelicula {
private String Titulo;
private String imagenSrc;
private String descripcion;
private String genero;
private boolean favorita;



public boolean isFavorita() {
	return favorita;
}
public void setFavorita(boolean favorita) {
	this.favorita = favorita;
}
public String getGenero() {
	return genero;
}
public void setGenero(String genero) {
	this.genero = genero;
}
public String getTitulo() {
	return Titulo;
}
public void setTitulo(String nombre) {
	this.Titulo = nombre;
}
public String getImagenSrc() {
	return imagenSrc;
}
public void setImagenSrc(String imagenSrc) {
	this.imagenSrc = imagenSrc;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

}
