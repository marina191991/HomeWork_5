package hw8SQLite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class WeatherResponse {
        City cityObject;
        private String cod;
        private float message;
        private float cnt;
        List<ListTemp> list = null;

    public List<ListTemp> getList() {
        return list;
    }

    public void setList(ArrayList<ListTemp> list) {
        this.list = list;
    }

// Getter Methods

        public City getCity() {
            return cityObject;
        }

        public String getCod() {
            return cod;
        }

        public float getMessage() {
            return message;
        }

        public float getCnt() {
            return cnt;
        }

        // Setter Methods

        public void setCity(City cityObject) {
            this.cityObject = cityObject;
        }

        public void setCod(String cod) {
            this.cod = cod;
        }

        public void setMessage(float message) {
            this.message = message;
        }

        public void setCnt(float cnt) {
            this.cnt = cnt;
        }





}
