package gen.cn.cnjokegenerator.viewmodels;


import java.util.ArrayList;

import javax.inject.Inject;

import gen.cn.cnjokegenerator.base.BaseViewModel;
import gen.cn.cnjokegenerator.models.JokesModel;


public class JokesActivityViewModel extends BaseViewModel {


    ArrayList<JokesModel> jokeList;


    @Inject
    public JokesActivityViewModel() {
    }

    public ArrayList<JokesModel> getJokeList() {
        return jokeList;
    }

    public void setJokeList(ArrayList<JokesModel> jokeList) {
        this.jokeList = jokeList;
    }

    public String getTitle(int postition) {
        return postition + "/" + jokeList.size();

    }

}
