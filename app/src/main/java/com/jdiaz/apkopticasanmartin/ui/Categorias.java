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

import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.adapter.CategoriaAdapter;
import com.jdiaz.apkopticasanmartin.adapter.ProductoAdapter;
import com.jdiaz.apkopticasanmartin.controller.CategoriaDAO;
import com.jdiaz.apkopticasanmartin.controller.ProductoDAO;
import com.jdiaz.apkopticasanmartin.databinding.FragmentCategoriasBinding;
import com.jdiaz.apkopticasanmartin.model.Categoria;
import com.jdiaz.apkopticasanmartin.model.Producto;

import java.util.List;


public class Categorias extends Fragment {
    FragmentCategoriasBinding binding;
    View view;
    Context context;
    NavController navController;

    List<Categoria> categorias;
    List<Producto> productos;

    CategoriaDAO categoriaDAO;
    ProductoDAO productoDAO;

    @Override
    public void onDestroy() {
        super.onDestroy();
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
        categoriaDAO = new CategoriaDAO( context );
        productoDAO = new ProductoDAO( context );

        categorias = categoriaDAO.getCategorias();
        int[] images = { R.drawable.ic_gafas, R.drawable.ic_monturas, R.drawable.ic_contacto, R.drawable.ic_liquidos };
        binding.rvCategorias.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false ) );
        binding.rvCategorias.setAdapter( new CategoriaAdapter( context, navController, categorias, images ) );

        productos = productoDAO.getProductos("Novedades");
        binding.rvProductos.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.VERTICAL, false ) );
        binding.rvProductos.setAdapter( new ProductoAdapter( context, navController, productos ) );
    }
}