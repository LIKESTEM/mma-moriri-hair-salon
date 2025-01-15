package com.groupa.mma_moriri.exception;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ModelAndView modelAndView = new ModelAndView("error_page");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == 404) {
                modelAndView.addObject("errorMessage", "Page not found");
            } else if (statusCode == 500) {
                modelAndView.addObject("errorMessage", "Internal server error occurred");
            }
            else if (statusCode == 400) {
                modelAndView.addObject("errorMessage", "Bad request");
            }
            else if (statusCode == 403) {
                modelAndView.addObject("errorMessage", "Access Denied");
            }
            else if (statusCode == 401) {
                modelAndView.addObject("errorMessage", "Unauthorized");
            }
            else {
                Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
                if (throwable instanceof NullPointerException) {
                    modelAndView.addObject("errorMessage", "A null pointer exception occurred");
                } else{
                modelAndView.addObject("errorMessage", "Unexpected error occurred");
             }
            }
        } else {
            Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
            if (throwable instanceof NullPointerException) {
                modelAndView.addObject("errorMessage", "A null pointer exception occurred");
            } else{
            modelAndView.addObject("errorMessage", "Unexpected error occurred");
        }}

        return modelAndView;
    }
}
