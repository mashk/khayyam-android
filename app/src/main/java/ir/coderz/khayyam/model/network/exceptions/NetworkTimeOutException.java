package ir.coderz.khayyam.model.network.exceptions;

public class NetworkTimeOutException extends Exception {
    @Override
    public String getMessage() {
        return "Network time out";
    }
}
