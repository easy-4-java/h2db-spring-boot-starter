package org.h2.spring.boot;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link H2dbServerProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @see <a href="https://github.com/loong10k">https://github.com/loong10k</a>
 */
class H2dbServerPropertiesTest {

	@Nested
	@DisplayName("Default values")
	class DefaultValues {

		@Test
		@DisplayName("should have correct defaults")
		void shouldHaveCorrectDefaults() {
			H2dbServerProperties props = new H2dbServerProperties();
			assertThat(props.isEnabled()).isFalse();
			assertThat(props.getProtocol()).isEqualTo(H2dbServerProperties.Protocol.HTTP);
			assertThat(props.getTcpPort()).isEqualTo("9092");
			assertThat(props.isTcpAllowOthers()).isFalse();
			assertThat(props.isTcpDaemon()).isFalse();
			assertThat(props.isTcpSSL()).isFalse();
			assertThat(props.getTcpPassword()).isNull();
			assertThat(props.getTcpShutdown()).isNull();
			assertThat(props.isTcpShutdownForce()).isFalse();
			assertThat(props.getWebPort()).isEqualTo("8082");
			assertThat(props.isWebSSL()).isFalse();
			assertThat(props.isWebAllowOthers()).isFalse();
			assertThat(props.isWebDaemon()).isFalse();
			assertThat(props.isBrowser()).isFalse();
			assertThat(props.getPgPort()).isEqualTo("5435");
			assertThat(props.isPgAllowOthers()).isFalse();
			assertThat(props.isPgDaemon()).isFalse();
			assertThat(props.isProperties()).isFalse();
			assertThat(props.getProps()).isNull();
			assertThat(props.getBaseDir()).isEmpty();
			assertThat(props.getKey()).isNull();
			assertThat(props.isTrace()).isFalse();
			assertThat(props.isIfExists()).isFalse();
		}
	}

	@Nested
	@DisplayName("Setters and Getters")
	class SettersAndGetters {

		@Test
		@DisplayName("should set and get enabled")
		void shouldSetAndGetEnabled() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setEnabled(true);
			assertThat(props.isEnabled()).isTrue();
		}

