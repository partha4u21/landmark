
package com.landmark.assignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.landmark.assignment.APIResponse;
import com.landmark.assignment.R;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends BaseAdapter {
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
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView productImageView = null;
        TextView productTitle = null;
        TextView productCurrency = null;
        TextView productValue = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.list_item, parent, false);
        }

        if (convertView != null) {
            productImageView = (ImageView) convertView.findViewById(R.id.prod_image);
            productTitle = (TextView) convertView.findViewById(R.id.prod_title);
            productCurrency = (TextView) convertView.findViewById(R.id.prod_currency);
            productValue = (TextView) convertView.findViewById(R.id.prod_value);
        }

        final APIResponse.Products mProduct = listdata.get(position);

        if (mProduct.getUrl() != null) {
            String url = mProduct.getUrl();
            Glide.with(context)
                    .load(url)
//                    .placeholder(R.drawable.)
//                    .transform(new CircleTransform(context))
                    .into(productImageView);
        } else {
            // make sure Glide doesn't load anything into this view until told otherwise
            Glide.with(context).clear(productImageView);
            // remove the placeholder (optional); read comments below
            productImageView.setImageDrawable(null);
        }

        productTitle.setText(mProduct.getName());
        productValue.setText(String.valueOf(mProduct.getPrice()));
        productCurrency.setText(mProduct.getCurrency());

        return convertView;
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