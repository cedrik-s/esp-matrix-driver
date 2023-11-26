#include <ArduinoJson.h>
#include <WebServer.h>
#include "webserver-setup.h"
#include "gfx-setup.h"
#include "base64.h"

void handlePostLine() {
  if(server.uri() != "/line"){
    return;
  }

  String bodyString = server.arg("plain");

  Serial.print(bodyString);

  StaticJsonDocument<768> body;
  DeserializationError error = deserializeJson(body, bodyString);
  if (error) {
    Serial.print("deserializeJson() failed: ");
    Serial.println(error.c_str());
    return;
  }

  int x = body["xStart"];
  int y = body["yStart"];
  int tx = body["xEnd"]; 
  int ty = body["yEnd"];
  long color = body["color"];

  Serial.println(x);
  Serial.println(y);
  Serial.println(tx);
  Serial.println(ty);
  Serial.println(color);

  dma_display->drawLine(x, y, tx, ty, color);
  dma_display->drawLine(20, 20, 20, 40, myRED);

  server.send(200);

  delay(2000);
}