
package com.landmark.assignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.landmark.assignment.APIResponse;
import com.landmark.assignment.R;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<APIResponse.Products> listdata;
    private Context context;

    // RecyclerView recyclerView;  
    public ProductAdapter(Context context, ArrayList<APIResponse.Products> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    public void setListdata(APIResponse products) {
        listdata = products.getProducts();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final APIResponse.Products mProduct = listdata.get(position);

        if (mProduct.getUrl() != null) {
            String url = mProduct.getUrl();
            Glide.with(context)
                    .load(url)
//                    .placeholder(R.drawable.)
//                    .transform(new CircleTransform(context))
                    .into(holder.productImageView);
        } else {
            // make sure Glide doesn't load anything into this view until told otherwise
            Glide.with(context).clear(holder.productImageView);
            // remove the placeholder (optional); read comments below
            holder.productImageView.setImageDrawable(null);
        }

        holder.productTitle.setText(mProduct.getName());
        holder.productValue.setText(String.valueOf(mProduct.getPrice()));
        holder.productCurrency.setText(mProduct.getCurrency());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImageView;
        public TextView productTitle;
        public TextView productCurrency;
        public TextView productValue;

        public ViewHolder(View itemView) {
            super(itemView);
            this.productImageView = (ImageView) itemView.findViewById(R.id.prod_image);
            this.productTitle = (TextView) itemView.findViewById(R.id.prod_title);
            this.productCurrency = (TextView) itemView.findViewById(R.id.prod_currency);
            this.productValue = (TextView) itemView.findViewById(R.id.prod_value);
        }
    }
}  