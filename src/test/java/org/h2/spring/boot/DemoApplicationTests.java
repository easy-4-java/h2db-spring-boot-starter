package org.h2.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Spring Boot auto-configuration loading.
 *
 * @author Loong Wan
 * @see <a href="https://github.com/loong10k">https://github.com/loong10k</a>
 */
class DemoApplicationTests {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

	@Test
	@DisplayName("should auto-configure H2dbServerAutoConfiguration when enabled")
	void shouldAutoConfigureWhenEnabled() {
		contextRunner
				.withPropertyValues("h2.server.enabled=true")
				.withUserConfiguration(H2dbServerAutoConfiguration.class)
				.run(context -> {
					assertThat(context).hasSingleBean(H2dbServerAutoConfiguration.class);
					assertThat(context).hasSingleBean(H2dbServerProperties.class);
				});
	}

	@Test
	@DisplayName("should not auto-configure H2dbServerAutoConfiguration when disabled")
	void shouldNotAutoConfigureWhenDisabled() {
		contextRunner
				.withPropertyValues("h2.server.enabled=false")
				.withUserConfiguration(H2dbServerAutoConfiguration.class)
				.run(context -> assertThat(context).doesNotHaveBean(H2dbServerAutoConfiguration.class));
	}
}
