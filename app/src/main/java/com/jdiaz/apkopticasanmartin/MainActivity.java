package com.jdiaz.apkopticasanmartin;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static String URL_IMAGE = "https://oaemdl.es/images/optica/";
    public static String URL_API = "https://oaemdl.es/optica_sweb_php/";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            navView.setVisibility( View.VISIBLE );
            int id = navDestination.getId();
            if ( id == R.id.navigation_splash  )
                navView.setVisibility( View.INVISIBLE );
        });
    }

}