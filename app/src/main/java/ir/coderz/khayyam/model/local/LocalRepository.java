package ir.coderz.khayyam.model.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import ir.coderz.khayyam.model.Repository;
import ir.coderz.khayyam.model.entities.information.Info;
import ir.coderz.khayyam.model.entities.poem.Poem;
import rx.Observable;

/**
 * Created by sajad on 7/9/15.
 */
public class LocalRepository implements Repository {

    FileOperator fileOperator;

    @Inject
    public LocalRepository(FileOperator fileOperator) {
        this.fileOperator = fileOperator;
    }


    @Override
    public Observable<Info> getInfo() {
        Observable<Info> infoObservable = Observable.create(
                subscriber -> {
                    subscriber.onStart();
                    String jsonInfo = null;
                    try {
                        jsonInfo = fileOperator.load("info");
                        Info info = new Gson().fromJson(jsonInfo, Info.class);
                        subscriber.onNext(info);
                        subscriber.onCompleted();
                    } catch (IOException e) {
                        e.printStackTrace();
                        subscriber.onError(e);
                    }
                }
        );

//        Observable<Info> infoObservable = Observable.just(info);
        return infoObservable;
    }

    @Override
    public Observable<List<Poem>> getPoems(String editor) {
        Observable<List<Poem>> poemsObserver = Observable.create(
                subscriber -> {
                    try {
                        subscriber.onStart();
                        String jsonPoems = null;
                        jsonPoems = fileOperator.load(editor);
                        Type listType = new TypeToken<List<Poem>>() {
                        }.getType();
                        List<Poem> poems = new Gson().fromJson(jsonPoems, listType);
                        subscriber.onNext(poems);
                        subscriber.onCompleted();
                    } catch (IOException e) {
                        e.printStackTrace();
                        subscriber.onError(e);
                    }
                }
        );
//        Observable<List<Poem>> poemsObserver = Observable.just(poems);
        return poemsObserver;
    }
}
