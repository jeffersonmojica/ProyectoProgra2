package logica;
import dominio.Documentos;
import dominio.Imagenes;
import dominio.Videos;

import java.util.ArrayList;
import java.util.Objects;

public class GestorArchivos <T> {

    private ArrayList <Documentos> documentos = new ArrayList<>();
    private ArrayList <T> bd ;
    private ArrayList <Imagenes> imagenes = new ArrayList<>();
    private ArrayList <Videos> videos = new ArrayList<>();

    public GestorArchivos() {
        bd= new ArrayList<>();

    }
    public void agrArchivo(T archivo) {
        bd.add(archivo);
    }



    public void agregarArchivo(T archivo) {
        if (archivo.equals((Documentos)archivo)){
            documentos.add((Documentos) archivo);
            System.out.println("doc");
        }
        if (archivo.equals((Imagenes)archivo)) {
            imagenes.add((Imagenes) archivo);
        }if (archivo.equals((Videos)archivo)) {
            videos.add((Videos) archivo);

        }

    }

    @Override
    public String toString() {
        return "GestorArchivos{" +
                "documentos=" + documentos +
                ", imagenes=" + imagenes +
                ", videos=" + videos +
                '}'+"\n";
    }
}
