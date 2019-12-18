package com.frs.demo.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtil {

    public static SqlSessionFactory factory;
    static{
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream in = MybatisUtil.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        factory = builder.build(in);
    }

    public static SqlSession openSqlSession(){
        return factory.openSession();
    }

    public static void close(SqlSession sqlSession){
        if(null != sqlSession){
            sqlSession.close();
        }
    }
}
