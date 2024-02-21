package dominio;
//entre al sistema
// creo
// holi
public class Documentos {

    private String nombreDoc,idDoc;

    public Documentos(String nombreDoc, String idDoc) {
        this.nombreDoc = nombreDoc;
        this.idDoc = idDoc;
    }

    public Documentos() {
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    @Override
    public String toString() {
        return "Documentos{" +
                "nombreDoc='" + nombreDoc + '\'' +
                ", idDoc='" + idDoc + '\'' +
                '}';
        
        //imagnes "portadas"
        //cambiar nombre al proyecto
    }
}
