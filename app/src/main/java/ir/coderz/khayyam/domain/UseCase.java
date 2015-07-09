package ir.coderz.khayyam.domain;

import rx.Observable;

/**
 * Created by sajad on 7/2/15.
 */
public interface UseCase<T> {
    Observable<T> execute();
}
