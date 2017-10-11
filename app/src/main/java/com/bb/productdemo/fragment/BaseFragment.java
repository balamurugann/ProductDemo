package com.bb.productdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bb.productdemo.R;
import com.bb.productdemo.utils.AppConstants;
import com.squareup.picasso.Picasso;

/**
 * Created by bala.natarajan on 10/10/2017.
 *
 * Provide common functionality for Create,Update and List product screen
 */


public class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void replaceFragment(Fragment fragment, boolean addToStack, String tag) {
        try {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment, tag);
            if(addToStack)
                fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commitAllowingStateLoss();
        } catch (Exception e) {
            Toast.makeText(getActivity(),getString(R.string.exception_replace_fragment),Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * To set the product icon
     * @param image
     * @param imageType
     */
    public void setProductImage(ImageView image,String imageType){
        if(imageType.equals(AppConstants.IMAGE_MOBILE)){
            image.setTag(AppConstants.IMAGE_MOBILE);
            Picasso.with(getActivity()).load(R.drawable.mobile_icon).into(image);
        }else if(imageType.equals(AppConstants.IMAGE_HEADPHONE)){
            image.setTag(AppConstants.IMAGE_HEADPHONE);
            Picasso.with(getActivity()).load(R.drawable.headphone_icon).into(image);
        }else if(imageType.equals(AppConstants.IMAGE_POWERBANK)){
            image.setTag(AppConstants.IMAGE_POWERBANK);
            Picasso.with(getActivity()).load(R.drawable.powerbank_icon).into(image);
        }
    }

    /**
     * To set the full size image of product
     * @param image
     * @param imageType
     */
    public void setProductFullSizeImage(ImageView image,String imageType){
        if(imageType.equals(AppConstants.IMAGE_MOBILE)){
            image.setTag(AppConstants.IMAGE_MOBILE);
            Picasso.with(getActivity()).load(R.drawable.mobile).into(image);
        }else if(imageType.equals(AppConstants.IMAGE_HEADPHONE)){
            image.setTag(AppConstants.IMAGE_HEADPHONE);
            Picasso.with(getActivity()).load(R.drawable.headphone).into(image);
        }else if(imageType.equals(AppConstants.IMAGE_POWERBANK)){
            image.setTag(AppConstants.IMAGE_POWERBANK);
            Picasso.with(getActivity()).load(R.drawable.powerbank).into(image);

        }

    }
}
