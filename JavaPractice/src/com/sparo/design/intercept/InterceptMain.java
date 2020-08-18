package com.sparo.design.intercept;

import com.sparo.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * description: test for interceptor design mode entrance
 * Created by sdh on 2020-05-17
 */
public class InterceptMain {

    public static void main(String[] args) {

        List<InterceptChain.Interceptor> interceptorList = new ArrayList<>();
        interceptorList.add(new UrlInterceptor());
        interceptorList.add(new ResponseInterceptor());
        Request request = new Request().setHeader("{'token':'good luck'}").setJsonBody("{'data':'night'}").setUrl("https://www.baidu.com?a=b");
        RealChain chain = new RealChain(interceptorList, request, 0);
        Response response = chain.doProceed(request);
        Utils.printWithTag("response back", response);
    }
}
