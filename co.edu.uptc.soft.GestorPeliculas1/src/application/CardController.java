package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Pelicula;

public class CardController {
	

    @FXML
    private HBox box;

    @FXML
    private Label descripcion;

    @FXML
    private ImageView imagenPelicula;

    @FXML
    private Label tituloPelicula;

    
    private String[] colors = {"B9E5FF","BDB2FE","FB9AAB","FF5056"};
    
    public void setData(Pelicula pelicula) {
    	Image image = new Image(getClass().getResourceAsStream(pelicula.getImagenSrc()));
    	
    	imagenPelicula.setImage(image);
    	tituloPelicula.setText(pelicula.getTitulo());
    	descripcion.setText(pelicula.getDescripcion());
    	
    	box.setStyle("-fx-background-color: #" + colors[(int)(Math.random() * colors.length)] + ";" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.2), 10, 0, 0, 10);");

    	

    }
}
