package com.yjkim.spring.java.utility.data.string;

import com.yjkim.spring.java.utility.data.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class URLUtil
{

    public static final String PRE_HTTP = "http://";
    public static final String PRE_HTTPS = "https://";
    
    public static String getDomainName(String urlStr)
    {
        if(StringUtils.isBlank(urlStr))
        {
            return StringUtils.EMPTY;
        }
        
        try
        {
            URL url = new URL(urlStr);
            return url.getHost().replace("www\\.", "");
        } catch (MalformedURLException e)
        {
            log.error(e.getMessage(), e);
            return StringUtils.EMPTY;
        }
    }

    public static String addQueryParam (String uri, String key, String value)
    {
        if (StringUtils.isBlank(uri) || StringUtils.isBlank(key))
        {
            return "";
        }

        if (!(uri.contains(PRE_HTTP) || uri.contains(PRE_HTTPS)) && !uri.startsWith("forward:"))
        {
            uri = PRE_HTTP + uri;
        }

        if (uri.startsWith("redirect:"))
        {
            uri = uri.replace("redirect:", "");
        }

        if (uri.startsWith("forward:"))
        {
            uri = uri.replace("forward:", "");
        }

        try
        {
            URI oldUri = new URI(uri);

            // getRawQuery()로 percent-encoding 원본 유지 (getQuery()는 디코딩하여 %26→& 등 손실 발생)
            String existingQuery = oldUri.getRawQuery();
            String newParam = key + "=" + (value != null ? value : "");
            String newQuery;

            if (existingQuery == null)
            {
                newQuery = newParam;
            }
            else
            {
                // 기존 파라미터 중 같은 key 제거 후 새 값 추가
                String filteredQuery = Arrays.stream(existingQuery.split("&"))
                        .filter(param -> !param.equals(key + "=")
                                && !param.startsWith(key + "="))
                        .collect(Collectors.joining("&"));

                newQuery = filteredQuery.isEmpty() ? newParam : filteredQuery + "&" + newParam;
            }

            // 다중 인자 URI 생성자는 query를 재인코딩하므로 raw 문자열로 직접 조립
            StringBuilder result = new StringBuilder();
            if (oldUri.getScheme() != null) result.append(oldUri.getScheme()).append("://");
            if (oldUri.getRawAuthority() != null) result.append(oldUri.getRawAuthority());
            if (oldUri.getRawPath() != null) result.append(oldUri.getRawPath());
            result.append("?").append(newQuery);
            if (oldUri.getRawFragment() != null) result.append("#").append(oldUri.getRawFragment());
            return result.toString();
        } catch (URISyntaxException e)
        {
            log.error(e.getMessage(), e);
            return uri;
        }
    }

    public static <T> T convertStringToMap (String str, Class<T> classType)
    {
        Map<String, String> map = new HashMap<>();
        String[] keyValues = str.split("&");
        for (String keyValue : keyValues)
        {
            String[] pair = keyValue.split("=");
            if (pair.length == 2)
            {
                String key = pair[0];
                String value = pair[1];
                map.put(key, value);
            }
        }
        T result = null;
        try
        {
            result = JsonUtil.convertObjectWithUnkown(map, classType);
        } catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }

        return result;
    }

}
