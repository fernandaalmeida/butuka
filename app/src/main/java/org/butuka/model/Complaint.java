package org.butuka.model;

import org.butuka.constant.Constants;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class Complaint implements Serializable {
    private String location = "";
    private String date = "";
    private String time = "";
    private String violator = "";
    private String description = "";
    private FileProp fileProp = null;

    public Complaint() {

    }

    public Map<String, String> toHashMap() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEYS.LOCATION_KEY, (location.length()) <= 1 ? "null" : location);
        map.put(Constants.KEYS.DATE_KEY, (date.length() <= 1) ? "null" : date);
        map.put(Constants.KEYS.TIME_KEY, (time.length() <= 1) ? "null" : time);
        map.put(Constants.KEYS.VIOLATOR_KEY, (violator.length()) <= 1 ? "null" : violator);
        map.put(Constants.KEYS.DESCRIPTION_KEY, (description.length()) <= 1 ? "null" : description);
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

    public FileProp getFileProp() {
        return fileProp;
    }

    public void setFileProp(FileProp fileProp) {
        this.fileProp = fileProp;
    }
}
