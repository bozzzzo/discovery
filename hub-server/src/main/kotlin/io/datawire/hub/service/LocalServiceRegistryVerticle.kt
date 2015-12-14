package io.datawire.hub.service

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.logging.LoggerFactory

/**
 * A simple service registry that is not cluster-aware.
 *
 * @author Philip Lombardi <plombardi@datawire.io>
 */


class LocalServiceRegistryVerticle(): AbstractVerticle() {

  private val log = LoggerFactory.getLogger(LocalServiceRegistryVerticle::class.java)

  override fun start() {

    // todo(plombardi): tenancy bootstrap

    //val tenants = SimpleTenancyManager()
    //val defaultTenant = UUID.randomUUID()
    //tenants.addTenant(TenantInfo(defaultTenant, "notasecret"))

    webSocket { ws ->

      // todo:
      //
      // Reject:
      //  - client specifies an invalid path
      //  - client supplies an unknown foo id
      //  - client supplies an invalid foo token
      //
      //
      logSocket(ws)
      verify(ws)


      if (!ws.path().startsWith("/v1/services")) {
        ws.reject()
        return@webSocket
      }

    }
  }

  private fun verify(socket: ServerWebSocket) {

  }

  private fun webSocket(func: (ServerWebSocket) -> Unit) {
    vertx.createHttpServer().websocketHandler(func).listen(config().getInteger("port"))
  }

  private fun logSocket(socket: ServerWebSocket) {
    log.info("""
--[ WebSocket Connection ]------------------------------------------------------
       ws path : ${socket.path()}
        ws uri : ${socket.uri()}
 ws local-addr : ${socket.localAddress()}
ws remote-addr : ${socket.remoteAddress()}
 connection id :   text --> ${socket.textHandlerID()}
                 binary --> ${socket.binaryHandlerID()}
--------------------------------------------------------------------------------
""")
  }
}