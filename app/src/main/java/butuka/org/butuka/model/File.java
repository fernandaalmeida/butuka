package butuka.org.butuka.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.webkit.MimeTypeMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butuka.org.butuka.util.Base64;

/**
 * Created by iagobelo on 30/09/2016.
 */

public class File {
    private String fileName;
    private Uri uri;
    private String mime;
    private Context context;

    public File(Context context, Uri uri) {
        this.context = context;
        this.uri = uri;

        getFileProp();
    }

    public String getMime() {
        return this.mime;
    }

    private void getFileProp() {
        // Pega extensão do arquivo.
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        this.mime = mime.getExtensionFromMimeType(cR.getType(uri));

        // Pega nome do arquivo.
        String result = null;

        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }

        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');

            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        fileName = result;
    }

    public String toBase64() throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        return Base64.encodeBytes(getBytes(inputStream));
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public Uri getUri() {
        return uri;
    }

    public String getFileName() {
        return fileName;
    }
}
