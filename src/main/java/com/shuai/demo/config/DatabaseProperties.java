package com.shuai.demo.config;

public class DatabaseProperties
{
  private String url;
  private String user;
  private String password;
  private Integer initialPoolSize;
  private Integer minPoolSize;
  private Integer maxPoolSize;
  private String dialect;
  private String driver;
  private Integer batchSize;
  private boolean enableHazelcast = false;
  private boolean hazelcastQueryCache;
  private boolean hazelcastUseMinimalPuts;
  private String hazelcastCacheRegionFactory;
  private boolean hazelcastUseNativeClient;
  private String hazelcastNativeClientAddress;
  private String hazelcastNativeClientGroup;
  private String hazelcastNativeClientPassword;
  private String schema;
  private String packageToScan;

  public String getUrl()
  {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUser() {
    return this.user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getInitialPoolSize() {
    return this.initialPoolSize;
  }

  public void setInitialPoolSize(Integer initialPoolSize) {
    this.initialPoolSize = initialPoolSize;
  }

  public Integer getMinPoolSize() {
    return this.minPoolSize;
  }

  public void setMinPoolSize(Integer minPoolSize) {
    this.minPoolSize = minPoolSize;
  }

  public Integer getMaxPoolSize() {
    return this.maxPoolSize;
  }

  public void setMaxPoolSize(Integer maxPoolSize) {
    this.maxPoolSize = maxPoolSize;
  }

  public String getSchema() {
    return this.schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getPackageToScan() {
    return this.packageToScan;
  }

  public void setPackageToScan(String packageToScan) {
    this.packageToScan = packageToScan;
  }

  public String getDialect() {
    return this.dialect;
  }

  public void setDialect(String dialect) {
    this.dialect = dialect;
  }

  public String getDriver() {
    return this.driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public Integer getBatchSize() {
    return this.batchSize;
  }

  public void setBatchSize(Integer batchSize) {
    this.batchSize = batchSize;
  }

  public boolean getEnableHazelcast() {
    return this.enableHazelcast;
  }

  public void setEnableHazelcast(boolean enableHazelcast) {
    this.enableHazelcast = enableHazelcast;
  }

  public boolean getHazelcastQueryCache() {
    return this.hazelcastQueryCache;
  }

  public void setHazelcastQueryCache(boolean hazelcastQueryCache) {
    this.hazelcastQueryCache = hazelcastQueryCache;
  }

  public boolean getHazelcastUseMinimalPuts() {
    return this.hazelcastUseMinimalPuts;
  }

  public void setHazelcastUseMinimalPuts(boolean hazelcastUseMinimalPuts) {
    this.hazelcastUseMinimalPuts = hazelcastUseMinimalPuts;
  }

  public String getHazelcastCacheRegionFactory() {
    return this.hazelcastCacheRegionFactory;
  }

  public void setHazelcastCacheRegionFactory(String hazelcastCacheRegionFactory)
  {
    this.hazelcastCacheRegionFactory = hazelcastCacheRegionFactory;
  }

  public boolean getHazelcastUseNativeClient() {
    return this.hazelcastUseNativeClient;
  }

  public void setHazelcastUseNativeClient(boolean hazelcastUseNativeClient) {
    this.hazelcastUseNativeClient = hazelcastUseNativeClient;
  }

  public String getHazelcastNativeClientAddress() {
    return this.hazelcastNativeClientAddress;
  }

  public void setHazelcastNativeClientAddress(String hazelcastNativeClientAddress)
  {
    this.hazelcastNativeClientAddress = hazelcastNativeClientAddress;
  }

  public String getHazelcastNativeClientGroup() {
    return this.hazelcastNativeClientGroup;
  }

  public void setHazelcastNativeClientGroup(String hazelcastNativeClientGroup) {
    this.hazelcastNativeClientGroup = hazelcastNativeClientGroup;
  }

  public String getHazelcastNativeClientPassword() {
    return this.hazelcastNativeClientPassword;
  }

  public void setHazelcastNativeClientPassword(String hazelcastNativeClientPassword)
  {
    this.hazelcastNativeClientPassword = hazelcastNativeClientPassword;
  }
}