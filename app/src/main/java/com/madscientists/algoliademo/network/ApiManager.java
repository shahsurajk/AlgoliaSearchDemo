package com.madscientists.algoliademo.network;

import com.madscientists.algoliademo.model.AlgoliaSearchResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by madscientist on 7/10/17.
 */

public interface ApiManager {

    @GET("search")
    Observable<AlgoliaSearchResult> getSearchResultsForQuery(@Query("query")String toSearch, @Query("page")int forPage);
}
