package application;

//import java.io.InputStream;
import javafx.fxml.FXML;
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
   /* public void setData(Pelicula pelicula) {
        String imagenSrc = pelicula.getImagenSrc();
        
        // Verificar si la ruta de la imagen no es null
        if (imagenSrc != null) {
            try {
                // Intenta cargar la imagen
                InputStream inputStream = getClass().getResourceAsStream(imagenSrc);
                
                // Verifica si el InputStream no es null antes de crear la imagen
                if (inputStream != null) {
                    Image image = new Image(inputStream);
                    imagenPelicula.setImage(image);
                } else {
                    // Manejar el caso en el que el InputStream es null
                    System.err.println("InputStream es nulo para la ruta de imagen: " + imagenSrc);
                }
            } catch (Exception e) {
                // Maneja la excepción según sea necesario
                e.printStackTrace();
            }
        } else {
            // Manejar el caso en el que la ruta de la imagen es null
            // Puedes asignar una imagen predeterminada o mostrar un mensaje de error
            System.err.println("La ruta de la imagen es nula para la película: " + pelicula.getTitulo());
            // También podrías asignar una imagen predeterminada, si tienes una
            // Image imageDefault = new Image(getClass().getResourceAsStream("/path/a/tu/imagen/default.jpg"));
            // imagenPelicula.setImage(imageDefault);
        }

        // Resto del código...
        tituloPelicula.setText(pelicula.getTitulo());
        descripcion.setText(pelicula.getDescripcion());
    }*/

}