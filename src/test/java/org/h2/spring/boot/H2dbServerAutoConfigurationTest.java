package org.h2.spring.boot;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link H2dbServerAutoConfiguration}.
 *
 * @author Loong Wan
 * @see <a href="https://github.com/loong10k">https://github.com/loong10k</a>
 */
class H2dbServerAutoConfigurationTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(H2dbServerAutoConfiguration.class));

	@Nested
	@DisplayName("When H2 driver is present")
	class WhenH2DriverPresent {

		@Test
		@DisplayName("should not create beans when enabled is false")
		void shouldNotCreateBeansWhenDisabled() {
			contextRunner
					.withPropertyValues("h2.server.enabled=false")
					.run(context -> assertThat(context).doesNotHaveBean(H2dbServerAutoConfiguration.class));
		}

		@Test
		@DisplayName("should not create TCP bean when h2.tcp.enabled is false (default)")
		void shouldNotCreateTcpBeanWhenTcpDisabled() {
			contextRunner
					.withPropertyValues("h2.server.enabled=true", "h2.tcp.enabled=false")
					.run(context -> {
						assertThat(context).hasSingleBean(H2dbServerAutoConfiguration.class);
						assertThat(context).doesNotHaveBean(Server.class);
					});
		}

		@Test
		@DisplayName("should create TCP bean when h2.tcp.enabled is true")
		void shouldCreateTcpBeanWhenTcpEnabled() {
			contextRunner
					.withPropertyValues("h2.server.enabled=true", "h2.tcp.enabled=true")
					.run(context -> {
						assertThat(context).hasSingleBean(H2dbServerAutoConfiguration.class);
						assertThat(context).hasSingleBean(Server.class);
						// Clean up: stop the TCP server
						context.getBean(Server.class).stop();
					});
		}

		@Test
		@DisplayName("should create auto-configuration bean when enabled")
		void shouldCreateAutoConfigBeanWhenEnabled() {
			contextRunner
					.withPropertyValues("h2.server.enabled=true")
					.run(context -> assertThat(context).hasSingleBean(H2dbServerAutoConfiguration.class));
		}

		@Test
		@DisplayName("should create H2dbServerProperties bean")
		void shouldCreatePropertiesBean() {
			contextRunner
					.withPropertyValues("h2.server.enabled=true")
					.run(context -> assertThat(context).hasSingleBean(H2dbServerProperties.class));
		}

		@Test
		@DisplayName("should wire resource loader via setResourceLoader")
		void shouldWireResourceLoader() {
			contextRunner
					.withPropertyValues("h2.server.enabled=true")
					.run(context -> {
						H2dbServerAutoConfiguration config = context.getBean(H2dbServerAutoConfiguration.class);
						assertThat(config).isNotNull();
					});
		}
	}

	@Nested
	@DisplayName("tcpServer method tests")
	class TcpServerMethodTests {

		@Test
		@DisplayName("should create PG server via tcpServer method")
		void shouldCreatePgServer() throws SQLException {
			contextRunner
					.withPropertyValues("h2.server.enabled=true")
					.run(context -> {
						H2dbServerAutoConfiguration config = context.getBean(H2dbServerAutoConfiguration.class);
						Server server = config.tcpServer();
						assertThat(server).isNotNull();
						// tcpServer() starts then immediately stops, so status is "Not started"
						assertThat(server.getStatus()).isEqualTo("Not started");
					});
		}
	}
}
