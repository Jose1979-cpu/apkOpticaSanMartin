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

import com.jdiaz.apkopticasanmartin.R;
import com.jdiaz.apkopticasanmartin.databinding.FragmentLoginBinding;


public class Login extends Fragment {
FragmentLoginBinding binding;
View view;
Context context;
NavController navController;

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container,false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);

        binding.btnRegistrar.setOnClickListener(v -> navController.navigate( R.id.navigation_registrar ) );
    }
}