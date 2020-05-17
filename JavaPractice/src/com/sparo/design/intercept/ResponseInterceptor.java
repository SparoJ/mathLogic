package com.sparo.design.intercept;

/**
 * description: 模拟拦截器 修改返回数据
 * Created by sdh on 2020-05-17
 */
public class ResponseInterceptor implements InterceptChain.Interceptor {
    @Override
    public Response onIntercept(InterceptChain.Chain chain) {
        Request request = chain.request();
        Response response = chain.doProceed(request);
        response.setHeader("{'content-type':'json'}");
        response.setResposneTag("{'test':'over'}");
        return response;
    }
}
