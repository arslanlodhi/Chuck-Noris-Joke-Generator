package gen.cn.cnjokegenerator.repositories;



import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

import javax.inject.Inject;

import gen.cn.cnjokegenerator.api.ApiService;
import gen.cn.cnjokegenerator.base.BaseViewModel;
import gen.cn.cnjokegenerator.di.AppModule;
import gen.cn.cnjokegenerator.enums.CallTypeEnums;
import gen.cn.cnjokegenerator.enums.ViewModelEventsEnum;
import gen.cn.cnjokegenerator.models.JokesModel;
import gen.cn.cnjokegenerator.models.JokesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JokesRepository {

    public static String ESCAPE="javascript";
    @Inject
    ApiService apiService;

    @Inject
    AppModule appModule;

    @Inject
    public JokesRepository(){

    }
    Call<JokesResponse> fetchRandomJokesApi;

    public MutableLiveData<ArrayList<JokesModel>> fetchRandom20Jokes(final BaseViewModel viewModel, String firstName, String lastName, ArrayList<String> categories) {
        final MutableLiveData<ArrayList<JokesModel>> jokesList=new MutableLiveData<>();

        if (appModule.isConnectedToInternet()) {
            if (fetchRandomJokesApi != null)
                fetchRandomJokesApi.cancel();

            fetchRandomJokesApi = apiService.getJokes(firstName,lastName,categories,ESCAPE);

            viewModel.notifyObserver(CallTypeEnums.JOKES,ViewModelEventsEnum.ON_API_CALL_START, null);
            fetchRandomJokesApi.enqueue(new Callback<JokesResponse>() {
                @Override
                public void onResponse(Call<JokesResponse> call, Response<JokesResponse> response) {
                    viewModel.notifyObserver(CallTypeEnums.JOKES,ViewModelEventsEnum.ON_API_CALL_STOP, null);
                    if (!call.isCanceled() && response.isSuccessful()) {
                        if(response.body().getValue()!=null)
                        jokesList.postValue(response.body().getValue());
                        else
                            viewModel.notifyObserver(CallTypeEnums.JOKES,ViewModelEventsEnum.ON_API_REQUEST_FAILURE, null);


                    }else{
                        viewModel.notifyObserver(CallTypeEnums.JOKES,ViewModelEventsEnum.ON_API_REQUEST_FAILURE, null);
                    }
                }

                @Override
                public void onFailure(Call<JokesResponse> call, Throwable t) {
                    viewModel.notifyObserver(CallTypeEnums.JOKES,ViewModelEventsEnum.ON_API_REQUEST_FAILURE, null);
                    viewModel.notifyObserver(CallTypeEnums.JOKES,ViewModelEventsEnum.ON_API_CALL_STOP, null);
                    t.printStackTrace();
                }
            });

        } else {
            viewModel.notifyObserver(CallTypeEnums.JOKES,ViewModelEventsEnum.NO_INTERNET_CONNECTION, null);
        }
        return jokesList;
    }





}
