package com.teach.yo.codeshop.htmlParser;

import android.text.Html;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：
 *
 * @author chenyou
 * @date 2016/04/04
 */
public class HtmlUtil {

    public static void showHtml(TextView textView, String resource, SpanClickListener listener) {
        HtmlImageGetter getter = new HtmlImageGetter(textView);
//        textView.setText(Html.fromHtml(resource, getter, null));
        textView.setText(Html.fromHtml(resource));
        textView.setMovementMethod(new LinkMovementMethodExt(listener));
        textView.setTag(getter);
    }


    public static String removeImageTag(String resource) {
        String regex = "";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(regex);
        if (matcher.find()) {
            return matcher.replaceAll("");
        }

        return resource;
    }


}
