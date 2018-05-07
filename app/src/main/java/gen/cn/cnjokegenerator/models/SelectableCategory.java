package gen.cn.cnjokegenerator.models;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import gen.cn.cnjokegenerator.BR;

public class SelectableCategory extends BaseObservable{


    boolean isSelected;
    String title;

    public SelectableCategory(String title, boolean isSelected) {
        this.isSelected = isSelected;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        this.notifyPropertyChanged(BR.selected);
    }





}
