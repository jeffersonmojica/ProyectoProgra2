package interfaz;



import logic.Pelicula;
import model.Gestor;

import javax.swing.*;

public class Runner {

    public static void main(String[] args) {
        String nombre="";
        Pelicula p;
        Gestor gs = new Gestor();
        int opcion = 0;

        while (opcion != 2) {
            opcion = JOptionPane.showOptionDialog(null, "¿seleccione una opcion?",
                    "Encuesta", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new Object[]{"listas de reproduccion","peliculas", "salir"}, "Usuario");
            switch (opcion){
                case 0:
                    while (opcion != 4) {
                        opcion = JOptionPane.showOptionDialog(null, "¿seleccione una opcion?",
                                "Encuesta", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                null, new Object[]{"categorias", "vistas", "favoritas", "ver lista de todas las peliculas", "atras"}, "Usuario");
                        switch ( opcion ){
                            case 0:
                                String[] categoria1 = { "Acción","Comedia","Drama","Fantasía","Suspenso","Terror","Romance","Animacion"};
                                String opcion2 = (String) JOptionPane.showInputDialog(null,
                                        "Seleecione una categoria", "Opciones",JOptionPane.QUESTION_MESSAGE,null, categoria1,categoria1[0]);
                                JOptionPane.showMessageDialog(null, gs.mostrarPorCategoria(opcion2),"Peliculas:",JOptionPane.CLOSED_OPTION);

                                break;
                            case 1 :
                                JOptionPane.showMessageDialog(null,gs.mostrarPorEstado("vista"),"peliculas vistas",JOptionPane.CLOSED_OPTION);
                                break;
                            case 2 :
                                JOptionPane.showMessageDialog(null,gs.mostrarPeliculasFavoritas(),"peliculas favoritas",JOptionPane.CLOSED_OPTION);
                                break;
                            case 3 :
                                System.out.println(" muestra todas las peliculas");
                                JOptionPane.showMessageDialog(null, gs.toString(),"Peliculas:",JOptionPane.CLOSED_OPTION);
                                System.out.println(gs.toString());
                                break;
                        }}


                    break;

                    //****************************************************************************



                case 1 :
                    while (opcion != 5) {
                        opcion = JOptionPane.showOptionDialog(null, "¿seleccione una opcion?",
                                "Encuesta", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                null, new Object[]{ "agregar pelicula","eliminar pelicula","buscar pelicula","agregar a favoritos","agregar a vistas","atras"}, "Usuario");
                        switch (opcion) {
                            case 0:
                                System.out.println("agrega pelicula");
                                nombre = JOptionPane.showInputDialog(null, "Ingrese nombre:");
                                String[] categoria = { "Acción","Comedia","Drama","Fantasía","Suspenso","Terror","Romance","Animacion"};
                                String opcion1 = (String) JOptionPane.showInputDialog(null,
                                        "Seleecione una categoria", "Opciones",JOptionPane.QUESTION_MESSAGE,null, categoria,categoria[0]);
                                String director = JOptionPane.showInputDialog(null, "Ingrese nombre del director:");
                                int V_NV = Integer.parseInt(String.valueOf(JOptionPane.showOptionDialog(null, "¿seleccione una opcion?",
                                        "Encuesta", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                        null, new Object[]{"vista ", "por ver"}, "Usuario")));
                                String estado="";
                                if (V_NV==0){
                                    estado= "vista";
                                }else if (V_NV ==1) {estado = " por ver";}
                                p = new Pelicula(nombre,opcion1,director,estado);
                                gs.agregarPelicula(p);
                                break;
                            case 1 :
                                System.out.println("elimina pelicula");
                                nombre = JOptionPane.showInputDialog(null, "Ingrese nombre:");
                                gs.eliminar(nombre);
                                break;
                            case 2 :
                                System.out.println("busca pelicula");
                                nombre = JOptionPane.showInputDialog(null, "Ingrese nombre:");
                                JOptionPane.showMessageDialog(null, gs.buscar(nombre),"Peliculas:",JOptionPane.CLOSED_OPTION);

                                break;
                            case 3 :
                                System.out.println("agrega a favoritos");
                                nombre = JOptionPane.showInputDialog(null, "Ingrese nombre:");
                                gs.agregarAFavoritos(nombre);

                                break;
                            case 4:
                                nombre = JOptionPane.showInputDialog(null, "Ingrese nombre:");
                                gs.agregarAVistas(nombre);
                                break;


                        }}
                    }

        }
    }}

//committttt