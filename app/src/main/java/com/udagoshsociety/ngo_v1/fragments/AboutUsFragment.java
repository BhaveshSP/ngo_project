package com.udagoshsociety.ngo_v1.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udagoshsociety.ngo_v1.R;

public class AboutUsFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView activitiesText = view.findViewById(R.id.aboutUsActivitiesText);
        activitiesText.setText(R.string.activities_text);
        TextView summaryText = view.findViewById(R.id.aboutUsSummaryText);
        summaryText.setText(R.string.summary_text);
        TextView needText = view.findViewById(R.id.aboutUsNeedText);
        needText.setText(R.string.need_text);
        TextView longTermImpactText = view.findViewById(R.id.aboutUsLongTermImpactText);
        longTermImpactText.setText(R.string.long_term_impact_text);


    }
}