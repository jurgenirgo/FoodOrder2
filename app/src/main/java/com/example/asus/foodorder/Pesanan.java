package com.example.asus.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.asus.foodorder.Model.HistoryPesanan;
import com.example.asus.foodorder.Util.TinyDB;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class Pesanan extends AppCompatActivity {

    @BindView(R.id.txtInfo)
    TextView txtInfo;
    @BindView(R.id.txtNamaPemesan)
    TextView txtNamaPemesan;
    @BindView(R.id.txtMejaPemesan)
    TextView txtMejaPemesan;
    @BindView(R.id.txtPesanan)
    TextView txtPesanan;
    @BindView(R.id.txtTotalPembayaran)
    TextView txtTotalPembayaran;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtIdPesanan)
    TextView txtIdPesanan;

    String idPesanan;
    String namaPemesan;
    String mejaPemesan;
    String namaPesanan;
    String total_pembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        ButterKnife.bind(this);

        TinyDB db = new TinyDB(getBaseContext());

        idPesanan = db.getString("dbIdPesanan");
        namaPemesan = db.getString("dbNama");
        mejaPemesan = db.getString("dbMeja");
        namaPesanan = db.getString("dbPesanan");
        total_pembayaran = db.getString("dbTotalPemb");

        txtIdPesanan.setText("ID Pesanan : " + idPesanan);
        txtNamaPemesan.setText("Nama Pemesan : " + namaPemesan);
        txtMejaPemesan.setText("Meja Pemesan : " + mejaPemesan);
        txtPesanan.setText(namaPesanan);
        txtTotalPembayaran.setText("Total Pembayaran : " + total_pembayaran);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtInfo.setText("PESANAN SEDANG DI SIAPKAN");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtInfo.setText("PESANAN SEDANG DI ANTAR");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txtInfo.setText("SELAMAT MENIKMATI");
                                progressBar.setVisibility(View.GONE);

                                new TinyDB(getBaseContext()).putBoolean("isOrdering", false);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(Pesanan.this, MainActivity.class));

                                        insertHistoryPemesanan();
                                    }
                                }, 2000);
                            }
                        }, 5000);
                    }
                }, 5000);
            }
        }, 5000);
    }

    private void insertHistoryPemesanan() {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Number currentNum = realm.where(HistoryPesanan.class).max("id");
                    int nextId;
                    if (currentNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentNum.intValue() + 1;
                    }

                    HistoryPesanan pesanan = realm.createObject(HistoryPesanan.class);
                    pesanan.setId(nextId);
                    pesanan.setIdPesanan(idPesanan);
                    pesanan.setNamaPemesan(namaPemesan);
                    pesanan.setMejaPemesan(mejaPemesan);
                    pesanan.setNamaMakanan(namaPesanan);
                    pesanan.setTotalPembayaran(Integer.parseInt(total_pembayaran));
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Pesanan.this, MainActivity.class));
    }
}
