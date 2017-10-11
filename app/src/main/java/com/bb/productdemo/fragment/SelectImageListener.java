package com.bb.productdemo.fragment;

/**
 * Created by bala.natarajan on 10/11/2017.
 */

public interface SelectImageListener {
    /**
     * Select the image type from the dialog box. Type will mobile,headphone and powerbank
     * @param imageType type of image selected
     */
    void onSelectImage(String imageType);
}
