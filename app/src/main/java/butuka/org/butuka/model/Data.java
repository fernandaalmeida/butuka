package butuka.org.butuka.model;

import android.net.Uri;

/**
 * Created by iagobelo on 30/09/2016.
 */

public class Data {
    private String fileName;
    private Uri uri;
    private String mime;
    private StringBuffer base64;

    public Data() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
