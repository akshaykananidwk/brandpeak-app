package com.iqueen.brandpeak.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iqueen.brandpeak.databinding.ActivityFestivalBinding;
import com.iqueen.brandpeak.Ads.BannerAdManager;
import com.iqueen.brandpeak.R;
import com.iqueen.brandpeak.adapters.FestivalAdapter;
import com.iqueen.brandpeak.items.FestivalItem;
import com.iqueen.brandpeak.listener.ClickListener;
import com.iqueen.brandpeak.ui.dialog.DialogMsg;
import com.iqueen.brandpeak.utils.Constant;
import com.iqueen.brandpeak.utils.Util;
import com.iqueen.brandpeak.viewmodel.FestivalViewModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FestivalActivity extends AppCompatActivity implements ClickListener<FestivalItem> {

    ActivityFestivalBinding binding;
    FestivalViewModel festivalViewModel;
    FestivalAdapter festivalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFestivalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Util.applyStatusBarPadding(binding.clMain);

        BannerAdManager.showBannerAds(this, binding.llAdview);
        setUpUi();
        initViewModel();
    }

    private void setUpUi() {

        binding.toolbar.toolbarIvMenu.setBackground(getResources().getDrawable(R.drawable.ic_back));
        binding.toolbar.toolName.setText(getResources().getString(R.string.latest_festivals));

        binding.toolbar.toolbarIvMenu.setOnClickListener(v -> {
            onBackPressed();
        });

        festivalAdapter = new FestivalAdapter(this, this, false);
        binding.rvFestival.setAdapter(festivalAdapter);

        binding.swipeRefresh.setOnRefreshListener(() -> {
            festivalViewModel.setFestivalOgj("Festival");
        });
    }

    private void initViewModel() {
        festivalViewModel = new ViewModelProvider(this).get(FestivalViewModel.class);

        festivalViewModel.setFestivalOgj("Festival");
        festivalViewModel.getFestivals().observe(this, result -> {

            if (result.data != null) {
                binding.swipeRefresh.setRefreshing(false);
                if (result.data.size() > 0) {
                    binding.animationView.setVisibility(View.GONE);



                    festivalAdapter.setFestData(result.data);
                } else {
                    binding.animationView.setVisibility(View.VISIBLE);
                }
            }

        });

    }

    @Override
    public void onClick(FestivalItem data) {
        if (!data.isActive) {
            DialogMsg dialogMsg = new DialogMsg(this, true);
            dialogMsg.showWarningDialog(getString(R.string.no_festival_image), getString(R.string.festival_image_create),
                    getString(R.string.ok), false);
            dialogMsg.show();
            return;
        }
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constant.INTENT_TYPE, Constant.FESTIVAL);
        intent.putExtra(Constant.INTENT_FEST_ID, data.id);
        intent.putExtra(Constant.INTENT_FEST_NAME, data.name);
        intent.putExtra(Constant.INTENT_POST_IMAGE, "");
        intent.putExtra(Constant.INTENT_VIDEO, data.video);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}