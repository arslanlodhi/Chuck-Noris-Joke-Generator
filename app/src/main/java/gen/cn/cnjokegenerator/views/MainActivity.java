package gen.cn.cnjokegenerator.views;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import java.util.ArrayList;

import gen.cn.cnjokegenerator.R;
import gen.cn.cnjokegenerator.adapters.CategoriesAdapter;
import gen.cn.cnjokegenerator.base.BaseActivity;
import gen.cn.cnjokegenerator.databinding.ActivityMainBinding;
import gen.cn.cnjokegenerator.enums.CallTypeEnums;
import gen.cn.cnjokegenerator.enums.ViewModelEventsEnum;
import gen.cn.cnjokegenerator.models.JokesModel;
import gen.cn.cnjokegenerator.models.SelectableCategory;
import gen.cn.cnjokegenerator.viewmodels.MainActivityViewModel;


public class MainActivity extends BaseActivity<MainActivityViewModel, ActivityMainBinding> {


    CategoriesAdapter categoriesAdapter;

    @Override
    public Class<MainActivityViewModel> getViewModel() {
        return MainActivityViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        binding.setQueryModel(viewModel.getQueryModel());
        fetchCategories();


    }

    @Override
    public void onObserve(CallTypeEnums callType, ViewModelEventsEnum event, Object eventMessage) {
        switch (event) {
            case NO_INTERNET_CONNECTION:
                showSnackBack(getString(R.string.no_internet_connection));
                break;
            case ON_API_REQUEST_FAILURE:
                showSnackBack(getString(R.string.try_again));
                break;
            case ON_API_CALL_START:
                if (callType == CallTypeEnums.JOKES)
                    viewModel.getQueryModel().setLoading(true);
                break;
            case ON_API_CALL_STOP:
                if (callType == CallTypeEnums.JOKES)
                    viewModel.getQueryModel().setLoading(false);
                break;
            default:
                break;
        }
    }

    public void fetchJokes(View view) {

        binding.txtFirstName.setError(null);
        binding.txtLastName.setError(null);
        InputMethodManager imm = (InputMethodManager) getSystemService(getBaseContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        if (viewModel.getQueryModel().getFirstName().get().trim().length() == 0)
            binding.txtFirstName.setError(getString(R.string.error_txt_firstname));
        else if (viewModel.getQueryModel().getLastName().get().trim().length()== 0)
            binding.txtLastName.setError(getString(R.string.error_txt_lastname));
        else {
            viewModel.fetchJokes().observe(this, new Observer<ArrayList<JokesModel>>() {
                @Override
                public void onChanged(@Nullable ArrayList<JokesModel> jokesModels) {
                    Log.e("Jokes", "" + jokesModels.size());
                    JokesActivity.openJokesActivity(MainActivity.this, jokesModels);

                }
            });

        }


    }

    public void fetchCategories() {
        viewModel.fetchCategories().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> categories) {
                Log.e("Cagegories", "" + categories.size());
                intializeCategoris(categories);

            }
        });
    }


    public void intializeCategoris(ArrayList<String> categories) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        binding.rvCategories.setLayoutManager(layoutManager);
        binding.rvCategories.setNestedScrollingEnabled(false);
        categoriesAdapter = new CategoriesAdapter(new CategoriesAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(SelectableCategory item) {
                viewModel.getQueryModel().setCategories(categoriesAdapter.getSelectedItems());
            }
        }, categories);
        binding.rvCategories.setAdapter(categoriesAdapter);
    }

    public void showSnackBack(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}
