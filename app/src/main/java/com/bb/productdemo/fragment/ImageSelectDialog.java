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

    private void initViews(View view){
        ImageView img1 = (ImageView)view.findViewById(R.id.img1);
        ImageView img2 = (ImageView)view.findViewById(R.id.img2);
        ImageView img3 = (ImageView)view.findViewById(R.id.img3);

        img1.setTag(AppConstants.IMAGE_MOBILE);
        img2.setTag(AppConstants.IMAGE_HEADPHONE);
        img3.setTag(AppConstants.IMAGE_POWERBANK);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img1:
            case R.id.img2:
            case R.id.img3:
                dismiss();
                String tagValue = view.getTag().toString();
                selectImageListener.onSelectImage(tagValue);
                break;

        }

    }
}
