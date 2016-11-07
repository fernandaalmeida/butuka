package butuka.org.butuka.io;

import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by iagobelo on 25/08/2016.
 */
public class FileHandler {
    private InputStream mInputStream;
    private OutputStream mOutputStream;

    /**
     * Le arquivo através do diretorio passado.
     *
     * @param path Diretório do arquivo.
     * @return Array de int (bytes) do arquivo.
     * @throws IOException
     */
    public byte[] readFile(String path) throws IOException {
        mInputStream = new BufferedInputStream(new FileInputStream(path)); // Abre o arquivo.
        return read();
    }

    /**
     * @param uri
     * @return
     * @throws IOException
     */
    public byte[] readFile(Uri uri) throws IOException {
        mInputStream = new BufferedInputStream(new FileInputStream(new File(String.valueOf(uri)))); // Abre o arquivo.
        return read();
    }

    /**
     * @return
     * @throws IOException
     */
    private byte[] read() throws IOException {
        int size = mInputStream.available(); // Pega o tamanho do array de bytes.
        byte[] data = new byte[size]; // Cria array com o tamanho do arquivo retornado.

        for (int i = 0; i < size; i++) {
            data[i] = (byte) mInputStream.read();
        }
        mInputStream.close();
        return data;
    }

    /**
     * @param path  Diretório do arquivo.
     * @param bytes Array de bytes a serem escritos no arquivo.
     * @throws IOException
     */
    public void writeFile(String path, int[] bytes) throws IOException {
        mOutputStream = new FileOutputStream(path);
        for (int aByte : bytes) {
            mOutputStream.write(aByte);
        }
        mOutputStream.close();
    }

    /**
     * @param path
     * @param bytes
     * @throws IOException
     */
    public void writeFile(String path, byte[] bytes) throws IOException {
        int[] data = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            data[i] = (int) bytes[i];
        }
        writeFile(path, data);
    }

    /**
     * @param path
     * @param s
     * @throws IOException
     */
    public void writeFile(String path, String s) throws IOException {
        byte[] bytes = s.getBytes();
        writeFile(path, bytes);
    }

    /**
     * Cria arquivo no diretorio passado.
     *
     * @param path
     * @return True se o arquivo foi criado com sucesso e False para o contrário.
     * @throws IOException
     */
    public boolean createNewFile(String path) throws IOException {
        File file = new File(path);
        return file.createNewFile();
    }
}
