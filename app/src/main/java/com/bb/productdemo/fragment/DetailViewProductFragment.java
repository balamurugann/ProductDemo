package com.bb.productdemo.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.productdemo.R;
import com.bb.productdemo.database.domain.Product;
import com.bb.productdemo.fragment.createproduct.CreateProductFragment;

/**
 * Created by bala.natarajan on 10/10/2017.
 */

/**
 * To display the detail view of product also have the option to edit the product
 */
public class DetailViewProductFragment extends BaseFragment implements View.OnClickListener {
    public final static String TAG = "DetailViewProductFragment";
    public static final String BUNDLE_PRODUCT_DETAIL = "bundle_product_details";

    private Product mProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailview_product, container, false);
        initViewsAndDisplayValues(view);

        return(view);
    }

    @Override
    public void onResume() {
        super.onResume();

        TextView lblTitle = (TextView)getActivity().findViewById(R.id.lblTitle);
        lblTitle.setText(getString(R.string.product_detail_title));
    }

    /**
     * To display the product details
     * @param view
     */
    private void initViewsAndDisplayValues(View view){

        TextView lblEdit = (TextView)view.findViewById(R.id.lblEdit);
        lblEdit.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            mProduct = bundle.getParcelable(BUNDLE_PRODUCT_DETAIL);
            if(mProduct!=null){
                TextView lblName = (TextView)view.findViewById(R.id.lblName);
                setTextAndColor(lblName,mProduct.getName(),"",Color.BLACK,0,"");

                TextView lblDescription = (TextView)view.findViewById(R.id.lblDescription);
                setTextAndColor(lblDescription,mProduct.getDescription(),"",Color.BLACK,0,"");

                TextView lblActualPrice = (TextView)view.findViewById(R.id.lblActualPrice);
                setTextAndColor(lblActualPrice,getString(R.string.product_actual_price)+": ",mProduct.getActualPrice(),Color.BLACK,Color.RED,"$");

                TextView lblSalesPrice = (TextView)view.findViewById(R.id.lblSalesPrice);
                setTextAndColor(lblSalesPrice,getString(R.string.product_sales_price)+": ",mProduct.getSalesPrice(),Color.BLACK,Color.RED,"$");

                TextView lblColors = (TextView)view.findViewById(R.id.lblColors);
                setTextAndColor(lblColors,getString(R.string.product_color)+": ",mProduct.getColors(),Color.BLACK,Color.GRAY,"");


                TextView lblSellerName = (TextView)view.findViewById(R.id.lblSellerName);
                setTextAndColor(lblSellerName,getString(R.string.product_seller_name)+": ",mProduct.getSellerDetails().getName(),Color.BLACK,Color.GRAY,"");

                TextView lblSellerAddress = (TextView)view.findViewById(R.id.lblSellerAddress);
                setTextAndColor(lblSellerAddress,getString(R.string.product_seller_address)+": ",mProduct.getSellerDetails().getAddress(),Color.BLACK,Color.GRAY,"");

                TextView lblSellerConductNumber = (TextView)view.findViewById(R.id.lblSellerConductNumber);
                setTextAndColor(lblSellerConductNumber,getString(R.string.product_seller_contact_number)+": ",mProduct.getSellerDetails().getMobilenumber(),Color.BLACK,Color.GRAY,"");

                ImageView imgProdcut = (ImageView) view.findViewById(R.id.imgProduct);
                imgProdcut.setTag(mProduct.getImage());
                setProductImage(imgProdcut,mProduct.getImage());
                imgProdcut.setOnClickListener(this);
            }
        }
    }

    /**
     *  To set multi color for same label
     * @param textView
     * @param firstMessage
     * @param secondMessage
     * @param firstColor
     * @param secondColor
     * @param dollorSymbol
     */
    private void setTextAndColor(TextView textView, String firstMessage, String secondMessage,int firstColor,
                                 int secondColor,String dollorSymbol){
        Spannable word = new SpannableString(firstMessage);

        word.setSpan(new ForegroundColorSpan(firstColor), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(word);

        Spannable wordTwo;

        if(TextUtils.isEmpty(dollorSymbol))
             wordTwo = new SpannableString(secondMessage);
        else
            wordTwo = new SpannableString(dollorSymbol+secondMessage);

        wordTwo.setSpan(new ForegroundColorSpan(secondColor), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(wordTwo);
    }

    /**
     * Dialog to display full size imageview
     * @param type
     */
    private void showFullSizeImage(String type){
        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.preview_image);
        Button btnClose = (Button)dialog.findViewById(R.id.btnClose);
        ImageView imgPreview = (ImageView)dialog.findViewById(R.id.imgPreview);
        setProductFullSizeImage(imgPreview,type);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgProduct:
                showFullSizeImage(view.getTag().toString());
                break;
            case R.id.lblEdit:
                Bundle args = new Bundle();
                args.putParcelable(CreateProductFragment.BUNDLE_CREATE_PRODUCT, mProduct);
                CreateProductFragment toFragment = new CreateProductFragment();
                toFragment.setArguments(args);
                replaceFragment(toFragment,false, CreateProductFragment.TAG);
                break;
        }
    }
}
