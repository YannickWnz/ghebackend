package GHEBACKEND.GHEBACKEND.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Builder
@Data
public class CustomException{
    private String message;
    private HttpStatus status;
    private int code;

    public CustomException(String message, HttpStatus status, int code){
        // super(message);
        this.message = message;
        this.status =status;
        this.code = code;
    }

    public CustomException(String message, HttpStatus status){
        // super(message);
        this.message = message;
        this.status =status;
        this.code = status.value();
    }
}
