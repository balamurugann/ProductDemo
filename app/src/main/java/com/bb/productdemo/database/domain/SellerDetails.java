package com.bb.productdemo.database.domain;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bala.natarajan on 10/11/2017.
 */

public class SellerDetails  implements Parcelable{
    @ColumnInfo(name = "seller_name")
    String name;
    @ColumnInfo(name = "seller_address")
    String address;
    @ColumnInfo(name = "mobile_number")
    String mobilenumber;

    public SellerDetails(){

    }

    protected SellerDetails(Parcel in) {
        name = in.readString();
        address = in.readString();
        mobilenumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(mobilenumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SellerDetails> CREATOR = new Creator<SellerDetails>() {
        @Override
        public SellerDetails createFromParcel(Parcel in) {
            return new SellerDetails(in);
        }

        @Override
        public SellerDetails[] newArray(int size) {
            return new SellerDetails[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }
}
