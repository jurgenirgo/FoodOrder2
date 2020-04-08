package com.example.asus.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.asus.foodorder.Adapter.ListMakananAdapter;
import com.example.asus.foodorder.Model.FoodDataModel;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Pemesanan extends AppCompatActivity implements ListMakananAdapter.GetDataCallBack {

    @BindView(R.id.et_nama_pemesan)
    EditText etNamaPemesan;
    @BindView(R.id.et_meja_pemesan)
    EditText etMejaPemesan;
    @BindView(R.id.btn_selesaiPesan)
    Button btnSelesaiPesan;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tilNamaPesanan)
    TextInputLayout tilNamaPesanan;
    @BindView(R.id.tilMejaPemesanan)
    TextInputLayout tilMejaPemesanan;

    ArrayList<FoodDataModel> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new ListMakananAdapter(this, getDataMakanan(), this));

        btnSelesaiPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaPemesan = etNamaPemesan.getText().toString();
                String mejaPemesan = etMejaPemesan.getText().toString();

                // container error
                String error_ = "";

                if (namaPemesan.equals("")) {
                    tilNamaPesanan.setError("Nama Pemesan Kosong!");
                    error_ += "error nama pemesan";
                } else {
                    tilNamaPesanan.setError(null);
                    error_ = "";
                }

                if (mejaPemesan.equals("")) {
                    tilMejaPemesanan.setError("Meja Pemesan Kosong!");
                    error_ += "error meja pemesan";
                } else {
                    tilMejaPemesanan.setError(null);
                    error_ = "";
                }

                if (error_.equals("")) {
                    String pemesanan = "";
                    int total_pembayaran = 0;

                    ArrayList<FoodDataModel> model = models;

                    for (FoodDataModel data : model) {
                        if (data.getTotalItem() > 0) {
                            pemesanan += data.getNamaMakanan() + " x " + data.getTotalItem() + " = " + data.getTotalHarga() + "\n";
                            total_pembayaran = total_pembayaran + data.getTotalHarga();
                        }
                    }

                    Intent i = new Intent(Pemesanan.this, HasilPemesanan.class);
                    i.putExtra("NAMA", namaPemesan);
                    i.putExtra("MEJA", mejaPemesan);
                    i.putExtra("PEMESANAN", pemesanan);
                    i.putExtra("TOTAL_PEMBAYARAN", total_pembayaran);

                    startActivity(i);

                    //Log.d("OUTPUT", "Nama : " + namaPemesan + ", Meja : " + mejaPemesan + "\nPemesanan : " + pemesanan + "\nTotal Pembayaran : " + total_pembayaran);
                }
            }
        });
    }

    public ArrayList<FoodDataModel> getDataMakanan() {
        ArrayList<FoodDataModel> data = new ArrayList<>();

        data.add(new FoodDataModel(
                "Ayam Goreng Original",
                "https://i.imgur.com/C2fMFEe.jpg",
                0,
                14000,
                0
        ));

        data.add(new FoodDataModel(
                "Chicken Wings",
                "https://i.imgur.com/BTWBmZ4.jpg",
                0,
                13000,
                0
        ));

        data.add(new FoodDataModel(
                "Burger",
                "https://i.imgur.com/w2ciU1A.jpg",
                0,
                15000,
                0
        ));

        data.add(new FoodDataModel(
                "French Fries",
                "https://i.imgur.com/panPraQ.jpg",
                0,
                9000,
                0
        ));

        data.add(new FoodDataModel(
                "Roti Bakar",
                "https://i.imgur.com/14r2x45.jpg",
                0,
                9000,
                0
        ));

        data.add(new FoodDataModel(
                "Sphageti Bolognaise",
                "https://i.imgur.com/hhdksEq.jpg",
                0,
                15000,
                0
        ));

        data.add(new FoodDataModel(
                "Nasi",
                "https://i.imgur.com/50zvpFG.jpg",
                0,
                5000,
                0
        ));

        data.add(new FoodDataModel(
                "Pizza",
                "https://i.imgur.com/u43i9zX.jpg",
                0,
                30000,
                0
        ));

        data.add(new FoodDataModel(
                "Red Velvet",
                "https://i.imgur.com/JGl7znU.jpg",
                0,
                14000,
                0
        ));

        data.add(new FoodDataModel(
                "Coffe",
                "https://i.imgur.com/e5aW4bO.jpg",
                0,
                12000,
                0
        ));

        data.add(new FoodDataModel(
                "Air Mineral",
                "https://i.imgur.com/l7RVV2i.jpg",
                0,
                5000,
                0
        ));

        data.add(new FoodDataModel(
                "Teh Manis",
                "https://i.imgur.com/KeXvMHm.jpg",
                0,
                5000,
                0
        ));

        data.add(new FoodDataModel(
                "Orange Juice",
                "https://i.imgur.com/l3jQciX.jpg",
                0,
                8000,
                0
        ));

        data.add(new FoodDataModel(
                "Lemon Tea",
                "https://i.imgur.com/HBcGa3Z.jpg",
                0,
                9000,
                0
        ));

        data.add(new FoodDataModel(
                "Milo Dinosaur",
                "https://i.imgur.com/NcH5JBN.jpg",
                0,
                12000,
                0
        ));

        return data;
    }

    @Override
    public void listenerMethod(ArrayList<FoodDataModel> model) {
        this.models = model;
    }
}
