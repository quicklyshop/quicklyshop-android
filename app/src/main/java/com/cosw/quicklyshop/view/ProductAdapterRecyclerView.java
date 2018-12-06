package com.cosw.quicklyshop.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.adapter.PictureAdapterRecyclerView;
import com.cosw.quicklyshop.model.Product;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

class ProductAdapterRecyclerView extends RecyclerView.Adapter<ProductAdapterRecyclerView.ProductViewHolder> {

    private List<Product> mDataset;
    private int resource;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductAdapterRecyclerView(List<Product> myDataset, int resource) {
        mDataset = myDataset;
        this.resource = resource;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new ProductAdapterRecyclerView.ProductViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Product p = this.mDataset.get(position);
        holder.getName().setText(p.getName());
        holder.getDescription().setText(p.getDescription());
        holder.getPrice().setText(Double.toString(p.getPrice()));
        holder.getSupplier().setText(p.getSupplier());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Data
    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView description;
        private TextView price;
        private TextView supplier;

        public ProductViewHolder(View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.product_name);
            this.description = itemView.findViewById(R.id.product_description);
            this.price = itemView.findViewById(R.id.product_price);
            this.supplier = itemView.findViewById(R.id.product_supplier);
        }
    }

}
