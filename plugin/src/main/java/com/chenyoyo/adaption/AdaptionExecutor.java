package com.chenyoyo.adaption;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @author chenyo
 * @Title: ${file_name}
 * @Description: 用于生成多套dimens配置文件
 * @date 2015/9/21 8:56
 */


public class AdaptionExecutor {

    /**
     * 作为插件源码的时候给插件调用执行
     */
    public static void execute(String path) {

        createBase(path);

        createAll(path);
//
    }

    /**
     * 测试功能的时候，可以直接作为Java 程序运行
     *
     * @param args
     */
    public static void main(String[] args) {
        execute(new File("").getAbsolutePath());
    }

    /**
     * //生成标准px文件 ,需要生成一系列的dimens 的时候调用
     */
    public static void createBase(String path) {
        File resFile = new File(path + "/src/main/res");
        DimensCreator.createBaseFile(resFile.getAbsolutePath(), 360);
    }

    /**
     * 根据已经生成的标准 dimens 文件1242 文件夹下的东西 ，生成其余目录 默认已经生成了1-400px的了 ，其他的时候，手动添加进来的dimens后要调用一下下面的代码
     */
    public static void createAll(String path) {
        DimensParser parser = new DimensParser();
        try {

            File resFile = new File(path + "/src/main/res");

            List<DimenValues> list1 = parser.parse(new FileInputStream(resFile.getAbsolutePath() + "/values-sw360dp/dimens.xml"));
            DimensCreator creator = new DimensCreator(resFile.getAbsolutePath(), list1);
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