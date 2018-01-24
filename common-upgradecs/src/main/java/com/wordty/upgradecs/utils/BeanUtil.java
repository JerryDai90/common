package com.wordty.upgradecs.utils;

import com.wordty.upgradecs.service.IConf;
import com.wordty.upgradecs.service.IMigration;
import com.wordty.upgradecs.service.imp.FileConfImp;
import com.wordty.upgradecs.service.imp.MigrationImp;

/**
 * Created by jerry on 1/23/18.
 */
public class BeanUtil {

    private static IConf conf;
    private static IMigration migration;

    public static IConf getIConf(){
        if( null == conf ){
            conf = new FileConfImp(MigrationImp.class.getClassLoader().getResource("conf.properties").getPath());
        }
        return conf;
    }


    public static IMigration getIMigration(){
        if( null == migration ){
            migration = new MigrationImp();
        }
        return migration;
    }



}
