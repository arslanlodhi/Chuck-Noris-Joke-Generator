package gen.cn.cnjokegenerator.interfaces;


import gen.cn.cnjokegenerator.enums.CallTypeEnums;
import gen.cn.cnjokegenerator.enums.ViewModelEventsEnum;

public interface ViewModelCallBackObserver<T> {

    void onObserve(CallTypeEnums callType, ViewModelEventsEnum event, T eventMessage);

}
