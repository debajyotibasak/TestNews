package com.debajyotibasak.testnews.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debajyotibasak.testnews.R;
import com.debajyotibasak.testnews.adapter.NewsAdapter;
import com.debajyotibasak.testnews.di.component.DaggerViewModelFactoryComponent;
import com.debajyotibasak.testnews.di.component.ViewModelFactoryComponent;
import com.debajyotibasak.testnews.di.module.api.ContextModule;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainActivityViewModelFactory viewModelFactory;

    private RecyclerView mRecylerView;
    private ProgressBar mProgress;
    private NewsAdapter adapter;
    private MainActivityViewModel mainActivityViewModel;

    private void initViews() {
        setContentView(R.layout.activity_main);
        mRecylerView = findViewById(R.id.rv_news);
        mProgress = findViewById(R.id.progress);
        adapter = new NewsAdapter(new ArrayList<>(), this);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setAdapter(adapter);
    }

    private void initData() {
        ViewModelFactoryComponent daggerAppComponent = DaggerViewModelFactoryComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        daggerAppComponent.inject(MainActivity.this);

        mainActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initData();

        mainActivityViewModel.getNewsArticles().observe(this, listResource -> {
            if (listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:
                        mProgress.setVisibility(View.GONE);
                        mRecylerView.setVisibility(View.VISIBLE);
                        if (listResource.data != null) {
                            adapter.addItems(listResource.data);
                        }
                        break;
                    case LOADING:
                        mProgress.setVisibility(View.VISIBLE);
                        mRecylerView.setVisibility(View.GONE);
                        break;
                    case ERROR:
                        mProgress.setVisibility(View.GONE);
                        mRecylerView.setVisibility(View.GONE);
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }
}
