package butuka.org.butuka.model;

/**
 * Created by iagobelo on 30/09/2016.
 */

public class Data {
    private String mime;
    private StringBuffer base64;

    public String getMime() {
        if (mime != null) {
            return mime;
        } else {
            return "null";
        }
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setMimeFromPath(String path) {
        String[] aux = path.split("\\.");
        setMime(aux[aux.length - 1]);
    }

    public StringBuffer getBase64() {
        return base64;
    }

    public void setBase64(StringBuffer base64) {
        this.base64 = base64;
    }

}
