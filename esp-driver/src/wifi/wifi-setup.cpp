#include <WiFi.h>

/**
 * WLAN CONFIGURATION
 */

const char* ssid = "your_wifi_ssid";
const char* password = "your_wifi_password";

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