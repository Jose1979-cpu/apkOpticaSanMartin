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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jdiaz.apkopticasanmartin.MainActivity;
import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.controller.SincronizarDAO;
import com.jdiaz.apkopticasanmartin.controller.UtilDAO;
import com.jdiaz.apkopticasanmartin.databinding.FragmentSplashBinding;
import com.jdiaz.apkopticasanmartin.db.VolleySingleton;
import com.jdiaz.apkopticasanmartin.model.Categoria;
import com.jdiaz.apkopticasanmartin.model.CategoriaMarca;
import com.jdiaz.apkopticasanmartin.model.Marca;
import com.jdiaz.apkopticasanmartin.model.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Splash extends Fragment {
    FragmentSplashBinding binding;
    View view;
    Context context;
    NavController navController;

    UtilDAO utilDAO;
    SincronizarDAO sincronizarDAO;
    FirebaseFirestore firestore;

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
        firestore = FirebaseFirestore.getInstance();
        
//        if ( !utilDAO.getCount("Producto") ) Sincronizar();
        new Handler().postDelayed( () -> Navigation.findNavController(view).navigate( R.id.navigation_novedades ), 3000 );
    }

    private void SincronizarFirebase() {
        firestore.collection("categoria").orderBy("id").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if ( !queryDocumentSnapshots.isEmpty() ) {
                ArrayList<DocumentSnapshot> docs = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot doc : docs)
                    sincronizarDAO.CategoriaInsert( doc.getLong("id"), doc.getString("detalle") );
            }
        }).addOnFailureListener(e -> { } );
    }

    private void Sincronizar() {
        String url = MainActivity.URL_API + "sincronizar";
        JsonObjectRequest request = new JsonObjectRequest( Request.Method.GET, url, null, response -> {
            try {
                if ( response.getBoolean("success") ) {
                    JSONObject jso;
                    JSONObject data = new JSONObject( response.getString("data") );

                    JSONArray categorias = new JSONArray( data.getString("categorias") );
                    for ( int i=0; i < categorias.length(); i++ ) {
                        jso = new JSONObject( categorias.get(i).toString() );
                        //sincronizarDAO.CategoriaInsert( jso.getInt("id"), jso.getString("Detalle") );

                        JSONObject jsoMarca;
                        List<Marca> marcas = new ArrayList<>();

                        try {
                            JSONArray jsoMarcas = new JSONArray( jso.getString("marcas") );
                            for ( int j=0; j < jsoMarcas.length(); j++ ) {
                                jsoMarca = new JSONObject( jsoMarcas.get(j).toString() );
                                marcas.add( new Marca( jsoMarca.getInt("id"), jsoMarca.getString("Detalle") ) );
                            }
                        } catch (JSONException e) {
                            jsoMarca = new JSONObject( jso.getString("marcas") );
                            marcas.add( new Marca( jsoMarca.getInt("id"), jsoMarca.getString("Detalle") ) );
                        }

                        firestore.collection("categoria").add( new Categoria( jso.getInt("id"), jso.getString("Detalle"), marcas ) );
                    }

                    JSONArray marcas = new JSONArray( data.getString("marcas") );
                    for ( int i=0; i < marcas.length(); i++ ) {
                        jso = new JSONObject( marcas.get(i).toString() );
                        //sincronizarDAO.MarcaInsert( jso.getInt("id"), jso.getString("Detalle") );
                        firestore.collection("marca").add( new Marca( jso.getInt("id"), jso.getString("Detalle") ) );
                    }

                    JSONArray productos = new JSONArray( data.getString("productos") );
                    for ( int i=0; i < productos.length(); i++ ) {
                        jso = new JSONObject( productos.get(i).toString() );
                        // sincronizarDAO.ProductoInsert( jso );
                        firestore.collection("producto").add( new Producto( jso ) );
                    }

                }
            } catch (JSONException e) { throw new RuntimeException(e); }

        }, error -> Log.i("Sincronizar", error.getMessage() ) );
        VolleySingleton.getInstance( context ).addToRequestQueue( request );

    }

}