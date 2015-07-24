package ir.coderz.khayyam.injector.module;

import android.app.Activity;

import java.util.Calendar;

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

        try {
            long savedMil = Long.parseLong(preference.getPreference(editor));
            long nowMil = Calendar.getInstance().getTimeInMillis();
            long diff = nowMil - savedMil;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays > 30) {
                return new RestRepository();
            } else {
                return new LocalRepository(activity);
            }

        } catch (Exception e) {
            return new RestRepository();
        }

    }

    @PreActivity
    @Provides
    String editor() {
        return editor;
    }
}
