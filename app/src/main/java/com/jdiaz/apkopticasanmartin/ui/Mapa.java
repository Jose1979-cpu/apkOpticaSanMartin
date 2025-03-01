package com.jdiaz.apkopticasanmartin.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.databinding.FragmentMapaBinding;

import java.util.Objects;

public class Mapa extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {
    private static final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1;

    FragmentMapaBinding binding;
    View view;
    Context context;
    NavController navController;
    GoogleMap googleMap;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMapaBinding.inflate(inflater,container,false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);

        SupportMapFragment supportMapFragment = ( SupportMapFragment ) getChildFragmentManager().findFragmentById( R.id.mapViewMapa );
        if ( supportMapFragment != null ) supportMapFragment.getMapAsync(this);

        binding.tvTitulo.setOnClickListener( v -> binding.cvDetalle.setVisibility( View.VISIBLE ) );
        binding.tvDetalle.setOnClickListener( v -> binding.cvDetalle.setVisibility( View.INVISIBLE ) );

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if ( ActivityCompat.checkSelfPermission( context, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_DENIED )
            if ( !ActivityCompat.shouldShowRequestPermissionRationale( requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION ) )
                ActivityCompat.requestPermissions( requireActivity(), new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION  }, REQUEST_PERMISSION_ACCESS_FINE_LOCATION );

        this.googleMap=googleMap;
        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setOnMapClickListener(this);
        this.googleMap.setMyLocationEnabled(true);
        this.googleMap.setOnMyLocationClickListener(this);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
        verUbicacion();
    }

    public void verUbicacion() {
        googleMap.clear();
        LatLng gps = new LatLng( -12.0649366, -77.0389829 );
        googleMap.addMarker( new MarkerOptions().position( gps ).title( "CFP Luis Cáceres Graziani" ) );
        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom( gps, 15) );
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }
}