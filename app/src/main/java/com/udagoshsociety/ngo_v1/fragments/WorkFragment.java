package com.udagoshsociety.ngo_v1.fragments;

import static androidx.activity.result.contract.ActivityResultContracts.*;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.AdapterView;
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
    private Button educationButton;

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
        educationButton = view.findViewById(R.id.workEducationButton);
        educationButton.setOnClickListener(view12 -> {
            showEducationDialog(view);
        });
        ArrayList<String> availableList = new ArrayList<>();
        availableList.add("3 Months");
        availableList.add("6 Months");

        ArrayList<String> roleList = new ArrayList<>();
        roleList.add(0,"Choose");
        roleList.add("Business Developer(Sales)");
        roleList.add("Graphic Designing");
        roleList.add("Social Media Marketing");
        roleList.add("Web Development");
        roleList.add("Marketing");
        roleList.add("Mobile App Development");
        roleList.add("Operations");
        roleList.add("Law Legal");
        roleList.add("Human Resources");
        roleList.add("Digital Marketing");
        roleList.add("Content Writing");
        TextView skillsTextView = view.findViewById(R.id.workSkillsText);


        ArrayAdapter<String> availableAdapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_dropdown_item_1line,availableList);
        availableSpinner.setAdapter(availableAdapter);

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_dropdown_item_1line,roleList);
        roleSpinner.setAdapter(roleAdapter);
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>0){
                    skillsTextView.setVisibility(View.VISIBLE);
                }
                String  selectedRole = roleSpinner.getSelectedItem().toString();
                addChips(chipGroup,selectedRole);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        Button uploadResumeButton = view.findViewById(R.id.workResumeUploadButton);
        uploadResumeButton.setOnClickListener(view1 -> pickResumeFromFiles());


        Button submitButton = view.findViewById(R.id.workSubmitButton);
        submitButton.setOnClickListener( view_ ->
                Toast.makeText(requireContext(), "Not Yet Implemented", Toast.LENGTH_SHORT).show()
         );
    }

    private void pickResumeFromFiles(){
        launcher.launch("application/zip");
    }


    private void showEducationDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.education_details_dialog_layout,viewGroup,false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();
        Spinner qualificationSpinner = dialogView.findViewById(R.id.educationHighestQualificationSpinner);
        ArrayList<String> qualifications = new ArrayList<>();
        qualifications.add("BE");
        qualifications.add("BTECH");
        qualifications.add("MBA");
        qualifications.add("HSC");
        qualifications.add("BCOMP");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line,qualifications);
        qualificationSpinner.setAdapter(adapter);
        Button doneButton = dialogView.findViewById(R.id.workDoneButton);
        doneButton.setOnClickListener(view1 -> {
            educationButton.setText("Edit");
            alertDialog.dismiss();
        });
    }


    private void addChips(ChipGroup chipGroup,String selectedRole){
        ArrayList<String> skillsList = new ArrayList<>();
        if(selectedRole.equals("Business Developer(Sales)"))
        {
            skillsList.add("English Proficiency (Spoken)");
            skillsList.add("English Proficiency (Written)");
            skillsList.add("MS Excel");
            skillsList.add("Digital Marketing");
            skillsList.add("Email Marketing");
            skillsList.add("Social Media Marketing");
        }
        else if(selectedRole.equals("Graphic Designing"))
        {
            skillsList.add("Adobe Photoshop");
            skillsList.add("Adobe Illustrator");
            skillsList.add("CorelDRAW");
            skillsList.add("Adobe after effects");
            skillsList.add("Adobe premium pro");
            skillsList.add("Adobe creative studio");
            skillsList.add("Video editing");
            skillsList.add("Adobe photoshop lightroom");
            skillsList.add("Ui ux design");

        }
        else if(selectedRole.equals("Social Media Marketing"))
        {
            skillsList.add("Social Media Marketing");
            skillsList.add("Digital Marketing");
            skillsList.add("English Proficiency (Writing)");
            skillsList.add("Search Engine Optimization");
            skillsList.add("Facebook Marketing");
            skillsList.add("Instagram Marketing");
            skillsList.add("Creative Marketing");
            skillsList.add("English Proficiency (Spoken)");
            skillsList.add("Email marketing");
            skillsList.add("Search engine marketing (SEM)");
        }
        else if(selectedRole.equals("Web Development"))
        {
            skillsList.add("HTML");
            skillsList.add("CSS");
            skillsList.add("JavaScript");
            skillsList.add("PHP");
            skillsList.add("React.js");
            skillsList.add("Wordpress");
            skillsList.add("Creative Marketing");
            skillsList.add("Node.js");
            skillsList.add("Bootstrap");
            skillsList.add("MySQL");
            skillsList.add("jQuery");
        }
        else if(selectedRole.equals("Marketing"))
        {
            skillsList.add("English Proficiency(Written)");
            skillsList.add("English Proficiency(Spoken)");
            skillsList.add("Digital Marketing");
            skillsList.add("MS Excel");
            skillsList.add("MS Office");
            skillsList.add("Social Media Marketing");
            skillsList.add("Creative Marketing");
            skillsList.add("Email Marketing");
            skillsList.add("Hindi Proficiency (Spoken)");
        }
        else if(selectedRole.equals("Mobile App Development"))
        {
            skillsList.add("Android Studio");
            skillsList.add("Flutter");
            skillsList.add("Java");
            skillsList.add("React Native");
            skillsList.add("iOS");
            skillsList.add("Firebase");
            skillsList.add("REST API");
            skillsList.add("Kotlin");
            skillsList.add("Node.js");
            skillsList.add("React.js");
        }

        else if(selectedRole.equals("Operations"))
        {
            skillsList.add("MS Excel");
            skillsList.add("English Proficiency(Written)");
            skillsList.add("English Proficiency(Spoken)");
            skillsList.add("MS Office");
            skillsList.add("MS Word");
            skillsList.add("MS Power Point");
        }

        else if(selectedRole.equals("Law Legal"))
        {
            skillsList.add("English Proficiency(Written)");
            skillsList.add("English Proficiency(Spoken)");
            skillsList.add("MS Office");
            skillsList.add("MS Word");
        }

        else if(selectedRole.equals("Human Rescouces"))
        {
            skillsList.add("MS Excel");
            skillsList.add("English Proficiency(Written)");
            skillsList.add("English Proficiency(Spoken)");
            skillsList.add("MS Office");
            skillsList.add("MS Word");
        }

        else if(selectedRole.equals("Digital Marketing"))
        {
            skillsList.add("Social Media Marketing");
            skillsList.add("Digital Marketing");
            skillsList.add("English Proficiency (Writing)");
            skillsList.add("English Proficiency (Spoken)");
            skillsList.add("Search Engine Optimization (SEO)");
            skillsList.add("Facebook Marketing");
            skillsList.add("Instagram Marketing");
            skillsList.add("Creative Marketing");
            skillsList.add("Email marketing");
            skillsList.add("Search engine marketing (SEM)");
        }

        else if(selectedRole.equals("Content Writing")){
            skillsList.add("Social Media Marketing");
            skillsList.add("Creative Writing");
            skillsList.add("English Proficiency (Writing)");
            skillsList.add("English Proficiency (Spoken)");
            skillsList.add("Blogging");
            skillsList.add("Digital Marketing");
            skillsList.add("Search Engine Optimization (SEO)");
        }

        ArrayList<String> selectedSkillsList = new ArrayList<>();
        chipGroup.removeAllViews();

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