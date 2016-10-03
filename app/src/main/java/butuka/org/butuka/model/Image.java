package butuka.org.butuka.model;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by iagobelo on 30/09/2016.
 */

public class Image {
    private String mime;
    private Bitmap bitmap;

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setMimeFromImgPath(String imgPath) {
        String[] aux = imgPath.split("\\.");
        setMime(aux[aux.length - 1]);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String toBase64() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if (mime.equalsIgnoreCase("png")) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        } else if (mime.equalsIgnoreCase("jpg")) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        } else {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        }
        byte[] bytes = stream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
