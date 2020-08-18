package com.sparo.produce.standard;

/**
 * description:
 * Created by sdh on 2020-08-07
 */
public abstract class AbstractProduce implements IProduce, Runnable{

    @Override
    public void run() {
        while(true) {
            produce();
        }
    }
}
