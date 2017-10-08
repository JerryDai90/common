package com.wordty.upgradecs.service;

import com.wordty.upgradecs.vo.ConfVO;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by jerry on 10/7/17.
 *
 * @author jerry
 * @date 2017 -10-07 10:36:45
 */
public interface IConf {

    /**
     * Read conf vo.
     *
     * @return conf vo
     * @author jerry
     * @date 2017 -10-07 10:39:07
     */
    ConfVO read() throws UnsupportedEncodingException, FileNotFoundException;

}
