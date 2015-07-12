package ir.coderz.khayyam.injector;

import ir.coderz.khayyam.injector.component.RepoComponent;

/**
 * Created by sajad on 7/12/15.
 */
public interface HasRepoComponent<C extends RepoComponent> {
    C getComponent();
}
