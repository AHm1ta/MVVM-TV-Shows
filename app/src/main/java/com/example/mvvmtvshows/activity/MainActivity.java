package com.example.mvvmtvshows.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmtvshows.R;
import com.example.mvvmtvshows.adapters.TVShowsAdapter;
import com.example.mvvmtvshows.databinding.ActivityMainBinding;
import com.example.mvvmtvshows.listener.TVShowsListener;
import com.example.mvvmtvshows.model.TVShow;
import com.example.mvvmtvshows.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {
    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows= new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage=1;
    private int totalPages=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        doInitialization();
    }
    private void doInitialization(){
        activityMainBinding.tvShowRV.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter= new TVShowsAdapter(tvShows,this);
        activityMainBinding.tvShowRV.setAdapter(tvShowsAdapter);
        activityMainBinding.tvShowRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (activityMainBinding.tvShowRV.canScrollVertically(1)){
                    if (currentPage <= totalPages){
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        activityMainBinding.imageWatchList.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),WatchlistActivity.class)));
        activityMainBinding.imageSearch.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowResponse ->{
            toggleLoading();
            if (mostPopularTVShowResponse!= null){
                totalPages= mostPopularTVShowResponse.getTotalPages();
                if (mostPopularTVShowResponse.getTvShows() !=null){
                    int oldCount= tvShows.size();
                    tvShows.addAll(mostPopularTVShowResponse.getTvShows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }
        });
    }

    private void toggleLoading(){
        if (currentPage==1){
            if (activityMainBinding.getIsLoading() !=null && activityMainBinding.getIsLoading()){
                activityMainBinding.setIsLoading(false);
            }else{
                activityMainBinding.setIsLoading(true);
            }
        }else {
            if (activityMainBinding.getIsLoadingMore() !=null && activityMainBinding.getIsLoadingMore()){
                activityMainBinding.setIsLoadingMore(false);
            }else{
                activityMainBinding.setIsLoadingMore(true);
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