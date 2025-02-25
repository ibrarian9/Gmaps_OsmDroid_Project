package com.tif.empatbelas;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button osmBtn = findViewById(R.id.btnMaps);
        osmBtn.setOnClickListener( v -> {
            startActivity(new Intent(this, OsmActivity.class));
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0.533505, 101.447403);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Pekanbaru"));

        TileProvider wmsTileProvider = TileProviderFactory.getWmsTileProvider();
        googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(wmsTileProvider));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 4));
    }
}
