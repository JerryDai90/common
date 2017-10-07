package com.wordty.upgradecs.service;

import java.io.IOException;

/**
 * Created by jerry on 10/7/17.
 */
public interface IMigration {

    void move() throws IOException;

}
