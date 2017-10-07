package com.madscientists.algoliademo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.madscientists.algoliademo.R;
import com.madscientists.algoliademo.adapter.Adapter_SearchResults;
import com.madscientists.algoliademo.model.AlgoliaSearchResult;
import com.madscientists.algoliademo.model.Hits;
import com.madscientists.algoliademo.network.ApiManager;
import com.madscientists.algoliademo.network.RetrofitManager;
import com.madscientists.algoliademo.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by madscientist on 7/10/17.
 */

public class Fragment_Home extends Fragment {

    @BindView(R.id.searchEditText)
    TextInputEditText searchEditText;
    @BindView(R.id.searchProgress)
    ProgressBar searchProgress;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private Adapter_SearchResults adapter_searchResults;
    private List<Hits>hitsList = new ArrayList<>();
    private int currentPageCount =-1;
    private LinearLayoutManager layoutManager;
    private boolean loadingMore = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initSeachET();
    }

    private void initSeachET() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter e) throws Exception {
                searchEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().trim().length()!=0){
                            e.onNext(charSequence.toString().trim());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        }).debounce(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        searchString = (String) o;
                        Utils.hideKeyboard(getActivity());
                        removePagination();
                        addPagination();
                        perfromSearch(false);
                    }
                });
    }

    private void removePagination() {
        recyclerView.clearOnScrollListeners();
    }

    private void handleProgressView(boolean toShow){
        if (toShow){
            searchProgress.setVisibility(View.VISIBLE);
        }else{
            searchProgress.setVisibility(View.GONE);
        }
    }
    private void perfromSearch(final boolean isPaginatedList) {
        handleProgressView(true);
        if (isAdded()) {
            RetrofitManager.getInstance().create(ApiManager.class).getSearchResultsForQuery(searchString, currentPageCount + 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<AlgoliaSearchResult>() {
                        @Override
                        public void accept(AlgoliaSearchResult algoliaSearchResult) throws Exception {
                            if (isAdded()) {
                                handleProgressView(false);
                                loadingMore = false;
                                currentPageCount = algoliaSearchResult.getPage();
                                if (!isPaginatedList) {
                                    hitsList.clear();
                                }
                                hitsList.addAll(algoliaSearchResult.getHits());
                                adapter_searchResults.notifyDataSetChanged();
                            }
                        }
                    });
        }
    }
    private void addPagination(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int currentFirstVisibleItem = 0;
            int currentVisibleItemCount = 0;
            int totalItemCount = 0;
            int currentScrollState = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                this.currentScrollState = newState;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentVisibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                //  pastVisiblesItems = layoutManage
                currentFirstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                //    Log.e(TAG, "scroll tp:: dy: " + currentFirstVisibleItem + "  scroll state:: "+currentScrollState);
                this.isScrollCompleted();

            }

            //&& this.currentScrollState == list.SCROLL_STATE_SETTLING
            private void isScrollCompleted() {
                if (this.currentVisibleItemCount > 0
                        && this.totalItemCount == (currentFirstVisibleItem + currentVisibleItemCount)) {
                    /*** In this way I detect if there's been a scroll which has completed ***/
                    /*** do the work for load more date! ***/
                    //  Log.e(TAG, "On SCroll Called" + " entry is :: " + entry);
                    if (!loadingMore) {
                        loadingMore = true;
                        if (isAdded()) {
                            perfromSearch(true);
                        }
                    }
                }
            }
        });
    }
    private String searchString="";
    private void initRecyclerView(){
        adapter_searchResults = new Adapter_SearchResults(getActivity(),hitsList);
        recyclerView.setAdapter(adapter_searchResults);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
