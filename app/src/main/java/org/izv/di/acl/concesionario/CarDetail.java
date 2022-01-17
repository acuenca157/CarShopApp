package org.izv.di.acl.concesionario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;


import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class CarDetail extends AppCompatActivity {

    Car car;

    SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        this.car = getIntent().getParcelableExtra("car");

        //Ponemos la navegaciond e la action bar
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle(car.title);

        String[] images = car.imagesUrls.toArray(new String[0]);

        //Hacemos el Slider
        SlideAdapter slideAdapter = new SlideAdapter(images, this);
        sliderView = findViewById(R.id.image_slider);
        sliderView.setSliderAdapter(slideAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        //Asignamos titulo, precio 7 descripción
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        tvTitle.setText(car.title);
        TextView tvPrice = findViewById(R.id.tvPriceDetail);
        tvPrice.setText(car.price);
        TextView tvDesc = findViewById(R.id.tvDescDetail);
        tvDesc.setText(car.description);


        //Creamos las Categorias y las añadimos al slider
        View linearLayout =  findViewById(R.id.tagsContainer);

        for (String tag : car.categories) {
            TextView valueTV = new TextView(this);
            valueTV.setText(tag);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            valueTV.setLayoutParams(params);


            ((LinearLayout) linearLayout).addView(valueTV);
        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.itemWeb:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.autos-dominguez.com"));
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }
}