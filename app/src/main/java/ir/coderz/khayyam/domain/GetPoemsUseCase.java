package ir.coderz.khayyam.domain;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import ir.coderz.khayyam.model.Preference;
import ir.coderz.khayyam.model.Repository;
import ir.coderz.khayyam.model.entities.poem.Poem;
import ir.coderz.khayyam.model.local.FileOperator;
import ir.coderz.khayyam.model.network.RestRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sajad on 7/2/15.
 */
public class GetPoemsUseCase implements UseCase<List<Poem>> {

    private Repository repository;
    private FileOperator fileOperator;
    private Preference preference;
    private String editor;
    private List<Poem> poems;

    @Inject
    public GetPoemsUseCase(Repository repository, FileOperator fileOperator, Preference preference, String editor) {
        this.repository = repository;
        this.fileOperator = fileOperator;
        this.preference = preference;
        this.editor = editor;
    }

    @Override
    public Observable<List<Poem>> execute() {
        return repository.getPoems(editor)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(
                        poems -> this.poems = poems
                ).doOnCompleted
                        (
                                () -> {
                                    if (repository instanceof RestRepository) {
                                        fileOperator.save(editor, new Gson().toJson(poems));
                                        Calendar calendar = Calendar.getInstance();
                                        preference.writeToPreference(editor, calendar.getTimeInMillis() + "");
                                    }
                                }
                        );

    }
}
