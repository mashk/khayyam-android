package ir.coderz.khayyam_android.model.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import ir.coderz.khayyam_android.model.Repository;
import ir.coderz.khayyam_android.model.entities.information.Info;
import ir.coderz.khayyam_android.model.entities.poem.Poem;
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
        String jsonInfo = fileOperator.load("info");
        Info info = new Gson().fromJson(jsonInfo, Info.class);
        Observable<Info> infoObservable = Observable.just(info);
        return infoObservable;
    }

    @Override
    public Observable<List<Poem>> getPoems(String editor) {
        String jsonPoems = fileOperator.load(editor);
        Type listType = new TypeToken<List<Poem>>() {
        }.getType();
        List<Poem> poems = new Gson().fromJson(jsonPoems, listType);
        Observable<List<Poem>> poemsObserver = Observable.just(poems);
        return poemsObserver;
    }
}
