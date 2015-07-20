package ir.coderz.khayyam.injector.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import ir.coderz.khayyam.injector.PreActivity;
import ir.coderz.khayyam.model.Preference;
import ir.coderz.khayyam.model.Repository;
import ir.coderz.khayyam.model.local.LocalRepository;
import ir.coderz.khayyam.model.network.RestRepository;

/**
 * Created by sajad on 7/5/15.
 */
@Module
public class RepoModule {

    private String editor;
    private Activity activity;


    public RepoModule(String editor, Activity actvity) {
        this.editor = editor;
        this.activity = actvity;

    }

//    @PreActivity
    @Provides
    Repository repository() {
        Preference preference = new Preference(activity);
        if (preference.getPreference(editor).equals("true")) {
            return new LocalRepository(activity);
        } else {
            return new RestRepository();
        }
    }

    @PreActivity
    @Provides
    String editor() {
        return editor;
    }
}
