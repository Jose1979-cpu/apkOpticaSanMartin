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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.adapter.ProductoAdapter;
import com.jdiaz.apkopticasanmartin.controller.ProductoDAO;
import com.jdiaz.apkopticasanmartin.databinding.FragmentProductosBinding;
import com.jdiaz.apkopticasanmartin.model.Categoria;
import com.jdiaz.apkopticasanmartin.model.Marca;
import com.jdiaz.apkopticasanmartin.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class Productos extends Fragment {
    FragmentProductosBinding binding;
    View view;
    Context context;
    NavController navController;

    FirebaseFirestore firestore;

    ProductoAdapter productoAdapter;
    List<Producto> productos;
    List<Marca> marcas;
    Categoria categoria;

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductosBinding.inflate(inflater,container,false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        firestore = FirebaseFirestore.getInstance();

        categoria = getArguments() != null ? (Categoria) getArguments().getSerializable("categoria") : null;
        binding.ivBack.setOnClickListener( v -> navController.navigateUp() );
        binding.tvCategoria.setText( categoria.getDetalle() );

        marcas =  categoria.getMarcas();
        marcas.add(0, new Marca( -1, "Todas" ) );

        binding.spnMarcas.setAdapter( new ArrayAdapter<>( context, android.R.layout.simple_spinner_dropdown_item, marcas ) );
        binding.spnMarcas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ( productos != null )
                    productoAdapter.Filtrar( marcas.get( position ).getId() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        getProductos();
    }

    private void getProductos() {
        firestore.collection("producto")
            .whereEqualTo("idCategoria", categoria.getId() )
            .orderBy("id")
            .get().addOnSuccessListener(queryDocumentSnapshots -> {

            if ( queryDocumentSnapshots.isEmpty() ) { productos = null; return; }

            productos = new ArrayList<>();
            ArrayList<DocumentSnapshot> docs = ( ArrayList<DocumentSnapshot> ) queryDocumentSnapshots.getDocuments();
            for( DocumentSnapshot doc : docs )
                productos.add( doc.toObject( Producto.class ) );

            productoAdapter = new ProductoAdapter( context, navController, productos );
            binding.rvProductos.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.VERTICAL, false ) );
            binding.rvProductos.setAdapter( productoAdapter );

        }).addOnFailureListener(e -> productos = null );
    }

}