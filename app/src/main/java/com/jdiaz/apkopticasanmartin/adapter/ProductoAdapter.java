package com.jdiaz.apkopticasanmartin.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.jdiaz.apkopticasanmartin.MainActivity;
import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.model.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder>{
    Context context;
    NavController navController;
    List<Producto> productos = new ArrayList<>();
    List<Producto> productosTodos;

    public ProductoAdapter(Context context, NavController navController, List<Producto> productos) {
        this.context = context;
        this.navController = navController;
        this.productosTodos = productos;
        Filtrar( -1 );
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( context ).inflate( R.layout.item_producto, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        Producto producto = productos.get( position );
        holder.tvModelo.setText( producto.getModelo() );
        holder.tvMarca.setText( producto.getMarca() );

        Picasso.with( context )
                .load(MainActivity.URL_IMAGE + producto.getModelo() + ".jpg" )
                .fit().centerCrop()
                .placeholder( R.drawable.ic_monturas )
                .error( R.drawable.ic_monturas )
                .into( holder.ivProducto );

        holder.itemView.setOnClickListener( v -> verProducto( producto ) );
    }

    private void verProducto(Producto producto) {
        Bundle bundle = new Bundle();
        bundle.putSerializable( "producto", producto );
        navController.navigate( R.id.navigation_producto, bundle );
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }


    public void Filtrar(int idMarca ) {
        productos.clear();
        for ( Producto producto : productosTodos )
            if ( idMarca == -1 ) productos.add( producto );
            else productos = productosTodos.stream().filter(i -> i.getIdMarca() == idMarca ).collect( Collectors.toList() );

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProducto;
        TextView tvModelo, tvMarca;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProducto = itemView.findViewById( R.id.ivProducto_item );
            tvModelo = itemView.findViewById( R.id.tvModelo_item );
            tvMarca = itemView.findViewById( R.id.tvMarca_item );
        }
    }

}
