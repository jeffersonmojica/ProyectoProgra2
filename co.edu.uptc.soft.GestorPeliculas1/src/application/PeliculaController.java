package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import logica.FavoritosManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Pelicula;

public class PeliculaController implements Initializable {


	@FXML
	private Label descripcion;

	@FXML
	private ImageView imagenPelicula;

	@FXML
	private Label tituloPelicula;

	@FXML
	private Label generoPelicula;

	@FXML
	private ImageView iconoFavorito;

	private Pelicula pelicula;
    private FavoritosManager favoritosManager; // Instancia de FavoritosManager

	public void setData(Pelicula pelicula) {
		Image image = new Image(getClass().getResourceAsStream(pelicula.getImagenSrc()));
		imagenPelicula.setImage(image);
		tituloPelicula.setText(pelicula.getTitulo());
		descripcion.setText(pelicula.getDescripcion());
		generoPelicula.setText(pelicula.getGenero());
		this.pelicula = pelicula;
		iconoFavorito.setImage(new Image(getClass().getResourceAsStream(pelicula.isFavorita() ? "/Img/favorito_on.png" : "/Img/favorito_off.png")));
	}
	
	 @FXML
	    public void marcarFavorito(MouseEvent event) {
	        pelicula.setFavorita(!pelicula.isFavorita());
	        actualizarIconoFavorito();

	        if (pelicula.isFavorita()) {
	            favoritosManager.agregarFavorito(pelicula);
	        } else {
	            favoritosManager.quitarFavorito(pelicula);
	        }

	        mostrarMensaje("Película marcada como favorita");
	    }

    private void actualizarIconoFavorito() {
        String iconPath = pelicula.isFavorita() ? "/Img/favorito_on.png" : "/Img/favorito_off.png";
        Image image = new Image(getClass().getResourceAsStream(iconPath));
        iconoFavorito.setImage(image);
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

	public void initialize(URL location, ResourceBundle resources) {
        favoritosManager = FavoritosManager.getInstancia(); // Inicialización del favoritosManager
    }
}
