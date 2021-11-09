package shatalov.finalProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import shatalov.finalProject.dto.ErrorDto;
import shatalov.finalProject.exception.EmptyZipException;
import shatalov.finalProject.exception.TooManyFilesException;
import shatalov.finalProject.exception.TooShortTxtException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleRuntimeException(RuntimeException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(EmptyZipException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleEmptyZipException(EmptyZipException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(TooManyFilesException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleTooManyFilesException(TooManyFilesException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(TooShortTxtException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleTooShortTxtException(TooShortTxtException e) {
        return new ErrorDto(e.getMessage());
    }
}
