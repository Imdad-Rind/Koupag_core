package com.Koupag.controllers;

import com.Koupag.execptions.*;
import com.Koupag.execptions.UnknownError;
import com.Koupag.execptions.errorResponse.UnknownErrorResponse;
import com.Koupag.execptions.errorResponse.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(UserAlreadyRegistered.class)
    public ResponseEntity<errorResponse> UserAlreadyRegistered(final UserAlreadyRegistered ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<errorResponse> UserNotFound(final UserNotFoundException ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DonorNotFound.class)
    public ResponseEntity<errorResponse> DonorNotFound(final DonorNotFound ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VolunteerNotFound.class)
    public ResponseEntity<errorResponse> VolunteerNotFound(final VolunteerNotFound ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecipientNotFound.class)
    public ResponseEntity<errorResponse> RecipientNotFound(final RecipientNotFound ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DonationRequestNotFound.class)
    public ResponseEntity<errorResponse> DonationNotFound(final DonationRequestNotFound ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SurplusMaterialNotFound.class)
    public ResponseEntity<errorResponse> SurplusMaterialNotFound(final SurplusMaterialNotFound ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRoleNotFound.class)
    public ResponseEntity<errorResponse> UserRoleNotFound(final UserRoleNotFound ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchUserExist.class)
    public ResponseEntity<errorResponse> NoSuchUserExist(final NoSuchUserExist ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyVerified.class)
    public ResponseEntity<errorResponse> AlreadyVerified(final AlreadyVerified ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.CONFLICT.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DonationAlreadyExists.class)
    public ResponseEntity<errorResponse> DonationAlreadyExists(final DonationAlreadyExists ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.CONFLICT.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotVerified.class)
    public ResponseEntity<errorResponse> NotVerified(final NotVerified ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.FORBIDDEN.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(NotModified.class)
    public ResponseEntity<errorResponse> NotModified(final NotModified ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_MODIFIED.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
    }
    @ExceptionHandler(NullPointerExceptionWrapper.class)
    public ResponseEntity<errorResponse> NullPointerExceptionWrapper(final NullPointerExceptionWrapper ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementExceptionWrapper.class)
    public ResponseEntity<errorResponse> NoSuchElementExceptionWrapper(final NoSuchElementExceptionWrapper ex, WebRequest webRequest){
        errorResponse response = new errorResponse(HttpStatus.NOT_FOUND.value(),  ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnknownError.class)
    public ResponseEntity<UnknownErrorResponse> UnknownError(final UnknownError ex, WebRequest webRequest){
        UnknownErrorResponse response = new UnknownErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), new Date(),ex.getCause());
        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
