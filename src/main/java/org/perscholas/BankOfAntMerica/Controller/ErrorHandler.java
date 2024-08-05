package org.perscholas.BankOfAntMerica.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.perscholas.BankOfAntMerica.Security.AuthenticatedUserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@Controller
@ControllerAdvice //is used to define this class as an Exception handler
public class ErrorHandler {
    private final AuthenticatedUserUtils authenticatedUserUtils;

    public ErrorHandler(AuthenticatedUserUtils authenticatedUserUtils) {
        this.authenticatedUserUtils = authenticatedUserUtils;
    }

   /* @ExceptionHandler(NoResourceFoundException.class)
    @RequestMapping(value = {"/error/404", "/404"})
    public ModelAndView error404(Exception e, HttpServletRequest request) {
        ModelAndView response = new ModelAndView("error/404");
        log.debug("!!!!!!!!!!!!!!!!!! IN ERROR CONTROLLER : 404 NOT FOUND : {} {}", request.getMethod(), request.getRequestURI());
        response.setStatus(HttpStatus.NOT_FOUND);

        return response;
    }*/

 // this is not an expectation for the case study - this is just extra to understand a little about how error controller works
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(HttpServletRequest request, Exception exception) {
        log.warn("Error page exception : {}", exception.getMessage(),exception);

        ModelAndView response = null;
        response = new ModelAndView("error/500");
        if (exception.getClass() == NoResourceFoundException.class) {
            response.setViewName("error/404");
        }

        if (authenticatedUserUtils.isUserInRole("ADMIN")) {
            response.addObject("requestUrl", request.getRequestURI());
            response.addObject("message", exception.getMessage());

            String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(exception));
            response.addObject("stackTrace", stackTrace);
            if (exception.getCause() != null) {
                response.addObject("rootCause", ExceptionUtils.getRootCause(exception));

                String rootTrace = getHTMLStackTrace(ExceptionUtils.getRootCauseStackTrace(exception));
                response.addObject("rootTrace", rootTrace);
            }
        }

        return response;
    }


    private String getHTMLStackTrace(String[] stack) {
        StringBuffer result = new StringBuffer();
        for (String frame : stack) {
            // Change this to be your package name
            if (frame.contains("org.perscholas.BankOfAntMerica")) {
                result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
            } else if (frame.contains("Caused by:")) {
                result.append("Caused By:<br>");
            }
        }

        return result.toString();
    }
}
