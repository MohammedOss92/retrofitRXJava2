package com.alyndroid.facebookv2.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alyndroid.facebookv2.data.PostsClient;
import com.alyndroid.facebookv2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();

    public void getPosts() {
//        PostsClient.getINSTANCE().getPosts().enqueue(new Callback<List<PostModel>>() {
//            @Override
//            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
//                postsMutableLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<PostModel>> call, Throwable t) {
//                posts.setValue("errr");
//            }
//        });

        Observable observable = PostsClient.getINSTANCE().getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());

        Observer<List<PostModel>> observer = new Observer<List<PostModel>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PostModel> value) {
                postsMutableLiveData.setValue(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e);
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);

    }
}
