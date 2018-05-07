package gen.cn.cnjokegenerator.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import java.util.ArrayList;
import gen.cn.cnjokegenerator.BR;


public class JokesQueryModel extends BaseObservable{

    ObservableField<String> firstName;
    ObservableField<String> lastName;
    ArrayList<String> categories;
    boolean isLoading;

    public JokesQueryModel(){
        firstName=new ObservableField<>("");
        lastName=new ObservableField<>("");
        categories=new ArrayList<>();

    }

    public ObservableField<String>  getFirstName() {
        return firstName;
    }

    public void setFirstName(ObservableField<String>  firstName) {
        this.firstName = firstName;
    }

    public ObservableField<String>  getLastName() {
        return lastName;
    }

    public void setLastName(ObservableField<String>  lastName) {
        this.lastName = lastName;
    }
    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        this.notifyPropertyChanged(BR.loading);
    }

}
