package com.dragon.blockchain_demo;

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
    private static final LatLng px1 = new LatLng(24.951935, 121.206727);
    private static final LatLng px2 = new LatLng(24.960167, 121.222208);
    private static final LatLng carryfout1 = new LatLng(24.962665, 121.231956);
    private static final LatLng store1 = new LatLng( 24.965758, 121.190980);
    private static final LatLng store2 = new LatLng( 24.970760, 121.195787);
    private static final LatLng store3 = new LatLng( 24.970890, 121.201638);

    private Marker mPingChen1;
    private Marker mPingChen2;
    private Marker mPingChen3;
    private Marker mPingChen4;
    private Marker mPingChen5;
    private Marker mPingChen6;


    private static final LatLng me = new LatLng(24.968083, 121.192203);
    private Marker mme;
    private static final LatLng start = new LatLng(24.964852, 121.209432);


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

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        map = googleMap;
        mPingChen1 = map.addMarker(new MarkerOptions().position(px1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mPingChen2 = map.addMarker(new MarkerOptions().position(carryfout1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mPingChen3 = map.addMarker(new MarkerOptions().position(px2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mPingChen4 = map.addMarker(new MarkerOptions().position(store1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mPingChen5 = map.addMarker(new MarkerOptions().position(store2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mPingChen6 = map.addMarker(new MarkerOptions().position(store3).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mme=map.addMarker(new MarkerOptions().position(me).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(start, (float) 12.9));
        map.setOnMarkerClickListener(this);



    }
}
