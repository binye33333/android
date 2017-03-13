package com.chenyoyo.adaption;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by chenyo on 2016/1/14.
 * description：
 */
public class DimensCreator {
    private List<DimenValues> list;
    private static final String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<resources>";
    private static final String xmlFooter = "</resources>";
    private final String DIRTEMPLATE = "values-sw%sdp";
    private final String dimenTemplate = "<dimen name=\"%s\">%sdp</dimen>";
    private final String DIRPath;


    public static void createBaseFile(String path, int size) {
        String dimenTemplate = "<dimen name=\"size_%s_dp\">%s</dimen>";
        File dir = new File(path + "/values-sw360dp");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File baseFile = new File(dir.getAbsolutePath() + "/dimens.xml");
        if (baseFile.exists()) {
            baseFile.delete();
        }

        String data = xmlHeader + "\n";
        for (int i = 1; i < size + 1; i++) {
            String itemData = String.format(dimenTemplate, String.valueOf(i), i + "dp") + "\n";
            data += itemData;
        }
        data += xmlFooter;

        FileOutputStream outputStream = null;
        ByteArrayInputStream inputStream = null;
        try {
            outputStream = new FileOutputStream(baseFile);
            inputStream = new ByteArrayInputStream(data.getBytes());
            byte[] buffer = new byte[inputStream.available()];
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public DimensCreator(String path, List<DimenValues> l) {
        DIRPath = path;
        list = l;
    }

    public void createAll() {
        for (int i : Config.supportDevices) {
            String dir = DIRPath.replace("values", "").trim() + File.separator + String.format(DIRTEMPLATE, "" + i);
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            File file = new File(dirFile.getPath() + File.separator + "dimens.xml");
            if(file.exists()){
                file.delete() ;
            }

            float scale = (float) i / Config.defaultValue;
            createSingleFile(file, scale);
        }
    }


    private void createSingleFile(File file, float scale) {
        String data = xmlHeader + "\n";
        for (DimenValues values : list) {
            String itemValue = values.value;
            if (values.value.contains("dp")) {
                float v = Float.parseFloat(values.value.replace("dp", "").trim());
                // 1242 的图  要先除以 1.15 换算到 1080px 图后做运算,除以3 转成 dp ，然后用dp 做单位
                itemValue = formatDimen(v * scale);
            }
            String itemData = String.format(dimenTemplate, values.name, itemValue) + "\n";
            data += itemData;
        }
        data += xmlFooter;

        FileOutputStream outputStream = null;
        ByteArrayInputStream inputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            inputStream = new ByteArrayInputStream(data.getBytes());
            byte[] buffer = new byte[inputStream.available()];
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static String formatDimen(double dimen) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(dimen);
    }
}
