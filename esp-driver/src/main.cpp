#include "wifi-setup.h"
#include "gfx-setup.h"
#include "webserver-setup.h"
#include "setupBackendConnection.h"

void setup() {
  Serial.begin(115200);
  setupGFX();
  setupWiFi();
  setupWebServer();
}

void loop() {
  //check for requests
  server.handleClient();
  ensureBackendConnection();
  delay(2);
}