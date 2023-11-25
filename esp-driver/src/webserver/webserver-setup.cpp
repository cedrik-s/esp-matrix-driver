#include <ArduinoJson.h>
#include <WebServer.h>
#include "../gfx/gfx-setup.h"
#include "base64.h"

/**
 * WebServer Configuration
 */
#define PORT 80
WebServer server(PORT);

void handleNotFound() {
  server.send(404);
}

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

  int x = body["sx"];
  int y = body["sy"];
  int tx = body["tx"]; 
  int ty = body["ty"];
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

void setupWebServer() {
  server.on("/bitmap", HTTP_POST, handlePostBitmap);
  server.on("/line", HTTP_POST, handlePostLine);
  server.onNotFound(handleNotFound);

  server.begin();
}