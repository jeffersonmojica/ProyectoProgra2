package dominio;

public class Videos {
    private String nombreVid, idVid;

    public Videos(String nombreVid, String idVid) {
        this.nombreVid = nombreVid;
        this.idVid = idVid;
    }

    public Videos() {
    }

    @Override
    public String toString() {
        return "Videos{" +
                "nombreVid='" + nombreVid + '\'' +
                ", idVid='" + idVid + '\'' +
                '}';
    }
}
