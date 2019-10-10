package top.erricliu.huatai.matchingsystem.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import top.erricliu.huatai.matchingsystem.service.PersistenceService;

/**
 * @author liubi
 * @date 2019-10-08 16:52
 **/

@Configuration
    @AutoConfigureAfter({PersistenceService.class})
    public class SubscriberConfig {
        // 消息监听适配器，注入接受消息方法，输入方法名字 反射方法
        @Bean
        public MessageListenerAdapter getMessageListenerAdapter(PersistenceService persistenceService) {
            return new MessageListenerAdapter(persistenceService, "messageGetAndPersistence");
            //当没有继承MessageListener时需要写方法名字
        }

        // 创建消息监听容器
        @Bean
        public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
            RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
            redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
            redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic("TransactionMsgCache"));
            return redisMessageListenerContainer;
        }

}
