#include <WiFi.h>
#include <HTTPClient.h>
#include "webserver-setup.h"
#include "configuration.h"
#include "ArduinoJson.h"

long lastBackendRegistration = 0;

short registerAtBackend(){
    if(WiFi.status( )== WL_CONNECTED){
      HTTPClient http;

      DynamicJsonDocument doc(128);
      doc["macAddress"] = WiFi.macAddress();
      doc["ipAddress"] = WiFi.localIP().toString();
      doc["port"] = PORT;
      doc["height"] = PANEL_RES_Y;
      doc["width"] = PANEL_RES_X;

      String body;
      serializeJson(doc, body);

      http.begin(BACKEND_URL);
      http.addHeader("Content-Type", "application/json");
      int httpResponseCode = http.POST(body);
      
      if (httpResponseCode>0) {
        Serial.print("HTTP Response code: ");
        Serial.println(httpResponseCode);
        String payload = http.getString();
        Serial.println(payload);
      }
      else {
        Serial.print("Error code: ");
        Serial.println(httpResponseCode);
      }
      // Free resources
      http.end();
      return httpResponseCode == 200;
    }
    else {
      Serial.println("WiFi Disconnected. Could not register with backend.");
    }
}

/**
 * @brief This method registers the esp at the configured backend.
 * It should be run periodically (on loop) to ensure the backend is still aware of this device.
 * The method will only send requests in a fixed interval, so calling it rapidly is not very costly.
 */
void ensureBackendConnection(){
    long currentTime = millis();
    if(currentTime - lastBackendRegistration > BACKEND_REGISTRATION_INTERVAL){
        if(registerAtBackend() > 0){
            lastBackendRegistration = currentTime;
        }
    }
}