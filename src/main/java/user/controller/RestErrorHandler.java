package user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import user.exception.UserNotFoundException;

@ControllerAdvice
public class RestErrorHandler {

	 private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);
	 
	    @ExceptionHandler(UserNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public void handleTodoNotFoundException(UserNotFoundException ex) {
	        LOGGER.debug("handling 404 error on a User entry");
	    }
}
