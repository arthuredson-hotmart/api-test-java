package test.com;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Builder;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class test {

        static Integer variavel = 4;

        private static final String UTF_8 = "UTF-8";

        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        private static final int SESSION_TIME = 30;

        private static final String URL_PARAMETER_BAR = "/";

        public static final String URL_PROTOCOL_HTTP = "http";
        public static final String URL_PROTOCOL_HTTPS = "https";
        public static final String URL_PROTOCOL_HTTP_SEPARATOR = "://";

        private static final String DEFAULT_VALUE = "0";

    private static final String[] TAGS = new String[] { "utm_source", "utm_medium", "utm_term", "utm_content",
            "utm_campaign", "src", "sck", "source" };
    private static final int MAX_UTM_LENGTH = 997;

        static String protocol;
        static String host;
        static String path;
        static String query;

        public static void main(String[] args) throws UnsupportedEncodingException {

            Map<String, String> tags = new HashMap<>();
            tags.putAll(toQueryParameterMapFromURL("https://payment.hotmart.com/next/T8483491O?off=zowm4rpu&checkoutMode=10&ck=sa_we_venta_RetoFluye_1p_social_media_insta&utm_medium=social&utm_source=linktree&utm_campaign=inscr%C3%ADbete+a+mi+programa+%22soy+abundante%22!&bid=1689094899004"));
            System.out.println(buildTags(tags));

            System.out.println(udfDecodeUrl("{utm_medium -> social, utm_campaign -> inscr?bete a mi programa \\\"soy abundante!\\\"}"));

            System.out.println("testee decode  "+ getSrcFromHsrc("c3RhcGlhcw%3D%"));
            System.out.println("testee decode22  "+ getSrcFromHsrc("a2V5d29yZA%3D%3D%3Fsrc%3D1")); // a2V5d29yZA==?src=1 tem um return null
            System.out.println("testee encode  "+ urlSafeBase64("stapias\n" +
                    "==")); // link redirector


        /*    LocalDateTime eventDT = LocalDateTime.parse("2022-11-28 09:15:24.919", FORMATTER);
            LocalDateTime nextEventDT = LocalDateTime.parse("2022-11-28 08:59:58.129", FORMATTER);

            System.out.println("DATA_PARSE  -  "+ LocalDateTime.parse("2022-11-28 08:59:58.129", FORMATTER));

            if (nextEventDT.isAfter(eventDT)) {
                System.out.println("A data é depois");

            } else {
                System.out.println("A data é antes");
            }



            if(isInSessionLegado("2022-12-01 09:10:34.822", "2022-12-01 08:50:55.721")){
                System.out.println("true está em seção");
            } else {
                System.out.println("false não está em seção");
            }

            if(variavel % 2 == 0){
                variavel = 5;
                // System.out.println("valor de com.hotmart.analytics.teste " +variavel);
                //System.out.println("valor de com.hotmart.analytics.teste " + this.variavel);
            }

            // try {
            //       System.out.println("TESTE OFERTA "+URLDecoder.decode("05ej1iksJWT", UTF_8));
            //  } catch (UnsupportedEncodingException e) {
            //       throw new RuntimeException(e);
            //   }

            String url = "https://clubedosferas.club.hotmart.com/lesson/R4jXL6Yd7a/lives-no-instagram-secreto";

            try {
                java.net.URL javaURL;
                if(!isHasProtocol(url)){
                    javaURL = new java.net.URL (URL_PROTOCOL_HTTP + URL_PROTOCOL_HTTP_SEPARATOR + url);
                }else{
                    javaURL = new java.net.URL (url);
                }
                protocol = javaURL.getProtocol();
                host = javaURL.getHost();
                path = javaURL.getPath();
                query = javaURL.getQuery();
            } catch (MalformedURLException e) {
                System.out.println("erro");
            }

            String pageUrl = removeLastBar(host+ path);
            System.out.println("site  " + pageUrl);

            CustomerEvent event = CustomerEvent.builder().account(null).affiliationCode("P68458494D").producerId("29776884")
                    .pageUrl("").build();
           if (!hasOwner(event)) {
                System.out.println("nao pegou o process_payment");
           } else {
               System.out.println("1 -  pegou o process_payment");
           }

            if (event.getPageUrl() != null && event.getPageUrl().contains("localhost")) {
                System.out.println("nao  pegou o process_payment");
            } else {
                System.out.println("2 -  pegou o process_payment");
            } */

        }

    public static String udfDecodeUrl (String encodedUrl) {
        try {
            return URLDecoder.decode(encodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

        private static boolean hasOwner(CustomerEvent event) {
            return !(isBlank(event.getAccount()) && isBlank(event.getAffiliationCode()) && isBlank(event.getProducerId()));
        }

        public static boolean isBlank(String value) {
            return StringUtils.isBlank(value) || DEFAULT_VALUE.equals(value);
       }

        public static boolean isInSessionLegado(String begin, String end){
            LocalDateTime beginDT = LocalDateTime.parse(begin, FORMATTER);
            LocalDateTime endDT = LocalDateTime.parse(end, FORMATTER);
            System.out.println(ChronoUnit.MINUTES.between(beginDT, endDT));
            return ChronoUnit.MINUTES.between(beginDT, endDT) <= SESSION_TIME;
        }

        public static String removeLastBar(String url) {

            String strURL = url.trim();
            if (URL_PARAMETER_BAR.equals(strURL)) {
                return null;
            }

            int index = strURL.lastIndexOf(URL_PARAMETER_BAR);
            if (index > 0 && index == strURL.length() - 1) {
                return strURL.substring(0, index);
            }
            return strURL;
        }



        public static boolean isHasProtocol(String url){
            return url.contains(URL_PROTOCOL_HTTP + URL_PROTOCOL_HTTP_SEPARATOR)
                    || url.contains(URL_PROTOCOL_HTTPS + URL_PROTOCOL_HTTP_SEPARATOR);
        }

    public static String getSrcFromHsrc(String hsrc) {
        try {
            return decode64(hsrc);
        } catch (Exception e) {
            try {
                return decode64(decode(hsrc));
            } catch (Exception e2) {

            }
        }
        return hsrc;
    }
    public static String decode64(String text) {
        try {
            byte[] decoded = Base64.getDecoder().decode(text);
            return new String(decoded, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decode(String value) {
        try {
            if (StringUtils.isNotBlank(value)) {
                return URLDecoder.decode(value, UTF_8);
            }
        } catch (Exception e) {

        }
        return value;
    }

    public static String urlSafeBase64(String s) throws UnsupportedEncodingException {
        return URLEncoder.encode(Base64.getEncoder().encodeToString(s.getBytes()), "UTF-8");
    }

    public static Map<String, String> toQueryParameterMapFromURL(String url) {
        Map<String, String> map = new HashMap<String, String>();
        if (url == null) {
            return map;
        }
        String[] queryArray = url.split(Pattern.quote(URL.URL_QUERY_SEPARATOR));
        String queryString = queryArray.length > 1 ? queryArray[1] : queryArray[0];
        return toQueryParameterMap(queryString);
    }

    public static Map<String, String> toQueryParameterMap(String queryString) {
        Map<String, String> map = new HashMap<String, String>();
        if (queryString == null) {
            return map;
        }
        String[] params = queryString.split(URL.URL_PARAMETER_SEPARATOR);
        for (String parameter : params) {
            String[] keyValue = parameter.split(URL.URL_PARAMETER_KEYVALUE_SEPARATOR);
            if (keyValue.length > 1) {
                map.put(keyValue[0], StringUtils.defaultIfBlank(keyValue[1], ""));
            }
        }
        return map;
    }

    public static String cleanURL(String url){
        if (url == null) {
            return url;
        }
        return url.replaceAll("[^a-zA-Z0-9-._~:/?#@!$&'()*+,;=]" , "");
    }

    public static String removeEmoji(String text) {
        return text.replaceAll("[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\p{Sc}\\p{Punct}\\s]", "");
    }

    public static String buildTags(Map<String, String> tags) {

        String hsrc = tags.get("hsrc");
        if (StringUtils.isNotBlank(hsrc) && StringUtils.isBlank(tags.get("src"))) {
            tags.put("src", getSrcFromHsrc(hsrc));
        }

        JsonObject tagsJO = new JsonObject();
        for (String tag : TAGS) {
            String tagVal = tags.get(tag);
            if (tagVal != null) {
                String val = CharSetUtils.delete(decode(tagVal), "\t", "\r", "\n", "\b");
                val = removeEmoji(val);
                if (val.length() > MAX_UTM_LENGTH) {
                    val = val.substring(0, MAX_UTM_LENGTH);
                }
                tagsJO.addProperty(tag, val);
            }
        }

        return tagsJO.toString();
    }




    }
