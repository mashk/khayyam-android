package ir.coderz.khayyam_android.injector.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ir.coderz.khayyam_android.KhayyamApp;

/**
 * Created by sajad on 7/9/15.
 */
@Module
public class AppModule {
    KhayyamApp khayyamApp;

    public AppModule(KhayyamApp khayyamApp) {
        this.khayyamApp = khayyamApp;
    }

    @Provides
    Context contextProvider() {
        return khayyamApp.getApplicationContext();
    }
}
