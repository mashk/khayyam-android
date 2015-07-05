package ir.coderz.khayyam_android.domain;

import javax.inject.Inject;

import ir.coderz.khayyam_android.model.Repository;
import ir.coderz.khayyam_android.model.entities.information.Info;
import ir.coderz.khayyam_android.model.network.RestRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sajad on 7/2/15.
 */
public class GetInfoUseCase implements UseCase<Info> {

    private Repository repository;
    Info info;

    @Inject
    public GetInfoUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Info> execute() {
        return repository.getInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(
                        info -> this.info = info
                );
    }
}
