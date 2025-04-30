package com.example.fiestas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ahmed.easyslider.EasySlider;
import ahmed.easyslider.SliderItem;

public class agosto extends AppCompatActivity {
    EasySlider easySlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agosto);

        easySlider = findViewById(R.id.sliderId);

        List<SliderItem> easySliders=new ArrayList<>();
        easySliders.add(new SliderItem("Pagos a la Pachamama", R.drawable.ofrendapachamama1));
        easySliders.add(new SliderItem("Pagos a la Pachamama", R.drawable.ofrendapachamama2));
        easySliders.add(new SliderItem("Pagos a la Pachamama", R.drawable.ofrendapachamama3));
        easySliders.add(new SliderItem("Pagos a la Pachamama", R.drawable.ofrendapachamama4));
        easySlider.setPages(easySliders);
    }
}
