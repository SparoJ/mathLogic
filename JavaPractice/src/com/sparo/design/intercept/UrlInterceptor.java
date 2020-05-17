package com.sparo.design.intercept;

/**
 * description: 模拟拦截器 发起链路上（从左至右）执行拦截操作
 * Created by sdh on 2020-05-17
 */
public class UrlInterceptor implements InterceptChain.Interceptor {

    @Override
    public Response onIntercept(InterceptChain.Chain chain) {
        String url = chain.request().getUrl();
        if(url==null || url.length()==0) {
            return new Response("{'msg':'err', 'code':'400', 'data':'error url'}");
        }
        StringBuilder builder= new StringBuilder();
        builder.append(url);
        if(url.contains("?")) {
            builder.append("&");
        } else {
         builder.append("?");
        }
        builder.append("key=").append("value");
        chain.request().setUrl(builder.toString());
        return chain.doProceed(chain.request());
    }
}
