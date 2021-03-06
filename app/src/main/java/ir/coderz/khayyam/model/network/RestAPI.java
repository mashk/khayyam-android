package ir.coderz.khayyam.model.network;

import java.util.List;

import ir.coderz.khayyam.model.entities.information.Info;
import ir.coderz.khayyam.model.entities.poem.Poem;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by sajad on 6/30/15.
 */
public interface RestAPI {
    String END_POINT = "http://mashk.github.io/khayyam-api/v0";
    String INFO = "/info.json";
    String POEMS = "/poems.json";

    @GET(INFO)
    Observable<Info> getInfo();

    @GET("/{editor}/" + POEMS)
    Observable<List<Poem>> getPoems(@Path("editor") String editor);

}
