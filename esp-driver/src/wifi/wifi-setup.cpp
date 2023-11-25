#include <WiFi.h>
#include "wifi-credentials.h"

/**
 * WLAN CONFIGURATION
 */

const char* ssid = WIFI_SSID;
const char* password = WIFI_PASSWORD;

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