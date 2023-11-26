#include <ArduinoJson.h>
#include <WebServer.h>
#include "gfx-setup.h"
#include "base64.h"
#include "lineHandler.h"
#include "bitmapHandler.h"

/**
 * WebServer Configuration
 */
#define PORT 80
WebServer server(PORT);

void handleNotFound() {
  server.send(404);
}

void setupWebServer() {
  server.on("/bitmap", HTTP_POST, handlePostBitmap);
  server.on("/line", HTTP_POST, handlePostLine);
  server.onNotFound(handleNotFound);

  server.begin();
}