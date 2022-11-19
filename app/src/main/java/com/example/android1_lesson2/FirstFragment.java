package com.example.android1_lesson2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class FirstFragment extends Fragment {

    public static final String TEXT_BUNDLE_KEY = "text";
    public static final String IMAGE_BUNDLE_KEY = "image";
    private ImageView ivImage;
    private EditText etInput;
    private MaterialButton btnSend;
    private Uri imageForSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivImage = view.findViewById(R.id.iv_image);
        etInput = view.findViewById(R.id.et_input);
        btnSend = view.findViewById(R.id.btn_send);

        setUpListener();
    }

    private void setUpListener() {
        ivImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intent);
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etInput.getText().toString().trim();

                if (text.isEmpty() || imageForSend == null){
                    if (text.isEmpty()){
                        Toast.makeText(requireContext(), "Выберите text ", Toast.LENGTH_SHORT).show();}
                    if (imageForSend == null){
                        Toast.makeText(requireContext(), "Выберите image", Toast.LENGTH_SHORT).show();}
                }
                else {
                        Bundle bundle = new Bundle();
                        bundle.putString(TEXT_BUNDLE_KEY, text);
                        bundle.putString(IMAGE_BUNDLE_KEY, imageForSend.toString());
                        getParentFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_right_to_left,R.anim.enter_left_to_right)
                                .add(R.id.fragment_container, SecondFragment.class, bundle)
                            .addToBackStack("FirstFragment")
                            .commit();
                    }
                }
            });
    }

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            @Nullable Intent data = result.getData();
            if (data != null) {
                @Nullable Uri imageFromGallery = data.getData();
                if (imageFromGallery != null) {
                    ivImage.setImageURI(imageFromGallery);
                    imageForSend = imageFromGallery;
                }
            }
        }
    });
}