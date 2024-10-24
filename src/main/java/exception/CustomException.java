package exception;

public class CustomException extends RuntimeException{

    private final String runtimeValue;
    private final ErrorCode errorCode;

    public CustomException(ErrorCode err){
        this(err, "customException.");
    }

    public CustomException(ErrorCode err, String runtimeValue){
        this.errorCode = err;
        this.runtimeValue = runtimeValue;
    }

}
