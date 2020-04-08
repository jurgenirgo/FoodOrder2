package com.example.asus.foodorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.asus.foodorder.Adapter.HistoryPesananAdapter;
import com.example.asus.foodorder.Model.HistoryPesanan;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class History extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewHistory)
    RecyclerView recyclerViewHistory;
    @BindView(R.id.txtPesananKosong)
    TextView txtPesananKosong;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        // inisialisasi back button di toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //end inisialisasi back button

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<HistoryPesanan> results = realm.where(HistoryPesanan.class).findAll();

                if (results.size() > 0) {

                    // init recyclerview
                    recyclerViewHistory.setHasFixedSize(true);
                    recyclerViewHistory.setLayoutManager(new LinearLayoutManager(History.this));
                    recyclerViewHistory.setAdapter(new HistoryPesananAdapter(results));
                } else {
                    if (txtPesananKosong.getVisibility() == View.GONE) {
                        txtPesananKosong.setVisibility(View.VISIBLE);
                    } else {
                        txtPesananKosong.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    // inisialisasi backbutton
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
