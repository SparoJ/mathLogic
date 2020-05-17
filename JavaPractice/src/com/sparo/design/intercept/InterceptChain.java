package com.sparo.design.intercept;

/**
 * description: top abstract interface
 * Created by sdh on 2020-05-17
 */
public interface InterceptChain {

    interface Interceptor {
        Response onIntercept(Chain chain);
    }

    interface Chain {

        Response doProceed(Request request);

        Request request();
    }
}
