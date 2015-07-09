package ir.coderz.khayyam.model.network.exceptions;

public class NetworkUnknownHostException extends Exception{
    @Override
    public String getMessage() {
        return "Network Unknown Host Exception";
    }
}
