package com.udagoshsociety.ngo_v1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.udagoshsociety.ngo_v1.R;
import com.udagoshsociety.ngo_v1.databinding.FragmentHomeBinding;
import com.udagoshsociety.ngo_v1.models.DonationOption;
import com.udagoshsociety.ngo_v1.adapters.DonationOptionsAdapter;

import java.util.ArrayList;

public class DonateFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<DonationOption> list  = createDonationOptions();
        DonationOptionsAdapter adapter = new DonationOptionsAdapter(requireContext(),list);
        RecyclerView recyclerView = view.findViewById(R.id.donationOptionsRecyclerView);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<DonationOption> createDonationOptions(){
        ArrayList<DonationOption> list = new ArrayList<>();
        list.add(new DonationOption(R.drawable.money,"Money"));
        list.add(new DonationOption(R.drawable.diet,"Food"));
        list.add(new DonationOption(R.drawable.books,"Books"));
        list.add(new DonationOption(R.drawable.male_clothes,"Clothes"));
        list.add(new DonationOption(R.drawable.house,"Shelter"));
        list.add(new DonationOption(R.drawable.other,"Others"));
        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}