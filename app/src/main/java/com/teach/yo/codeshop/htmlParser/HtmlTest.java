package com.teach.yo.codeshop.htmlParser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.teach.yo.codeshop.R;

import java.util.ArrayList;

/**
 * 说明：
 *
 * @author chenyou
 * @date 2016/04/05
 */
public class HtmlTest extends Activity {

    TextView textView;
    public static String htmlContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_text_view_parser);
        textView = (TextView) findViewById(R.id.test_html_textView);

        htmlContent = "<h1>习近平为党员干部做人做事划出的四条底线</h1><br>&nbsp;&nbsp;&nbsp;&nbsp;底线是事物质变的分界线、做人做事的警戒线，不可踩、更不可越。党员干部必须牢固树立底线意识<br><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;时刻牢记越过底线的严重后果，始终警醒自己坚守底线。近日，习近平在对“两学一做”学习教育的重要指示中强调，" +
                "要“把做人做事的底线划出来”。<a href=\"http://www.dreamdu.com/xhtml/\">超链接</a>党员干部必须坚守哪些底线呢？请随“学习中国”小编一起学习习近平为党员干部做人做事划出的四条底线。<br>"
                + "<img src='http://upload-images.jianshu.io/upload_images/259-fd5b57a0954ae387.jpg?" +
                "imageMogr2/auto-orient/strip%7CimageView2/2/w/1240'><br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;底线是事物质变的分界线、做人做事的警戒线，不可踩、更不可越。党员干部必须牢固树立底线意识，" +
                "时刻牢记越过底线的严重后果，始终警醒自己坚守底线。近日，习近平在对“两学一做”学习教育的重要指示中强调，" +
                "要“把做人做事的底线划出来”。<a href=\"http://www.dreamdu.com/xhtml/\">超链接</a>党员干部必须坚守哪些底线呢？请随“学习中国”小编一起学习习近平为党员干部做人做事划出的四条底线。<br>"
                + "<img src='http://upload-images.jianshu.io/upload_images/259-fd5b57a0954ae387.jpg?" +
                "imageMogr2/auto-orient/strip%7CimageView2/2/w/1240'><br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;底线是事物质变的分界线、做人做事的警戒线，不可踩、更不可越。党员干部必须牢固树立底线意识，" +
                "时刻牢记越过底线的严重后果，始终警醒自己坚守底线。近日，习近平在对“两学一做”学习教育的重要指示中强调，" +
                "要“把做人做事的底线划出来”。党员干部必须坚守哪些底线呢？请随“学习中国”小编一起学习习近平为党员干部做人做事划出的四条底线。<br>"
                + "<img src='http://www.chinanews.com/cr/2016/0410/2989689424'><br>";

        HtmlUtil.showHtml(textView, htmlContent, new SpanClickListener() {
            @Override
            public void onClick(int type, String url) {
                if (type == LinkMovementMethodExt.CLICKABLE_SPAN_IMAGE) {
                    Toast.makeText(HtmlTest.this, "click image:" + url, Toast.LENGTH_SHORT).show();
                } else if (type == LinkMovementMethodExt.CLICKABLE_SPAN_URL) {
                    Toast.makeText(HtmlTest.this, "click url:" + url, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
