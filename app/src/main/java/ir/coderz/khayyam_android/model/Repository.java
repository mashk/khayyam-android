package ir.coderz.khayyam_android.model;

import java.util.List;

import ir.coderz.khayyam_android.model.entities.information.Info;
import ir.coderz.khayyam_android.model.entities.poem.Poem;
import rx.Observable;

/**
 * Created by sajad on 6/30/15.
 */
public interface Repository {

    Observable<Info> getInfo();

    Observable<List<Poem>> getPoems(String editor);
}
