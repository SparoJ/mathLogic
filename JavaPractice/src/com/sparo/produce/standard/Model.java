package com.sparo.produce.standard;

/**
 * description:
 * Created by sdh on 2020-08-07
 */

public interface Model<T> {

    Runnable createProduce();
    Runnable createConsume();

}
