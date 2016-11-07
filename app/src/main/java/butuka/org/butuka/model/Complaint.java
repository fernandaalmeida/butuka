package butuka.org.butuka.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butuka.org.butuka.constant.Constants;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class Complaint {
    private String location;
    private String date;
    private String time;
    private String violator;
    private String description;
    private File file;

    public Complaint() {

    }

    public Map<String, String> toHashMap() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEYS.LOCATION_KEY, location);
        map.put(Constants.KEYS.DATE_KEY, date);
        map.put(Constants.KEYS.TIME_KEY, time);
        map.put(Constants.KEYS.VIOLATOR_KEY, violator);
        map.put(Constants.KEYS.DESCRIPTION_KEY, description);

        if (file.getUri() != null && file.getMime() != null) {
            map.put(Constants.KEYS.DATA_KEY, file.toBase64());
            map.put(Constants.KEYS.MIME_KEY, file.getMime());
        }
        return map;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getViolator() {
        return violator;
    }

    public void setViolator(String violator) {
        this.violator = violator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
