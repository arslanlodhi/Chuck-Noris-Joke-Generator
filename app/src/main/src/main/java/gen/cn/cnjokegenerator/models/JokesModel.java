package gen.cn.cnjokegenerator.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import gen.cn.cnjokegenerator.base.BaseModel;

/**
 * Created by arslanlodhi on 5/6/18.
 */

public class JokesModel implements Serializable, Parcelable {

    @SerializedName("id")
    int id;
    @SerializedName("joke")
    String joke;

    @SerializedName("categories")
    ArrayList<String> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public String getCategoryString(){
        String str="";
        if(categories!=null)
            for (String category: categories) {
                str=str +" "+category.toUpperCase();
            }
        return str;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    protected JokesModel(Parcel in) {
        id = in.readInt();
        joke = in.readString();
        if (in.readByte() == 0x01) {
            categories = new ArrayList<String>();
            in.readList(categories, String.class.getClassLoader());
        } else {
            categories = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(joke);
        if (categories == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(categories);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<JokesModel> CREATOR = new Parcelable.Creator<JokesModel>() {
        @Override
        public JokesModel createFromParcel(Parcel in) {
            return new JokesModel(in);
        }

        @Override
        public JokesModel[] newArray(int size) {
            return new JokesModel[size];
        }
    };
}
