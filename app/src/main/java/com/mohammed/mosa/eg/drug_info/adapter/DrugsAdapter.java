package com.mohammed.mosa.eg.drug_info.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import drug_info.R;
import drug_info.databinding.DrugItemBinding;
import com.mohammed.mosa.eg.drug_info.utility.Drug;
import com.mohammed.mosa.eg.drug_info.utility.Utility;

import java.util.ArrayList;

public class DrugsAdapter extends RecyclerView.Adapter<DrugsAdapter.ViewHolder> {
    private ArrayList<Drug> drugs;
    private final onDrugClickedListener listener;

    public DrugsAdapter(ArrayList<Drug> drugs, onDrugClickedListener listener) {
        this.drugs = drugs;
        this.listener = listener;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drug_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Drug drug = drugs.get(position);
        holder.bind(drug);
    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }

    public interface onDrugClickedListener{
        void drugClicked(Drug drug);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        DrugItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DrugItemBinding.bind(itemView);
        }

        public void bind(Drug drug){

            binding.tvDrugCom.setText(Utility.ToTitle(Utility.fit(drug.getCompany())));
            binding.tvDrugName.setText(Utility.ToTitle(Utility.fit(drug.getFirstName())));
            binding.tvDrugPrice.setText(String.format("%s L.E", drug.getPrice()));
            binding.lyMineLayout.setOnClickListener(K -> listener.drugClicked(drug));
        }


    }
}
