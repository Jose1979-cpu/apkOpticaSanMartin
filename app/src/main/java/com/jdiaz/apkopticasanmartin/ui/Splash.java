package com.jdiaz.apkopticasanmartin.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jdiaz.apkopticasanmartin.MainActivity;
import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.controller.SincronizarDAO;
import com.jdiaz.apkopticasanmartin.controller.UtilDAO;
import com.jdiaz.apkopticasanmartin.databinding.FragmentSplashBinding;
import com.jdiaz.apkopticasanmartin.db.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Splash extends Fragment {
    FragmentSplashBinding binding;
    View view;
    Context context;
    NavController navController;

    UtilDAO utilDAO;
    SincronizarDAO sincronizarDAO;

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        utilDAO = new UtilDAO( context );
        sincronizarDAO = new SincronizarDAO(context);
        
        if ( !utilDAO.getCount("Producto") ) Sincronizar();
        new Handler().postDelayed( () -> Navigation.findNavController(view).navigate( R.id.navigation_novedades ), 3000 );
    }

    private void Sincronizar() {
        String url = MainActivity.URL_API + "sincronizar";
        JsonObjectRequest request = new JsonObjectRequest( Request.Method.GET, url, null, response -> {
            try {
                if ( response.getBoolean("success") ) {
                    JSONObject data = new JSONObject( response.getString("data") );

                    JSONArray categorias = new JSONArray( data.getString("categorias") );
                    for ( int i=0, filas = categorias.length(); i < filas; i++ )
                        sincronizarDAO.CategoriaInsert( new JSONObject( categorias.get(i).toString() )  );

                    JSONArray marcas = new JSONArray( data.getString("marcas") );
                    for ( int i=0, filas = marcas.length(); i < filas; i++ )
                        sincronizarDAO.MarcaInsert( new JSONObject( marcas.get(i).toString() )  );

                    JSONArray categoria_marcas = new JSONArray( data.getString("categoria_marcas") );
                    for ( int i=0, filas = categoria_marcas.length(); i < filas; i++ )
                        sincronizarDAO.CategoriaMarcaInsert( new JSONObject( categoria_marcas.get(i).toString() )  );

                    JSONArray productos = new JSONArray( data.getString("productos") );
                    for ( int i=0, filas = productos.length(); i < filas; i++ )
                        sincronizarDAO.ProductoInsert( new JSONObject( productos.get(i).toString() )  );

                }
            } catch (JSONException e) { throw new RuntimeException(e); }

        }, error -> Log.i("Sincronizar", error.getMessage() ) );
        VolleySingleton.getInstance( context ).addToRequestQueue( request );

    }

}