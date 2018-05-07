package gen.cn.cnjokegenerator.views.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gen.cn.cnjokegenerator.R;
import gen.cn.cnjokegenerator.base.BaseFragment;
import gen.cn.cnjokegenerator.base.BaseViewModel;
import gen.cn.cnjokegenerator.databinding.FragmentJokesBinding;
import gen.cn.cnjokegenerator.enums.CallTypeEnums;
import gen.cn.cnjokegenerator.enums.ViewModelEventsEnum;
import gen.cn.cnjokegenerator.models.JokesModel;



public class JokesFragment extends BaseFragment<BaseViewModel,FragmentJokesBinding> {

    private static final String ARG_JOKE = "joke";

    public JokesFragment() {
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_jokes;
    }

    @Override
    public void onObserve(CallTypeEnums callType, ViewModelEventsEnum event, Object eventMessage) {

    }

    @Override
    public Class<BaseViewModel> getViewModel() {
        return BaseViewModel.class;
    }

    public static JokesFragment newInstance(JokesModel joke) {
        JokesFragment fragment = new JokesFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_JOKE, joke);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            JokesModel jokeModel = bundle.getParcelable(ARG_JOKE);
            binding.setJokes(jokeModel);
        }

        return binding.getRoot();
    }
}