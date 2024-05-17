package config;

import java.sql.Connection;

import org.redisson.api.RedissonClient;

public class AppProperties {

	private String userName;
	private String password;
	private String url;
	private String driverName;
	private String redisUrl;
	private RedissonClient redissonClient;
	private Connection connection;

	public AppProperties(String userName, String password, String url, String driverName, String redisUrl, RedissonClient redissonClient, Connection connection) {
		this.userName = userName;
		this.password = password;
		this.url = url;
		this.driverName = driverName;
		this.redisUrl = redisUrl;
		this.redissonClient = redissonClient;
		this.connection =connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public RedissonClient getRedissonClient() {
		return redissonClient;
	}

	public void setRedissonClient(RedissonClient redisConfig) {
		this.redissonClient = redisConfig;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRedisUrl() {
		return redisUrl;
	}

	public void setRedisUrl(String redisUrl) {
		this.redisUrl = redisUrl;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

}
