package com.example.cchat;

import com.example.cchat.dao.CommentDao;
import com.example.cchat.dao.NewsDao;
import com.example.cchat.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class CchatApplicationTests {

    @Autowired
    NewsDao newsDao;

    @Test
    void contextLoads() throws IOException {
        //解析网页
        Document document = Jsoup.parse(new URL("https://news.hzu.edu.cn/2021/0701/c9430a213751/page.htm"),30000);
        Element masthead = document.select("div.wp_articlecontent").first();
        String els = masthead.html();
        System.out.println(els.replaceAll("src=\"","src=\"https://news.hzu.edu.cn"));

    }

    @Test
    void test() throws IOException {
        //解析网页
        Document document = Jsoup.parse(new URL("https://news.hzu.edu.cn/ybdtlc/list5.htm"), 30000);
        //根据class来获取文档
        Elements elements = document.getElementsByClass("column-news-title");
        int n = 0;

        //遍历获取
        for (Element el : elements) {
            String href = el.getElementsByTag("a").eq(0).attr("href");

            String url1 = "https://news.hzu.edu.cn" + href;
//            System.out.println(url1);
            //解析网页
            Document document1 = Jsoup.parse(new URL(url1), 30000);
            //根据class来获取标题
            String title = document1.getElementsByClass("arti-title").text();
            //获取时间
            String timea = document1.getElementsByClass("arti-update").text();
            //获取内容
            System.out.println(title);
            System.out.println(timea);
            Element masthead = document1.select("div.wp_articlecontent").first();
            String content1 = masthead.html();
            String content = content1.replaceAll("src=\"", "src=\"https://news.hzu.edu.cn");

            //对时间字符串进行处理，转换成date类型
            String[] time = timea.split("日");
            String a = time[0].replaceAll("年", "-");
            String date1 = a.replaceAll("月", "-");
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                //使用SimpleDateFormat的parse()方法生成Date
                date = sf.parse(date1);
//                //打印Date
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            News news1 = new News();
            news1.setN_content(content);
            news1.setN_time(date);
            news1.setN_title(title);
            news1.setN_views("0");
            newsDao.addNews(news1);
        }
    }

}
