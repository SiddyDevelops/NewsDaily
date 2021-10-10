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
import com.siddydevelops.newsdaily.model.Article;
import com.siddydevelops.newsdaily.R;
import com.siddydevelops.newsdaily.model.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFragment extends Fragment {

    String apiKEY = "20f26aa9d4274493bbbc92962ed20579";
    ArrayList<Article> articles;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewHealth;
    private String category = "health";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_fragment,null);

        recyclerViewHealth = view.findViewById(R.id.recyclerViewHEALTH);
        articles = new ArrayList<>();
        recyclerViewHealth.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), articles);
        recyclerViewHealth.setAdapter(adapter);

        findNews();

        return view;
    }

    private void findNews()
    {

        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,apiKEY).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful())
                {
                    articles.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

    }
}
