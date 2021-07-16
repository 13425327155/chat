package com.example.cchat.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class JavaReptile {

    private String NewUrl;

    public static void main(String[] args) throws Exception{
        //创建对象
        JavaReptile reptile = new JavaReptile();
        //调用相关的方法
        reptile.getAllHistory("https://news.hzu.edu.cn/xxywsh/list.htm");  //获取所有链接

    }

    /**
     * 获取最新的
     * @throws Exception
     */
//    public String getNewHistory(String url) throws Exception{
//        //解析网页
//        Document document = Jsoup.parse(new URL(url),30000);
//        //根据class来获取文档
//        Elements elements = document.getElementsByClass("showMoreNChildren");
//        //遍历这个文档
//        for (Element el: elements) {
//            //获取“a”标签下的第1个“href”里的内容
//            NewUrl = el.getElementsByTag("a").eq(0).attr("href");
//            System.out.println(NewUrl);
//        }
//        return NewUrl;
//    }

    /**
     * 获取资讯内容
     */
    public void getHistoryText(String url)throws Exception{

        //解析网页
        Document document = Jsoup.parse(new URL("https://news.hzu.edu.cn/2021/0701/c9430a213751/page.htm"),30000);
        //根据class来获取文档
        Elements title = document.getElementsByClass("arti-title");
        Elements time = document.getElementsByClass("arti-update");
        System.out.println(title);
        System.out.println(time.text());

        Element masthead = document.select("div.wp_articlecontent").first();
        String els = masthead.html();
        els.replaceAll("src=\"","src=\"https://news.hzu.edu.cn");
        System.out.println(els);

    }

    /**
     * 获取所有
     */
    public void getAllHistory(String url) throws Exception{
        //解析网页
        Document document = Jsoup.parse(new URL(url),30000);
        //根据class来获取文档
        Elements elements = document.getElementsByClass("maR0");
        //遍历获取
        for (Element el: elements) {
            String href = el.getElementsByTag("a").eq(0).attr("href");
            System.out.println(href);
        }
    }
}

//        //解析网页
//        Document document = Jsoup.parse(new URL(url),30000);
//        //根据class来获取文档
//        Elements title = document.getElementsByClass("big_title");
//        System.out.println(title.text());
//        //根据id来获取文档内容
//        Element content = document.getElementById("font_area");
//        //根据 p 标签来获取文档内容
//        Elements contents_text = content.getElementsByTag("p");
//        //遍历获取
//        for (Element el_text: contents_text) {
//            String text = el_text.text();
//            System.out.println(text);
//        }
