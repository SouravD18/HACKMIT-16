package toilet.hackmit;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by abigailkatcoff on 9/17/16.
 */
public class Toilet {
    public Toilet(LatLng latLng, int gender, int id, String building, int floor) {
        this.latLng = latLng;
        this.gender = gender;
        this.id = id;
        this.building = building;
        this.floor = floor;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    private LatLng latLng;
    private int gender;
    private int id;
    private String building;
    private int floor;
}
