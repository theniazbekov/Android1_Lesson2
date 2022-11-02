package com.example.android1_lesson2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ThirdFragment extends Fragment {

    FirstFragment firstFragment = new FirstFragment();
    private EditText etLogin;
    private EditText etPassword;
    private Button btnInPut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etLogin = view.findViewById(R.id.et_login);
        etPassword = view.findViewById(R.id.et_password);
        btnInPut = view.findViewById(R.id.btn_input);
        setUpListener();

    }

    private void setUpListener() {
        btnInPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (login.equals("Suba") && password.equals("password")) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, firstFragment, "First Fragment")
                            .addToBackStack(null)
                            .commit();
                } else {
                    if (!login.equals("Suba")) {
                        etLogin.setError("falls");
                    }
                    if (!password.equals("password")) {
                        etPassword.setError("falls");
                    }
                    if (!login.equals("Suba") && !password.equals("password")){
                        etLogin.setError("falls");
                        etPassword.setError("falls");
                    }

                    }
                }
            });
    }
}