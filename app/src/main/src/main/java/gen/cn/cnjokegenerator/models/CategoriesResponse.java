package gen.cn.cnjokegenerator.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arslanlodhi on 5/6/18.
 */

public class CategoriesResponse implements Serializable{

    @SerializedName("type")
    String type;

    @SerializedName("value")
    ArrayList<String> value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }




}
