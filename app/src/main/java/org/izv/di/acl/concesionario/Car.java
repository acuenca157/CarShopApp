package org.izv.di.acl.concesionario;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.style.AlignmentSpan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Car implements Parcelable {
    int ref;
    String title;
    String description;
    String price;
    ArrayList<String> categories;
    ArrayList<String> imagesUrls;

    public Car(int ref, String title, String description, String price, String categories, String imagesUrls){
        this.ref = ref;
        this.title = title;
        this.description = description;
        this.price = price;
        String[] arrCat = categories.split("%%");
        this.categories = new ArrayList<String>();
        for (String cat : arrCat) {
            this.categories.add(cat);
        }

        String[] arrImgs = imagesUrls.split("%%");
        this.imagesUrls = new ArrayList<String>();
        for (String cat : arrImgs) {
            this.imagesUrls.add(cat);
        }
    }

    protected Car(Parcel in) {
        ref = in.readInt();
        title = in.readString();
        description = in.readString();
        price = in.readString();
        categories = in.createStringArrayList();
        imagesUrls = in.createStringArrayList();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public int getRef(){return ref;}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<String> getImagesUrls() {
        return imagesUrls;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ref);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeStringList(categories);
        dest.writeStringList(imagesUrls);
    }
}
