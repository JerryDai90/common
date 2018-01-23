package com.wordty.upgradecs.service;

import com.wordty.upgradecs.utils.BeanUtil;

import java.io.IOException;

/**
 * Created by jerry on 1/23/18.
 */
public class Main {

    /**
     * .
     *
     * @param args input arguments
     * @throws IOException io exception
     * @author jerry
     * @date 2017 -10-07 22:15:12
     */
    public static void main(String[] args) throws IOException {
        BeanUtil.getIMigration().move();
    }
}
