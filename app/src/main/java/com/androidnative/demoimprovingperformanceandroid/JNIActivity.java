package com.androidnative.demoimprovingperformanceandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.androidnative.demoimprovingperformanceandroid.databinding.ActivityJniactivityBinding;

public class JNIActivity extends AppCompatActivity {
    private ActivityJniactivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJniactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        System.loadLibrary("native-lib");
        binding.checkPerformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JNILib.numLoop = 1000;
                JNILib.checkPerformance(JNILib.TYPE.AREA_CIRCLE);
            }
        });
    }



}
