package dominio;
//holi soy yo de nuevo
// bien 
public class Imagenes {
    private String nombreImg, idImg;

    public Imagenes(String nombreImg, String idImg) {
        this.nombreImg = nombreImg;
        this.idImg = idImg;
    }

    public Imagenes() {
    }

    public String getNombreImg() {
        return nombreImg;
    }

    public void setNombreImg(String nombreImg) {
        this.nombreImg = nombreImg;
    }

    public String getIdImg() {
        return idImg;
    }

    public void setIdImg(String idImg) {
        this.idImg = idImg;
    }

    @Override
    public String toString() {
        return "Imagenes{" +
                "nombreImg='" + nombreImg + '\'' +
                ", idImg='" + idImg + '\'' +
                '}';
    }
}
