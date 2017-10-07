package com.wordty.upgradecs.service.imp;

import com.wordty.common.assist.utils.Assert;
import com.wordty.common.assist.utils.Utils;
import com.wordty.upgradecs.service.IConf;
import com.wordty.upgradecs.service.IMigration;
import com.wordty.upgradecs.vo.ConfVO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

/**
 * Created by jerry on 10/7/17.
 *
 * @author jerry
 * @date 2017 -10-07 22:15:12
 */
public class MigrationImp implements IMigration {

    /**
     * The Conf.
     */
    private IConf conf = new FileConfImp("");


    public void move() throws IOException {
        Assert.notNull(conf, "配置文件为初始化");
        ConfVO confVO = conf.read();

        List<String> srcPath = confVO.getSrcPath();
        String classPath = confVO.getClassPath().get(0);


        List<String> updateFilePath = confVO.getUpdateFilePath();
        for (String filePath : updateFilePath) {

            String absoluteSrcPath = Utils.joinFilePath(confVO.getAbsoluteProjectBasePath(), filePath);
            File srcFile = new File(absoluteSrcPath);
            if (srcFile.isDirectory()) {
                continue;
            }
            //如果是资源文件，直接复制到指定文件夹
            if (filePath.indexOf("WebRoot") != -1) {
//                xxx(srcFile, confVO, filePath);
                String targetFilePath = Utils.joinFilePath(confVO.getTargetBasePath(), filePath);
                FileUtils.copyFile(srcFile, new File(targetFilePath));
                continue;
            }

            String _package = "";
            for(String _srcPath : srcPath){
                int srcIndex = filePath.indexOf(_srcPath+"/");
                if( srcIndex == 0 ){
                    _package = filePath.substring((_srcPath+"/").length());
                    break;
                }
            }

            //非指定源码目录不做匹配
            if(StringUtils.isEmpty(_package)){
                break;
            }
            //java 文件 classPath
            File classFile = new File(Utils.joinFilePath(confVO.getAbsoluteProjectBasePath(), classPath, _package));
            File[] fileFilter = fileFilter(classFile.getParentFile(), srcFile.getName());
            for (File file : fileFilter) {
                if( file.getName().indexOf(".class") != -1 || file.getName().indexOf(".java") != -1 ){
                    _package = removePx(_package)+".class";
                }
                String targetFilePath = Utils.joinFilePath(confVO.getTargetBasePath(), getClassPath(), _package);
                FileUtils.copyFile(file, new File(targetFilePath));
            }
        }
    }


//    private void xxx(File srcFile, ConfVO conf, String filePath) throws IOException {
//        String targetFilePath = Utils.joinFilePath(conf.getTargetBasePath(), filePath);
//        FileUtils.copyFile(srcFile, new File(targetFilePath));
//    }

    /**
     * .
     *
     * @param args input arguments
     * @throws IOException io exception
     * @author jerry
     * @date 2017 -10-07 22:15:12
     */
    public static void main(String[] args) throws IOException {
        IMigration migration = new MigrationImp();

        migration.move();
    }

    /**
     * Get class path string.
     *
     * @return string
     * @author jerry
     * @date 2017 -10-07 22:15:12
     */
    public static String getClassPath(){
        return Utils.joinFilePath("WebRoot", "WEB-INF", "classes");
    }


    /**
     * Remove px string.
     *
     * @param filename filename
     * @return string
     * @author jerry
     * @date 2017 -10-07 22:15:12
     */
    public static String removePx(String filename) {
        return filename.replaceAll(".java", "").replaceAll(".class", "");
    }

    /**
     * File filter file [ ].
     *
     * @param classFile class file
     * @param filename  filename
     * @return file [ ]
     * @author jerry
     * @date 2017 -10-07 22:15:12
     */
    private File[] fileFilter(File classFile, final String filename){
        File[] fileFilter = classFile.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return false;
                }
                String cn = removePx(pathname.getName());
                String sn = removePx(filename);

                if (cn.equals(sn)) {
                    return true;
                }
                if (cn.indexOf(sn) != -1 && cn.indexOf("$") != -1) {
                    return true;
                }
                return false;
            }
        });
        return fileFilter;
    }


}
