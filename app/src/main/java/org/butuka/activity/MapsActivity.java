package org.butuka.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.butuka.R;
import org.butuka.constant.Constants;
import org.butuka.util.Utils;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int MY_LOCATION_REQUEST_CODE = 100;
    private GoogleMap mMap;
    private String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION
            , Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initMyLocation();

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // Cria bundle e seta os dados a serem retornados via intent.
                Bundle bundle = new Bundle();
                bundle.putDouble(Constants.KEYS.LATITUDE, latLng.latitude);
                bundle.putDouble(Constants.KEYS.LONGITUDE, latLng.latitude);

                Intent intent = new Intent();
                intent.putExtras(bundle);

                // Define o resultado como ok, e passa a intent com o bundle.
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Utils.showMessage(MapsActivity.this, "Porfavor ative a permissão para utilizar localização.");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Requisita permissão de localização ao usuário.
                requestPermissions(permissions, MY_LOCATION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PERMISSION_GRANTED && grantResults[1] == PERMISSION_GRANTED) {
                Utils.showMessage(MapsActivity.this, "Permissões concedidas com sucesso!");
            }
        }
    }
}
