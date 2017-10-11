package com.bb.productdemo.fragment.createproduct;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.productdemo.R;
import com.bb.productdemo.database.AppDatabase;
import com.bb.productdemo.database.domain.Product;
import com.bb.productdemo.database.domain.SellerDetails;
import com.bb.productdemo.fragment.BaseFragment;
import com.bb.productdemo.fragment.ImageSelectDialog;
import com.bb.productdemo.fragment.ListProductFragment;
import com.bb.productdemo.fragment.model.ProductColor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bala.natarajan on 10/10/2017.
 */

/**
 * To create new product and insert the product into local database
 */
public class CreateProductFragment extends BaseFragment implements View.OnClickListener, CreateProductView {
    public final static String TAG = "CreateProductFragment";
    public final static String BUNDLE_CREATE_PRODUCT = "bundle_create_product";

    private AppDatabase mDatabase;
    private ImageView imgProduct;
    private CreateProductPresenter createProductPresenter;
    Product product;

    private EditText txtProductName;
    private EditText txtProductDescription;
    private EditText txtProductActualPrice;
    private EditText txtProductSalesPrice;
    private EditText txtSellerName;
    private EditText txtSellerAddress;
    private EditText txtSellerMobileNumber;
    private EditText txtProductColor;
    Button btnAddProduct;
    TextView lblTitle;

    final ArrayList<ProductColor> mColorList = new ArrayList<ProductColor>();
    // String array for alert dialog multi choice items
    final String[] colors = new String[]{
            "Red",
            "Green",
            "Blue"
    };
    // Boolean array for initial selected items
    final boolean[] mCheckedColors = new boolean[]{
            false, // Red
            false, // Green
            false // Blue
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_product, container, false);

        initViews(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lblTitle = (TextView)getActivity().findViewById(R.id.lblTitle);

        mDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        createProductPresenter = new CreateProductPresenterImpl(this);

        // Get the product from product detail page to edit.
        Bundle bundle  = getArguments();
        if(bundle!=null){
            product = bundle.getParcelable(BUNDLE_CREATE_PRODUCT);
            if(product!=null){
                txtProductName.setText(product.getName());
                txtProductDescription.setText(product.getDescription());
                txtProductActualPrice.setText(product.getActualPrice());
                txtProductSalesPrice.setText(product.getSalesPrice());
                txtSellerName.setText(product.getSellerDetails().getName());
                txtSellerAddress.setText(product.getSellerDetails().getAddress());
                txtSellerMobileNumber.setText(product.getSellerDetails().getMobilenumber());
                txtProductColor.setText(product.getColors());
                lblTitle.setText(getString(R.string.edit_product));
                setProductImage(imgProduct,product.getImage());
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();
        // To change the button name and label name based on Create product and Edit Product
        if(product!=null){
            btnAddProduct.setText(getString(R.string.update_product));
        }else{
            lblTitle.setText(getString(R.string.create_product));
        }
    }

    /**
     *  Initialize the views in the layout
     * @param view
     */

    private void initViews(View view){

        txtProductColor = (EditText) view.findViewById(R.id.txtProductColor);
        txtProductColor.setOnClickListener(this);

        imgProduct = (ImageView)view.findViewById(R.id.imgProduct);
        imgProduct.setOnClickListener(this);
        imgProduct.setTag("");

        txtProductName = (EditText) view.findViewById(R.id.txtProductName);
        txtProductDescription = (EditText) view.findViewById(R.id.txtProductDescription);
        txtProductActualPrice = (EditText) view.findViewById(R.id.txtProductActualPrice);
        txtProductSalesPrice  = (EditText) view.findViewById(R.id.txtProductSalesPrice);
        txtSellerName = (EditText) view.findViewById(R.id.txtSellerName);
        txtSellerAddress = (EditText) view.findViewById(R.id.txtSellerAddress);
        txtSellerMobileNumber = (EditText) view.findViewById(R.id.txtSellerMobileNumber);

        btnAddProduct = (Button)view.findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(this);

    }

    /**
     *  Set the image icon based on imagetype
     * @param imageType type of image(mobile,headphone,powerbank)
     */
    public void setProductImageType(String imageType){
        setProductImage(imgProduct,imageType);
    }

    @Override
    public void setProductNameError() {
        txtProductName.requestFocus();
        txtProductName.setError(getString(R.string.enter_product_name) );
    }

    @Override
    public void setProductDescriptionError() {
        txtProductDescription.requestFocus();
        txtProductDescription.setError(getString(R.string.enter_product_description) );
    }

    @Override
    public void setProductActualPriceError() {
        txtProductActualPrice.requestFocus();
        txtProductActualPrice.setError(getString(R.string.enter_product_actual_price) );
    }

    @Override
    public void setProductSalesPriceError() {
        txtProductSalesPrice.requestFocus();
        txtProductSalesPrice.setError(getString(R.string.enter_product_sales_price) );
    }

    @Override
    public void setProductColorsError() {
        txtProductColor.requestFocus();
        txtProductColor.setError(getString(R.string.select_colors) );
    }

    @Override
    public void setProductSellerNameError() {
        txtSellerName.requestFocus();
        txtSellerName.setError(getString(R.string.enter_seller_name) );
    }

    @Override
    public void setProductSellerAddressError() {
        txtSellerAddress.requestFocus();
        txtSellerAddress.setError(getString(R.string.enter_seller_address) );
    }

    @Override
    public void setProductSellerMobileNumberError() {
        txtSellerMobileNumber.requestFocus();
        txtSellerMobileNumber.setError(getString(R.string.enter_seller_mobile_number) );
    }

    @Override
    public void setProductImageError() {
        Toast.makeText(getActivity(),getString(R.string.please_select_image),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToProductList() {
        getProductDetailsToAdd();
        replaceFragment(new ListProductFragment(),false,null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgProduct:
                FragmentManager fm = getFragmentManager();
                DialogFragment newFragment = new ImageSelectDialog();
                newFragment.show(fm,null);
                break;
            case R.id.btnAddProduct:
                createProductPresenter.validateProductDetails(txtProductName.getText().toString().trim(),txtProductDescription.getText().toString().trim(),
                        txtProductActualPrice.getText().toString().trim(),txtProductSalesPrice.getText().toString().trim(),txtProductColor.getText().toString().trim(),
                        txtSellerName.getText().toString().trim(),txtSellerAddress.getText().toString().trim(),txtSellerMobileNumber.getText().toString().trim(),imgProduct.getTag().toString());
                break;

            case R.id.txtProductColor:
                showColors();
                break;

        }
    }

    /**
     * To show the dialog with color selection option
     */
    private void showColors(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // make a list to hold state of e   very color
        for (int i = 0; i < colors.length; i++) {
            ProductColor productColor = new ProductColor();
            productColor.setName(colors[i]);
            productColor.setSelected(mCheckedColors[i]);
            mColorList.add(productColor);
        }

        // Do something here to pass only arraylist on this both arrays ('colors' & 'checkedColors')
        builder.setMultiChoiceItems(colors, mCheckedColors, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // set state to vo in list
                mColorList.get(which).setSelected(isChecked);
            }
        });

        builder.setCancelable(false);

        builder.setTitle(getString(R.string.select_product_colors));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                txtProductColor.setText("");

                // save state of selected vos
                ArrayList<ProductColor> selectedList = new ArrayList<>();
                for (int i = 0; i < mColorList.size(); i++) {
                    ProductColor productColor = mColorList.get(i);
                    colors[i] = productColor.getName();
                    mCheckedColors[i] = productColor.isSelected();
                    if (productColor.isSelected()) {
                        selectedList.add(productColor);
                    }
                }

                for (int i = 0; i < selectedList.size(); i++) {
                    // if element is last then not attach comma or attach it
                    if (i != selectedList.size() - 1)
                        txtProductColor.setText(txtProductColor.getText() + selectedList.get(i).getName() + " ,");
                    else
                        txtProductColor.setText(txtProductColor.getText() + selectedList.get(i).getName());
                }
                mColorList.clear();
            }
        });

