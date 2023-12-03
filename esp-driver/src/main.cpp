#include "wifi-setup.h"
#include "gfx-setup.h"
#include "websocket-setup.h"

void setup() {
  Serial.begin(115200);
  setupGFX();
  setupWiFi();
  setupWebSocket();
}

void loop() {
  //check for requests
  websocketLoop();
  delay(2);
  
}