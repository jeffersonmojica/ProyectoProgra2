package intefaz;

import javax.swing.*;

import dominio.Documentos;
import dominio.Imagenes;
import dominio.Videos;
import logica.GestorArchivos;
public class Runner {
    public static void main(String[] args) {

        GestorArchivos gertor = new GestorArchivos();
        int opcion1 = 0;
        while (opcion1 != 4) {
            opcion1 = JOptionPane.showOptionDialog(null, "seleccione",
                    "Gestor de archivos", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new Object[]{"ingresar archivo", "mostrar","eliminar","editar","salir"}, "");
            switch (opcion1) {
                case 0:
                    int opcion2 = 0;
                    while (opcion2 != 3) {
                        String nombreArchivo = String.valueOf((JOptionPane.showInputDialog("ingrese nombre del archivo")));
                        String codigoArchivo = String.valueOf((JOptionPane.showInputDialog("ingrese codigo del archivo")));
                        opcion2 = JOptionPane.showOptionDialog(null, "seleccione",
                                "Gestor de archivos", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                null, new Object[]{"doc", "imagen", "video", "atras"}, "");
                        switch (opcion2) {
                            case 0:
                                Documentos doc = new Documentos(nombreArchivo,codigoArchivo);
                                //gertor.agregarArchivo(doc);
                                gertor.agrArchivo(doc);
                            //    System.out.println();
                                opcion2=3;
                                break;
                            case 1:
                                Imagenes im = new Imagenes(nombreArchivo,codigoArchivo);
                                //gertor.agregarArchivo(im);
                                gertor.agrArchivo(im);
                                opcion2=3;
                                break;
                            case 2:
                                Videos vid = new Videos(nombreArchivo,codigoArchivo);
                                //gertor.agregarArchivo(vid);
                                gertor.agrArchivo(vid);
                                opcion2=3;
                                break;


                        }

                    }
                case 1:
                    System.out.println(gertor.toString());
                    break;

            }
        }
    }
}
