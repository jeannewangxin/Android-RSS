package fr.ws.reader.util;

import com.joanzapata.iconify.Icon;

public enum FontelloIcons implements Icon {

    icon_eye('\uE806'),
    icon_pencil('\uE80e'),      //笔
    icon_garbage('\uE83a'),     //垃圾桶
    icon_favorite('\uE803'),    //心（空心）
    icon_category('\uE81b'),   //分类
    icon_choose('\uF0b0'),    //排序
    icon_heart('\uE829'),      //心（实心）
    icon_search('\uE801'),    //搜索
    icon_person('\uF2c0'),     //个人
    icon_contact('\uE823'),    //联系
    icon_photo('\uE808'),    //图片
    icon_logout('\uE826'),
    icon_delete('\uF1f8'),
    icon_down_dir('\uE813'),
    icon_bell('\uE810'),
    icon_mail('\uF0e0'),
    icon_phone('\uF10b'),
    fe_github('\uE816');//注：这里我并没有把所有的图标都加上

    char character;

    FontelloIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}