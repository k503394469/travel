package com.liu.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class JsoupTest {
    public static void main(String[] args) throws Exception {
//        Document doc = Jsoup.parse("http://femhzs.mofcom.gov.cn/fecpmvc/pages/fem/CorpJWList_nav.pageNoLink.html?session=T&sp=28");
        for (int i = 0; i < 3; i++) {
            Document doc = Jsoup.connect("http://femhzs.mofcom.gov.cn/fecpmvc/pages/fem/CorpJWList_nav.pageNoLink.html?sp="+i+"").get();
            Elements select = doc.select("#Body > div > div > div > div.m-data-wrap.f-mt30 > table > tbody");
            Elements tr=null;
            tr = select.select("tr");
            Elements td = tr.select("td");
            System.out.println(td.text());
        }

    }
}
