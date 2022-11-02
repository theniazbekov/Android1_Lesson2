package com.example.android1_lesson2;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    ThirdFragment thirdFragment = new ThirdFragment();
    private ImageView ivFullscreen;
    private TextView tvText;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        getData();
        setUpListener();
    }

    private void initialize(View view) {
        ivFullscreen = view.findViewById(R.id.iv_fullScreen);
        tvText = view.findViewById(R.id.tv_text);
    }

    private void getData() {
        Bundle data = getArguments();
        if(data != null){
            String text = data.getString(FirstFragment.TEXT_BUNDLE_KEY);
            Uri imageForGet = Uri.parse(data.getString(FirstFragment.IMAGE_BUNDLE_KEY));
            tvText.setText(text);
            ivFullscreen.setImageURI(imageForGet);
        }
    }

    private void setUpListener() {
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, thirdFragment, "ThirdFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);

    }
}