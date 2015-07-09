package ir.coderz.khayyam.injector.module;

import dagger.Module;
import dagger.Provides;
import ir.coderz.khayyam.injector.PreActivity;
import ir.coderz.khayyam.model.Repository;
import ir.coderz.khayyam.model.local.FileOperator;
import ir.coderz.khayyam.model.local.LocalRepository;

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
