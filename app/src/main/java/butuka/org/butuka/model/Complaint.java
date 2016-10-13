package butuka.org.butuka.model;

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
    private Image image;

    public Complaint() {

    }

    public Map<String, String> toHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEYS.LOCATION_KEY, location);
        map.put(Constants.KEYS.DATE_KEY, date);
        map.put(Constants.KEYS.TIME_KEY, time);
        map.put(Constants.KEYS.VIOLATOR_KEY, violator);
        map.put(Constants.KEYS.DESCRIPTION_KEY, description);

        if (image != null && image.getBase64Image() != null) {
            map.put(Constants.KEYS.IMAGE_KEY, String.valueOf(image.getBase64Image()));
            map.put(Constants.KEYS.MIME_KEY, image.getMime());
        } else {
            map.put(Constants.KEYS.IMAGE_KEY, "null");
            map.put(Constants.KEYS.MIME_KEY, "null");
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
