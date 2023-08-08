package test.com;

import lombok.Data;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
public class URL {

    private static final Logger logger = Logger.getLogger(Browser.class.getName());

    public static final String URL_PROTOCOL_HTTP = "http";
    public static final String URL_PROTOCOL_HTTPS = "https";
    public static final String URL_PROTOCOL_HTTP_SEPARATOR = "://";
    public static final String URL_QUERY_SEPARATOR = "?";
    public static final String URL_PARAMETER_SEPARATOR = "&";
    public static final String URL_PARAMETER_BAR = "/";
    public static final String URL_PARAMETER_KEYVALUE_SEPARATOR = "=";

    private String protocol;
    private String host;
    private String path;
    private String query;

    public URL(String protocol, String url) {

        try {
            java.net.URL javaURL;
            if(!isHasProtocol(url)){
                javaURL = new java.net.URL (protocol + URL_PROTOCOL_HTTP_SEPARATOR + url);
            }else{
                javaURL = new java.net.URL (url);
            }
            this.protocol = javaURL.getProtocol();
            this.host = javaURL.getHost();
            this.path = javaURL.getPath();
            this.query = javaURL.getQuery();
        } catch (MalformedURLException e) {
            logger.log(Level.SEVERE, "URL:" + url, e);
        }
    }

    public URL(String url) {
        this(URL_PROTOCOL_HTTP, url);
    }

    public boolean isHasProtocol(String url){
        return url.contains(URL_PROTOCOL_HTTP + URL_PROTOCOL_HTTP_SEPARATOR)
                || url.contains(URL_PROTOCOL_HTTPS + URL_PROTOCOL_HTTP_SEPARATOR);
    }

    public Map<String, String> getQueryParameter() {
        return test.toQueryParameterMap(query);
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(protocol);
        result.append(URL_PROTOCOL_HTTP_SEPARATOR);
        if (host != null) {
            result.append(host);
        }
        if (path != null) {
            result.append(path);
        }
        if (query != null) {
            result.append(URL_QUERY_SEPARATOR);
            result.append(query);
        }
        return result.toString();
    }

    public String toStringWithoutQuery(){
        StringBuilder result = new StringBuilder();
        result.append(protocol);
        result.append(URL_PROTOCOL_HTTP_SEPARATOR);
        if (host != null) {
            result.append(host);
        }
        if (path != null) {
            result.append(path);
        }
        return test.cleanURL(result.toString());
    }
}
