package butuka.org.butuka.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butuka.org.butuka.constant.Constants;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class Complaint implements Serializable {
    private String location;
    private String date;
    private String time;
    private String violator;
    private String description;
    private String base64;
    private String fileMime;

    public Complaint() {

    }

    public Map<String, String> toHashMap() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEYS.LOCATION_KEY, (location.length()) <= 1 ? "null" : location);
        map.put(Constants.KEYS.DATE_KEY, (date.length() <= 1) ? "null" : date);
        map.put(Constants.KEYS.TIME_KEY, (time.length() <= 1) ? "null" : time);
        map.put(Constants.KEYS.VIOLATOR_KEY, (violator.length()) <= 1 ? "null" : violator);
        map.put(Constants.KEYS.DESCRIPTION_KEY, (description.length()) <= 1 ? "null" : description);
        map.put(Constants.KEYS.DATA_KEY, (base64 == null) ? "null" : base64);
        map.put(Constants.KEYS.MIME_KEY, (fileMime == null) ? "null" : fileMime);
        return map;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", violator='" + violator + '\'' +
                ", description='" + description + '\'' +
                ", base64='" + base64 + '\'' +
                ", fileMime='" + fileMime + '\'' +
                '}';
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

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getFileMime() {
        return fileMime;
    }

    public void setFileMime(String fileMime) {
        this.fileMime = fileMime;
    }
}
