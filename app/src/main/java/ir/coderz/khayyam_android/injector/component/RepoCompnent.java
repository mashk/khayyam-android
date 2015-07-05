package ir.coderz.khayyam_android.injector.component;

import dagger.Component;
import ir.coderz.khayyam_android.injector.PreActivity;
import ir.coderz.khayyam_android.injector.module.RepoModule;
import ir.coderz.khayyam_android.model.Repository;
import ir.coderz.khayyam_android.model.network.RestRepository;
import ir.coderz.khayyam_android.view.activities.MainActivity;

/**
 * Created by sajad on 7/5/15.
 */
@PreActivity
@Component(modules = RepoModule.class)
public interface RepoCompnent {
    void inject(MainActivity mainActivity);

    Repository REPOSITORY();

    String EDITOR();
}
