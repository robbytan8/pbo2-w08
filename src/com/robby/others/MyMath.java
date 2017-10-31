package com.robby.others;

/**
 *
 * @author Robby
 */
public class MyMath {

    public Integer add(Integer value1, Integer value2) throws Exception {
        if (value1 > 0 && value2 > 0 && (value1 + value2) < 0) {
            throw new Exception("Number overflow");
        }
        return value1 + value2;
    }
}
