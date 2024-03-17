package application;
/*
import java.awt.event.ActionEvent;

//import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Pelicula;

public class PeliculaController {


    @FXML
    private Label descripcion;

    @FXML
    private ImageView imagenPelicula;

    @FXML
    private Label tituloPelicula;
    
	
    @FXML
    private Label generoPelicula;
    
    @FXML
    private ImageView iconoReproduccion;
    
	public void setData(Pelicula pelicula) {
		Image image = new Image(getClass().getResourceAsStream(pelicula.getImagenSrc()));
		imagenPelicula.setImage(image);
		tituloPelicula.setText(pelicula.getTitulo());
		descripcion.setText(pelicula.getDescripcion());
		generoPelicula.setText(pelicula.getGenero());
		
	}
	
	
	
	   @FXML
	    public void reproducirPelicula(MouseEvent event) {
	        tituloPelicula.setText("Reproducir");
	        mostrarMensaje("Reproduciendo película");
	    }

	    private void mostrarMensaje(String mensaje) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Información");
	        alert.setHeaderText(null);
	        alert.setContentText(mensaje);
	        alert.showAndWait();
	    }
	    @FXML
	    public void initialize() {
	        // Ocultar el icono de reproducción al inicio
	        iconoReproduccion.setVisible(false);
	    }

	    @FXML
	    public void mostrarIcono() {
	        // Mostrar el icono de reproducción al pasar el cursor sobre la imagen
	        iconoReproduccion.setVisible(true);
	    }
}*/
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Pelicula;

public class PeliculaController {

    @FXML
    private Label descripcion;

    @FXML
    private ImageView imagenPelicula;

    @FXML
    private Label tituloPelicula;
    
    @FXML
    private Label generoPelicula;
    
    public void setData(Pelicula pelicula) {
        Image image = new Image(getClass().getResourceAsStream(pelicula.getImagenSrc()));
        imagenPelicula.setImage(image);
        tituloPelicula.setText(pelicula.getTitulo());
        descripcion.setText(pelicula.getDescripcion());
        generoPelicula.setText(pelicula.getGenero());
    }
    
    @FXML
    public void reproducirPelicula(MouseEvent event) {
        tituloPelicula.setText("Reproducir");
        mostrarMensaje("Reproduciendo película");
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
