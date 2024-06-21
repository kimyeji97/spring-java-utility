package com.yjkim.spring.java.utility.http;

import com.yjkim.spring.java.utility.exception.PlatformRuntimeException;
import com.yjkim.spring.java.utility.exception.http.PlatformBadRequestException;
import com.yjkim.spring.java.utility.exception.http.PlatformNotFoundException;
import com.yjkim.spring.java.utility.exception.http.PlatformServiceUnavailableException;
import com.yjkim.spring.java.utility.exception.http.PlatformUnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

@Slf4j
@Service
public class RestClientBase {
    
    @Autowired
    protected RestTemplate restTemplate;
    
    static final public String getHttpErrorMessage(RestClientException e) {
        String message = e.getMessage();
        if (e instanceof RestClientResponseException) {
            RestClientResponseException ex = (RestClientResponseException) e;
            if (StringUtils.isNotBlank(ex.getResponseBodyAsString())) {
                message = ex.getResponseBodyAsString();
            }
        }
        return message;
    }
    
    public void handleException(HttpStatus httpStatus , Exception ex) {
        String errorMessage = null;
        if (ex instanceof RestClientResponseException) {
            errorMessage = getHttpErrorMessage((RestClientResponseException) ex);
        } else {
            errorMessage = ex.getMessage();
        }
        
        if (httpStatus == null) {
            throw new PlatformServiceUnavailableException(errorMessage , ex);
        } else {
            switch (httpStatus) {
                case NOT_FOUND:
                    throw new PlatformNotFoundException(errorMessage , ex);
                case BAD_REQUEST:
                    throw new PlatformBadRequestException(errorMessage , ex);
                    // case CONFLICT:
                    // exception = new MacrogenConflictException(errorMessage);
                    // break;
                    // case PRECONDITION_FAILED:
                    // exception = new MacrogenPreconditionFailedException(errorMessage);
                    // break;
                case UNAUTHORIZED:
                    throw new PlatformUnauthorizedException(errorMessage , ex);
                    // case INTERNAL_SERVER_ERROR:
                    // exception = new PlatformInternalServerErrorException(errorMessage);
                    // break;
                default:
                    throw new PlatformRuntimeException(errorMessage , ex);
            }
        }
    }
    
