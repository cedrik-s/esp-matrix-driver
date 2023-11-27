#include <Arduino.h>
#include "configuration.h"
#include <WebSocketsClient.h>

WebSocketsClient webSocket;

/**
 * STOMP messages need to be NULL-terminated (i.e., \0 or \u0000).
 * However, when we send a String or a char[] array without specifying 
 * a length, the size of the message payload is derived by strlen() internally,
 * thus dropping any NULL values appended to the "msg"-String.
 * 
 * To solve this, we first convert the String to a NULL terminated char[] array
 * via "c_str" and set the length of the payload to include the NULL value.
 */
void sendMessage(String & msg) {
    webSocket.sendTXT(msg.c_str(), msg.length() + 1);
}

void webSocketEvent(WStype_t type, uint8_t * payload, size_t length) {

    switch (type) {
        case WStype_DISCONNECTED:
            Serial.printf("[WSc] Disconnected!\n");
            webSocket.begin(BACKEND_IP, BACKEND_WS_PORT, STOMPURL);
            break;
        case WStype_CONNECTED:
            {
                Serial.printf("[WSc] Connected to url: %s\n",  payload);
                
                String msg = "CONNECT\r\naccept-version:1.1,1.0\r\nheart-beat:10000,10000\r\n\r\n";
                sendMessage(msg);
            }
            break;
        case WStype_TEXT:
            {
                // #####################
                // handle STOMP protocol
                // #####################

                String text = (char*) payload;
                Serial.printf("[WSc] get text: %s\n", payload);

                if (text.startsWith("CONNECTED")) {

                    Serial.printf("[WSc] Subscribing to /hello");
                    String msg = "SUBSCRIBE\nid:sub-0\ndestination:/topic/test\n\n";
                    sendMessage(msg);
                    delay(1000);

                    Serial.printf("[WSc] Send Registration to /hello");
                    msg = "SEND\ndestination:/app/hello\n\n{\"message\":\"Registration!\"}";
                    sendMessage(msg);
                    delay(1000);
                    
                } else {

                    // do something with messages
                    
                }

                break;
            }
        case WStype_BIN:
            Serial.printf("[WSc] get binary length: %u\n", length);

            // In Example Code it uses hash.h Library for this task but as it currently isnt included this does not work
            //hexdump(payload, length);

            // send data to server
            // webSocket.sendBIN(payload, length);
            break;
    }

}

void setupWebSocket() {
    webSocket.begin(BACKEND_IP, BACKEND_WS_PORT, STOMPURL);
    webSocket.setExtraHeaders(); // remove "Origin: file://" header because it breaks the connection with Spring's default websocket config
    webSocket.onEvent(webSocketEvent);
}

void websocketLoop() {
    webSocket.loop();
}