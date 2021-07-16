package com.example.cchat.utils;

import com.example.cchat.dao.NewsDao;
import com.example.cchat.entity.News;
import com.example.cchat.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Resource;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class  NewsReptile{

    @Resource
    private NewsDao newsDao;

    private String NewUrl;

    public static void main(String[] args) throws Exception{

        //创建对象
        JavaReptile reptile = new JavaReptile();
        //调用相关的方法
        reptile.getAllHistory("https://news.hzu.edu.cn/ybdtlc/list.htm");  //获取所有链接

    }

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
        System.out.println(time);
        Element masthead = document.select("div.wp_articlecontent").first();
        String els = masthead.html();
        els.replaceAll("src=\"","src=\"https://news.hzu.edu.cn");
        System.out.println(els);

    }

    /**
     * 存储数据
     */
    //public

    /**
     * 获取所有
     */
    public void getAllHistory(String url) throws Exception{
        //解析网页
        Document document = Jsoup.parse(new URL(url),30000);
        //根据class来获取文档
        Elements elements = document.getElementsByClass("column-news-title");
        int n = 0;

        //遍历获取
        for (Element el: elements) {
            String href = el.getElementsByTag("a").eq(0).attr("href");

            String url1 = "https://news.hzu.edu.cn" + href;
//            System.out.println(url1);
            //解析网页
            Document document1 = Jsoup.parse(new URL(url1),30000);
            //根据class来获取标题
            String title = document1.getElementsByClass("arti-title").text();
            //获取时间
            String timea = document1.getElementsByClass("arti-update").text();
            //获取内容
            Element masthead = document1.select("div.wp_articlecontent").first();
            String content1 = masthead.html();
            String content = content1.replaceAll("src=\"","src=\"https://news.hzu.edu.cn");

            //对时间字符串进行处理，转换成date类型
            String[] time = timea.split("日");
            String a = time[0].replaceAll("年","-");
            String date1 =  a.replaceAll("月","-");
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                //使用SimpleDateFormat的parse()方法生成Date
                date = sf.parse(date1);
//                //打印Date
//                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            News news1 = new News();
            news1.setN_content(content);
            news1.setN_time(date);
            news1.setN_title(title);
            news1.setN_views("0");
            newsDao.addNews(news1);


//            System.out.println(news1);
//            newsService.addNews(news1);
//            n++;
//            System.out.println(title);
//            System.out.println(date1);
//            System.out.println(content);
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

