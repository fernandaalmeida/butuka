package butuka.org.butuka.constant;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class Constants {

    private Constants() {
    }

    public interface URLS {
        /**
         * Service
         */
        String WEB_SERVICE_URL = "http://outthere.esy.es/butuka/service.php";
        String WEB_SERVICE_LOCAL_URL = "http://192.168.25.221/butuka/service.php";
    }

    public interface KEYS {
        /**
         * KEYS
         */
        String LOCATION_KEY = "location";
        String DATE_KEY = "date";
        String TIME_KEY = "time";
        String VIOLATOR_KEY = "violator";
        String DESCRIPTION_KEY = "description";
        String IMAGE_KEY = "image";
        String MIME_KEY = "image-mime";
    }

    public interface MESSAGES {
        String SUCCESS = "Denuncia enviada com sucesso!";

        /**
         * Menssagens de erro.
         */
        String NO_NETWORK = "Não foi possível acessar a rede!";
        String FAILED = "Falha ao enviar denuncia, tente novamente!";
    }
}
