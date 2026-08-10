package org.h2.spring.boot;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * Auto-configuration for H2 Database server.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @see <a href="https://github.com/loong10k">https://github.com/loong10k</a>
 */
@Configuration
@ConditionalOnClass(org.h2.Driver.class)
@ConditionalOnProperty(prefix = H2dbServerProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ H2dbServerProperties.class })
public class H2dbServerAutoConfiguration implements ResourceLoaderAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ResourceLoader resourceLoader;

	@Autowired
	private H2dbServerProperties properties;

	/**
	 * Start the H2 TCP server.
	 * @return the started H2 TCP server
	 * @throws SQLException if the server fails to start
	 */
	@Bean
	@ConditionalOnExpression("${h2.tcp.enabled:false}")
	public Server h2TcpServer() throws SQLException {
		logger.info("Starting H2 TCP server on port {}", properties.getTcpPort());
		return Server.createTcpServer("-tcpAllowOthers", "-tcpPort", properties.getTcpPort()).start();
	}

	/**
	 * Start the H2 PG server.
	 * @return the started H2 PG server
	 * @throws SQLException if the server fails to start
	 */
	public Server tcpServer() throws SQLException {
		Server server = Server.createPgServer("-tcpAllowOthers", "-tcpPort", properties.getTcpPort());
		server.start();
		server.stop();
		return server;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
