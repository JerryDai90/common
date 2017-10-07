package com.wordty.common.assist.utils;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.*;

/**
 * Created by jerry on 6/21/17.
 */
public class Utils {

    public static Map<String, Object> map(Object ...obj){

        if( obj.length % 2 != 0 ){
            throw new RuntimeException("参数个数必须可整除2");
        }
        Map<String, Object> hm = new HashMap<String, Object>();

        for( int i = 0; i < (obj.length); i++ ){
            if( i % 2 != 0 ){
                hm.put(obj[i -1].toString(), obj[i]);
            }
        }
        return hm;
    }

    public static <T>List<T> list(T ...obj){
        return Arrays.asList(obj);
    }

    public static boolean isNullOrmpty(Object obj){
        if( null == obj ){
            return true;
        }
        if( obj instanceof List ){
            return ((List)obj).isEmpty();
        }else if( obj instanceof Map ){
            return ((Map)obj).isEmpty();
        }else if( obj instanceof Object[] ){
            return ((Object[])obj).length == 0 ;
        }else{
            return obj.toString().length() == 0;
        }
    }

    /**
     * 合并指定的目录路径，依据当前的操作系统分隔符
     * @param str
     * @return
     */
    public static String joinFilePath(String ...str){
        List<String> file = new ArrayList<String>(str.length);
        for( String _str : str ){
            _str = StringUtils.removeEnd(_str, "/");
            _str = StringUtils.removeEnd(_str, "\\");
            file.add(_str);
        }
        return StringUtils.join(file, File.separator);
    }

    public static void main(String[] args) {

        Map<String, Object> map = map("xxx", 1, "www", 2);

        System.out.println(map);

    }

}
