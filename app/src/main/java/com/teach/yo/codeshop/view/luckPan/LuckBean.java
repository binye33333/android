package com.teach.yo.codeshop.view.luckPan;

import java.util.List;

/**
 * Created by chenyou729 on 17/3/20.
 */

public class LuckBean {
    public int lottery_id; //抽奖转盘ID
    public String lottery_desc; //抽奖转盘描述
    public String wheel_icon_url;//转盘url
    public String point_icon_url; //指针url
    public String background_icon_url;// 转盘背景图
    public List<LuckItemInfo> details;
}
