#include <ArduinoJson.h>
#include <WebServer.h>
#include "webserver-setup.h"
#include "gfx-setup.h"
#include "base64.h"

void handlePostBrightness(){
  if(server.uri() != "/brightness"){
    return;
  }

  String bodyString = server.arg("plain");

  StaticJsonDocument<2048> body;
  DeserializationError error = deserializeJson(body, bodyString);
  if (error) {
    Serial.print("deserializeJson() failed: ");
    Serial.println(error.c_str());
    return;
  }

  uint8_t brightness = body["brightness"];

  dma_display->setBrightness8(brightness);

  server.send(200);
}