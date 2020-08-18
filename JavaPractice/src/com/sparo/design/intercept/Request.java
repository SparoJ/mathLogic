package com.sparo.design.intercept;

/**
 * description: mock request
 * Created by sdh on 2020-05-17
 */
public class Request {

    private String url;
    private String header;
    private String jsonBody;
    private String requestTag;

    public String getUrl() {
        return url;
    }

    public Request setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public Request setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public Request setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
        return this;
    }

    public String getRequestTag() {
        return requestTag;
    }

    public Request setRequestTag(String requestTag) {
        this.requestTag = requestTag;
        return this;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", header='" + header + '\'' +
                ", jsonBody='" + jsonBody + '\'' +
                ", requestTag='" + requestTag + '\'' +
                '}';
    }
}
