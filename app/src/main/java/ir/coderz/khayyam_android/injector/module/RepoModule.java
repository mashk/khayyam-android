package ir.coderz.khayyam_android.injector.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.coderz.khayyam_android.injector.PreActivity;
import ir.coderz.khayyam_android.model.Repository;
import ir.coderz.khayyam_android.model.network.RestRepository;

/**
 * Created by sajad on 7/5/15.
 */
@Module
public class RepoModule {

    private String editor;

    public RepoModule(String editor) {
        this.editor = editor;
    }

    @PreActivity
    @Provides
    Repository repository() {
        return new RestRepository();
    }

    @PreActivity
    @Provides
    String editor() {
        return editor;
    }
}
