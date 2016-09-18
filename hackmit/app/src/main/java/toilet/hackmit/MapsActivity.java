package toilet.hackmit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.plus.model.people.Person;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private List<Toilet> getToilets(){
        ArrayList<Toilet> toilets = new ArrayList<>();
        //TODO:AK get info from the database.
        toilets.add(new Toilet(new LatLng(-35.02, 151.35), Person.Gender.FEMALE, 1, "Stata", 1));
        toilets.add(new Toilet(new LatLng(-35, 151.33), Person.Gender.MALE, 2, "Bldg", 4));
        toilets.add(new Toilet(new LatLng(-35.01, 151.02), Person.Gender.OTHER, 3, "Other Bldg", 2));
        return toilets;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        List<Toilet> toilets = getToilets();
        List<Marker> markers=new ArrayList<>();
        //builder to make sure the map includes all the markers we place
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Toilet toilet: toilets)
        {
            builder.include(toilet.getLatLng());
            //add marker for each toilet
            MarkerOptions options = new MarkerOptions().position(toilet.getLatLng()).title(toilet.getBuilding());
            //snippet is the text shown at the top
            String snippet=("Building: " + toilet.getBuilding() + "\nFloor: " + toilet.getFloor());
            switch (toilet.getGender())
            {
                case Person.Gender.FEMALE:
                    options.position(toilet.getLatLng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                    snippet=snippet+ "\nGender: " + "Female";
                    break;
                case Person.Gender.MALE:
                    options.position(toilet.getLatLng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    snippet=snippet+ "\nGender: " + "Male";
                    break;
                case Person.Gender.OTHER:
                    options.position(toilet.getLatLng()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    snippet=snippet+ "\nGender: " + "Unisex";
                    break;
            }
            options.position(toilet.getLatLng()).snippet(snippet);
            markers.add(mMap.addMarker(options));
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,0);
        googleMap.moveCamera(cu);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        ((TextView) findViewById(R.id.info)).setText(marker.getSnippet());
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        //update the text
        ((TextView) findViewById(R.id.info)).setText(marker.getSnippet());
        return false;
    }

}
