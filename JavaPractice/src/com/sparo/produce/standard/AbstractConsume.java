package com.sparo.produce.standard;

/**
 * description:
 * Created by sdh on 2020-08-07
 */
public abstract class AbstractConsume implements IConsume, Runnable{

    @Override
    public void run() {
        while(true) {
            consume();
        }
    }
}
