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
import com.siddydevelops.newsdaily.model.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseFragment extends Fragment {

    private final int layout;
    private final int viewId;

    private final String apiKEY = "20f26aa9d4274493bbbc92962ed20579";
    private ArrayList<Article> articles;
    private Adapter adapter;
    private final String country = "in";
    private final String category;

    protected BaseFragment(String category, int layout, int viewId) {
        this.category = category;
        this.layout = layout;
        this.viewId = viewId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layout, null);

        RecyclerView recyclerView = view.findViewById(viewId);
        articles = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), articles);
        recyclerView.setAdapter(adapter);

        if (this.category.equals("home")) {
            findNews();
        } else {
            findNewsWithCategory();
        }

        return view;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getNews(country, 100, apiKEY).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    articles.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

    private void findNewsWithCategory() {
        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, apiKEY).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
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
