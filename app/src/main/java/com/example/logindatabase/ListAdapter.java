package com.example.logindatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

/* Adapter is built to reuse Views, when a View is scrolled so that is no longer visible,
 * it can be used for one of the new Views appearing. This reused View is the convertView. If this is null
 * it means that there is no recycled View and we have to create a new one, otherwise we should use it
 * to avoid creating a new. */

public class ListAdapter extends BaseAdapter {

    public ArrayList<Product> listProducts;
    private Context context;


    public ListAdapter(Context context, ArrayList<Product> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
    }

    @Override
    public int getCount() {
        return listProducts.size();
    }

    @Override
    public Product getItem(int position) {
        return listProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /* LAYOUT_INFLATER_SERVICE is used with getSystemService to retrieve a LayoutInflater for inflating
     *  layout resources in this context.
     * */

    @Override
    public View getView(final int position, View convertView
            , ViewGroup parent) {
        View row;

        final ListViewHolder listViewHolder;


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.activity_list_view, parent, false);
            listViewHolder = new ListViewHolder();
            listViewHolder.tvProductName = row.findViewById(R.id.tvProductName);
            listViewHolder.ivProduct = row.findViewById(R.id.ivProduct);
            listViewHolder.tvPrice = row.findViewById(R.id.tvPrice);
            listViewHolder.btnPlus = row.findViewById(R.id.ib_addnew);
            listViewHolder.edTextQuantity = row.findViewById(R.id.editTextQuantity);
            listViewHolder.btnMinus = row.findViewById(R.id.ib_remove);


            row.setTag(listViewHolder);
        } else {

            row = convertView;
            listViewHolder = (ListViewHolder) row.getTag();
        }
        final Product products = getItem(position);

        listViewHolder.tvProductName.setText(products.ProductName);
        listViewHolder.ivProduct.setImageResource(products.ProductImage);
        listViewHolder.tvPrice.setText("$ " + products.ProductPrice);
        listViewHolder.edTextQuantity.setText(products.CartQuantity + "");
        listViewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(position, listViewHolder.edTextQuantity, 1);
            }
        });
        //listViewHolder.edTextQuantity.setText("0");
        listViewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(position, listViewHolder.edTextQuantity, -1);

            }
        });


        return row;
    }

    private void updateQuantity(int position, EditText edTextQuantity, int value) {

        Product products = getItem(position);
        if (value > 0) {
            products.CartQuantity = products.CartQuantity + 1;
        } else {
            if (products.CartQuantity > 0) {
                products.CartQuantity = products.CartQuantity - 1;
            }

        }
        edTextQuantity.setText(products.CartQuantity + "");
    }
}