		@Test
		@DisplayName("should set and get protocol")
		void shouldSetAndGetProtocol() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setProtocol(H2dbServerProperties.Protocol.TCP);
			assertThat(props.getProtocol()).isEqualTo(H2dbServerProperties.Protocol.TCP);
		}

		@Test
		@DisplayName("should set and get tcpPort")
		void shouldSetAndGetTcpPort() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setTcpPort("9999");
			assertThat(props.getTcpPort()).isEqualTo("9999");
		}

		@Test
		@DisplayName("should set and get tcpAllowOthers")
		void shouldSetAndGetTcpAllowOthers() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setTcpAllowOthers(true);
			assertThat(props.isTcpAllowOthers()).isTrue();
		}

		@Test
		@DisplayName("should set and get tcpDaemon")
		void shouldSetAndGetTcpDaemon() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setTcpDaemon(true);
			assertThat(props.isTcpDaemon()).isTrue();
		}

		@Test
		@DisplayName("should set and get tcpSSL")
		void shouldSetAndGetTcpSSL() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setTcpSSL(true);
			assertThat(props.isTcpSSL()).isTrue();
		}

		@Test
		@DisplayName("should set and get tcpPassword")
		void shouldSetAndGetTcpPassword() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setTcpPassword("secret");
			assertThat(props.getTcpPassword()).isEqualTo("secret");
		}

		@Test
		@DisplayName("should set and get tcpShutdown")
		void shouldSetAndGetTcpShutdown() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setTcpShutdown("tcp://localhost");
			assertThat(props.getTcpShutdown()).isEqualTo("tcp://localhost");
		}

		@Test
		@DisplayName("should set and get tcpShutdownForce")
		void shouldSetAndGetTcpShutdownForce() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setTcpShutdownForce(true);
			assertThat(props.isTcpShutdownForce()).isTrue();
		}

		@Test
		@DisplayName("should set and get webPort")
		void shouldSetAndGetWebPort() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setWebPort("8080");
			assertThat(props.getWebPort()).isEqualTo("8080");
		}

		@Test
		@DisplayName("should set and get webSSL")
		void shouldSetAndGetWebSSL() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setWebSSL(true);
			assertThat(props.isWebSSL()).isTrue();
		}

		@Test
		@DisplayName("should set and get webAllowOthers")
		void shouldSetAndGetWebAllowOthers() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setWebAllowOthers(true);
			assertThat(props.isWebAllowOthers()).isTrue();
		}

		@Test
		@DisplayName("should set and get webDaemon")
		void shouldSetAndGetWebDaemon() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setWebDaemon(true);
			assertThat(props.isWebDaemon()).isTrue();
		}

		@Test
		@DisplayName("should set and get browser")
		void shouldSetAndGetBrowser() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setBrowser(true);
			assertThat(props.isBrowser()).isTrue();
		}

		@Test
		@DisplayName("should set and get pgPort")
		void shouldSetAndGetPgPort() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setPgPort("5432");
			assertThat(props.getPgPort()).isEqualTo("5432");
		}

		@Test
		@DisplayName("should set and get pgAllowOthers")
		void shouldSetAndGetPgAllowOthers() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setPgAllowOthers(true);
			assertThat(props.isPgAllowOthers()).isTrue();
		}

		@Test
		@DisplayName("should set and get pgDaemon")
		void shouldSetAndGetPgDaemon() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setPgDaemon(true);
			assertThat(props.isPgDaemon()).isTrue();
		}

		@Test
		@DisplayName("should set and get properties")
		void shouldSetAndGetProperties() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setProperties(true);
			assertThat(props.isProperties()).isTrue();
		}

		@Test
		@DisplayName("should set and get props")
		void shouldSetAndGetProps() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setProps("classpath:hsql.properties");
			assertThat(props.getProps()).isEqualTo("classpath:hsql.properties");
		}

		@Test
		@DisplayName("should set and get baseDir")
		void shouldSetAndGetBaseDir() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setBaseDir("/tmp/h2db");
			assertThat(props.getBaseDir()).isEqualTo("/tmp/h2db");
		}

		@Test
		@DisplayName("should set and get key")
		void shouldSetAndGetKey() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setKey("myKey");
			assertThat(props.getKey()).isEqualTo("myKey");
		}

		@Test
		@DisplayName("should set and get trace")
		void shouldSetAndGetTrace() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setTrace(true);
			assertThat(props.isTrace()).isTrue();
		}

		@Test
		@DisplayName("should set and get ifExists")
		void shouldSetAndGetIfExists() {
			H2dbServerProperties props = new H2dbServerProperties();
			props.setIfExists(true);
			assertThat(props.isIfExists()).isTrue();
		}
	}

	@Nested
	@DisplayName("Protocol enum")
	class ProtocolEnum {

		@Test
		@DisplayName("should get correct protocol int values")
		void shouldGetCorrectProtocolValues() {
			assertThat(H2dbServerProperties.Protocol.HTTP.get()).isEqualTo(0);
			assertThat(H2dbServerProperties.Protocol.TCP.get()).isEqualTo(1);
			assertThat(H2dbServerProperties.Protocol.PG.get()).isEqualTo(2);
		}

		@Test
		@DisplayName("should compare protocols using equals(Protocol)")
		void shouldCompareProtocols() {
			assertThat(H2dbServerProperties.Protocol.HTTP.equals(H2dbServerProperties.Protocol.HTTP)).isTrue();
			assertThat(H2dbServerProperties.Protocol.HTTP.equals(H2dbServerProperties.Protocol.TCP)).isFalse();
		}

		@Test
		@DisplayName("should compare protocols using equals(int)")
		void shouldCompareProtocolsByInt() {
			assertThat(H2dbServerProperties.Protocol.HTTP.equals(0)).isTrue();
			assertThat(H2dbServerProperties.Protocol.HTTP.equals(1)).isFalse();
		}

		@Test
		@DisplayName("should find protocol by int value via valueOfIgnoreCase")
		void shouldFindProtocolByIntValue() {
			assertThat(H2dbServerProperties.Protocol.valueOfIgnoreCase(0)).isEqualTo(H2dbServerProperties.Protocol.HTTP);
			assertThat(H2dbServerProperties.Protocol.valueOfIgnoreCase(1)).isEqualTo(H2dbServerProperties.Protocol.TCP);
			assertThat(H2dbServerProperties.Protocol.valueOfIgnoreCase(2)).isEqualTo(H2dbServerProperties.Protocol.PG);
		}

		@Test
		@DisplayName("should throw exception for unknown protocol key")
		void shouldThrowExceptionForUnknownKey() {
			assertThatThrownBy(() -> H2dbServerProperties.Protocol.valueOfIgnoreCase(99))
					.isInstanceOf(NoSuchElementException.class)
					.hasMessageContaining("Cannot found Protocol with key '99'");
		}
	}

	@Nested
	@DisplayName("PREFIX constant")
	class PrefixConstant {

		@Test
		@DisplayName("should have correct prefix value")
		void shouldHaveCorrectPrefix() {
			assertThat(H2dbServerProperties.PREFIX).isEqualTo("h2.server");
		}
	}
}
