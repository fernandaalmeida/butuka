package org.butuka.util;

import android.util.Base64;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by iagobelo on 06/10/2016.
 */

public class LightEncode {
    private int CHUNK = 900;
    private InputStream stream;

    public LightEncode(InputStream stream) {
        this.stream = stream;
    }

    public StringBuffer startEncode() throws IOException {
        // Pega o comprimento do arquivo.
        int fileSize = stream.available();

        // Pega a quantide de chunks q sera carregada.
        int chunkAmount = getChunkAmount(fileSize);
        StringBuffer strBase64 = new StringBuffer();

        byte[] bytes = new byte[CHUNK];

        for (int i = 0; i < chunkAmount; i++) {
            stream.read(bytes);
            strBase64.append(Base64.encodeToString(bytes, Base64.DEFAULT));
        }
        return strBase64;
    }

    /**
     *
     * @param size
     * @return
     */
    private int getChunkAmount(int size) {
        if ((float) size % (float) CHUNK == 0) {
            return (size / CHUNK);
        } else {
            return ((size / CHUNK) + 1);
        }
    }
}
