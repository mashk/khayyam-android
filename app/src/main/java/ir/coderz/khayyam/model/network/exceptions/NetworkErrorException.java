package ir.coderz.khayyam.model.network.exceptions;

public class NetworkErrorException extends Exception {
    @Override
    public String getMessage() {
        return "Network Error Exception";
    }
}
