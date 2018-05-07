package gen.cn.cnjokegenerator.repositories;


import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

import javax.inject.Inject;

import gen.cn.cnjokegenerator.api.ApiService;
import gen.cn.cnjokegenerator.base.BaseViewModel;
import gen.cn.cnjokegenerator.di.AppModule;
import gen.cn.cnjokegenerator.enums.CallTypeEnums;
import gen.cn.cnjokegenerator.enums.ViewModelEventsEnum;
import gen.cn.cnjokegenerator.models.CategoriesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoriesRepository {
    @Inject
    ApiService apiService;

    @Inject
    AppModule appModule;


    @Inject
    public CategoriesRepository(){

    }

    Call<CategoriesResponse> getAllCategoriesApiCall;

    public MutableLiveData<ArrayList<String>> fetchAllCategories(final BaseViewModel viewModel) {
        final MutableLiveData<ArrayList<String>> categoriesList=new MutableLiveData<>();

        if (appModule.isConnectedToInternet()) {
            if (getAllCategoriesApiCall != null)
                getAllCategoriesApiCall.cancel();

            getAllCategoriesApiCall = apiService.getCategories();

            viewModel.notifyObserver(CallTypeEnums.CATEGORIES,ViewModelEventsEnum.ON_API_CALL_START, null);
            getAllCategoriesApiCall.enqueue(new Callback<CategoriesResponse>() {
                @Override
                public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                    viewModel.notifyObserver(CallTypeEnums.CATEGORIES,ViewModelEventsEnum.ON_API_CALL_STOP, null);
                    if (!call.isCanceled() && response.isSuccessful()) {
                        categoriesList.postValue(response.body().getValue());

                    }
                }

                @Override
                public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                    viewModel.notifyObserver(CallTypeEnums.CATEGORIES,ViewModelEventsEnum.ON_API_REQUEST_FAILURE, null);
                    viewModel.notifyObserver(CallTypeEnums.CATEGORIES,ViewModelEventsEnum.ON_API_CALL_STOP, null);
                    t.printStackTrace();
                }
            });

        } else {
            viewModel.notifyObserver(CallTypeEnums.CATEGORIES,ViewModelEventsEnum.NO_INTERNET_CONNECTION, null);
        }
        return categoriesList;
    }





}