    protected HttpHeaders createHeader() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.clear();
        // String txId = MDC.get(MacrogenConstants.IPTV_TX_ID);
        // if (txId == null)
        // {
        // txId = UuidGenerator.get();
        // }
        //
        // requestHeaders.setBasicAuth(MacrogenConstants.SYSTEM_CODE, cpnSystemPassword);
        // requestHeaders.add(MacrogenConstants.IPTV_TX_ID, txId);
        // requestHeaders.setBasicAuth(MacrogenConstants.SYSTEM_CODE, this.cpnSystemPassword);
        requestHeaders.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return requestHeaders;
    }
    
    
    public <T, I> ResponseEntity<T> exchange(String targetUrl , HttpMethod method , HttpHeaders requestHeaders ,
                                             I requestBody , Class<T> responseType) {
        log.debug("request uri: {}. method: {}, body: {}" , targetUrl , method , requestBody);
        
        ResponseEntity<T> responseEntity = null;
        
        try {
            RequestEntity<I> requestEntity =
                    new RequestEntity<I>(requestBody , requestHeaders , method , URI.create(targetUrl));
            
            if (restTemplate == null) {
                throw new PlatformRuntimeException("REST TEMPLATE IS NULL");
            }
            responseEntity = restTemplate.exchange(requestEntity , responseType);
        } catch (RestClientException e) {
            HttpStatus statusCode = null;
            if (e instanceof HttpStatusCodeException) {
                statusCode = HttpStatus.valueOf(( (HttpStatusCodeException) e ).getStatusCode().value());
            }
            
            if (log.isDebugEnabled()) {
                log.error("Request > RestClientException. Error Message:{}" , e.getMessage() , e);
            } else if (log.isErrorEnabled()) {
                log.error("Request > RestClientException. Error Message:{}" , e.getMessage());
            }
            handleException(statusCode , e);
        } catch (Exception e) {
            log.error("Request > Exception." , e);
            throw new PlatformServiceUnavailableException(e.getMessage());
        }
        
        log.debug("responseEntity : {}" , responseEntity);
        
        return responseEntity;
    }
    
    public <T, I> ResponseEntity<T> exchange(String targetUrl , HttpMethod method , I requestBody , Class<T> responseType) {
        return exchange(targetUrl , method , createHeader() , requestBody , responseType);
    }
    
    public <T> ResponseEntity<T> exchange(String targetUrl , HttpMethod method , Class<T> responseType) {
        return exchange(targetUrl , method , (Void) null , responseType);
    }
    
    public <T> ResponseEntity<T> get(String targetUrl , HttpHeaders requestHeaders , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.GET , requestHeaders , (Void) null , responseType);
    }
    
    public <T> ResponseEntity<T> get(String targetUrl , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.GET , (Void) null , responseType);
    }
    
    public <T, I> ResponseEntity<T> post(String targetUrl , HttpHeaders requestHeaders , I requestBody , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.POST , requestHeaders , requestBody , responseType);
    }
    
    
    public <T, I> ResponseEntity<T> post(String targetUrl , I requestBody , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.POST , requestBody , responseType);
    }
    
    public <T> ResponseEntity<T> post(String targetUrl , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.POST , (Void) null , responseType);
    }
    
    public <T, I> ResponseEntity<T> put(String targetUrl , HttpHeaders requestHeaders , I requestBody , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.PUT , requestHeaders , requestBody , responseType);
    }
    
    public <T, I> ResponseEntity<T> put(String targetUrl , I requestBody , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.PUT , requestBody , responseType);
    }
    
    public <T> ResponseEntity<T> put(String targetUrl , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.PUT , (Void) null , responseType);
    }
    
    public <T> ResponseEntity<T> del(String targetUrl , HttpHeaders requestHeaders , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.DELETE , requestHeaders , (Void) null , responseType);
    }
    
    public <T> ResponseEntity<T> del(String targetUrl , Class<T> responseType) {
        return exchange(targetUrl , HttpMethod.DELETE , (Void) null , responseType);
    }
    
    public <T, I> ResponseEntity<T> exchange(String targetUrl , HttpMethod method , HttpHeaders requestHeaders ,
                                             I requestBody , ParameterizedTypeReference<T> responseType) {
        log.debug("request uri: {}. method: {}, body: {}" , targetUrl , method , requestBody);
        
        ResponseEntity<T> responseEntity = null;
        
        try {
            RequestEntity<I> requestEntity =
                    new RequestEntity<I>(requestBody , requestHeaders , method , URI.create(targetUrl));
            
            if (restTemplate == null) {
                throw new PlatformRuntimeException("REST TEMPLATE IS NULL");
            }
            responseEntity = restTemplate.exchange(requestEntity , responseType);
        } catch (RestClientException e) {
            HttpStatus statusCode = null;
            if (e instanceof HttpStatusCodeException) {
                statusCode = HttpStatus.valueOf(( (HttpStatusCodeException) e ).getStatusCode().value());
            }
            
            if (log.isErrorEnabled()) {
                log.error("Request > exception. Error Message:{}" , e.getMessage() , e);
            }
            handleException(statusCode , e);
        } catch (Exception e) {
            log.error("Request > exception." , e);
            throw new PlatformServiceUnavailableException(e.getMessage());
        }
        
        log.debug("responseEntity : {}" , responseEntity);
        
        return responseEntity;
    }
    
    public <T> ResponseEntity<T> exchange(String targetUrl , HttpMethod method , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , method , (Void) null , responseType);
    }
    
    public <T, I> ResponseEntity<T> exchange(String targetUrl , HttpMethod method , I requestBody , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , method , createHeader() , requestBody , responseType);
    }
    
    public <T> ResponseEntity<T> get(String targetUrl , HttpHeaders requestHeaders , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.GET , requestHeaders , (Void) null , responseType);
    }
    
    public <T> ResponseEntity<T> get(String targetUrl , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.GET , (Void) null , responseType);
    }
    
    public <T, I> ResponseEntity<T> post(String targetUrl , HttpHeaders requestHeaders , I requestBody , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.POST , requestHeaders , requestBody , responseType);
    }
    
    public <T, I> ResponseEntity<T> post(String targetUrl , I requestBody , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.POST , requestBody , responseType);
    }
    
    public <T> ResponseEntity<T> post(String targetUrl , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.POST , (Void) null , responseType);
    }
    
    public <T, I> ResponseEntity<T> put(String targetUrl , HttpHeaders requestHeaders , I requestBody , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.PUT , requestHeaders , requestBody , responseType);
    }
    
    public <T, I> ResponseEntity<T> put(String targetUrl , I requestBody , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.PUT , requestBody , responseType);
    }
    
    public <T> ResponseEntity<T> put(String targetUrl , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.PUT , (Void) null , responseType);
    }
    
    public <T> ResponseEntity<T> del(String targetUrl , HttpHeaders requestHeaders , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.DELETE , requestHeaders , (Void) null , responseType);
    }
    
    public <T> ResponseEntity<T> del(String targetUrl , ParameterizedTypeReference<T> responseType) {
        return exchange(targetUrl , HttpMethod.DELETE , (Void) null , responseType);
    }
}