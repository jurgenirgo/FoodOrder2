package com.example.asus.foodorder.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.foodorder.Model.FoodDataModel;
import com.example.asus.foodorder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMakananAdapter extends RecyclerView.Adapter<ListMakananAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FoodDataModel> foodModel;
    private GetDataCallBack callBack;

    public interface GetDataCallBack {
        void listenerMethod(ArrayList<FoodDataModel> model);
    }

    public ListMakananAdapter(Context context, ArrayList<FoodDataModel> foodModel, GetDataCallBack callback) {
        this.context = context;
        this.foodModel = foodModel;
        this.callBack = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final FoodDataModel model = foodModel.get(position);

        Picasso.get()
                .load(model.getGambar())
                .into(holder.imageMakanan);

        holder.txtNamaMakanan.setText(model.getNamaMakanan());
        holder.txtJumlahItem.setText(String.valueOf(model.getTotalItem()));
        holder.txtTotalHarga.setText(String.valueOf(model.getTotalHarga()));

        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getTotalItem() > 0) {
                    int item = model.getTotalItem() - 1;
                    int harga = model.getHarga() * item;

                    model.setTotalHarga(harga);
                    model.setTotalItem(item);

                    foodModel.set(position, model);
                    callBack.listenerMethod(foodModel);
                    notifyDataSetChanged();
                }
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item = model.getTotalItem() + 1;
                int harga = model.getHarga() * item;

                model.setTotalHarga(harga);
                model.setTotalItem(item);

                foodModel.set(position, model);
                callBack.listenerMethod(foodModel);
                notifyDataSetChanged();
            }
        });
    }

    public void updateList(ArrayList<FoodDataModel> data) {
        foodModel = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return foodModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtNamaMakanan)
        TextView txtNamaMakanan;
        @BindView(R.id.imageMakanan)
        ImageView imageMakanan;
        @BindView(R.id.btnMin)
        Button btnMin;
        @BindView(R.id.btnPlus)
        Button btnPlus;
        @BindView(R.id.txtTotalHarga)
        TextView txtTotalHarga;
        @BindView(R.id.txtJumlahItem)
        TextView txtJumlahItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
