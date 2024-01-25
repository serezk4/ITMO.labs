package com.serezka.client.configuration.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;
import org.springframework.messaging.MessageChannel;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.MessageHandler;

@Configuration
@PropertySource("classpath:client.properties")
@Log4j2
public class TCPClientConfiguration {

    @Bean
    public AbstractClientConnectionFactory clientConnectionFactory(@Value("${server.host}") String host,
                                                                   @Value("${server.port}") int port) {
        log.info("Connecting to server at {}:{}", host, port);

        TcpNetClientConnectionFactory factory = new TcpNetClientConnectionFactory(host, port);

        factory.setSerializer(TcpCodecs.lengthHeader4());
        factory.setDeserializer(TcpCodecs.lengthHeader4());

        return factory;
    }

    @Bean
    public TcpOutboundGateway tcpOutGateway(AbstractClientConnectionFactory clientConnectionFactory) {
        TcpOutboundGateway outGateway = new TcpOutboundGateway();

        outGateway.setConnectionFactory(clientConnectionFactory);
        outGateway.setOutputChannelName("outboundChannel");

        return outGateway;
    }

    @Bean
    public MessageChannel outboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel inboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "inboundChannel")
    public MessageHandler responseHandler() {
        // Здесь должен быть ваш обработчик ответов от сервера
        return message -> {
            // Логика обработки ответа
            log.info("Response received: {}", message.getPayload());
        };
    }
}
