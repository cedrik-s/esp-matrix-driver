#include <ArduinoJson.h>
#include <WebServer.h>
#include "webserver-setup.h"
#include "gfx-setup.h"
#include "base64_2.h"

void handlePostBitmap() {
  if(server.uri() != "/bitmap"){
    return;
  }

  String bodyString = server.arg("plain");

  Serial.print(bodyString);

  StaticJsonDocument<2048> body;
  DeserializationError error = deserializeJson(body, bodyString);
  if (error) {
    Serial.print("deserializeJson() failed: ");
    Serial.println(error.c_str());
    return;
  }

  const char* bitmap = base64_decode(body["bitmap"],true).c_str();
  int x = body["x"];
  int y = body["y"];
  int w = body["w"]; 
  int h = body["h"];
  long color = body["color"];

  Serial.println(bitmap);

  dma_display->clearScreen();

  dma_display->drawBitmap(
    x,
    y, 
    (unsigned char*) bitmap,
    w,
    h,
    myRED
  );

  server.send(200);

}
