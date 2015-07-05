package ir.coderz.khayyam_android.model.network;

import java.util.List;

import ir.coderz.khayyam_android.BuildConfig;
import ir.coderz.khayyam_android.model.Repository;
import ir.coderz.khayyam_android.model.entities.information.Info;
import ir.coderz.khayyam_android.model.entities.poem.Poem;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by sajad on 6/30/15.
 */
public class RestRepository implements Repository {
    RestAPI restAPI;

    public RestRepository() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RestAPI.END_POINT)
                .build();
        if (BuildConfig.DEBUG)
            restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        restAPI = restAdapter.create(RestAPI.class);
    }

    @Override
    public Observable<Info> getInfo() {
        return restAPI.getInfo();
    }

    @Override
    public Observable<List<Poem>> getPoems(String editor) {
        return restAPI.getPoems(editor);
    }
}
