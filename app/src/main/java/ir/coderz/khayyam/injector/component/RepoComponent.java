package ir.coderz.khayyam.injector.component;

import dagger.Component;
import ir.coderz.khayyam.injector.PreActivity;
import ir.coderz.khayyam.injector.module.RepoModule;
import ir.coderz.khayyam.model.Repository;
import ir.coderz.khayyam.model.local.LocalRepository;
import ir.coderz.khayyam.model.network.RestRepository;
import ir.coderz.khayyam.view.activities.MainActivity;
import ir.coderz.khayyam.view.activities.PoemListActivity;

/**
 * Created by sajad on 7/5/15.
 */
@PreActivity
@Component(dependencies = AppComponent.class, modules = RepoModule.class)
public interface RepoComponent {
    void injectMain(MainActivity mainActivity);

    void injectPoemList(PoemListActivity poemListActivity);

    void inject(LocalRepository localRepository);

    void inject(RestRepository restRepository);

    Repository REPOSITORY();

    String EDITOR();

}
