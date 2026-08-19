package org.h2.spring.boot;

import java.util.NoSuchElementException;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(H2dbServerProperties.PREFIX)
/**
 * <p>Auto-configuration for H2dbServerProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class H2dbServerProperties {

	public static final String PREFIX = "h2.server";

	// type of server
	/**
	 * <p>Auto-configuration for Protocol.</p>
	 * @author <a href="https://github.com/loong10k">Loong Wan</a>
	 * @since 1.0.0
	 */
	public enum Protocol {

		HTTP(0), TCP(1), PG(2);

		private final int protocol;

		Protocol(int protocol) {
			this.protocol = protocol;
		}

		/**
		 * <p>Get.</p>
		 * @return the result
		 */
		public int get() {
			return protocol;
		}

		/**
		 * <p>Equals.</p>
		 * @param protocol
		 * @return the result
		 */
		public boolean equals(Protocol protocol) {
			return this.compareTo(protocol) == 0;
		}

		/**
		 * <p>Equals.</p>
		 * @param protocol
		 * @return the result
		 */
		public boolean equals(int protocol) {
			return this.compareTo(Protocol.valueOfIgnoreCase(protocol)) == 0;
		}

		/**
		 * <p>Value of ignore case.</p>
		 * @param key
		 * @return the result
		 */
		public static Protocol valueOfIgnoreCase(int key) {
			for (Protocol protocol : Protocol.values()) {
				if (protocol.get() == key) {
					return protocol;
				}
			}
			throw new NoSuchElementException("Cannot found Protocol with key '" + key + "'.");
		}

	}

	/** Whether Enable H2 Server. */
	private boolean enabled = false;

	/**
	 * H2 Server 服务类型；
	 * <p> HTTP（Start the web server with the H2 Console ）, </p>
	 * <p> TCP（Start the TCP server ）, </p>
	 * <p> PG（Start the PG server） </p>
	 */
	private Protocol protocol = Protocol.HTTP;

	/** TCP port for remote connections(default: 9092) */
	private String tcpPort = "9092";
	/** Whether Allow other computers to connect */
	protected boolean tcpAllowOthers;
	/** Whether Use a daemon thread */
	protected boolean tcpDaemon;
	/** Whether Use encrypted (SSL) connections */
	protected boolean tcpSSL;
	/** The password for shutting down a TCP server */
	private String tcpPassword;
	/** The url for Stop the TCP server; example: tcp://localhost */
	private String tcpShutdown;
	/** Whether Do not wait until all connections are closed */
	protected boolean tcpShutdownForce;

	/** The Web port (default: 8082) */
	private String webPort = "8082";
	/** Whether Use encrypted (HTTPS) connections */
	protected boolean webSSL;
	/** Whether Allow other computers to connect */
	protected boolean webAllowOthers;
	/** Whether Use a daemon thread */
	protected boolean webDaemon;
	/** Whether Start a browser connecting to the web server */
	protected boolean browser;

	/** The PG port (default: 5435) */
	private String pgPort = "5435";
	/** Whether Allow other computers to connect */
	protected boolean pgAllowOthers;
	/** Whether Use a daemon thread */
	protected boolean pgDaemon;

	/** The dir of Server properties (default: ~, disable: null) */
	protected boolean properties;
	/** HyperSQL Server 外部配置文件；如 ： classpath:hsql.properties */
	protected String props;
	/** The base directory for H2 databases (all servers) */
	private String baseDir = "";
	/** Allows to map a database name to another (all servers) */
	private String key;
	/** Whether Print additional trace information (all servers) */
	protected boolean trace = false;
	/** Whether Only existing databases may be opened (all servers) */
	protected boolean ifExists;

	/** @return return whether enabled is enabled. */
	public boolean isEnabled() {
		return enabled;
	}

	/** @param enabled set the enabled. */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/** @return return the protocol. */
	public Protocol getProtocol() {
		return protocol;
	}

	/** @param protocol set the protocol. */
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	/** @return return the tcp port. */
	public String getTcpPort() {
		return tcpPort;
	}

	/** @param tcpPort set the tcp port. */
	public void setTcpPort(String tcpPort) {
		this.tcpPort = tcpPort;
	}

	/** @return return whether tcp allow others is enabled. */
	public boolean isTcpAllowOthers() {
		return tcpAllowOthers;
	}

	/** @param tcpAllowOthers set the tcp allow others. */
	public void setTcpAllowOthers(boolean tcpAllowOthers) {
		this.tcpAllowOthers = tcpAllowOthers;
	}

	/** @return return whether tcp daemon is enabled. */
	public boolean isTcpDaemon() {
		return tcpDaemon;
	}

	/** @param tcpDaemon set the tcp daemon. */
	public void setTcpDaemon(boolean tcpDaemon) {
		this.tcpDaemon = tcpDaemon;
	}

	/** @return return whether tcp s s l is enabled. */
	public boolean isTcpSSL() {
		return tcpSSL;
	}

	/** @param tcpSSL set the tcp s s l. */
	public void setTcpSSL(boolean tcpSSL) {
		this.tcpSSL = tcpSSL;
	}

	/** @return return the tcp password. */
	public String getTcpPassword() {
		return tcpPassword;
	}

	/** @param tcpPassword set the tcp password. */
	public void setTcpPassword(String tcpPassword) {
		this.tcpPassword = tcpPassword;
	}

	/** @return return the tcp shutdown. */
	public String getTcpShutdown() {
		return tcpShutdown;
	}

	/** @param tcpShutdown set the tcp shutdown. */
	public void setTcpShutdown(String tcpShutdown) {
		this.tcpShutdown = tcpShutdown;
	}

	/** @return return whether tcp shutdown force is enabled. */
	public boolean isTcpShutdownForce() {
		return tcpShutdownForce;
	}

	/** @param tcpShutdownForce set the tcp shutdown force. */
	public void setTcpShutdownForce(boolean tcpShutdownForce) {
		this.tcpShutdownForce = tcpShutdownForce;
	}

	/** @return return the web port. */
	public String getWebPort() {
		return webPort;
	}

	/** @param webPort set the web port. */
	public void setWebPort(String webPort) {
		this.webPort = webPort;
	}

	/** @return return whether web s s l is enabled. */
	public boolean isWebSSL() {
		return webSSL;
	}

	/** @param webSSL set the web s s l. */
	public void setWebSSL(boolean webSSL) {
		this.webSSL = webSSL;
	}

	/** @return return whether web allow others is enabled. */
	public boolean isWebAllowOthers() {
		return webAllowOthers;
	}

	/** @param webAllowOthers set the web allow others. */
	public void setWebAllowOthers(boolean webAllowOthers) {
		this.webAllowOthers = webAllowOthers;
	}

	/** @return return whether web daemon is enabled. */
	public boolean isWebDaemon() {
		return webDaemon;
	}

	/** @param webDaemon set the web daemon. */
	public void setWebDaemon(boolean webDaemon) {
		this.webDaemon = webDaemon;
	}

	/** @return return whether browser is enabled. */
	public boolean isBrowser() {
		return browser;
	}

	/** @param browser set the browser. */
	public void setBrowser(boolean browser) {
		this.browser = browser;
	}

	/** @return return the pg port. */
	public String getPgPort() {
		return pgPort;
	}

	/** @param pgPort set the pg port. */
	public void setPgPort(String pgPort) {
		this.pgPort = pgPort;
	}

	/** @return return whether pg allow others is enabled. */
	public boolean isPgAllowOthers() {
		return pgAllowOthers;
	}

	/** @param pgAllowOthers set the pg allow others. */
	public void setPgAllowOthers(boolean pgAllowOthers) {
		this.pgAllowOthers = pgAllowOthers;
	}

	/** @return return whether pg daemon is enabled. */
	public boolean isPgDaemon() {
		return pgDaemon;
	}

	/** @param pgDaemon set the pg daemon. */
	public void setPgDaemon(boolean pgDaemon) {
		this.pgDaemon = pgDaemon;
	}

	/** @return return whether properties is enabled. */
	public boolean isProperties() {
		return properties;
	}

	/** @param properties set the properties. */
	public void setProperties(boolean properties) {
		this.properties = properties;
	}

	/** @return return the props. */
	public String getProps() {
		return props;
	}

	/** @param props set the props. */
	public void setProps(String props) {
		this.props = props;
	}

	/** @return return the base dir. */
	public String getBaseDir() {
		return baseDir;
	}

	/** @param baseDir set the base dir. */
	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	/** @return return the key. */
	public String getKey() {
		return key;
	}

	/** @param key set the key. */
	public void setKey(String key) {
		this.key = key;
	}

	/** @return return whether trace is enabled. */
	public boolean isTrace() {
		return trace;
	}

	/** @param trace set the trace. */
	public void setTrace(boolean trace) {
		this.trace = trace;
	}

	/** @return return whether if exists is enabled. */
	public boolean isIfExists() {
		return ifExists;
	}

	/** @param ifExists set the if exists. */
	public void setIfExists(boolean ifExists) {
		this.ifExists = ifExists;
	}

}