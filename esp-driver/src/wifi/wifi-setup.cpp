#include <WiFi.h>
#include <configuration.h>

#define CONNECTION_TIMEOUT 20

/**
 * WLAN CONFIGURATION
 */

const char *ssid = WIFI_SSID;
const char *password = WIFI_PASSWORD;

String get_wifi_status(int status)
{
  switch (status)
  {
  case WL_IDLE_STATUS:
    return "WL_IDLE_STATUS";
  case WL_SCAN_COMPLETED:
    return "WL_SCAN_COMPLETED";
  case WL_NO_SSID_AVAIL:
    return "WL_NO_SSID_AVAIL";
  case WL_CONNECT_FAILED:
    return "WL_CONNECT_FAILED";
  case WL_CONNECTION_LOST:
    return "WL_CONNECTION_LOST";
  case WL_CONNECTED:
    return "WL_CONNECTED";
  case WL_DISCONNECTED:
    return "WL_DISCONNECTED";
  default:
    return "UNKNOWN_STATUS!";
  }
}

void setupWiFi()
{
  pinMode(LED_BUILTIN, OUTPUT);
  int status = WL_IDLE_STATUS;
  Serial.println("\nConnecting");
  Serial.println(get_wifi_status(status));
  WiFi.begin(ssid, password);
  int timeout_counter = 0;
  while (status != WL_CONNECTED)
  {
    digitalWrite(LED_BUILTIN,!digitalRead(LED_BUILTIN)); 
    delay(500);
    timeout_counter++;
    status = WiFi.status();
    Serial.println(get_wifi_status(status));
    if(timeout_counter>=CONNECTION_TIMEOUT*2){
      ESP.restart();
    }
  }
  digitalWrite(LED_BUILTIN, LOW); 
  Serial.print("Connected to the WiFi network");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
}