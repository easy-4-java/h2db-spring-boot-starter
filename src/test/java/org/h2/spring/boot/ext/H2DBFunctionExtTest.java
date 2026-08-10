package org.h2.spring.boot.ext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link H2DBFunctionExt}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @see <a href="https://github.com/loong10k">https://github.com/loong10k</a>
 */
class H2DBFunctionExtTest {

	@Nested
	@DisplayName("uuid()")
	class UuidTests {

		@Test
		@DisplayName("should return non-null UUID string")
		void shouldReturnNonNullUuid() {
			String uuid = H2DBFunctionExt.uuid();
			assertThat(uuid).isNotNull().isNotEmpty();
		}

		@Test
		@DisplayName("should return valid UUID format")
		void shouldReturnValidUuidFormat() {
			String uuid = H2DBFunctionExt.uuid();
			assertThat(uuid).matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
		}

		@Test
		@DisplayName("should return unique values on successive calls")
		void shouldReturnUniqueValues() {
			String uuid1 = H2DBFunctionExt.uuid();
			String uuid2 = H2DBFunctionExt.uuid();
			assertThat(uuid1).isNotEqualTo(uuid2);
		}
	}

	@Nested
	@DisplayName("now()")
	class NowTests {

		@Test
		@DisplayName("should return non-null date string")
		void shouldReturnNonNullDate() {
			String now = H2DBFunctionExt.now();
			assertThat(now).isNotNull().isNotEmpty();
		}
	}

	@Nested
	@DisplayName("getIp()")
	class GetIpTests {

		@Test
		@DisplayName("should return non-null IP address")
		void shouldReturnNonNullIp() {
			String ip = H2DBFunctionExt.getIp();
			assertThat(ip).isNotNull().isNotEmpty();
		}
	}

	@Nested
	@DisplayName("date_format()")
	class DateFormatTests {

		@Test
		@DisplayName("should format date string according to pattern")
		void shouldFormatDate() {
			String formatted = H2DBFunctionExt.date_format("2024-01-15", "yyyy-MM-dd");
			assertThat(formatted).isEqualTo("2024-01-15");
		}

		@Test
		@DisplayName("should return empty string for null date")
		void shouldReturnEmptyForNullDate() {
			String result = H2DBFunctionExt.date_format(null, "yyyy-MM-dd");
			assertThat(result).isEmpty();
		}

		@Test
		@DisplayName("should return empty string for invalid date")
		void shouldReturnEmptyForInvalidDate() {
			String result = H2DBFunctionExt.date_format("not-a-date", "yyyy-MM-dd");
			assertThat(result).isEmpty();
		}

		@Test
		@DisplayName("should format date with different pattern")
		void shouldFormatDateWithDifferentPattern() {
			String formatted = H2DBFunctionExt.date_format("2024/01/15", "yyyy/MM/dd");
			assertThat(formatted).isEqualTo("2024/01/15");
		}
	}
}
