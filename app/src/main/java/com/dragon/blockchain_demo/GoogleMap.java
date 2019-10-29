package com.dragon.blockchain_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GoogleMap extends FragmentActivity
        implements OnMapReadyCallback , com.google.android.gms.maps.GoogleMap.OnMarkerClickListener {
    private static final String TAG ="" ;
    public com.google.android.gms.maps.GoogleMap map;

    ImageButton eat ;
    ImageButton tour;
    ImageButton bed ;
    Button confirm;
    ImageButton back;

    private static final LatLng hotel1= new LatLng(25.109010, 121.845632);
    private static final LatLng hotel2= new LatLng(25.110625, 121.844526);
    private static final LatLng hotel3= new LatLng(25.109676, 121.845616);
    private static final LatLng hotel4= new LatLng(25.109855, 121.844356);
    private static final LatLng hotel5= new LatLng(25.108561, 121.844020);

    private static final LatLng eat1 = new LatLng(25.107650, 121.843681);
    private static final LatLng eat2 = new LatLng(25.108748, 121.844386);
    private static final LatLng eat3 = new LatLng( 25.108309, 121.843955);
    private static final LatLng eat4 = new LatLng( 25.108485, 121.843971);
    private static final LatLng eat5 = new LatLng( 25.108585, 121.843848);

    private static final LatLng play1 = new LatLng(25.108063, 121.842264);
    private static final LatLng play2 = new LatLng(24.962665, 121.231956);
    private static final LatLng play3 = new LatLng( 25.108733, 121.843395);
    private static final LatLng play4 = new LatLng( 25.107273, 121.842398);
    private static final LatLng play5 = new LatLng( 25.107925, 121.843741);

    private Marker mhotel1;
    private Marker mhotel2;
    private Marker mhotel3;
    private Marker mhotel4;
    private Marker mhotel5;

    private Marker meat1;
    private Marker meat2;
    private Marker meat3;
    private Marker meat4;
    private Marker meat5;

    private Marker mplay1;
    private Marker mplay2;
    private Marker mplay3;
    private Marker mplay4;
    private Marker mplay5;
    PlacesClient placesClient;




    private static final LatLng me = new LatLng(25.109561, 121.844743);
    private Marker mme;
    //private static final LatLng start = new LatLng(24.964852, 121.209432);

    //Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        eat  = findViewById(R.id.imageButton6);
        bed  = findViewById(R.id.imageButton5);
        tour  = findViewById(R.id.imageButton2);
        confirm = findViewById(R.id.button);
        back = findViewById(R.id.button2);
        String[] list = new String[2];
        String apiKey = "AIzaSyD3FAtc8B4KBl87ELlMzSfIigOD_21oEMw";
        Places.initialize(getApplicationContext(), apiKey);
        placesClient = Places.createClient(this);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoogleMap.this, ShopList2.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoogleMap.this, MainActivity.class));
            }
        });

        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mhotel1.setVisible(false);
                mhotel2.setVisible(false);
                mhotel3.setVisible(false);
                mhotel4.setVisible(false);
                mhotel5.setVisible(false);

                meat1.setVisible(true);
                meat2.setVisible(true);
                meat3.setVisible(true);
                meat4.setVisible(true);
                meat5.setVisible(true);

                mplay1.setVisible(false);
                mplay2.setVisible(false);
                mplay3.setVisible(false);
                mplay4.setVisible(false);
                mplay5.setVisible(false);

            }
        });
        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mhotel1.setVisible(true);
                mhotel2.setVisible(true);
                mhotel3.setVisible(true);
                mhotel4.setVisible(true);
                mhotel5.setVisible(true);

                meat1.setVisible(false);
                meat2.setVisible(false);
                meat3.setVisible(false);
                meat4.setVisible(false);
                meat5.setVisible(false);

                mplay1.setVisible(false);
                mplay2.setVisible(false);
                mplay3.setVisible(false);
                mplay4.setVisible(false);
                mplay5.setVisible(false);


            }
        });
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mhotel1.setVisible(false);
                mhotel2.setVisible(false);
                mhotel3.setVisible(false);
                mhotel4.setVisible(false);
                mhotel5.setVisible(false);

                meat1.setVisible(false);
                meat2.setVisible(false);
                meat3.setVisible(false);
                meat4.setVisible(false);
                meat5.setVisible(false);

                mplay1.setVisible(true);
                mplay2.setVisible(true);
                mplay3.setVisible(true);
                mplay4.setVisible(true);
                mplay5.setVisible(true);


            }
        });

    }
    public void set_marker()
    {
        //String[] placeId = new String[5];
        List<String> placeId = new ArrayList<String>();
        placeId.add("ChIJEdUaERhFXTQRcLJO6yBxBH4");
        placeId.add("ChIJU4e0kBdFXTQRIEZfgRpkmfU");
        placeId.add("ChIJwQdTIxhFXTQR7U3Fl45Wkfw");
        placeId.add("ChIJKTVU8UzFDRQRe5Dk1rXutGA");
        placeId.add("ChIJwcBWZj1FXTQRU5nMO-ydvKc");
        List<Marker> markers = new ArrayList<Marker>();
        markers.add(mhotel1);
        markers.add(mhotel2);
        markers.add(mhotel3);
        markers.add(mhotel4);
        markers.add(mhotel5);


        for(int i = 0;i<5;i++)
        {
            get_rate_name(placeId.get(i),markers.get(i));
        }
    }
    public void get_rate_name(String place_ID,Marker marker)
    {

        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.RATING);
        FetchPlaceRequest request = FetchPlaceRequest.newInstance(place_ID, placeFields);
        DecimalFormat df = new DecimalFormat("######0.00");

        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            marker.setTitle(place.getName());
            marker.setSnippet("評價" + df.format(place.getRating()));
            Log.i(TAG, "Place found: " + place.getName());
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                int statusCode = apiException.getStatusCode();
                // Handle error with given status code.
                Log.e(TAG, "Place not found: " + exception.getMessage());
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();

        return false;
    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        map = googleMap;
        String placeId = "ChIJEdUaERhFXTQRcLJO6yBxBH4";
        //get_rate_name(placeId);
        mhotel1 = map.addMarker(new MarkerOptions().position(hotel1).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))).title("123"));
        mhotel2 = map.addMarker(new MarkerOptions().position(hotel2).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))).title("test"));
        mhotel3 = map.addMarker(new MarkerOptions().position(hotel3).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))));
        mhotel4 = map.addMarker(new MarkerOptions().position(hotel4).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))));
        mhotel5 = map.addMarker(new MarkerOptions().position(hotel5).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))));

        meat1 = map.addMarker(new MarkerOptions().position(eat1).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("eat",100,100))));
        meat2 = map.addMarker(new MarkerOptions().position(eat2).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("eat",100,100))));
        meat3 = map.addMarker(new MarkerOptions().position(eat3).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("eat",100,100))));
        meat4 = map.addMarker(new MarkerOptions().position(eat4).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("eat",100,100))));
        meat5 = map.addMarker(new MarkerOptions().position(eat5).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("eat",100,100))));

        mplay1 = map.addMarker(new MarkerOptions().position(play1).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("tour",100,100))));
        mplay2 = map.addMarker(new MarkerOptions().position(play2).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("tour",100,100))));
        mplay3 = map.addMarker(new MarkerOptions().position(play3).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("tour",100,100))));
        mplay4 = map.addMarker(new MarkerOptions().position(play4).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("tour",100,100))));
        mplay5 = map.addMarker(new MarkerOptions().position(play5).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("tour",100,100))));

        mhotel1.setVisible(true);
        mhotel2.setVisible(true);
        mhotel3.setVisible(true);
        mhotel4.setVisible(true);
        mhotel5.setVisible(true);

        meat1.setVisible(false);
        meat2.setVisible(false);
        meat3.setVisible(false);
        meat4.setVisible(false);
        meat5.setVisible(false);

        mplay1.setVisible(false);
        mplay2.setVisible(false);
        mplay3.setVisible(false);
        mplay4.setVisible(false);
        mplay5.setVisible(false);

        mme=map.addMarker(new MarkerOptions().position(me).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("man",125,125))));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(me,(float)16.5));
        map.setOnMarkerClickListener(this);
        set_marker();


    }
}
