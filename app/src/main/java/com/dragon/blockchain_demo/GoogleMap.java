package com.dragon.blockchain_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMap extends FragmentActivity
        implements OnMapReadyCallback , com.google.android.gms.maps.GoogleMap.OnMarkerClickListener {
    public com.google.android.gms.maps.GoogleMap map;
    private static final LatLng hotel1= new LatLng(25.109010, 121.845632);
    private static final LatLng hotel2= new LatLng(25.110707, 121.844478);
    private static final LatLng hotel3= new LatLng(25.109801, 121.845568);
    private static final LatLng hotel4= new LatLng(25.109767, 121.844182);
    private static final LatLng hotel5= new LatLng(25.110613, 121.845316);

    private static final LatLng eat1 = new LatLng(24.960167, 121.222208);
    private static final LatLng eat2 = new LatLng(24.962665, 121.231956);
    private static final LatLng eat3 = new LatLng( 24.965758, 121.190980);
    private static final LatLng eat4 = new LatLng( 24.970760, 121.195787);
    private static final LatLng eat5 = new LatLng( 24.970890, 121.201638);

    private static final LatLng play1 = new LatLng(24.960167, 121.222208);
    private static final LatLng play2 = new LatLng(24.962665, 121.231956);
    private static final LatLng play3 = new LatLng( 24.965758, 121.190980);
    private static final LatLng play4 = new LatLng( 24.970760, 121.195787);
    private static final LatLng play5 = new LatLng( 24.970890, 121.201638);

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



    private static final LatLng me = new LatLng(25.109561, 121.844743);
    private Marker mme;
    //private static final LatLng start = new LatLng(24.964852, 121.209432);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
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
        mhotel1 = map.addMarker(new MarkerOptions().position(hotel1).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))));
        mhotel2 = map.addMarker(new MarkerOptions().position(hotel2).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))));
        mhotel3 = map.addMarker(new MarkerOptions().position(hotel3).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))));
        mhotel4 = map.addMarker(new MarkerOptions().position(hotel4).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))));
        mhotel5 = map.addMarker(new MarkerOptions().position(hotel5).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bed",100,100))));

        mme=map.addMarker(new MarkerOptions().position(me).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("man",125,125))));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(me,17));
        map.setOnMarkerClickListener(this);



    }
}
