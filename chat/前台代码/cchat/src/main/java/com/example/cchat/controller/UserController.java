package com.example.cchat.controller;


import com.example.cchat.entity.RespPageBean;
import com.example.cchat.entity.User;
import com.example.cchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAllUsers();
    }

    @GetMapping("/findByPage/{page}/{size}")
    public RespPageBean findByPage(@PathVariable("page") Integer page,
                                   @PathVariable("size") Integer size) {
        return userService.findByPage(page, size);
    }

    @GetMapping("/findByName/{user_name}")
    public List<User> findByName(@PathVariable("user_name") String user_name){
        return userService.findByName(user_name);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteById(id);
        if (userService.findById(id) == null){
            return "delete success!";
        }else return "delete error!";
    }

    //注册账号
    @PostMapping(value = "/api/register")
    public boolean insertUser(
            @RequestParam("user_name") String user_name,
            @RequestParam("user_number") String user_number,
            @RequestParam("user_password") String user_password,
            @RequestParam("user_phone") String user_phone,
            @RequestParam("user_type") String user_type, MultipartFile user_img
    ){
        //1、创建一个User对象接受请求过来的信息
        User user = new User();
        user.setUser_name(user_name);
        user.setUser_number(user_number);
        user.setUser_password(user_password);
        user.setUser_phone(user_phone);
        user.setUser_type(user_type);
        if (user_img != null){
            try {
//                BASE64Encoder encoder = new BASE64Encoder();
                Base64.Encoder encoder = Base64.getEncoder();
                //通过base64来转化图片
//                String data = encoder.encode(user_img.getBytes());
                String data = encoder.encodeToString(user_img.getBytes());
                //将data写入数据库
                user.setUser_img(data);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//        //2、获得头像的文件名
//        String fileName = user_img.getOriginalFilename();
//        //3、防止图片名相同出现bug，使用uuid加密
//        String hToken = UUID.randomUUID().toString();
//        String HeadName = hToken + fileName;
//        //4、设置图片存放的文件夹地址
//        String filePath = "C:\\Users\\acer\\Desktop\\img\\";
//        //5、获取图片路径
//        String fileAddress = filePath + HeadName;
//        //6、使用工具类上传图片，并且把图片路径插入数据库
//        try {
//            FileUtil.uploadFile(user_img.getBytes(),filePath,HeadName);
//            user.setUser_img(fileAddress);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return userService.insertUser(user,user_number);
    }

    @PostMapping(value = "api/userLogin")
    public User userLogin(@RequestBody Map<String,String> param, HttpServletRequest request){
        String user_number = param.get("user_number");
        String user_password = param.get("user_password");
        //登录判断
        User user = userService.userLogin(user_number,user_password);
        if (user != null){
            //登陆成功利用session存储学号和密码
            HttpSession session =request.getSession(true);
            session.setAttribute("user",user);
            return user;
        }
        return null;
    }

    @RequestMapping(value = "unLogin")
    public String unLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "unLogin OK";
    }

    //根据学号查询个人信息
    @GetMapping(value = "/api/getUserByUserNum/{user_number}")
    public User getUserByUserNum(@PathVariable("user_number") String user_number){
        final String pre = "data:image/jpeg;base64,";
        User user = userService.getUserByUserNum(user_number);
        if (user != null){
            user.setUser_img(pre + user.getUser_img());
//            log.info(user.getUser_img());
            return user;
        }else {
//            log.info("没有对应记录,查询学号为" + user_number);
            return null;
        }
    }

    @PostMapping(value = "/api/updateUserInfo")
    public boolean updateUserInfo(
            @RequestParam(name = "user_name",required = false) String user_name,
            @RequestParam(name = "user_number",required = false) String user_number,
            @RequestParam(name = "user_password",required = false) String user_password,
            @RequestParam(name = "user_phone",required = false) String user_phone,
            @RequestParam(name = "user_type",required = false) String user_type,
            @RequestParam(name = "user_img",required = false) MultipartFile user_img
    ){
        User user = new User();
        user.setUser_number(user_number);
        //if判断防止出现null异常
        if (user_password != null && !user_password.equals("")){
            user.setUser_password(user_password);
        }
        if (user_phone != null && !user_phone.equals("")){
            user.setUser_phone(user_phone);
        }
        if (user_type != null && !user_type.equals("")){
            user.setUser_type(user_type);
        }
        if (user_name != null && !user_name.equals("")){
            user.setUser_name(user_name);
        }
        if (user_img != null){
            try {
                Base64.Encoder encoder = Base64.getEncoder();
//                BASE64Encoder encoder = new BASE64Encoder();
                //通过base64来转化图片
//                String data = encoder.encode(user_img.getBytes());
                String data = encoder.encodeToString(user_img.getBytes());
                //将data写入数据库
                user.setUser_img(data);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return userService.updateUserInfo(user);
    }

    @PostMapping("/api/updatePassword")
    public boolean updatePassword(
            @RequestParam(name = "user_number") String user_number,
            @RequestParam(name = "user_password",required = false) String user_password,
            @RequestParam(name = "old_password",required = false) String old_password
    ){
        return userService.updatePassword(old_password,user_password,user_number);
    }
}
