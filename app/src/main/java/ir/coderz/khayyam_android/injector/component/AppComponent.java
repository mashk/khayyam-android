package ir.coderz.khayyam_android.injector.component;

import android.content.Context;

import dagger.Component;
import ir.coderz.khayyam_android.injector.module.AppModule;

/**
 * Created by sajad on 7/9/15.
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    Context PROVIDE_CONTEXT();
}
