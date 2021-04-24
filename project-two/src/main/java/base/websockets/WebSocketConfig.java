package base.websockets;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    final static Logger socialLog = Logger.getLogger(SocketHandler.class);

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        socialLog.info("registerWebsocketHandlers method called");
        registry.addHandler(myHandler(), "/chat").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler myHandler() {
        socialLog.info("Creating WebSocketHandler bean");
        return new SocketHandler();
    }

}