package com.androidnative.demoimprovingperformanceandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.androidnative.demoimprovingperformanceandroid.databinding.ActivityAlgorithmBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AlgorithmActivity extends AppCompatActivity {
    private ActivityAlgorithmBinding binding;
    private ArrayList<OnBoardingModel> list;
    private OnBoardingAdapter onBoardingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlgorithmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        for(int i = 0;i<5000;i++) {
            list.add(new OnBoardingModel(new Random().nextInt(10000), R.drawable.image_resize));
        }
        onBoardingAdapter = new OnBoardingAdapter(this,list);
        binding.rcView.setAdapter(onBoardingAdapter);
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long startTime = System.currentTimeMillis();
                ArrayList<OnBoardingModel> tempList = new ArrayList<>(list);
                bubbleSortArrayList(tempList);
                Log.d("DEBUG", "Time bubble sort "+": " + (System.currentTimeMillis() - startTime) +"ms");
                new Handler(Looper.getMainLooper()).postDelayed(() -> onBoardingAdapter.setNewList(list), 5000);
            }
        });

        binding.binarySearch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                long startTime = System.currentTimeMillis();
                ArrayList<OnBoardingModel> tempList = new ArrayList<>(list);
                sort(tempList);
                onBoardingAdapter.setNewList(tempList);
                Log.d("DEBUG", "Time quick sort "+": " + (System.currentTimeMillis() - startTime) +"ms");
                new Handler(Looper.getMainLooper()).postDelayed(() -> onBoardingAdapter.setNewList(list), 5000);
            }
        });
    }

    public void bubbleSortArrayList(ArrayList<OnBoardingModel> list) {
        OnBoardingModel temp;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
        onBoardingAdapter.setNewList(list);
    }

    void sort(List<OnBoardingModel> list) {
        sort(list, 0, list.size() - 1);
    }

     void sort(List<OnBoardingModel> list, int from, int to) {
        if (from < to) {
            int pivot = from;
            int left = from + 1;
            int right = to;
            OnBoardingModel pivotValue = list.get(pivot);
            while (left <= right) {
                // left <= to -> limit protection
                while (left <= to && pivotValue.getText() >= list.get(left).getText()) {
                    left++;
                }
                // right > from -> limit protection
                while (right > from && pivotValue.getText() < list.get(right).getText()) {
                    right--;
                }
                if (left < right) {
                    Collections.swap(list, left, right);
                }
            }
            Collections.swap(list, pivot, left - 1);
            sort(list, from, right - 1); // <-- pivot was wrong!
            sort(list, right + 1, to);   // <-- pivot was wrong!
        }
    }
}