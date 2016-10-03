package butuka.org.butuka.constant;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class Constants {

    private Constants() {
    }

    public interface Error {

        /**
         * Menssagens de erro.
         */
        public static final String NO_NETWORK = "Não foi possível acessar a rede!";
    }

    public interface URLS {
        /**
         * Service
         */
        String WEB_SERVICE_URL = "http://outthere.esy.es/butuka/service.php";
    }

    public interface KEYS {
        /**
         * KEYS
         */
        String LOCATION_KEY = "location";
        String DATE_KEY = "date";
        String TIME_KEY = "time";
        String VIOLATOR_KEY = "violator";
        String DESCRIPTION_KEY = "violator";
        String IMAGE_KEY = "image";
        String MIME_KEY = "image-mime";
    }
}
