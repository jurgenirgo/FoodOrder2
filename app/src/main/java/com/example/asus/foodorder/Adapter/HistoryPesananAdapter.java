package com.example.asus.foodorder.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.foodorder.Model.HistoryPesanan;
import com.example.asus.foodorder.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryPesananAdapter extends RecyclerView.Adapter<HistoryPesananAdapter.ViewHolder> {

    private List<HistoryPesanan> list;

    public HistoryPesananAdapter(List<HistoryPesanan> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_pesanan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryPesanan model = list.get(position);

        holder.txtIdPesanan.setText("ID Pesanan : " + model.getIdPesanan());
        holder.txtNamaPemesan.setText("Nama Pemesan : " + model.getNamaPemesan());
        holder.txtMejaPemesan.setText("Meja Pemesan : " + model.getMejaPemesan());
        holder.txtPesanan.setText(model.getNamaMakanan());
        holder.txtTotalPembayaran.setText("Total Pembayaran : " + model.getTotalPembayaran());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNamaPemesan)
        TextView txtNamaPemesan;
        @BindView(R.id.txtMejaPemesan)
        TextView txtMejaPemesan;
        @BindView(R.id.txtPesanan)
        TextView txtPesanan;
        @BindView(R.id.txtTotalPembayaran)
        TextView txtTotalPembayaran;
        @BindView(R.id.txtIdPesanan)
        TextView txtIdPesanan;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
