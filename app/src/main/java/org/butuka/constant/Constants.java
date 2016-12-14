package org.butuka.constant;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class Constants {

    private Constants() {
    }

    public interface URLS {
        // SERVICE
        String WEB_SERVICE_URL = "http://butuka.org/butuka/service.php";
        String WEB_SERVICE_LOCAL_URL = "http://192.168.25.221/butuka/service.php";
    }

    public interface KEYS {
        // KEYS
        String LOCATION_KEY = "location";
        String DATE_KEY = "date";
        String TIME_KEY = "time";
        String VIOLATOR_KEY = "violator";
        String DESCRIPTION_KEY = "description";
        String MIME_KEY = "mime";
        String DATA_KEY = "data";
        String LATITUDE = "latitude";
        String LONGITUDE = "longitude";
    }

    public interface MESSAGES {
        String SUCCESS = "Denúncia enviada com sucesso.";

        // Menssagens de erro.
        String NO_NETWORK = "Não foi possível acessar a rede, verifique sua conexão.";
        String FAILED = "Falha ao enviar denúncia, tente novamente em alguns instantes.";
    }
}
