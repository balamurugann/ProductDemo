package com.bb.productdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.productdemo.R;
import com.bb.productdemo.database.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bala.natarajan on 10/11/2017.
 */

/**
 * Adapter to adapt the products data to recycler view
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> lstProducts = new ArrayList<>();
    private final ActionCallback callback;

    public ProductAdapter(@NonNull ActionCallback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_product, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onRedirectToDetail(lstProducts.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = lstProducts.get(position);
        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText("$"+product.getSalesPrice());
    }

    @Override
    public int getItemCount() {
        return lstProducts.size();
    }

    public Product getProducts(int position) {
        return lstProducts.get(position);
    }

    /**
     *  Set new product list and notify the adapter
     * @param products list of products from database
     */
    public void setProducts(@NonNull List<Product> products) {
        this.lstProducts = products;
        notifyDataSetChanged();
    }

    /**
     *  To handle recycler view click
     */
    public interface ActionCallback {
        /**
         *  Called when clicking recycler view
         * @param product product to pass from list to detailview page
         */
        void onRedirectToDetail(Product product);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, price;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}