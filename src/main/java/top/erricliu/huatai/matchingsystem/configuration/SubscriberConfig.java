package top.erricliu.huatai.matchingsystem.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import top.erricliu.huatai.matchingsystem.service.EnduranceService;


@Configuration
    @AutoConfigureAfter({EnduranceService.class})
    public class SubscriberConfig {

        /**
         * 消息监听适配器，注入接受消息方法，输入方法名字 反射方法
         */
        @Bean
        public MessageListenerAdapter getMessageListenerAdapter(EnduranceService enduranceService) {
            return new MessageListenerAdapter(enduranceService, "messageGetAndEndurance"); //当没有继承MessageListener时需要写方法名字
        }

        /**
         * 创建消息监听容器
         */
        @Bean
        public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
            RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
            redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
            redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic("ClearingInfo"));
            return redisMessageListenerContainer;
        }

}
