package com.mohammed.mosa.eg.drug_info.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mohammed.mosa.eg.drug_info.adapter.DrugsAdapter;
import com.mohammed.mosa.eg.drug_info.db.DrugDBAccess;
import com.mohammed.mosa.eg.drug_info.utility.Drug;
import com.mohammed.mosa.eg.drug_info.utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import drug_info.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DrugDBAccess drugDBAccess;
    Drug selectedDrug;

    int searchType = 0;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        drugDBAccess = DrugDBAccess.getInstance(this);

        DrugsAdapter drugsAdapter = new DrugsAdapter(wayOfSearch(1, "drug"), drug -> {
            selectedDrug = drug;
            binding.tvDrugData.setVisibility(View.VISIBLE);
            binding.tvDrugData.setText(String.format("%s\n%s",
                    Utility.fit(Utility.ToTitle(drug.getFirstName().toLowerCase())),
                    Utility.ToTitle(drug.getPharmacology().toLowerCase()).trim()));
        });

        binding.tvDrugData.setOnClickListener(view -> {
            if(selectedDrug != null) {
                Intent intent = new Intent(getBaseContext(), InformationActivity.class);
                intent.putExtra(Utility.DRUG, selectedDrug);
                startActivity(intent);
            }
        });

        binding.tvAlter.setOnClickListener(K -> {
            if(selectedDrug != null ){
                drugDBAccess.open();
                ArrayList<Drug> drugs = drugDBAccess.searchAlter(selectedDrug.getPharmacology(), selectedDrug.getCompany());
                drugDBAccess.close();
                Collections.sort(drugs, new Comparator<Drug>() {
                    @Override
                    public int compare(Drug drug, Drug t1) {
                        int a = drug.getFirstName().compareTo(t1.getFirstName());
                        if(a == 0){
                            return drug.getLastName().compareTo(t1.getLastName());
                        }
                        else
                            return a;
                    }
                });
                drugsAdapter.setDrugs(drugs);
                drugsAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), String.format("Found %s ", drugs.size()), Toast.LENGTH_SHORT).show();
            }
        });

        binding.tvSimiler.setOnClickListener(K ->{
            if(selectedDrug != null ){
                drugDBAccess.open();
                ArrayList<Drug> drugs = drugDBAccess.searchSmiler(selectedDrug.getPharmacology());
                drugDBAccess.close();
                Collections.sort(drugs, new Comparator<Drug>() {
                    @Override
                    public int compare(Drug drug, Drug t1) {
                        int a = drug.getFirstName().compareTo(t1.getFirstName());
                        if(a == 0){
                            return drug.getLastName().compareTo(t1.getLastName());
                        }
                        else
                            return a;
                    }
                });
                drugsAdapter.setDrugs(drugs);

                drugsAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), String.format("Found %s ", drugs.size()), Toast.LENGTH_SHORT).show();

            }
        });

        binding.tvCostLess.setOnClickListener(K ->{
            if(selectedDrug != null ){
                drugDBAccess.open();
                ArrayList<Drug> drugs = drugDBAccess.searchCostLess(selectedDrug.getPharmacology(), selectedDrug.getPrice());
                drugDBAccess.close();
                Collections.sort(drugs);
                drugsAdapter.setDrugs(drugs);
                drugsAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), String.format("Found %s ", drugs.size()), Toast.LENGTH_SHORT).show();

            }
        });

        binding.spSearchType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchType = i;
                // changing input type
                if(i == 2)
                    binding.svSearch.setInputType(InputType.TYPE_CLASS_NUMBER);
                else
                    binding.svSearch.setInputType(InputType.TYPE_CLASS_TEXT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                drugsAdapter.setDrugs(wayOfSearch(searchType, query));
                drugsAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() >= Utility.shortestLength) {
                    drugsAdapter.setDrugs(wayOfSearch(searchType, newText));
                    drugsAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        binding.rvDrugs.setAdapter(drugsAdapter);

        binding.rvDrugs.setLayoutManager(new LinearLayoutManager(this));

        binding.rvDrugs.setHasFixedSize(true);

    }

    private ArrayList<Drug> wayOfSearch(int type, String data){
        ArrayList<Drug> drugs = new ArrayList<>();
        drugDBAccess.open();
        switch (type){
            case 0:
            case 1:
                drugs = drugDBAccess.searchByFirstName(data);
                break;
            case 2:
                if(Utility.isNumber(data))
                    drugs = drugDBAccess.searchByPrice(Float.parseFloat(data));
                else {
                    binding.svSearch.setQuery("", false);
                    Toast.makeText(getBaseContext(), "please enter a valid number: eg 18", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                drugs = drugDBAccess.searchByCompany(data);
                break;
            case 4:
                drugs = drugDBAccess.searchByEffect(data);
                break;
            case 5:
                drugs = drugDBAccess.searchByFirstNameAnyPosition(data);
                break;
            case 6:
                String[] words = data.split(" ");
                if(Utility.isNumber(words[0]))
                    drugs = drugDBAccess.searchByPriceAndName(words[0], words[1]);
                else
                    Toast.makeText(getBaseContext(), "please enter a valid number: eg 18", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(getBaseContext(), "under development", Toast.LENGTH_SHORT).show();
                break;

        }
        drugDBAccess.close();
        if(type == 2 ){
            Collections.sort(drugs);
        }
        else {
            Collections.sort(drugs, new Comparator<Drug>() {
                @Override
                public int compare(Drug drug, Drug t1) {
                    int a = drug.getFirstName().compareTo(t1.getFirstName());
                    if(a == 0){
                        return drug.getLastName().compareTo(t1.getLastName());
                    }
                    else
                        return a;
                }
            });
        }

        return drugs;
    }




    

}