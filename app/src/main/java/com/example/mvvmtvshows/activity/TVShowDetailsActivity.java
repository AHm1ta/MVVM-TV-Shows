package com.example.mvvmtvshows.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mvvmtvshows.R;
import com.example.mvvmtvshows.adapters.ImageSliderAdapter;
import com.example.mvvmtvshows.databinding.ActivityTVShowDetailsBinding;
import com.example.mvvmtvshows.viewmodels.MostPopularTVShowsViewModel;
import com.example.mvvmtvshows.viewmodels.TVShowDetailsViewModel;

public class TVShowDetailsActivity extends AppCompatActivity {
    private ActivityTVShowDetailsBinding activityTVShowDetailsBinding;
    private TVShowDetailsViewModel tvShowDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTVShowDetailsBinding= DataBindingUtil.setContentView(this, R.layout.activity_t_v_show_details);
        doInitialization();
    }

    private void doInitialization(){
        tvShowDetailsViewModel = new ViewModelProvider(this).get(TVShowDetailsViewModel.class);
        getTvShowDetails();
    }

    private void getTvShowDetails(){
        activityTVShowDetailsBinding.setIsLoading(true);
        String tvShowId= String.valueOf(getIntent().getIntExtra("id", -1));
        tvShowDetailsViewModel.getTVShowDetails(tvShowId).observe(
                this,tvShowDetailsResponse -> {
                    activityTVShowDetailsBinding.setIsLoading(false);
                    //Toast.makeText(getApplicationContext(), tvShowDetailsResponse.getTvShowDetails().getUrl() , Toast.LENGTH_SHORT).show();
                    if (tvShowDetailsResponse.getTvShowDetails() != null){
                        if (tvShowDetailsResponse.getTvShowDetails().getPictures() != null){
                            loadImageSlider(tvShowDetailsResponse.getTvShowDetails().getPictures());
                        }
                    }
                }
        );
    }

    private void loadImageSlider(String[] sliderImages){
        activityTVShowDetailsBinding.sliderViewPager.setOffscreenPageLimit(1);
        activityTVShowDetailsBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
        activityTVShowDetailsBinding.sliderViewPager.setVisibility(View.VISIBLE);
        activityTVShowDetailsBinding.viewFadingEdge.setVisibility(View.VISIBLE);
        setUpSliderIndicator(sliderImages.length);
        activityTVShowDetailsBinding.sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderIndicator(position);
            }
        });
    }

    private void setUpSliderIndicator(int count){
        ImageView[] indicators= new ImageView[count];
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i=0; i< indicators.length; i++){
            indicators[i]= new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.background_slider_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            activityTVShowDetailsBinding.layoutSliderIndicators.addView(indicators[i]);
        }
        activityTVShowDetailsBinding.layoutSliderIndicators.setVisibility(View.VISIBLE);
        setCurrentSliderIndicator(0);
    }

    private void setCurrentSliderIndicator(int position){
        int childCount= activityTVShowDetailsBinding.layoutSliderIndicators.getChildCount();
        for (int i= 0; i< childCount; i++){
            ImageView imageView= (ImageView) activityTVShowDetailsBinding.layoutSliderIndicators.getChildAt(i);
            if (i== position){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_slider_indicator_active)
                );
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_slider_indicator_inactive)
                );
            }
        }
    }
}