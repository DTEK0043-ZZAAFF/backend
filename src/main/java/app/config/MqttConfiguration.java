package app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * MQTT configuration class
 *
 * Automatically configures MQTT messaging.
 */
@Configuration
@IntegrationComponentScan
public class MqttConfiguration {
    /**
     * MQTT server URL to connect. Value annotation makes Spring automatically read value from properties.
     */
    @Value("${my.mqtt.url}")
    private String mqttUrl;

    /**
     * Client id to use when connecting to MQTT server
     */
    @Value("${my.mqtt.clientId}")
    private String clientId;

    @Value("${my.mqtt.user}")
    private String user;

    @Value("${my.mqtt.pass}")
    private String pass;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs(mqttUrl);
        factory.setUserName(user);
        factory.setPassword(pass);
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("/NOT-CONFIGURED/");
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * This interface enables sending messages
     */
    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MyGateway {

        /** Sends data to MQTT server
         *
         * @param data data to send into MQTT server with default topic
         */
        void sendToMqtt(String data);

        /** Sends data to MQTT server
         *
         * Message object includes topic for message being sent.
         *
         * @param message Message to send.
         */
        void sendToMqtt(Message message);

    }
}