package com.example.asus.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.asus.foodorder.Util.TinyDB;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout pesanapp;
    private LinearLayout cekpesan;
    private LinearLayout about;
    private LinearLayout history;

    @BindView(R.id.imgAyamGoreng)
    ImageView imgAyamGoreng;
    @BindView(R.id.imgBurger)
    ImageView imgBurger;
    @BindView(R.id.imgKentang)
    ImageView imgKentang;
    @BindView(R.id.imgRedvelvet)
    ImageView imgRedvelvet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pesanapp = findViewById(R.id.pesanapp);
        pesanapp.setOnClickListener(this);

        cekpesan = findViewById(R.id.cekpesan);
        cekpesan.setOnClickListener(this);

        about = findViewById(R.id.about);
        about.setOnClickListener(this);

        history = findViewById(R.id.history);
        history.setOnClickListener(this);

        //set Image
        setImageHome();
    }

    private void setImageHome() {
        //ayam goreng
        Picasso.get()
                .load(R.drawable.ayamgoreng)
                .centerCrop()
                .fit()
                .into(imgAyamGoreng);

        // burger
        Picasso.get()
                .load(R.drawable.burger)
                .centerCrop()
                .fit()
                .into(imgBurger);
        // kentang
        Picasso.get()
                .load(R.drawable.kentang)
                .centerCrop()
                .fit()
                .into(imgKentang);
        //redvelvet
        Picasso.get()
                .load(R.drawable.redvelvet)
                .centerCrop()
                .fit()
                .into(imgRedvelvet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pesanapp:
                Intent moveIntent = new Intent(MainActivity.this, Pemesanan.class);
                startActivity(moveIntent);
                break;

            case R.id.cekpesan:
                Intent toPesanan = new Intent(MainActivity.this, Pesanan.class);

                TinyDB tinyDB = new TinyDB(getBaseContext());

                if (tinyDB.getBoolean("isOrdering")) {
                    startActivity(toPesanan);
                } else {
                    Toast.makeText(this, "Anda belum memesan!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.about:
                Intent moveAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(moveAbout);
                break;
            case R.id.history:
                startActivity(new Intent(MainActivity.this, History.class));
                break;
        }
    }

    private boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit) {
            Intent intent = new Intent(MainActivity.this, Splash.class);
            intent.putExtra("exit", "exit");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press Back Again to Exit", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

}
