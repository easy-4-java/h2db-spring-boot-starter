package org.h2.spring.boot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for H2 servers and JDBC operations.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @see <a href="https://github.com/loong10k">https://github.com/loong10k</a>
 */
class H2ServerTest {

	@Test
	@DisplayName("should perform CRUD via in-process H2 connection")
	void h2CrudTest() throws Exception {
		Class.forName("org.h2.Driver");
		try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "sa");
			 Statement stat = conn.createStatement()) {

			// create table
			stat.execute("CREATE TABLE IF NOT EXISTS TEST(NAME VARCHAR)");

			// insert
			stat.execute("INSERT INTO TEST VALUES('test-value-1')");
			stat.execute("INSERT INTO TEST VALUES('test-value-2')");

			// retrieve
			try (ResultSet result = stat.executeQuery("SELECT NAME FROM TEST ORDER BY NAME")) {
				assertThat(result.next()).isTrue();
				assertThat(result.getString("NAME")).isEqualTo("test-value-1");
				assertThat(result.next()).isTrue();
				assertThat(result.getString("NAME")).isEqualTo("test-value-2");
				assertThat(result.next()).isFalse();
			}

			// drop table
			stat.execute("DROP TABLE TEST");
		}
	}

	@Test
	@DisplayName("should start and stop H2 TCP server")
	void tcpServerTest() throws SQLException {
		Server server = Server.createTcpServer(
				"-tcp", "-tcpAllowOthers", "-tcpPort", "8043").start();
		assertThat(server.getStatus()).contains("TCP server running");
		server.stop();
		assertThat(server.getStatus()).isEqualTo("Not started");
	}

	@Test
	@DisplayName("should start and stop PG server")
	void pgServerTest() throws SQLException {
		Server server = Server.createPgServer("-pgAllowOthers", "-pgPort", "5535").start();
		assertThat(server.getStatus()).contains("PG server running");
		server.stop();
		assertThat(server.getStatus()).isEqualTo("Not started");
	}

	@Test
	@DisplayName("should start and stop web server")
	void webServerTest() throws SQLException {
		Server server = Server.createWebServer("-webAllowOthers", "-webPort", "0").start();
		assertThat(server.getStatus()).contains("running");
		server.stop();
		assertThat(server.getStatus()).isEqualTo("Not started");
	}
}
