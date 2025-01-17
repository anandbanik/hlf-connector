package hlf.java.rest.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Kafka properties is added for fetching Kafka properties as configuration and can be used
 * in Consumer and Producer using @Autowired
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

  private Consumer integration;
  private Producer eventListener;

  @Getter
  @Setter
  @ConditionalOnProperty("kafka.integration.brokerHost")
  @Configuration
  @ConfigurationProperties(prefix = "kafka.integration")
  public static class Consumer extends SSLProperties {
    private String brokerHost;
    private String groupId;
    private String topic;
    private String saslJaasConfig;
  }

  @Getter
  @Setter
  @ConditionalOnProperty("kafka.event-listener.brokerHost")
  @Configuration
  @ConfigurationProperties(prefix = "kafka.event-listener")
  public static class Producer extends SSLProperties {
    private String brokerHost;
    private String topic;
    private String saslJaasConfig;
  }

  /** The type Ssl properties is added for configuring SSL configuration for Kafka Cluster. */
  @Getter
  @Setter
  public static class SSLProperties {

    protected boolean sslEnabled;
    protected String securityProtocol;
    protected String sslKeystoreLocation;
    protected String sslKeystorePassword;
    protected String sslTruststoreLocation;
    protected String sslTruststorePassword;
    protected String sslKeyPassword;
  }
}
