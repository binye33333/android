package com.example.apptest.dimenCreate;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @author chenyo
 * @Title: ${file_name}
 * @Package com.example.apptest.Test
 * @Description: 用于生成多套dimens配置文件
 * @date 2015/9/21 8:56
 */


public class Test {
    public static void main(String[] args) {
        DimensParser parser = new DimensParser();
        try {
            List<DimenValues> list = parser.parse(new FileInputStream(Config.path + File.separator + "dimens.xml"));
            DimensCreator creator = new DimensCreator(Config.path, list);
            creator.createAll();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}