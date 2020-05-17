package com.sparo.design.intercept;

/**
 * description: mock response
 * Created by sdh on 2020-05-17
 */
public class Response {

    String header;
    String jsonBody;
    String resposneTag;

    public Response(String s) {
        jsonBody = s;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    public String getResposneTag() {
        return resposneTag;
    }

    public void setResposneTag(String resposneTag) {
        this.resposneTag = resposneTag;
    }

    @Override
    public String toString() {
        return "Response{" +
                "header='" + header + '\'' +
                ", jsonBody='" + jsonBody + '\'' +
                ", resposneTag='" + resposneTag + '\'' +
                '}';
    }
}
