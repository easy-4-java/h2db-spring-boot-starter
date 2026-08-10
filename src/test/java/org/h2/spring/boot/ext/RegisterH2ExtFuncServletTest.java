package org.h2.spring.boot.ext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link RegisterH2ExtFuncServlet}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @see <a href="https://github.com/loong10k">https://github.com/loong10k</a>
 */
class RegisterH2ExtFuncServletTest {

	@Test
	@DisplayName("should be instantiable")
	void shouldBeInstantiable() {
		RegisterH2ExtFuncServlet servlet = new RegisterH2ExtFuncServlet();
		assertThat(servlet).isNotNull();
	}

	@Test
	@DisplayName("should be an HttpServlet subclass")
	void shouldBeHttpServletSubclass() {
		RegisterH2ExtFuncServlet servlet = new RegisterH2ExtFuncServlet();
		assertThat(servlet).isInstanceOf(jakarta.servlet.http.HttpServlet.class);
	}
}