        builder.setNeutralButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mColorList.clear();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * To add or update new product into database
     */
    private void getProductDetailsToAdd(){
        Product product = new Product();
        product.setName(txtProductName.getText().toString());
        product.setDescription(txtProductDescription.getText().toString());
        product.setActualPrice(txtProductActualPrice.getText().toString());
        product.setSalesPrice(txtProductSalesPrice.getText().toString());
        product.setColors(txtProductColor.getText().toString());

        SellerDetails sellerDetails = new SellerDetails();
        sellerDetails.setName(txtSellerName.getText().toString());
        sellerDetails.setAddress(txtSellerAddress.getText().toString());
        sellerDetails.setMobilenumber(txtSellerMobileNumber.getText().toString());


        product.setSellerDetails(sellerDetails);
        product.setImage(imgProduct.getTag().toString());


        if(btnAddProduct.getText().toString().equals(getString(R.string.add_product))){
            insertProduct(product).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull Object o) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else{
            updateProduct(product).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull Object o) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    /**
     *  Observable to insert the new product
     * @param product
     * @return
     */
    public Observable insertProduct(final Product product) {
        return Observable.fromCallable(new Callable() {
            @Override
            public Object call() throws Exception {
                mDatabase.getProductDao().insert(product);
                return null;
            }
        });
    }

    /**
     * Observable to update the existing product
     * @param product
     * @return
     */
    public Observable<Product> updateProduct(final Product product) {
        return Observable.fromCallable(new Callable() {
            @Override
            public Product call() throws Exception {
                mDatabase.getProductDao().update(product);
                return null;
            }
        });
    }

        @Override
    public void onDestroy() {
        createProductPresenter.onDestroy();
        super.onDestroy();
    }
}
