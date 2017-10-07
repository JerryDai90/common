package com.wordty.upgradecs.service.imp;

import com.wordty.upgradecs.service.IConf;
import com.wordty.upgradecs.vo.ConfVO;

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


    public ConfVO read() {


        ConfVO vo = new ConfVO();

        vo.setTargetBasePath("/Users/jerry/Mix/共享/临时目录/暨南大学");


        return vo;
    }
}
