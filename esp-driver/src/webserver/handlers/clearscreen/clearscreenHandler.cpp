#include <ArduinoJson.h>
#include <WebServer.h>
#include "webserver-setup.h"
#include "gfx-setup.h"
#include "base64.h"

void handleClearScreen() {
  if(server.uri() != "/clearScreen"){
    return;
  }

  dma_display->clearScreen();

  server.send(200);
}