package gen.cn.cnjokegenerator.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import java.util.ArrayList;
import javax.inject.Inject;
import gen.cn.cnjokegenerator.base.BaseViewModel;
import gen.cn.cnjokegenerator.models.JokesModel;
import gen.cn.cnjokegenerator.models.JokesQueryModel;
import gen.cn.cnjokegenerator.repositories.CategoriesRepository;
import gen.cn.cnjokegenerator.repositories.JokesRepository;

public class MainActivityViewModel extends BaseViewModel {

    public JokesQueryModel getQueryModel() {
        return queryModel;
    }

    JokesQueryModel queryModel;
    LiveData<JokesModel> jokeList;
    LiveData<String> categoryList;

    @Inject
    JokesRepository jokesRepository;

    @Inject
    CategoriesRepository categoriesRepository;


    @Inject
    public MainActivityViewModel() {
        queryModel=new JokesQueryModel();
        jokeList=new LiveData<JokesModel>() {};
        categoryList=new LiveData<String>() {};
    }

    public MutableLiveData<ArrayList<JokesModel>> fetchJokes(){
        return jokesRepository.fetchRandom20Jokes(this,queryModel.getFirstName().get(),queryModel.getLastName().get(),queryModel.getCategories());
    }

    public MutableLiveData<ArrayList<String>> fetchCategories(){
        return categoriesRepository.fetchAllCategories(this);
    }

}
