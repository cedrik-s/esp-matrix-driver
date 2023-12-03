#include "wifi-setup.h"
#include "gfx-setup.h"
#include "webserver-setup.h"
#include "setupBackendConnection.h"
#include "websocket-setup.h"

void setup() {
  Serial.begin(115200);
  setupGFX();
  setupWiFi();
  setupWebServer();
  setupWebSocket();
}

void loop() {
  //check for requests
  server.handleClient();
  websocketLoop();
  delay(2);
  
}