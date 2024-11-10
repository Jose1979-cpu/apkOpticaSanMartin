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

import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.model.Categoria;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder>{
    Context context;
    NavController navController;
    List<Categoria> categorias;
    int[] images;

    public CategoriaAdapter(Context context, NavController navController, List<Categoria> categorias, int[] images) {
        this.context = context;
        this.navController = navController;
        this.categorias = categorias;
        this.images = images;
    }

    @NonNull
    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( context ).inflate( R.layout.item_categoria, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.ViewHolder holder, int position) {
        Categoria categoria = categorias.get( position );
        holder.ivCategoria.setImageResource( images[ position ] );
        holder.tvCategoria.setText( categoria.getDetalle() );

        holder.itemView.setOnClickListener( v -> verProductos( categoria ) );
    }

    private void verProductos(Categoria categoria) {
        Bundle bundle = new Bundle();
        bundle.putSerializable( "categoria", categoria );
        navController.navigate( R.id.navigation_productos, bundle );
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategoria;
        TextView tvCategoria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoria = itemView.findViewById( R.id.ivCategoria_item );
            tvCategoria = itemView.findViewById( R.id.tvCategoria_item );
        }
    }

}