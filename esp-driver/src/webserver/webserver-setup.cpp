#include <ArduinoJson.h>
#include <WebServer.h>
#include "gfx-setup.h"
#include "base64.h"
#include "lineHandler.h"
#include "bitmapHandler.h"
#include "clearScreenHandler.h"
#include "brightnessHandler.h"
#include "webserver-setup.h"

/**
 * WebServer Configuration
 */
WebServer server(PORT);

void handleNotFound() {
  server.send(404);
}

void setupWebServer() {
  server.on("/bitmap", HTTP_POST, handlePostBitmap);
  server.on("/line", HTTP_POST, handlePostLine);
  server.on("/clearScreen", HTTP_POST, handleClearScreen);
  server.on("/brightness", HTTP_POST, handlePostBrightness);
  
  server.onNotFound(handleNotFound);

  server.begin();
}