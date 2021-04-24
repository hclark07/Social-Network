package base;

import base.controller.LikesController;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class AppConfig {

    final static Logger socialLog = Logger.getLogger(LikesController.class);

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        socialLog.info("Calling MultipartResolver function");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10_000_000); // 10mb
        return multipartResolver;
    }

}
