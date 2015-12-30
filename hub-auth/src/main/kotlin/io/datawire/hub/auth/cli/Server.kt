package io.datawire.hub.auth.cli

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.datawire.hub.auth.Configuration
import io.datawire.hub.auth.HubAuthenticationVerticle
import io.vertx.core.Vertx
import net.sourceforge.argparse4j.inf.Namespace
import java.io.File


class Server(private val configuration: Configuration): Runnable {

  private val vertx = Vertx.vertx()

  override fun run() {
    val jwtProvider = configuration.jwtFactory.build(vertx)
    vertx.deployVerticle(HubAuthenticationVerticle(jwtProvider))
    System.`in`.read()
  }

  companion object Factory {

    private val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

    fun build(namespace: Namespace): Server {
      val configFile = namespace.get<File>("config")
      val config = mapper.readValue(configFile, Configuration::class.java)
      return Server(config)
    }
  }
}