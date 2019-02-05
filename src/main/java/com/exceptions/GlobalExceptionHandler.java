package com.exceptions;

import com.model.CustomResponseObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ryandesmond on 7/30/18.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public @ResponseBody CustomResponseObject handleError(HttpServletRequest req, Exception ex) {

        CustomResponseObject response = new CustomResponseObject();
        ExceptionPojo error = new ExceptionPojo(ex.getMessage());
        response.setError(error);
        response.setStatusCode(500);

        return response;
    }

    @ExceptionHandler(CustomDatabaseException.class)
    public @ResponseBody CustomResponseObject handleDatabaseError(HttpServletRequest req, CustomDatabaseException ex) {

        CustomResponseObject response = new CustomResponseObject();
        ExceptionPojo error = new ExceptionPojo(ex.getMessage());
        response.setError(error);
        response.setStatusCode(500);

        return response;
    }

}
