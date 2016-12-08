package cn.mybatis.first;

import cn.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis入门程序
 * Created by p1411 on 2016/12/8.
 */
public class MybatisFirst {

    //根据id查询用户信息,得到一条记录结果
    @Test
    public void findUserById() throws IOException {
        SqlSession sqlSession = null;
        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";

            InputStream inputStream = Resources.getResourceAsStream(resource);

            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //通过工厂得到SqlSession
            sqlSession = sqlSessionFactory.openSession();

            //通过SqlSession操作数据库
            //第一个参数:映射文件中的statement的id,等于namespace+"."+statement的id
            //第二个参数:指定和映射文件中所匹配的parameterType类型的参数
            //sqlSession.selectOne结果是与映射文件中所匹配的resultType类型的对象
            User user = sqlSession.selectOne("test.findUserById", 1);
            System.out.print(user.toString()+"\n bamboo唯一标识");

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (sqlSession != null) {
                //释放资源
                sqlSession.close();
            }
        }
    }

}
