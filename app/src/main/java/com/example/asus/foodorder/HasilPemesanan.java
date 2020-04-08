package com.example.asus.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asus.foodorder.Util.RandomId;
import com.example.asus.foodorder.Util.TinyDB;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HasilPemesanan extends AppCompatActivity {

    @BindView(R.id.txtNamaPemesan)
    TextView txtNamaPemesan;
    @BindView(R.id.txtMejaPemesan)
    TextView txtMejaPemesan;
    @BindView(R.id.txtPesanan)
    TextView txtPesanan;
    @BindView(R.id.txtTotalPembayaran)
    TextView txtTotalPembayaran;
    @BindView(R.id.cb_setuju_pesan)
    CheckBox cb_setuju_pesan;
    @BindView(R.id.btn_kirimPesanan)
    Button btn_kirimPesanan;
    @BindView(R.id.txtIdPesanan)
    TextView txtIdPesanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_pemesanan);
        ButterKnife.bind(this);

        final String idPesanan = RandomId.randomAlphaNumeric(6);
        final String namaPemesan = getIntent().getStringExtra("NAMA");
        final String mejaPemesan = getIntent().getStringExtra("MEJA");
        final String pesanan = getIntent().getStringExtra("PEMESANAN");
        final String total_pembayaran = String.valueOf(getIntent().getIntExtra("TOTAL_PEMBAYARAN", 0));

        txtIdPesanan.setText("Id Pesanan : " + idPesanan);
        txtNamaPemesan.setText("Nama Pemesan : " + namaPemesan);
        txtMejaPemesan.setText("Meja Pemesan : " + mejaPemesan);
        txtPesanan.setText(pesanan);
        txtTotalPembayaran.setText("Total Pembayaran : " + total_pembayaran);

        btn_kirimPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_setuju_pesan.isChecked()) {
                    TinyDB db = new TinyDB(getBaseContext());
                    db.putString("dbIdPesanan", idPesanan);
                    db.putString("dbNama", namaPemesan);
                    db.putString("dbMeja", mejaPemesan);
                    db.putString("dbPesanan", pesanan);
                    db.putString("dbTotalPemb", total_pembayaran);
                    db.putBoolean("isOrdering", true);

                    startActivity(new Intent(HasilPemesanan.this, Pesanan.class));
                } else {
                    Toast.makeText(HasilPemesanan.this, "Mohon di Checklist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
