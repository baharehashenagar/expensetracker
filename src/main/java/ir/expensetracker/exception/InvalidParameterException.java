package ir.expensetracker.exception;

public class InvalidParameterException extends RuntimeException{

    private String message;
    public InvalidParameterException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
