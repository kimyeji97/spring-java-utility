package com.yjkim.spring.java.utility.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor
{
    private final int RESPONSE_LOGGING_LENGTH = 500;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException
    {
        ServletRequestAttributes requestAttributes =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null)
        {
            HttpServletRequest httpServletRequest = requestAttributes.getRequest();

            // long startTime = System.currentTimeMillis();
            // String transactionId = setDefaultSessionValuesAndGetTransactionId(httpServletRequest);

            if (httpServletRequest != null)
            {
                // API ID extraction
                String api = httpServletRequest.getHeader("x-api-id");
                if (api == null)
                {
                    api = "[".concat(httpServletRequest.getMethod()).concat("]")
                        .concat(httpServletRequest.getRequestURL().toString());
                }

                // Client IP extraction
                String clientIp = httpServletRequest.getHeader("x-client-ip");
                if (clientIp == null)
                {
                    clientIp = httpServletRequest.getRemoteAddr();
                }
            }
        }

        // Api key extraction
        // String apiKey = httpServletRequest.getHeader("x-api-key");

        // Create transaction object
        // TransactionVo transaction =
        // TransactionVo.builder().transactionId(transactionId).api(api).clientIp(clientIp).apiKey(apiKey)
        // .startTime(startTime).build();
        //
        // transaction.setApi(request.getURI().toString());
        //
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        response = new BufferingClientHttpResponseWrapper(response);
        traceResponse(response);

        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body)
    {
        log.info("[===========================REQUEST=============================================]");
        log.info("[Method : {}, URI : {}]", request.getMethod(), request.getURI());
        log.info("[Request Headers]");
        for (Entry<String, List<String>> entry : request.getHeaders().entrySet())
        {
            log.info("  -> {}: {}", entry.getKey(), entry.getValue());
        }
        log.info("[Request Body : {}]", new String(body, StandardCharsets.UTF_8));

        // Write TxLog
        // CommonLog.txLogReq(transaction, request, new String(body, StandardCharsets.UTF_8));
    }

    private void traceResponse(ClientHttpResponse response) throws IOException
    {
        String contentType = "";
        if (response.getHeaders() != null && response.getHeaders().getContentType() != null)
        {
            contentType = response.getHeaders().getContentType().toString();
        }

        StringBuilder inputStringBuilder = new StringBuilder();
        if (response.getBody() != null && !contentType.contains("image"))
        {
            BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
            char[] buffer = null;
//            char[] buffer = new char[1024 * 10];
            if(StringUtils.contains(contentType, "text/htmnl")) {
                buffer = new char[1024];
            }
            else {
                buffer = new char[1024 * 10];
            }

//            int len;
//            while ((len = bufferedReader.read(buffer, 0, buffer.length)) != -1)
//            {
////                if(inputStringBuilder.length() > RESPONSE_LOGGING_LENGTH) {
////                    inputStringBuilder.append("...");
////                    break;
////                }
//                inputStringBuilder.append(buffer, 0, len);
//            }
            
            int len = bufferedReader.read(buffer, 0, buffer.length);
            if(len != -1) {
                inputStringBuilder.append(buffer, 0, len);
            }
        }

        log.info("[============================RESPONSE==========================================");
        log.info("[Status : {} {}]", response.getStatusCode(), response.getStatusText());
        log.info("[Headers : {}   ]", response.getHeaders());
        if(inputStringBuilder.length() > RESPONSE_LOGGING_LENGTH) {
            log.info("[Response Body : {} ...]", StringUtils.substring(inputStringBuilder.toString(), 0, RESPONSE_LOGGING_LENGTH));
        }
        else {
            log.info("[Response Body : {}]", inputStringBuilder.toString());    
        }
        log.info("[=========================REST TEMPLATE END====================================]");

        inputStringBuilder.setLength(0);
        inputStringBuilder.trimToSize();

        // long endTime = System.currentTimeMillis();
        //
        // // Transaction Update
        // transaction.setEndTime(endTime);
        // transaction.setStatus(response.getStatusCode().value());
        // // Write TxLog
        // CommonLog.txLogRes(transaction, response);
    }

    /**
     * Response wrapper ?겢?옒?뒪 response 媛앹껜?뿉?꽌 body ?궡?슜?쓣 蹂듭궗?븯?뿬 ?옱?궗?슜 媛??뒫?븯寃? 泥섎━?븿.
     *
     * @author silverb
     */
    public static class BufferingClientHttpResponseWrapper implements ClientHttpResponse
    {
        private final ClientHttpResponse response;

        private byte[] body;

        BufferingClientHttpResponseWrapper(ClientHttpResponse response)
        {
            this.response = response;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException
        {
            return HttpStatus.valueOf(response.getStatusCode().value());
        }

        @Override
        public int getRawStatusCode() throws IOException
        {
            return this.response.getStatusCode().value();
        }

        @Override
        public String getStatusText() throws IOException
        {
            return this.response.getStatusText();
        }

        @Override
        public HttpHeaders getHeaders()
        {
            return this.response.getHeaders();
        }

        @Override
        public InputStream getBody() throws IOException
        {
            if (this.body == null)
            {
                if (this.response.getBody() != null)
                {
                    this.body = FileCopyUtils.copyToByteArray(this.response.getBody());
                }
            }
            return (this.body == null) ? null : new ByteArrayInputStream(this.body);
        }

        @Override
        public void close()
        {
            this.response.close();
        }
    }
}
