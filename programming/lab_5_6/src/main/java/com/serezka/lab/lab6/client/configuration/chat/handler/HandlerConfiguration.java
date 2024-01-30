//package com.serezka.lab.lab6.client.configuration.chat.handler;
//
//import com.serezka.lab.core.io.client.ClientWorker;
//import com.serezka.lab.core.io.console.ConsoleWorker;
//import com.serezka.lab.lab6.client.handler.Client;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HandlerConfiguration {
//    @Bean
//    public Client client(@Qualifier("TCPClientWorker") ClientWorker channelWorker,
//                         @Qualifier("bufferedConsoleWorker") ConsoleWorker consoleWorker) {
//        return new Client(channelWorker, consoleWorker);
//    }
//}
