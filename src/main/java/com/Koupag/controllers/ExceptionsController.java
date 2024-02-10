package com.Koupag.controllers;

import com.Koupag.execptions.*;
import com.Koupag.execptions.UnknownError;
import com.Koupag.execptions.errorResponse.UnknownErrorResponse;
import com.Koupag.execptions.errorResponse.ErrorResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.UnknownHostException;
import java.util.Date;

@RestControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(UserAlreadyRegistered.class)
    public ResponseEntity<ErrorResponse> UserAlreadyRegistered(final UserAlreadyRegistered ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFound(final UserNotFoundException ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DonorNotFound.class)
    public ResponseEntity<ErrorResponse> DonorNotFound(final DonorNotFound ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VolunteerNotFound.class)
    public ResponseEntity<ErrorResponse> VolunteerNotFound(final VolunteerNotFound ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecipientNotFound.class)
    public ResponseEntity<ErrorResponse> RecipientNotFound(final RecipientNotFound ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OldPasswordDoNotMatch.class)
    public ResponseEntity<ErrorResponse> OldPasswordDoNotMatch(final OldPasswordDoNotMatch ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DonationRequestNotFound.class)
    public ResponseEntity<ErrorResponse> DonationNotFound(final DonationRequestNotFound ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SurplusMaterialNotFound.class)
    public ResponseEntity<ErrorResponse> SurplusMaterialNotFound(final SurplusMaterialNotFound ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRoleNotFound.class)
    public ResponseEntity<ErrorResponse> UserRoleNotFound(final UserRoleNotFound ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchUserExist.class)
    public ResponseEntity<ErrorResponse> NoSuchUserExist(final NoSuchUserExist ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyVerified.class)
    public ResponseEntity<ErrorResponse> AlreadyVerified(final AlreadyVerified ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DonationAlreadyExists.class)
    public ResponseEntity<ErrorResponse> DonationAlreadyExists(final DonationAlreadyExists ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotVerified.class)
    public ResponseEntity<ErrorResponse> NotVerified(final NotVerified ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.FORBIDDEN.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(NotModified.class)
    public ResponseEntity<ErrorResponse> NotModified(final NotModified ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_MODIFIED.value(),ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
    }
    @ExceptionHandler(NullPointerExceptionWrapper.class)
    public ResponseEntity<ErrorResponse> NullPointerExceptionWrapper(final NullPointerExceptionWrapper ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementExceptionWrapper.class)
    public ResponseEntity<ErrorResponse> NoSuchElementExceptionWrapper(final NoSuchElementExceptionWrapper ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),  ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnknownError.class)
    public ResponseEntity<UnknownErrorResponse> UnknownError(final UnknownError ex, WebRequest webRequest){
        UnknownErrorResponse response = new UnknownErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), new Date(),ex.getCause());
        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ErrorResponse>messagingException(final MessagingException ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.REQUEST_TIMEOUT.value(),  ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.REQUEST_TIMEOUT);
    }

    //UnknownHostException
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ErrorResponse>messagingException(final UnknownHostException ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.REQUEST_TIMEOUT.value(),  ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(WrongCredsException.class)
    public ResponseEntity<ErrorResponse>wrongCreds(final WrongCredsException ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),  ex.getMessage(), new Date());
        return  new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
