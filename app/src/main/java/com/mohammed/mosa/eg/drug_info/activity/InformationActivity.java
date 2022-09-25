package com.mohammed.mosa.eg.drug_info.activity;

import static com.mohammed.mosa.eg.drug_info.utility.Utility.NA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mohammed.mosa.eg.drug_info.utility.Drug;
import com.mohammed.mosa.eg.drug_info.utility.Utility;

import drug_info.databinding.ActivityInformationBinding;

public class InformationActivity extends AppCompatActivity {
    ActivityInformationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init(getIntent());
    }

    private void init(Intent intent){
        if(intent != null) {
            Drug drug = (Drug) intent.getSerializableExtra(Utility.DRUG);
            binding.tvFirstName.setText(Utility.ToTitle(drug.getFirstName().toLowerCase() + " " + drug.getLastName().toLowerCase()));
            binding.tvCom.setText(Utility.ToTitle(drug.getCompany().toLowerCase()));
            binding.tvPharmacology.setText(drug.getPharmacology().isEmpty()
                    || drug.getPharmacology().length() <= Utility.shortestLength ? NA: Utility.ToTitle(drug.getPharmacology().toLowerCase().trim()));
            binding.tvGi.setText(drug.getGi().isEmpty()
                    || drug.getGi().length() <= Utility.shortestLength ? NA: drug.getGi().toLowerCase().trim());
            binding.tvRoute.setText(drug.getRoute().isEmpty()
                    || drug.getRoute().length() <= Utility.shortestLength ? NA: drug.getRoute().toLowerCase().trim());
            binding.tvPrice.setText(String.format("%s L.E", drug.getPrice()));
        }
    }

}