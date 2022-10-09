package com.androidnative.demoimprovingperformanceandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.androidnative.demoimprovingperformanceandroid.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<OnBoardingModel> list;
    int render = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.render.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int image = -1;
                list = new ArrayList<>();
                for(int i = 0;i<10;i++) {
                    if(render%3==0) image = R.drawable.image_big_super;
                    else if(render%3==1) image = R.drawable.image_big;
                    else image = R.drawable.image_resize;
                    list.add(new OnBoardingModel(i, image));
                }
                long startTime = System.currentTimeMillis();
                setupView(list);
                render++;
                binding.tvTime.setText("Time " + image+": " + (System.currentTimeMillis() - startTime) +"ms");
            }
        });
    }

    private void setupView(List<OnBoardingModel> models) {
        binding.lyView.removeAllViews();
        for(OnBoardingModel model:models){
            AppCompatImageView view = new AppCompatImageView(this);
            LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,400);
            layoutParams.bottomMargin = 80;
            view.setLayoutParams(layoutParams);
            view.setImageResource(model.image);
            binding.lyView.addView(view);
        }
    }
}
