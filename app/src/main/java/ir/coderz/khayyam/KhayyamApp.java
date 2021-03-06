package ir.coderz.khayyam;

import android.app.Application;

import ir.coderz.khayyam.injector.component.AppComponent;
import ir.coderz.khayyam.injector.component.DaggerAppComponent;
import ir.coderz.khayyam.injector.module.AppModule;

/**
 * Created by sajad on 7/9/15.
 */
public class KhayyamApp extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .build();
    }


    public AppComponent getComponent() {
        return appComponent;
    }
}
