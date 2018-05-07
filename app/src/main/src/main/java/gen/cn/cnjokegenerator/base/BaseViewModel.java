package gen.cn.cnjokegenerator.base;

import android.arch.lifecycle.ViewModel;

import gen.cn.cnjokegenerator.enums.CallTypeEnums;
import gen.cn.cnjokegenerator.enums.ViewModelEventsEnum;
import gen.cn.cnjokegenerator.interfaces.ViewModelCallBackObserver;


public class BaseViewModel extends ViewModel {

    public ViewModelCallBackObserver callBackObserver;

    public void addObserver(ViewModelCallBackObserver callBackObserver) {
        this.callBackObserver = callBackObserver;
    }

    public void removeObserver() {
        callBackObserver = null;
    }

    public void notifyObserver(CallTypeEnums callTypeEnums,ViewModelEventsEnum eventType, String message){
        if(callBackObserver != null)
        callBackObserver.onObserve(callTypeEnums,eventType, message);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        removeObserver();
    }
}
