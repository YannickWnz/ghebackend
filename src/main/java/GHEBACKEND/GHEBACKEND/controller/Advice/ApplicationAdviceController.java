package GHEBACKEND.GHEBACKEND.controller.Advice;

import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.nio.file.AccessDeniedException;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
@RestControllerAdvice
public class ApplicationAdviceController {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(
        value = {RuntimeException.class,AccessDeniedException.class})
    public @ResponseBody ProblemDetail handleRuntimeException(final Exception exception){
        return ProblemDetail.forStatusAndDetail(BAD_REQUEST,"Vous ne disposez pas des droits");
    }


    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(value = 
        BadCredentialsException.class
    )
    public @ResponseBody ProblemDetail handleException(final BadCredentialsException exception){
         ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            UNAUTHORIZED,
            "Identifiants invalides");
        problemDetail.setProperty("erreur","nous n'avons pas pu vous authentifiés");
        return problemDetail;
    }

    
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(value = 
        {SignatureException.class, MalformedJwtException.class}
    )
    public @ResponseBody ProblemDetail handleException(final Exception exception){
        log.info(exception.getMessage(),exception);
        return ProblemDetail.forStatusAndDetail(
            UNAUTHORIZED,
            "Token invalide");
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody ProblemDetail exceptionHandler(Exception exception){
        return  ProblemDetail.forStatusAndDetail(FORBIDDEN, exception.getMessage());
    }

}
