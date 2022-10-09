package com.androidnative.demoimprovingperformanceandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import com.androidnative.demoimprovingperformanceandroid.databinding.ActivityMultipleThreadBinding;

public class MultipleThread extends AppCompatActivity {
    private ActivityMultipleThreadBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMultipleThreadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.toggle.isChecked()){
                    runWithBackGroundThread();
                } else runWithMainThread();
            }
        });

        binding.btnInteractApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"INTERACTTTTTTT",Toast.LENGTH_SHORT).show();
            }
        });
    }


    void runWithMainThread(){
        binding.mainText.setText("Calling Api...");
        SystemClock.sleep(3000);
        binding.mainText.setText("Done Api...");
    }

    void runWithBackGroundThread(){
        binding.mainText.setText("Calling Api...");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        binding.mainText.setText("Done Api...");
                    }
                });
            }
        });
        thread.start();
    }
}