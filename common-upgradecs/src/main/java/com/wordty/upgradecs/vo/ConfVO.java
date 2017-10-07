package com.wordty.upgradecs.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author jerry
 * @date 2017 -10-07 20:06:37
 */
public class ConfVO {


    /**
     * 工程路径.
     */
    private String absoluteProjectBasePath;

    /**
     * 源代码目录，可以设置多个（基于工程路径的相对路径）.
     */
    private List<String> srcPath = new ArrayList<String>();

    /**
     * 编译输出目录，目前只能设置一个（基于工程路径的相对路径）.
     */
    private List<String> classPath = new ArrayList<String>();


    /**
     * 需要更新的源代码文件路径（基于工程路径的相对路径）.
     */
    private List<String> updateFilePath = new ArrayList<String>();

    /**
     * 移动输出目录.
     */
    private String targetBasePath = "";

    public String getAbsoluteProjectBasePath() {
        return absoluteProjectBasePath;
    }

    public void setAbsoluteProjectBasePath(String absoluteProjectBasePath) {
        this.absoluteProjectBasePath = absoluteProjectBasePath;
    }

    public List<String> getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(List<String> srcPath) {
        this.srcPath = srcPath;
    }

    public List<String> getClassPath() {
        return classPath;
    }

    public void setClassPath(List<String> classPath) {
        this.classPath = classPath;
    }

    public List<String> getUpdateFilePath() {
        return updateFilePath;
    }

    public void setUpdateFilePath(List<String> updateFilePath) {
        this.updateFilePath = updateFilePath;
    }

    public String getTargetBasePath() {
        return targetBasePath;
    }

    public void setTargetBasePath(String targetBasePath) {
        this.targetBasePath = targetBasePath;
    }
}
