package com.example.asus.foodorder;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;


public class AboutActivity extends AppCompatActivity {

    private SliderLayout sliderLayout;
    private TextView txtNamaRestaurant, txtDescRestaurant, txtDescDev;
    private FloatingActionButton fabCoorRestaurant;

    String coordinate_restaurant = "-6.352999, 106.842078";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        sliderLayout = findViewById(R.id.imageSlider);
        txtNamaRestaurant = findViewById(R.id.txtNamaRestaurant);
        txtDescRestaurant = findViewById(R.id.txtDescRestaurant);
        txtDescDev = findViewById(R.id.txtDescDev);
        fabCoorRestaurant = findViewById(R.id.fabCoorRestaurant);

        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(3);

        setSliderViews();

        txtNamaRestaurant.setText("Aplikasi Pemesanan Makanan Restoran");
        txtDescRestaurant.setText("Aplikasi ini dibuat untuk penelitian ilmiah sebagai syarat dalam mencapai gelar setara sarjana muda");
        txtDescDev.setText("Pembuat : Muhammad Jurgen Irgo Fransar \nGoogle Developers 2019 \nhttps://github.com/jurgenirgo");

        fabCoorRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/" + coordinate_restaurant)));
            }
        });
    }


    private void setSliderViews() {
        for (int i = 0; i <= 2; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.img1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.img2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.img3);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}
