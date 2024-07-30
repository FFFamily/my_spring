package org.tutu.springframework.test.bean;

import org.tutu.springframework.beans.factory.DisposableBean;
import org.tutu.springframework.beans.factory.InitializingBean;

public class UserService  implements InitializingBean, DisposableBean {
    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId)+", 公司："+company+", 地点"+location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }
}
