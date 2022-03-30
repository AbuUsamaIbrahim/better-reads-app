package io.javabrains.betterReads.connection;
import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxAstraPropertie {

  private File secureConnectBundle;

  public File getSecureConnectBundle() {
    return secureConnectBundle;
  }

  public void setSecureConnectBundle(File secureConnectBundle) {
    this.secureConnectBundle = secureConnectBundle;
  }
}