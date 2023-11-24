
#include <ESP32-HUB75-MatrixPanel-I2S-DMA.h>
#include <WiFi.h>
#include <WebServer.h>

/**
 * LED CONFIGURATION
 */

#define PANEL_RES_X 128      // Number of pixels wide of each INDIVIDUAL panel module. 
#define PANEL_RES_Y 64     // Number of pixels tall of each INDIVIDUAL panel module.
#define PANEL_CHAIN 1      // Total number of panels chained one to another

#define R1_PIN 25
#define G1_PIN 26
#define B1_PIN 27
#define R2_PIN 14
#define G2_PIN 12
#define B2_PIN 13
#define A_PIN 23
#define B_PIN 19
#define C_PIN 5
#define D_PIN 17
#define E_PIN 32
#define LAT_PIN 4
#define OE_PIN 15
#define CLK_PIN 16
 
MatrixPanel_I2S_DMA *dma_display = nullptr;

uint16_t myBLACK = dma_display->color565(0, 0, 0);
uint16_t myWHITE = dma_display->color565(255, 255, 255);
uint16_t myRED = dma_display->color565(255, 0, 0);
uint16_t myGREEN = dma_display->color565(0, 255, 0);
uint16_t myBLUE = dma_display->color565(0, 0, 255);


/**
 * WLAN CONFIGURATION
 */

const char* ssid = "your_wifi_ssid";
const char* password = "your_wifi_password";

/**
 * WebServer Configuration
 */
#define PORT 80
WebServer server(PORT);

void setupGFX() {

  HUB75_I2S_CFG::i2s_pins _pins={R1_PIN, G1_PIN, B1_PIN, R2_PIN, G2_PIN, B2_PIN, A_PIN, B_PIN, C_PIN, D_PIN, E_PIN, LAT_PIN, OE_PIN, CLK_PIN};

  // Module configuration
  HUB75_I2S_CFG mxconfig(
    PANEL_RES_X,   // module width
    PANEL_RES_Y,   // module height
    PANEL_CHAIN,    // Chain length
    _pins
  );

  // Display Setup
  dma_display = new MatrixPanel_I2S_DMA(mxconfig);
  dma_display->begin();
  dma_display->setBrightness8(100); //0-255
  dma_display->clearScreen();
}

void setupWiFi() {
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  Serial.print("Connected to the WiFi network");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
}

void handleNotFound() {
  server.send(404);
}

void handlePostBitmap() {
  if(server.uri() != "/bitmap"){
    return;
  }

  String body = server.arg("plain");

  Serial.print(body);


}

void setupWebServer() {
  server.on("/bitmap", HTTP_POST, handlePostBitmap);
  server.onNotFound(handleNotFound);

  server.begin();
}

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

  //example code
  dma_display->clearScreen();
}