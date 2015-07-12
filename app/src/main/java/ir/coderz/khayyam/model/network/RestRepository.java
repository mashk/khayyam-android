package ir.coderz.khayyam.model.network;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import ir.coderz.khayyam.BuildConfig;
import ir.coderz.khayyam.model.Repository;
import ir.coderz.khayyam.model.entities.information.Info;
import ir.coderz.khayyam.model.entities.poem.Poem;
import ir.coderz.khayyam.model.network.exceptions.NetworkErrorException;
import ir.coderz.khayyam.model.network.exceptions.NetworkTimeOutException;
import ir.coderz.khayyam.model.network.exceptions.NetworkUnknownHostException;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by sajad on 6/30/15.
 */
public class RestRepository implements Repository {
    RestAPI restAPI;

    public RestRepository() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RestAPI.END_POINT)
                .setErrorHandler(new RetrofitErrorHandler())
                .build();
        if (BuildConfig.DEBUG)
            restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        restAPI = restAdapter.create(RestAPI.class);
    }

    @Override
    public Observable<Info> getInfo() {
        return restAPI.getInfo();
    }

    @Override
    public Observable<List<Poem>> getPoems(String editor) {
        return restAPI.getPoems(editor);
    }

    public class RetrofitErrorHandler implements retrofit.ErrorHandler {

        @Override
        public Throwable handleError(retrofit.RetrofitError cause) {

            if (cause.getKind() == retrofit.RetrofitError.Kind.NETWORK) {

                if (cause.getCause() instanceof SocketTimeoutException)
                    return new NetworkTimeOutException();

                else if (cause.getCause() instanceof UnknownHostException)
                    return new NetworkUnknownHostException();

                else if (cause.getCause() instanceof ConnectException)
                    return cause.getCause();

            } else {

                return new NetworkErrorException();
            }

            return new Exception();
        }

    }
}
