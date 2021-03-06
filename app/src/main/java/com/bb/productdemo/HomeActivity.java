package com.bb.productdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.Toast;

import com.bb.productdemo.fragment.ListProductFragment;
import com.bb.productdemo.fragment.SelectImageListener;
import com.bb.productdemo.fragment.createproduct.CreateProductFragment;

/**
 * Entry point of the application and act as a container for create,list and detailview of fragment.
 */
public class HomeActivity extends FragmentActivity implements SelectImageListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        replaceFragment(new ListProductFragment(),false,null);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    /**
     * To replace layout container with fragment
     * @param fragment fragment to replace the container
     * @param addToStack whether we need to add to backstack or not
     * @param tag Tag of fragment
     */
    public void replaceFragment(Fragment fragment, boolean addToStack, String tag) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment, tag);
            if(addToStack)
                fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commitAllowingStateLoss();
        } catch (Exception e) {
            Toast.makeText(this,getString(R.string.exception_replace_fragment),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSelectImage(String imageType) {
       Fragment fragment = getSupportFragmentManager().findFragmentByTag(CreateProductFragment.TAG);
        ((CreateProductFragment) fragment).setProductImageType(imageType);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
