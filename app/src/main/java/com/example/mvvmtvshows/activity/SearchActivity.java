package com.example.mvvmtvshows.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SearchView;

import com.example.mvvmtvshows.R;
import com.example.mvvmtvshows.adapters.TVShowsAdapter;
import com.example.mvvmtvshows.databinding.ActivitySearchBinding;
import com.example.mvvmtvshows.listener.TVShowsListener;
import com.example.mvvmtvshows.model.TVShow;
import com.example.mvvmtvshows.viewmodels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends AppCompatActivity implements TVShowsListener {
    private ActivitySearchBinding activitySearchBinding;
    private SearchViewModel viewModel;
    private List<TVShow> tvShows= new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage= 1;
    private int totalAvailablePage = 1;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding= DataBindingUtil.setContentView(this,R.layout.activity_search);
        doInitialization();
    }
    private void doInitialization(){
        activitySearchBinding.imageBack.setOnClickListener(v -> onBackPressed());
        activitySearchBinding.searchRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        tvShowsAdapter= new TVShowsAdapter(tvShows,this);
        activitySearchBinding.searchRecyclerView.setAdapter(tvShowsAdapter);
        activitySearchBinding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null){
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()){
                    timer= new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    currentPage= 1;
                                    totalAvailablePage= 1;
                                    searchTVShow(s.toString());
                                }
                            });
                        }
                    },800);
                }else {
                    tvShows.clear();
                    tvShowsAdapter.notifyDataSetChanged();
                }
            }
        });

        activitySearchBinding.searchRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activitySearchBinding.searchRecyclerView.canScrollVertically(1)){
                    if (!activitySearchBinding.inputSearch.getText().toString().isEmpty()){
                        if (currentPage < totalAvailablePage){
                            currentPage +=1;
                            searchTVShow(activitySearchBinding.inputSearch.getText().toString());
                        }
                    }
                }
            }
        });
        activitySearchBinding.inputSearch.requestFocus();
    }
    private void searchTVShow(String query){
        toggleLoading();
        viewModel.searchTVShow(query, currentPage).observe(this,tvShowResponse -> {
            toggleLoading();
            if (tvShowResponse != null){
                totalAvailablePage= tvShowResponse.getTotalPages();
                if (tvShowResponse.getTvShows()!= null){
                    int oldCount = tvShows.size();
                    tvShows.addAll(tvShowResponse.getTvShows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }
        });
    }

    private void toggleLoading(){
        if (currentPage==1){
            if (activitySearchBinding.getIsLoading() !=null && activitySearchBinding.getIsLoading()){
                activitySearchBinding.setIsLoading(false);
            }else{
                activitySearchBinding.setIsLoading(true);
            }
        }else {
            if (activitySearchBinding.getIsLoadingMore() !=null && activitySearchBinding.getIsLoadingMore()){
                activitySearchBinding.setIsLoadingMore(false);
            }else{
                activitySearchBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
//        intent.putExtra("id", tvShow.getId());
//        intent.putExtra("name", tvShow.getName());
//        intent.putExtra("startDate", tvShow.getStartDate());
//        intent.putExtra("country", tvShow.getCountry());
//        intent.putExtra("network", tvShow.getNetwork());
//        intent.putExtra("status", tvShow.getStatus());
        startActivity(intent);
    }
}