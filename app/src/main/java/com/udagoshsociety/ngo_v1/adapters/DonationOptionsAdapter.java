package com.udagoshsociety.ngo_v1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udagoshsociety.ngo_v1.R;
import com.udagoshsociety.ngo_v1.models.DonationOption;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DonationOptionsAdapter extends RecyclerView.Adapter<DonationOptionsAdapter.ViewHolder> {
    private ArrayList<DonationOption> list ;
    private Context context;

    public DonationOptionsAdapter(Context context, ArrayList<DonationOption> list){
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()) ;
        View itemView = layoutInflater.inflate(R.layout.donation_options_itemview_layout,parent,false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonationOption option = list.get(position);
        holder.title.setText(option.getTitle());
        holder.image.setImageResource(option.getImageId());
        holder.itemView.setOnClickListener(view ->{
            Snackbar snackbar = Snackbar.make(view,context.getString(R.string.thank_you_for_donation),Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", view1 -> {
                snackbar.dismiss();
            }).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.donationOptionsTitle);
            image = itemView.findViewById(R.id.donationOptionsImage);
        }

    }
}