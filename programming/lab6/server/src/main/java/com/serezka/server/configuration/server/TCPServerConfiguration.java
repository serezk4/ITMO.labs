package com.serezka.server.configuration.server;

import com.serezka.server.handler.command.Command;
import com.serezka.server.handler.handler.Handler;
import com.serezka.server.handler.io.channel.ChannelWorker;
import com.serezka.server.handler.io.format.FormatWorker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.List;

@Configuration
@EnableIntegration
@PropertySource("classpath:server.properties")
@Log4j2
public class TCPServerConfiguration {
    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory(@Value("${server.port}") int port) {
        log.info("selected port: {}", port);

        TcpNetServerConnectionFactory factory = new TcpNetServerConnectionFactory(port);

        factory.setSerializer(TcpCodecs.lengthHeader4());
        factory.setDeserializer(TcpCodecs.lengthHeader4());

        return factory;
    }

    @Bean
    public TcpInboundGateway tcpInGateway(AbstractServerConnectionFactory connectionFactory) {
        TcpInboundGateway inGateway = new TcpInboundGateway();

        inGateway.setConnectionFactory(connectionFactory);
        inGateway.setRequestChannelName("inboundChannel");

        return inGateway;
    }

    @Bean
    public MessageChannel inboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "inboundChannel")
    public MessageHandler handler(List<Command> commands,
                                  @Qualifier("csvFormatWorker") FormatWorker formatWorker,
                                  @Qualifier("TCPChannelWorker") ChannelWorker channelWorker) {
        Handler handler = new Handler(formatWorker, channelWorker);
        handler.setCommands(commands);
        return handler::handle;
    }

    @Bean
    @ServiceActivator(inputChannel = "outboundChannel")
    public MessageHandler tcpOutboundAdapter(AbstractServerConnectionFactory connectionFactory) {
        TcpSendingMessageHandler adapter = new TcpSendingMessageHandler();
        adapter.setConnectionFactory(connectionFactory);
        return adapter;
    }

    @Bean
    public MessageChannel outboundChannel() {
        return new DirectChannel();
    }
}
