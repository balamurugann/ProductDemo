package com.bb.productdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bb.productdemo.R;
import com.bb.productdemo.utils.AppConstants;

/**
 * Created by bala.natarajan on 10/2/2017.
 */

/**
 * Fragment Dialog to select the product image
 */
public class ImageSelectDialog extends DialogFragment implements View.OnClickListener{
    SelectImageListener selectImageListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_select, container, false);
        getDialog().setTitle(getString(R.string.select_image));

        initViews(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try
        {
            selectImageListener = (SelectImageListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString());
        }
    }

    /**
     * Initialize the views in the layout
     * @param view
     */
    private void initViews(View view){
        ImageView imgProduct1 = (ImageView)view.findViewById(R.id.imgProduct1);
        ImageView imgProduct2 = (ImageView)view.findViewById(R.id.imgProduct2);
        ImageView imgProduct3 = (ImageView)view.findViewById(R.id.imgProduct3);

        imgProduct1.setTag(AppConstants.IMAGE_MOBILE);
        imgProduct2.setTag(AppConstants.IMAGE_HEADPHONE);
        imgProduct3.setTag(AppConstants.IMAGE_POWERBANK);

        imgProduct1.setOnClickListener(this);
        imgProduct2.setOnClickListener(this);
        imgProduct3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgProduct1:
            case R.id.imgProduct2:
            case R.id.imgProduct3:
                dismiss();
                String tagValue = view.getTag().toString();
                selectImageListener.onSelectImage(tagValue);
                break;

        }

    }
}
