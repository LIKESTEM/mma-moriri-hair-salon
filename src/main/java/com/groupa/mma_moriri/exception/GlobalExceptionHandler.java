package com.groupa.mma_moriri.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllExceptions(Exception ex) {
        logger.error("Exception: ", ex);
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject("errorMessage", "An unexpected error occurred: " + ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleNullPointerException(NullPointerException ex) {
        logger.error("NullPointerException: ", ex);
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject("errorMessage", "A null pointer exception occurred: " + ex.getMessage());
        return modelAndView;
    }
}
