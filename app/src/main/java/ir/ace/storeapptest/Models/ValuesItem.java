package ir.ace.storeapptest.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ValuesItem implements Parcelable {
    private String title;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeByte(this.check ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.title = source.readString();
        this.check = source.readByte() != 0;
    }

    public ValuesItem() {
    }

    protected ValuesItem(Parcel in) {
        this.title = in.readString();
        this.check = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ValuesItem> CREATOR = new Parcelable.Creator<ValuesItem>() {
        @Override
        public ValuesItem createFromParcel(Parcel source) {
            return new ValuesItem(source);
        }

        @Override
        public ValuesItem[] newArray(int size) {
            return new ValuesItem[size];
        }
    };
}
