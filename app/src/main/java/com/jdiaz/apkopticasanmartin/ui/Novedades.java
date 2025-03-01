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
import com.google.firebase.firestore.FirebaseFirestore;
import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.adapter.CategoriaAdapter;
import com.jdiaz.apkopticasanmartin.databinding.FragmentNovedadesBinding;
import com.jdiaz.apkopticasanmartin.model.Categoria;
import com.jdiaz.apkopticasanmartin.model.Marca;

import java.util.ArrayList;
import java.util.List;

public class Novedades extends Fragment {
    FragmentNovedadesBinding binding;
    View view;
    Context context;
    NavController navController;

    FirebaseFirestore firestore;
    List<Marca> marcas;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNovedadesBinding.inflate(inflater,container,false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);

        firestore = FirebaseFirestore.getInstance();
        getMarcas();
    }

    private void getMarcas() {
        /*
        firestore.collection("categoria")
                .orderBy("id")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
                    if ( queryDocumentSnapshots.isEmpty() ) marcas = null;

                    marcas = new ArrayList<>();
                    ArrayList<DocumentSnapshot> docs = ( ArrayList<DocumentSnapshot> ) queryDocumentSnapshots.getDocuments();
                    for( DocumentSnapshot doc : docs )
                        marcas.add( doc.toObject( Marca.class ) );

                    int[] images = { R.drawable.ic_gafas, R.drawable.ic_monturas, R.drawable.ic_contacto, R.drawable.ic_liquidos };
                    binding.rvMarcas.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false ) );
                    binding.rvMarcas.setAdapter( new CategoriaAdapter( context, navController, marcas, images ) );
                }).addOnFailureListener(e -> marcas = null );

         */
    }

}