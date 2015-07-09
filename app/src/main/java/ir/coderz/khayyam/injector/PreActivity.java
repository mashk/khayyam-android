package ir.coderz.khayyam.injector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by sajad on 7/4/15.
 */
@Scope
@Retention(RUNTIME)
public @interface PreActivity {
}
