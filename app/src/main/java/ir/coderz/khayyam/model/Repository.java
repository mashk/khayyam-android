package ir.coderz.khayyam.model;

import java.util.List;

import ir.coderz.khayyam.model.entities.information.Info;
import ir.coderz.khayyam.model.entities.poem.Poem;
import rx.Observable;

/**
 * Created by sajad on 6/30/15.
 */
public interface Repository {

    Observable<Info> getInfo();

    Observable<List<Poem>> getPoems(String editor);
}
