package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logica.FavoritosManager;
import model.Pelicula;

public class Controller implements Initializable {

	@FXML
	private HBox cardLayout;
	@FXML
	private GridPane peliculaContainer;
	@FXML
	private TextField searchField;
    @FXML
    private ComboBox<String> genreComboBox;

	private List<Pelicula> agregadoRecientemente;
	private List<Pelicula> paraTi;
    private FavoritosManager favoritosManager; // Instancia de FavoritosManager

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    agregadoRecientemente = new ArrayList<>(agregadoRecientemente());
	    paraTi = new ArrayList<>(peliculas());
        favoritosManager = new FavoritosManager();

	    //********* Configurar ComboBox de género
	    List<String> genres = obtenerGeneros(paraTi);
	    genres.add(0, "Todos"); // Añadir una opción "Todos" al inicio
	    genreComboBox.setItems(FXCollections.observableArrayList(genres));

	    // Configurar listeners
	    searchField.textProperty().addListener((observable, oldValue, newValue) -> filterMoviesByTitle(newValue));
	    genreComboBox.setOnAction(event -> filterMoviesByGenre(genreComboBox.getValue()));

	    loadMovies();
	}
	
	

	//++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@FXML
	public void mostrarFavoritas(ActionEvent event) {
	    // Supongamos que tienes una lista de películas favoritas en tu clase FavoritosManager
	    List<Pelicula> peliculasFavoritas = FavoritosManager.getInstancia().obtenerFavoritos();

	    // Ahora puedes mostrar las películas favoritas en la interfaz de usuario
	    for (Pelicula pelicula : peliculasFavoritas) {
	        // Aquí puedes agregar lógica para mostrar cada película en la interfaz
	        System.out.println("Título: " + pelicula.getTitulo());
	        System.out.println("Descripción: " + pelicula.getDescripcion());
	        System.out.println("Género: " + pelicula.getGenero());
	        // Puedes usar los elementos de tu interfaz como Labels e ImageViews para mostrar la información de la película
	    }
	}


	//++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	/////////////////////////////----------busqueda ya no afecta "recientemente"---------------------------------
	
	private void filterMoviesByTitle(String searchText) {
	    List<Pelicula> filteredMovies = paraTi.stream()
	            .filter(pelicula -> pelicula.getTitulo().toLowerCase().contains(searchText.toLowerCase()) || searchText.isEmpty())
	            .collect(Collectors.toList());

	    displayFilteredMovies(filteredMovies, false); // El segundo parámetro indica que no se aplica el filtro a "Agregado Recientemente"
	}

	private void filterMoviesByGenre(String selectedGenre) {
	    List<Pelicula> filteredMovies = paraTi.stream()
	            .filter(pelicula -> "Todos".equals(selectedGenre) || pelicula.getGenero().equals(selectedGenre))
	            .collect(Collectors.toList());

	    displayFilteredMovies(filteredMovies, false); // El segundo parámetro indica que no se aplica el filtro a "Agregado Recientemente"
	}

	private void displayFilteredMovies(List<Pelicula> filteredMovies, boolean applyFilters) {
	    // Limpiar la interfaz de películas "Para Ti"
	    peliculaContainer.getChildren().clear();
	    cardLayout.getChildren().clear(); // Limpiar el layout de tarjetas

	    int column = 0;
	    int row = 1;

	    try {
	        // Mostrar películas "Agregado Recientemente"
	        for (Pelicula pelicula : agregadoRecientemente) {
	            FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("card.fxml"));
	            HBox cardBox = fxmlLoader.load();
	            CardController cardController = fxmlLoader.getController();
	            cardController.setData(pelicula);
	            HBox.setMargin(cardBox, new Insets(0, 10, 0, 10));
	            cardLayout.getChildren().add(cardBox);
	        }

	        // Mostrar películas filtradas "Para Ti"
	        for (Pelicula pelicula : filteredMovies) {
	            FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("pelicula.fxml"));
	            VBox peliculaBox = fxmlLoader.load();
	            PeliculaController peliculaController = fxmlLoader.getController();
	            peliculaController.setData(pelicula);
	            HBox.setMargin(peliculaBox, new Insets(0, 10, 0, 10));

	            if (column == 6) {
	                column = 0;
	                ++row;
	            }
	            peliculaContainer.add(peliculaBox, column++, row);
	            GridPane.setMargin(peliculaBox, new Insets(10, 10, 10, 10));
	        }

	        if (applyFilters) {
	            // Aplicar filtros solo si se indica que se deben aplicar
	            List<Pelicula> filteredRecentlyAdded = agregadoRecientemente.stream()
	                    .filter(pelicula -> paraTi.stream().anyMatch(p -> p.getTitulo().equals(pelicula.getTitulo())))
	                    .collect(Collectors.toList());
	            displayFilteredMovies(filteredRecentlyAdded, false); // No aplicar filtros en esta llamada
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	////////////////////////////

	private List<String> obtenerGeneros(List<Pelicula> peliculas) {
	    return peliculas.stream()
	            .map(Pelicula::getGenero)
	            .distinct()
	            .collect(Collectors.toList());
	}

private void loadMovies() {
	    int column = 0;
	    int row = 1;

	    try {
	        for (Pelicula value : agregadoRecientemente) {
	            FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("card.fxml"));
	            HBox cardBox = fxmlLoader.load();
	            CardController cardController = fxmlLoader.getController();
	            cardController.setData(value);
	            HBox.setMargin(cardBox, new Insets(0, 10, 0, 10));
	            cardLayout.getChildren().add(cardBox);
	        }

	        for (Pelicula pelicula : paraTi) {
	            FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("pelicula.fxml"));
	            VBox peliculaBox = fxmlLoader.load();
	            PeliculaController peliculaController = fxmlLoader.getController();
	            peliculaController.setData(pelicula);
	            HBox.setMargin(peliculaBox, new Insets(0, 10, 0, 10));

	            if (column == 6) {
	                column = 0;
	                ++row;
	            }
	            peliculaContainer.add(peliculaBox, column++, row);
	            GridPane.setMargin(peliculaBox, new Insets(10, 10, 10, 10));
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private List<Pelicula> agregadoRecientemente(){
		List<Pelicula> ls = new ArrayList<>();

		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("INTERSTELLAR");
		pelicula.setImagenSrc("/Img/p1.jpg");
		pelicula.setDescripcion("2014 - Ciencia ficción - 2h 49m");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("PARASITE");
		pelicula.setImagenSrc("/Img/p2.jpg");
		pelicula.setDescripcion("2019 - Suspenso - 2h 12m");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("PULP FICTION");
		pelicula.setImagenSrc("/Img/p3.jpg");
		pelicula.setDescripcion("1994 - Crimen - 2h 34m");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("EL PRECIO DEL MAÑANA");
		pelicula.setImagenSrc("/Img/p4.jpg");
		pelicula.setDescripcion("2011 - Acción - 1h 49m");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("BLADE RUNNER 2049");
		pelicula.setImagenSrc("/Img/p5.jpeg");
		pelicula.setDescripcion("2017 - Ciencia ficción - 2h 43m");
		ls.add(pelicula);


		pelicula = new Pelicula();
		pelicula.setTitulo("SPACEMAN");
		pelicula.setImagenSrc("/Img/p6.jpeg");
		pelicula.setDescripcion("2024 - Ciencia ficción - 1h 49m");
		ls.add(pelicula);

		return ls;

	}

	private List<Pelicula> peliculas(){
		List<Pelicula> ls = new ArrayList<>();

		//------CIENCIA FICCION--------
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("BLADE RUNNER 2049");
		pelicula.setImagenSrc("/Img/p5.jpeg");
		pelicula.setDescripcion("2017 - Ciencia ficción - 2h 43m");
		pelicula.setGenero("Ciencia ficción");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("SPACEMAN");
		pelicula.setImagenSrc("/Img/p6.jpeg");
		pelicula.setDescripcion("2024 - Ciencia ficción - 1h 49m");
		pelicula.setGenero("Ciencia ficción");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("SPIDER-MAN");
		pelicula.setImagenSrc("/Img/p7.jpg");
		pelicula.setDescripcion("2021 - Ciencia ficción - 2h 28m");
		pelicula.setGenero("Ciencia ficción");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("RESISTENCIA");
		pelicula.setImagenSrc("/Img/p18.jpg");
		pelicula.setDescripcion("2023 - Ciencia ficción - 2h 13m");
		pelicula.setGenero("Ciencia ficción");
		ls.add(pelicula);		

		pelicula = new Pelicula();
		pelicula.setTitulo("EL ORIGEN");
		pelicula.setImagenSrc("/Img/p19.jpeg");
		pelicula.setDescripcion("2010 - Ciencia ficción - 2h 28m");
		pelicula.setGenero("Ciencia ficción");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("INTERSTELLAR");
		pelicula.setImagenSrc("/Img/p1.jpg");
		pelicula.setDescripcion("2014 - Ciencia ficción - 2h 49m");
		pelicula.setGenero("Ciencia ficción");
		ls.add(pelicula);
		
		//-------------COMEDIA---------------------------------
		pelicula = new Pelicula();
		pelicula.setTitulo("MISTERIO A LA VISTA");
		pelicula.setImagenSrc("/Img/p8.jpeg");
		pelicula.setDescripcion("2023 - Comedia - 1h 28m");
		pelicula.setGenero("Comedia");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("EL MAESTRO LUCHADOR");
		pelicula.setImagenSrc("/Img/p9.jpg");
		pelicula.setDescripcion("2012 - Comedia - 1h 45m");
		pelicula.setGenero("Comedia");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("SUPERCOOL");
		pelicula.setImagenSrc("/Img/p10.jpeg");
		pelicula.setDescripcion("2007 - Comedia - 1h 59m");
		pelicula.setGenero("Comedia");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("DOS TONTOS MUY TONTOS");
		pelicula.setImagenSrc("/Img/p11.jpeg");
		pelicula.setDescripcion("1994 - Comedia - 1h 47m");
		pelicula.setGenero("Comedia");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("KICK-ASS");
		pelicula.setImagenSrc("/Img/p12.jpeg");
		pelicula.setDescripcion("2010 - Comedia - 1h 57m");
		pelicula.setGenero("Comedia");
		ls.add(pelicula);


		//--------------------TERROR------------------
		pelicula = new Pelicula();
		pelicula.setTitulo("LA HUERFANA");
		pelicula.setImagenSrc("/Img/p13.jpeg");
		pelicula.setDescripcion("2009 - Terror - 2h 3m");
		pelicula.setGenero("Terror");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("ANABELLE");
		pelicula.setImagenSrc("/Img/p14.jpeg");
		pelicula.setDescripcion("2014 - Terror - 1h 35m");
		pelicula.setGenero("Terror");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("EL ARO");
		pelicula.setImagenSrc("/Img/p15.jpg");
		pelicula.setDescripcion("2002 - Terror - 1h 55m");
		pelicula.setGenero("Terror");
		ls.add(pelicula);
		
		pelicula = new Pelicula();
		pelicula.setTitulo("EL CONJURO");
		pelicula.setImagenSrc("/Img/p16.jpeg");
		pelicula.setDescripcion("2013 - Terror - 1h 52m");
		pelicula.setGenero("Terror");
		ls.add(pelicula);
		
		pelicula = new Pelicula();
		pelicula.setTitulo("LA MONJA");
		pelicula.setImagenSrc("/Img/p17.jpeg");
		pelicula.setDescripcion("2018 - Terror - 1h 36m");
		pelicula.setGenero("Terror");
		ls.add(pelicula);

		//---------------------Acción--------------------------------------
		pelicula = new Pelicula();
		pelicula.setTitulo("BATMAN");
		pelicula.setImagenSrc("/Img/p20.jpg");
		pelicula.setDescripcion("2012 - Acción - 2h 45m");
		pelicula.setGenero("Acción");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("BUSQUEDA IMPLACABLE");
		pelicula.setImagenSrc("/Img/p21.jpeg");
		pelicula.setDescripcion("2008 - Acción - 1h 33m");
		pelicula.setGenero("Acción");
		ls.add(pelicula);
		
		pelicula = new Pelicula();
		pelicula.setTitulo("EL ESPECIALISTA");
		pelicula.setImagenSrc("/Img/p22.jpeg");
		pelicula.setDescripcion("2016 - Acción - 1h 38m");
		pelicula.setGenero("Acción");
		ls.add(pelicula);
		
		pelicula = new Pelicula();
		pelicula.setTitulo("JACK REACHER");
		pelicula.setImagenSrc("/Img/p23.jpeg");
		pelicula.setDescripcion("2012 - Acción - 2h 10m");
		pelicula.setGenero("Acción");
		ls.add(pelicula);
		
		pelicula = new Pelicula();
		pelicula.setTitulo("AL FILO DEL MAÑANA");
		pelicula.setImagenSrc("/Img/p24.jpeg");
		pelicula.setDescripcion("2014 - Acción - 1h 53m");
		pelicula.setGenero("Acción");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("EL PRECIO DEL MAÑANA");
		pelicula.setImagenSrc("/Img/p4.jpg");
		pelicula.setDescripcion("2011 - Acción - 1h 49m");
		pelicula.setGenero("Acción");
		ls.add(pelicula);
		
		pelicula = new Pelicula();
		pelicula.setTitulo("PULP FICTION");
		pelicula.setImagenSrc("/Img/p3.jpg");
		pelicula.setDescripcion("1994 - Acción - 2h 34m");
		pelicula.setGenero("Acción");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("PARASITE");
		pelicula.setImagenSrc("/Img/p2.jpg");
		pelicula.setDescripcion("2019 - Suspenso - 2h 12m");
		pelicula.setGenero("Suspenso");
		ls.add(pelicula);
		
		
		//------------------ANIMADAS---------------
		pelicula = new Pelicula();
		pelicula.setTitulo("CARS");
		pelicula.setImagenSrc("/Img/p25.jpg");
		pelicula.setDescripcion("2006 - Animada - 1h 57m");
		pelicula.setGenero("Animada");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("EL REY LEON");
		pelicula.setImagenSrc("/Img/p26.jpeg");
		pelicula.setDescripcion("1994 - Animada - 1h 28m");
		pelicula.setGenero("Animada");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("COMO ENTRENAR A TU DRAGON");
		pelicula.setImagenSrc("/Img/p27.jpeg");
		pelicula.setDescripcion("2010 - Animada - 1h 38m");
		pelicula.setGenero("Animada");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("SHREK");
		pelicula.setImagenSrc("/Img/p28.jpg");
		pelicula.setDescripcion("2001 - Animada - 1h 30m");
		pelicula.setGenero("Animada");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("KUNG FU PANDA");
		pelicula.setImagenSrc("/Img/p29.jpeg");
		pelicula.setDescripcion("2008 - Animada - 1h 32m");
		pelicula.setGenero("Animada");
		ls.add(pelicula);

		pelicula = new Pelicula();
		pelicula.setTitulo("RANGO");
		pelicula.setImagenSrc("/Img/p30.jpg");
		pelicula.setDescripcion("2011 - Animada - 1h 47m");
		pelicula.setGenero("Animada");
		ls.add(pelicula);
		//peliculas

		return ls;

	}
}


/*private void filterMoviesByTitle(String searchText) {
List<Pelicula> filteredMovies = paraTi.stream()
        .filter(pelicula -> pelicula.getTitulo().toLowerCase().contains(searchText.toLowerCase()) || searchText.isEmpty())
        .collect(Collectors.toList());

displayFilteredMovies(filteredMovies);
}

private void filterMoviesByGenre(String selectedGenre) {
List<Pelicula> filteredMovies = paraTi.stream()
        .filter(pelicula -> "Todos".equals(selectedGenre) || pelicula.getGenero().equals(selectedGenre))
        .collect(Collectors.toList());

displayFilteredMovies(filteredMovies);
}

private void displayFilteredMovies(List<Pelicula> filteredMovies) {
// Limpiar la interfaz
cardLayout.getChildren().clear();
peliculaContainer.getChildren().clear();

int column = 0;
int row = 1;

try {
    for (Pelicula pelicula : filteredMovies) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("pelicula.fxml"));
        VBox peliculaBox = fxmlLoader.load();
        PeliculaController peliculaController = fxmlLoader.getController();
        peliculaController.setData(pelicula);
        HBox.setMargin(peliculaBox, new Insets(0, 10, 0, 10));

        if (column == 6) {
            column = 0;
            ++row;
        }
        peliculaContainer.add(peliculaBox, column++, row);
        GridPane.setMargin(peliculaBox, new Insets(10, 10, 10, 10));
    }
} catch (IOException e) {
    e.printStackTrace();
}
}*/




