package com.jdiaz.apkopticasanmartin.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jdiaz.apkopticasanmartin.MainActivity;
import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.adapter.CategoriaAdapter;
import com.jdiaz.apkopticasanmartin.adapter.ProductoAdapter;
import com.jdiaz.apkopticasanmartin.controller.CategoriaDAO;
import com.jdiaz.apkopticasanmartin.controller.ProductoDAO;
import com.jdiaz.apkopticasanmartin.databinding.FragmentCategoriasBinding;
import com.jdiaz.apkopticasanmartin.model.Categoria;
import com.jdiaz.apkopticasanmartin.model.Producto;

import java.util.ArrayList;
import java.util.List;


public class Categorias extends Fragment {
    FragmentCategoriasBinding binding;
    View view;
    Context context;
    NavController navController;
    FirebaseFirestore firestore;

    List<Categoria> categorias;
    List<Producto> productos;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoriasBinding.inflate(inflater,container,false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        firestore = FirebaseFirestore.getInstance();

        getCategorias();
        getProductos();
    }

    private void getCategorias() {
        firestore.collection("categoria")
                .orderBy("id")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            if ( queryDocumentSnapshots.isEmpty() ) categorias = null;

            categorias = new ArrayList<>();
            ArrayList<DocumentSnapshot> docs = ( ArrayList<DocumentSnapshot> ) queryDocumentSnapshots.getDocuments();
            for( DocumentSnapshot doc : docs )
                categorias.add( doc.toObject( Categoria.class ) );

            int[] images = { R.drawable.ic_gafas, R.drawable.ic_monturas, R.drawable.ic_contacto, R.drawable.ic_liquidos };
            binding.rvCategorias.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false ) );
            binding.rvCategorias.setAdapter( new CategoriaAdapter( context, navController, categorias, images ) );
        }).addOnFailureListener(e -> categorias = null );
    }

    private void getProductos() {
        firestore.collection("producto")
                .whereEqualTo("estado","N")
                .orderBy("id")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            if ( queryDocumentSnapshots.isEmpty() ) productos = null;

            productos = new ArrayList<>();
            ArrayList<DocumentSnapshot> docs = ( ArrayList<DocumentSnapshot> ) queryDocumentSnapshots.getDocuments();
            for( DocumentSnapshot doc : docs )
                productos.add( doc.toObject( Producto.class ) );

            binding.rvProductos.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.VERTICAL, false ) );
            binding.rvProductos.setAdapter( new ProductoAdapter( context, navController, productos ) );
        }).addOnFailureListener(e -> productos = null );
    }

}