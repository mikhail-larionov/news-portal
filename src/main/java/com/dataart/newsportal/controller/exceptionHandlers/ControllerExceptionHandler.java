package com.dataart.newsportal.controller.exceptionHandlers;

import com.dataart.newsportal.exception.IllegalContentException;
import com.dataart.newsportal.exception.IllegalFileExtension;
import com.dataart.newsportal.exception.IllegalHeadingNameException;
import com.dataart.newsportal.exception.IllegalNumberOfFilesException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleRuntimeException(RuntimeException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(IllegalNumberOfFilesException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorDto handleIllegalZipFileSizeException(IllegalNumberOfFilesException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(IllegalFileExtension.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorDto handleIllegalFileExtension(IllegalFileExtension e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(IllegalContentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorDto handleIllegalContentException(IllegalContentException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(IllegalHeadingNameException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorDto handleIllegalHeadingNameException(IllegalHeadingNameException e) {
        return new ErrorDto(e.getMessage());
    }


    @Data
    @AllArgsConstructor
    public static class ErrorDto {
        String message;
    }
}

