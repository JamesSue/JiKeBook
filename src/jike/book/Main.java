package jike.book;

import jike.book.map.InterfaceJiKeUserMap;
import jike.book.pojo.Author;
import jike.book.pojo.JiKeUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static Main main;

    public static void main(String[] args) {
	// write your code here
        System.out.println("this is main");
        main=new Main();
       // main.findUserById();
       // main.inserUser();
        //main.updateUser();
       // main.deleteUser();
       // main.loginSelect();
       // main.selectAllUser();
       // main.selectUsersByMap();
        main.insertAuthor();



    }



    private void  findUserById(){
        String resource = "jike/book/map/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        JiKeUser jiKeUser=session.selectOne("findById", 1);
        System.out.println(jiKeUser.getUserName());
        session.close();

    }

    private void  insertUser(){
        String resource = "jike/book/map/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        JiKeUser user=new JiKeUser();
        user.setPassword("654321");
        user.setUserName("xiaoZhuang");
        session.insert("insertUser",user);
        session.commit();
        session.close();
    }

    private void  updateUser(){
        String resource = "jike/book/map/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        JiKeUser user=new JiKeUser();
        user.setPassword("123456");
        user.setUserName("chiyu");
        user.setId(4);
        session.update("updateUser",user);
        session.commit();
        session.close();
    }

    private void deleteUser(){
        String resource = "jike/book/map/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
//        JiKeUser user=new JiKeUser();
//        user.setPassword("123456");
//        user.setUserName("chiyu");
//        user.setId(4);
//        session.update("updateUser",user);
        InterfaceJiKeUserMap ijmap=session.getMapper(InterfaceJiKeUserMap.class);
        ijmap.deleteUser(1);
        session.commit();
        session.close();
    }

    private void loginSelect(){
        String resource = "jike/book/map/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("userName","chiyu1");
        hashMap.put("password","123456");
        /*
        注意 这里传递的参数是 Hashmap 类型而不是JiKeUser 类型，xml 配置文件也就需要 是 hashMap 类型
         */
        JiKeUser user=session.selectOne("loginSelect",hashMap);
        if(user==null){
            System.out.println("输入错误");
        }else{
            System.out.println("登录成功");
        }
        session.close();
    }

    private void selectAllUser(){
        String resource = "jike/book/map/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        List<JiKeUser> all=session.selectList("selectAllUser");
        for(int i=0;i<all.size();i++){
            System.out.println(all.get(i).getUserName()+"------------");
        }


        session.close();
    }

    private void selectUsersByMap(){
        String resource = "jike/book/map/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        List<JiKeUser> all=session.selectList("selectUsersByMap");
        for(int i=0;i<all.size();i++){
            System.out.println(all.get(i).getUserName()+"------------");
        }
        session.close();
    }


    //事物
    private void insertAuthor(){
        String resource = "jike/book/map/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession(false);
        JiKeUser one=new JiKeUser();
        one.setUserName("author001");
        one.setPassword("123456");

        //必须保存one
        session.insert("insertUser",one);
        //执行了上面的语句之后，同时也会给 one 赋值 相应的值
        System.out.println("新加入的用户信息： "+one.getUserName()+"----"+one.getId());

        Author at=new Author();
        at.setJiKeUser(one);
        at.setIdCard("610424");
        at.setRealName("sulong");

        session.insert("insertAuthor",at);
        session.commit();
        session.close();
    }







}
