package com.wordty.common.assist.utils;

import com.wordty.common.assist.exception.CloseResourceException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * provide close all resource method. call the close method through reflection.
 *
 * @author jerry
 * @date 2017 -06-16 18:37:27
 */
public class ResourceCloseUtil {

    /**
     * Close all resource, but resource must has a close method.
     *
     * @param obj resource list, at least one.
     * @throws CloseResourceException when resource not found, throw this exception.
     * @author jerry
     * @date 2017 -06-16 18:37:27
     */
    public static void close(Object ...obj) throws CloseResourceException{
        for( Object _obj  : obj ){
            if( null == _obj ){
                continue;
            }
            try{
                if( !_close(_obj) ){
                    try {
                        Class<? extends Object> clazz = _obj.getClass();
                        Method method = clazz.getMethod("close");
                        method.invoke(_obj);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        throw new CloseResourceException("无法处理："+_obj.getClass());
                    }
                }
            }catch (CloseResourceException e) {
                throw e;
            }catch (Exception e) {
//				e.printStackTrace();
            }
        }
    }

    /**
     * Close resource.
     *
     * @param _obj resource list
     * @return boolean
     * @throws Exception exception
     * @author jerry
     * @date 2017 -06-16 18:37:27
     */
    private static boolean _close(Object _obj) throws Exception{
        if( _obj instanceof BufferedReader ){
            ((BufferedReader)_obj).close();
        }else if( _obj instanceof PrintWriter ){
            ((PrintWriter)_obj).close();
        }else if( _obj instanceof Socket ){
            ((Socket)_obj).close();
        }else{
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        FileInputStream f = null;
        Object obj = new Object();
        close(f);
        System.out.println(111111);
    }
}
