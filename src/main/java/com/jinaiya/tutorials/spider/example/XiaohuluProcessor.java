package com.jinaiya.tutorials.spider.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Jin
 * @date 2019/1/14
 */
public class XiaohuluProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.putField("No.", page.getHtml().xpath("//span[@class='num']/text()"));
        page.putField("平台：", page.getHtml().xpath("//dl[@class='clearfix']/dd/h4/i/text()"));
        page.putField("主播：", page.getHtml().xpath("//dl[@class='clearfix']/dd/h4/text()"));
        page.putField("礼物价值：", page.getHtml().xpath("//td[@class='hsnum']/text()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new XiaohuluProcessor()).addUrl("https://www.xiaohulu.com/#miao").thread(5).run();
    }
}
