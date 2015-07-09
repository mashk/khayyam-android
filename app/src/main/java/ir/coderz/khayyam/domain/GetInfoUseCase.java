package ir.coderz.khayyam.domain;

import com.google.gson.Gson;

import javax.inject.Inject;

import ir.coderz.khayyam.model.Repository;
import ir.coderz.khayyam.model.entities.information.Info;
import ir.coderz.khayyam.model.local.FileOperator;
import ir.coderz.khayyam.model.network.RestRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sajad on 7/2/15.
 */
public class GetInfoUseCase implements UseCase<Info> {

    private Repository repository;
    private FileOperator fileOperator;
    Info info;

    @Inject
    public GetInfoUseCase(Repository repository,FileOperator fileOperator) {
        this.repository = repository;
        this.fileOperator = fileOperator;
    }

    @Override
    public Observable<Info> execute() {
        return repository.getInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(
                        info -> this.info = info
                ).doOnCompleted
                        (
                                () -> {
                                    if(repository instanceof RestRepository) {
                                        fileOperator.save("info", new Gson().toJson(info));
                                    }
                                }
                        );
    }
}
