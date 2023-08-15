package vn.LeThanhTuan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.LeThanhTuan.respone.ResponeObject;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponeObject> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ResponeObject apiResponse = new ResponeObject(HttpStatus.NOT_FOUND.name(), message, null);

        return new ResponseEntity<ResponeObject>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
