package butuka.org.butuka.model;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butuka.org.butuka.util.Base64;

/**
 * Created by iagobelo on 30/09/2016.
 */

public class Image {
    private String mime;
    private Bitmap bitmap;
    private ByteArrayOutputStream stream;
    private File file;
    private StringBuffer base64Image;

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

    public StringBuffer getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(StringBuffer base64Image) {
        this.base64Image = base64Image;
    }

    /**
     * Converte a imagem para base64.
     *
     * @return String em base64.
     */
    public String bitmapToBase64() {
        byte[] bytes = stream.toByteArray();
        //return Base64.encodeToString(bytes, Base64.DEFAULT);
        return Base64.encodeBytes(bytes);
    }

    /**
     * @param quality
     */
    public void compress(int quality) {
        stream = new ByteArrayOutputStream();

        if (bitmap != null && mime != null) {
            if (mime.equalsIgnoreCase("png")) {
                bitmap.compress(Bitmap.CompressFormat.PNG, quality, stream);
            } else if (mime.equalsIgnoreCase("jpg")) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
            }
        }
    }
}
