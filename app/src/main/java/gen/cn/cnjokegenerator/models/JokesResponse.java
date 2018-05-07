package gen.cn.cnjokegenerator.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

import gen.cn.cnjokegenerator.base.BaseModel;

/**
 * Created by arslanlodhi on 5/6/18.
 */

public class JokesResponse implements Serializable{

    @SerializedName("type")
    String type;

    @SerializedName("value")
    ArrayList<JokesModel> value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<JokesModel> getValue() {
        return value;
    }

    public void setValue(ArrayList<JokesModel> value) {
        this.value = value;
    }




}
