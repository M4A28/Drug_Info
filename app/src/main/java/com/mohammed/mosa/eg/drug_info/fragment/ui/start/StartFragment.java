package com.mohammed.mosa.eg.drug_info.fragment.ui.start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import drug_info.databinding.FragmentStartBinding;
import com.mohammed.mosa.eg.drug_info.db.DrugDBAccess;
import com.mohammed.mosa.eg.drug_info.utility.Drug;

import java.util.ArrayList;

public class StartFragment extends Fragment {

    private FragmentStartBinding binding;
    ArrayList<Drug> drugs;
    DrugDBAccess drugDBAccess;
    int shortestLength = 4;
    int searchType = 0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StartViewModel startViewModel =
                new ViewModelProvider(this).get(StartViewModel.class);

        binding = FragmentStartBinding.inflate(inflater, container, false);

//        final TextView textView = binding.textHome;
//        startViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return binding.getRoot();
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        drugs = new ArrayList<>();
//        drugDBAccess = DrugDBAccess.getInstance(getContext());
//        DrugsAdapter drugsAdapter = new DrugsAdapter(drugs, getContext());
//
//        binding.spSearchType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                searchType = i;
//                // changing input type
//                if(i == 2)
//                    binding.svSearch.setInputType(InputType.TYPE_CLASS_NUMBER);
//                else
//                    binding.svSearch.setInputType(InputType.TYPE_CLASS_TEXT);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                drugDBAccess.open();
//                drugs = drugDBAccess.searchByFirstName(query);
//                drugDBAccess.close();
//                drugsAdapter.setDrugs(wayOfSearch(searchType, query));
//                drugsAdapter.notifyDataSetChanged();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if(newText.length() >= shortestLength) {
//                    drugDBAccess.open();
//                    drugs = drugDBAccess.searchByFirstName(newText);
//                    drugDBAccess.close();
//                    drugsAdapter.setDrugs(wayOfSearch(searchType, newText));
//                    drugsAdapter.notifyDataSetChanged();
//                }
//                return false;
//            }
//        });
//
//        binding.rvDrugs.setAdapter(drugsAdapter);
//        binding.rvDrugs.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.rvDrugs.setHasFixedSize(true);
//    }

    private ArrayList<Drug> wayOfSearch(int type, String data){
        ArrayList<Drug> drugs = new ArrayList<>();
        drugDBAccess.open();
        switch (type){
            case 0:
            case 1:
                drugs = drugDBAccess.searchByFirstName(data);
                break;
            case 2:
                if(isNumber(data))
                    drugs = drugDBAccess.searchByPrice(Float.parseFloat(data));
                else {
                    binding.svSearch.setQuery("", false);
                    Toast.makeText(getContext(), "please enter a valid number: eg 18", Toast.LENGTH_SHORT).show();
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
                if(isNumber(words[0]))
                    drugs = drugDBAccess.searchByPriceAndName(words[0], words[1]);
                else
                    Toast.makeText(getContext(), "please enter a valid number: eg 18", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(getContext(), "under development", Toast.LENGTH_SHORT).show();
                break;

        }
        drugDBAccess.close();
        return drugs;
    }
    private boolean isNumber(String text){
        char[] chars = text.toCharArray();
        for(char c: chars){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}