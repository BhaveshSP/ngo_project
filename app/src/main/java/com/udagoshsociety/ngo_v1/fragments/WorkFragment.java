package com.udagoshsociety.ngo_v1.fragments;

import static androidx.activity.result.contract.ActivityResultContracts.*;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.udagoshsociety.ngo_v1.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class WorkFragment extends Fragment {
    private static final String TAG = "WorkFragment";
    private final ActivityResultLauncher<String> launcher = registerForActivityResult(new GetContent(),
            this::getPdfDetailsFromUri);
    private TextView pdfFileNameText ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ChipGroup chipGroup = view.findViewById(R.id.workSkillsChipGroup);
        Spinner availableSpinner = view.findViewById(R.id.workAvailableSpinner);
        Spinner roleSpinner = view.findViewById(R.id.workRoleSpinner);
        pdfFileNameText = view.findViewById(R.id.workResumeFileNameText);
        addChips(chipGroup);
        ArrayList<String> availableList = new ArrayList<>();
        availableList.add("3 Months");
        availableList.add("6 Months");

        ArrayList<String> roleList = new ArrayList<>();
        roleList.add("UI/UX Designer");
        roleList.add("Android Developer");
        roleList.add("React Native Developer");
        roleList.add("Web Developer");



        ArrayAdapter<String> availableAdapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_dropdown_item_1line,availableList);
        availableSpinner.setAdapter(availableAdapter);

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_dropdown_item_1line,roleList);
        roleSpinner.setAdapter(roleAdapter);

        Button uploadResumeButton = view.findViewById(R.id.workResumeUploadButton);
        uploadResumeButton.setOnClickListener(view1 -> pickResumeFromFiles());


        Button submitButton = view.findViewById(R.id.workSubmitButton);
        submitButton.setOnClickListener( view_ ->
                Toast.makeText(requireContext(), "Not Yet Implemented", Toast.LENGTH_SHORT).show()
         );
    }

    private void pickResumeFromFiles(){
        launcher.launch("application/pdf");
    }



    private void addChips(ChipGroup chipGroup){
        ArrayList<String> skillsList = new ArrayList<>();
        skillsList.add("Kotlin");
        skillsList.add("Java");
        skillsList.add("Python");
        skillsList.add("XML");
        skillsList.add("Firebase");
        skillsList.add("PHP");
        skillsList.add("CSS");
        skillsList.add("UI/UX");


        ArrayList<String> selectedSkillsList = new ArrayList<>();

        for (String skill : skillsList){
            Chip chip = new Chip(requireContext());
            chip.setText(skill);
            chip.setPadding(24,6,24,6);
            chip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.white)));
            chip.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorPrimary));
            chip.setOnClickListener(view -> {
                chipGroup.removeView(chip);
                String name = chip.getText().toString();
                if (selectedSkillsList.contains(name)){
                    selectedSkillsList.remove(name);
                    chip.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorPrimary));
                    chip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.white)));
                    chipGroup.addView(chip);
                }else{
                    selectedSkillsList.add(name);
                    chip.setTextColor(ContextCompat.getColor(requireContext(),R.color.white));
                    chip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.colorPrimary)));
                    chipGroup.addView(chip,0);
                }

            });
            chipGroup.addView(chip);
        }
    }

    @SuppressLint("Range")
    private void getPdfDetailsFromUri(Uri data){
        Cursor cursor = null;
        Log.d(TAG, "Location: "+data.toString());
        try {
            cursor = requireContext().getContentResolver().query(data,null,null,null,null);
            if (cursor!=null && cursor.moveToFirst()){
               String name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.d(TAG, "Location: "+ data);
                Log.d(TAG, "Location: Name"+name);
                pdfFileNameText.setText(name);
            }
        }catch (Exception e){
            Log.d(TAG, "Error: "+e.getLocalizedMessage());
        }finally {
            assert cursor != null;
            cursor.close();
        }

    }


}