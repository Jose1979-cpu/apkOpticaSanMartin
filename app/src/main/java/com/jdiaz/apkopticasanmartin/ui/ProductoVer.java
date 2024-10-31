package com.jdiaz.apkopticasanmartin.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdiaz.apkopticasanmartin.MainActivity;
import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.databinding.FragmentProductoBinding;
import com.jdiaz.apkopticasanmartin.model.Categoria;
import com.jdiaz.apkopticasanmartin.model.Producto;
import com.squareup.picasso.Picasso;

public class ProductoVer extends Fragment {
    FragmentProductoBinding binding;
    View view;
    Context context;
    NavController navController;

    Producto producto;

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentProductoBinding.inflate(inflater,container,false);
       return view = binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);

        producto = getArguments() != null ? ( Producto ) getArguments().getSerializable("producto") : null;
        if ( producto == null ) return;

        Picasso.with( context )
                .load(MainActivity.URL_IMAGE + producto.getModelo() + ".jpg" )
                .fit().centerCrop()
                .placeholder( R.drawable.ic_monturas )
                .error( R.drawable.ic_monturas )
                .into( binding.ivProducto );

        binding.ivBack.setOnClickListener( v -> navController.navigateUp() );
        binding.tvModelo.setText( producto.getModelo() );
        binding.tvMarca.setText( producto.getMarca() );
        binding.tvColorMarco.setText( String.format("Color de marco : %s", producto.getColorMarco() ) );
        binding.tvColorLente.setText( String.format("Color de lente : %s", producto.getColorLente() ) );
        binding.tvFormaMarco.setText( String.format("Forma de marco : %s", producto.getFormaMarco() ) );
        binding.tvMaterialMarco.setText( String.format("Material marco : %s", producto.getMaterialMontura() ) );
        binding.tvMaterialLente.setText( String.format("Material lente : %s", producto.getMaterialLente() ) );



    }

}