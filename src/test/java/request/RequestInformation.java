package factoryRequest;

import java.util.HashMap;
import java.util.Map;

public class RequestInformation {
    private String url;
    private String body;
    private Map<String,String> headers;

    public RequestInformation(){
        headers = new HashMap<>();
    }

    public String getUrl() {
        return url;
    }

    public RequestInformation setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getBody() {
        return body;
    }

    public RequestInformation setBody(String body) {
        this.body = body;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RequestInformation setHeaders(String attribute, String value) {
        this.headers.put(attribute,value);
        return this;
    }
}
