package com.wordty.upgradecs.service.imp;

import com.wordty.common.assist.utils.Utils;
import com.wordty.upgradecs.service.IConf;
import com.wordty.upgradecs.vo.ConfVO;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Created by jerry on 10/7/17.
 *
 * @author jerry
 * @date 2017 -10-07 10:59:59
 */
public class FileConfImp implements IConf {

    /**
     * The File path.
     */
    private String filePath;


    /**
     * Instantiates a new File conf imp.
     *
     * @param filePath the file path
     * @author jerry
     * @date 2017 -10-07 10:59:59
     */
    public FileConfImp(String filePath) {
        this.filePath = filePath;
    }


    public ConfVO read() throws UnsupportedEncodingException, FileNotFoundException {
        ConfVO vo = new ConfVO();
        InputStreamReader confInput = new InputStreamReader(new FileInputStream(filePath), "utf-8");//new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("conf.properties"), "utf-8");

        Properties properties = new Properties();
        try {
            properties.load(confInput);
            String absoluteProjectBasePath = properties.getProperty("absoluteProjectBasePath");
            String srcPath = properties.getProperty("srcPath");
            String classPath = properties.getProperty("classPath");
            String updateFilePath = properties.getProperty("updateFilePath");
            String targetBasePath = properties.getProperty("targetBasePath");

            vo.setAbsoluteProjectBasePath(absoluteProjectBasePath);
            vo.getSrcPath().addAll(Utils.list(srcPath, ";", String.class));
            vo.getClassPath().addAll(Utils.list(classPath, ";", String.class));
            vo.setTargetBasePath(targetBasePath);

            if (updateFilePath.indexOf("classpath:") != -1) {
                updateFilePath = updateFilePath.replaceAll("classpath:", "");
                URL resource = this.getClass().getClassLoader().getResource(updateFilePath);
                FileReader fileReader = new FileReader(resource.getPath());
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String readLine = "";
                while ((readLine = bufferedReader.readLine()) != null) {
                    vo.getUpdateFilePath().add(readLine.trim());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }
}
