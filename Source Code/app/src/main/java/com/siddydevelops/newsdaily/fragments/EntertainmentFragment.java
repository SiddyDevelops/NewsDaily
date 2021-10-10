package com.siddydevelops.newsdaily.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.siddydevelops.newsdaily.Adapter;
import com.siddydevelops.newsdaily.ApiUtilities;
import com.siddydevelops.newsdaily.ModelClass;
import com.siddydevelops.newsdaily.R;
import com.siddydevelops.newsdaily.mainNews;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {

    String apiKEY = "20f26aa9d4274493bbbc92962ed20579";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewEnt;
    private String category = "entertainment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entertainment_fragment,null);

        recyclerViewEnt = view.findViewById(R.id.recyclerViewENTERTAINMENT);
        modelClassArrayList = new ArrayList<>();
        recyclerViewEnt.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerViewEnt.setAdapter(adapter);

        findNews();

        return view;
    }

    private void findNews()
    {

        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,apiKEY).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });

    }
}
