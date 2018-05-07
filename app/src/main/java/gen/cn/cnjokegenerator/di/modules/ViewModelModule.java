package gen.cn.cnjokegenerator.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import gen.cn.cnjokegenerator.di.ViewModelFactory;
import gen.cn.cnjokegenerator.viewmodels.JokesActivityViewModel;
import gen.cn.cnjokegenerator.viewmodels.MainActivityViewModel;


@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel mainActivityViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(JokesActivityViewModel.class)
    abstract ViewModel jokesActivityViewModel(JokesActivityViewModel JokesActivityViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);


}