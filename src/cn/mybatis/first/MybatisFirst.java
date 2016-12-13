package cn.mybatis.first;

import cn.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
            System.out.print(user.toString() + "\n bamboo唯一标识");

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (sqlSession != null) {
                //释放资源
                sqlSession.close();
            }
        }
    }

    //根据用户名进行模糊查询
    @Test
    public void findUsserByName() {
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
            List<User> user = sqlSession.selectList("test.findUserByName", "张三");
            System.out.print(user.toString() + "\n bamboo唯一标识");
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (sqlSession != null) {
                //释放资源
                sqlSession.close();
            }
        }
    }

    //添加用户信息
    @Test
    public void insertUser() {
        SqlSession sqlSession = null;
        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";

            InputStream inputStream = Resources.getResourceAsStream(resource);

            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //通过工厂得到SqlSession
            sqlSession = sqlSessionFactory.openSession();
            //插入用户对象
            User user = new User();
            user.setUsername("宫子");
            user.setBirthday(new Date());
            user.setSex("女");
            user.setAddress("江苏扬州");
            //list中的user和映射文件中的resultType所指定的类型一致
            sqlSession.insert("test.insertUser", user);
            System.out.print(user.getId());
            //提交事务
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (sqlSession != null) {
                //释放资源
                sqlSession.close();
            }
        }
    }

    //根据id删除用户信息
    @Test
    public void deleteUserTest() {
        SqlSession sqlSession = null;
        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";

            InputStream inputStream = Resources.getResourceAsStream(resource);

            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //通过工厂得到SqlSession
            sqlSession = sqlSessionFactory.openSession();
            //传入id，删除用户
            sqlSession.delete("test.deleteUser", 26);
            //提交事务
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (sqlSession != null) {
                //释放资源
                sqlSession.close();
            }
        }
    }

    //根据id更新用户信息
    @Test
    public void updateUserTest() {
        SqlSession sqlSession = null;
        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";

            InputStream inputStream = Resources.getResourceAsStream(resource);

            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //通过工厂得到SqlSession
            sqlSession = sqlSessionFactory.openSession();
            //插入用户对象
            User user = new User();
            user.setId(16);
            user.setUsername("沓时娇");
            user.setBirthday(new Date());
            user.setSex("女");
            user.setAddress("江苏苏州");
            //list中的user和映射文件中的resultType所指定的类型一致
            sqlSession.update("test.updateUser", user);
            //提交事务
            sqlSession.commit();

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
