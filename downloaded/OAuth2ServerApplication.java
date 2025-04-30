package com.hrh.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ProjectName: Spring-Security-oAuth2
 * @Package: com.hrh.oauth2
 * @ClassName: Oauth2ApplicaDtion
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/2/4  下午 15:48
 * @Version: 1.0
 */
@SpringBootApplication
public class OAuth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServerApplication.class,args);
    }
}
