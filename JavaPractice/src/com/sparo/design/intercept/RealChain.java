package com.sparo.design.intercept;

import com.sparo.util.Utils;

import java.util.List;

/**
 * description:
 * Created by sdh on 2020-05-17
 */
public class RealChain implements InterceptChain.Chain {

    private List<InterceptChain.Interceptor> interceptorList;
    private Request request;
    private int curIndex;

    public RealChain(List<InterceptChain.Interceptor> interceptorList, Request request, int curIndex) {
        this.interceptorList = interceptorList;
        this.request = request;
        this.curIndex = curIndex;
    }
    @Override
    public Response doProceed(Request request) {
        if(curIndex>=0 && curIndex < interceptorList.size()) {
            RealChain chain = new RealChain(interceptorList, request, curIndex+1);
            return interceptorList.get(curIndex).onIntercept(chain);
        }
        //没有可用拦截器 最后发起request并 递归返回response结果
        Utils.printWithTag("request last", request);
        return new Response("{'msg':'success', 'code':'200', 'data':'hello interceptor'}");
    }

    @Override
    public Request request() {
        return request;
    }
}
