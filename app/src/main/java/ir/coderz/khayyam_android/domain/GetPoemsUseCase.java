package ir.coderz.khayyam_android.domain;

import java.util.List;

import javax.inject.Inject;

import ir.coderz.khayyam_android.model.Repository;
import ir.coderz.khayyam_android.model.entities.poem.Poem;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sajad on 7/2/15.
 */
public class GetPoemsUseCase implements UseCase<List<Poem>> {

    private Repository repository;
    private String editor;
    private List<Poem> poems;

    @Inject
    public GetPoemsUseCase(Repository repository, String editor) {
        this.repository = repository;
        this.editor = editor;
    }

    @Override
    public Observable<List<Poem>> execute() {
        return repository.getPoems(editor)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(
                        poems -> this.poems = poems
                );

    }
}
