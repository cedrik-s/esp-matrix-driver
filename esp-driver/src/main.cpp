#include "wifi/wifi-setup.h"
#include "gfx/gfx-setup.h"
#include "webserver/webserver-setup.h"

void setup() {
  Serial.begin(115200);
  setupGFX();
  setupWiFi();
  setupWebServer();
}

void loop() {
  //check for requests
  server.handleClient();
  delay(2);
}