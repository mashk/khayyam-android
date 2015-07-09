package ir.coderz.khayyam_android.injector.module;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import ir.coderz.khayyam_android.injector.PreActivity;
import ir.coderz.khayyam_android.model.Repository;
import ir.coderz.khayyam_android.model.local.FileOperator;
import ir.coderz.khayyam_android.model.local.LocalRepository;
import ir.coderz.khayyam_android.model.network.RestRepository;

/**
 * Created by sajad on 7/5/15.
 */
@Module
public class RepoModule {

    private String editor;
    private FileOperator fileOperator;

    public RepoModule(String editor,FileOperator fileOperator) {
        this.editor = editor;
        this.fileOperator = fileOperator;
    }

    @PreActivity
    @Provides
    Repository repository() {
//        return new RestRepository();
        return new LocalRepository(fileOperator);
    }

    @PreActivity
    @Provides
    String editor() {
        return editor;
    }
}
