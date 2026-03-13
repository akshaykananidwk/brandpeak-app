package com.iqueen.brandpeak.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.iqueen.brandpeak.databinding.ActivityAboutUsBinding;
import com.iqueen.brandpeak.Ads.BannerAdManager;
import com.iqueen.brandpeak.R;
import com.iqueen.brandpeak.utils.Util;
import com.iqueen.brandpeak.viewmodel.UserViewModel;

public class AboutUsActivity extends AppCompatActivity {

    ActivityAboutUsBinding binding;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Util.applyStatusBarPadding(binding.clMain);

        BannerAdManager.showBannerAds(this, binding.llAdview);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getDBAppInfo().observe(this, results -> {
            if(results != null){
                binding.setAboutdatabase(results);
                binding.tvDescription.setText(Html.fromHtml(results.description));
            }
        });

        binding.toolbar.toolName.setText(getResources().getString(R.string.menu_about_us));
        binding.toolbar.toolbarIvMenu.setBackground(getResources().getDrawable(R.drawable.ic_back));
        binding.toolbar.toolbarIvMenu.setOnClickListener(v -> {
            onBackPressed();
        });

    }
}