package com.bb.productdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.productdemo.R;
import com.bb.productdemo.adapter.ProductAdapter;
import com.bb.productdemo.database.AppDatabase;
import com.bb.productdemo.database.domain.Product;
import com.bb.productdemo.fragment.createproduct.CreateProductFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
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

public class ListProductFragment extends BaseFragment implements ProductAdapter.ActionCallback {
    public final static String TAG = "ListProductFragment";

    private ProductAdapter adapter;
    TextView lblNoProduct;

    FloatingActionButton btnFab;
    RecyclerView recyclerListProduct;

    private AppDatabase mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        btnFab = (FloatingActionButton) getActivity().findViewById(R.id.fab);



        lblNoProduct = (TextView)view.findViewById(R.id.lblNoProduct);

        recyclerListProduct = (RecyclerView) view.findViewById(R.id.recyclerListProduct);
        recyclerListProduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerListProduct.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteProduct(adapter.getProducts(viewHolder.getAdapterPosition())).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@NonNull Object o) {
                                listProcuctFromDB();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerListProduct);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter  = new ProductAdapter(this);

        mDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        listProcuctFromDB();

    }

    private void listProcuctFromDB(){
        mDatabase.getProductDao().getAllProduct().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Product>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }
            @Override
            public void onNext(List<Product> products) {
                if(products.size()>0){
                    lblNoProduct.setVisibility(View.GONE);
                    recyclerListProduct.setVisibility(View.VISIBLE);
                }else{
                    lblNoProduct.setVisibility(View.VISIBLE);
                    recyclerListProduct.setVisibility(View.GONE);
                }
                adapter.setProducts(products);
                recyclerListProduct.setAdapter(adapter);
            }
            @Override
            public void onError(Throwable t) {
            }
            @Override
            public void onComplete() {

            }
        });
    }

    public Observable deleteProduct(final Product product) {
        return Observable.fromCallable(new Callable() {
            @Override
            public Object call() throws Exception {
                mDatabase.getProductDao().delete(product);
                return null;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        TextView lblTitle = (TextView)getActivity().findViewById(R.id.lblTitle);
        lblTitle.setText(getString(R.string.product_list_title));

        btnFab.setVisibility(View.VISIBLE);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new CreateProductFragment(),true,CreateProductFragment.TAG);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        btnFab.setVisibility(View.GONE);
    }

    @Override
    public void onRedirectToDetail(Product product) {
        Bundle args = new Bundle();
        args.putParcelable(DetailViewProductFragment.BUNDLE_PRODUCT_DETAIL, product);
        DetailViewProductFragment toFragment = new DetailViewProductFragment();
        toFragment.setArguments(args);
        replaceFragment(toFragment,true,DetailViewProductFragment.TAG);
    }
}
