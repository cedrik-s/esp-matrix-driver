#include <Arduino.h>
#include "configuration.h"
#include <WebSocketsClient.h>
#include <ArduinoJson.h>
#include "gfx-setup.h"

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
void sendMessage(String &msg)
{
    webSocket.sendTXT(msg.c_str(), msg.length() + 1);
}

void webSocketEvent(WStype_t type, uint8_t *payload, size_t length)
{
    Serial.printf("[WSc] Event: %s\n", payload);

    switch (type)
    {
    case WStype_DISCONNECTED:
        Serial.printf("[WSc] Disconnected!\n");
        webSocket.begin(BACKEND_IP, BACKEND_WS_PORT, STOMPURL);
        break;
    case WStype_CONNECTED:
    {
        Serial.printf("[WSc] Connected to url: %s\n", payload);

        String msg = "CONNECT\r\naccept-version:1.1,1.0\r\nheart-beat:10000,10000\r\n\r\n";
        sendMessage(msg);
    }
    break;
    case WStype_TEXT:
    {
        // #####################
        // handle STOMP protocol
        // #####################

        String text = (char *)payload;
        Serial.printf("[WSc] get text: %s\n", payload);

        if (text.startsWith("CONNECTED"))
        {

            Serial.printf("[WSc] Subscribing \n");
            String msg = "SUBSCRIBE\nid:sub-0\ndestination:/client/drawInstructions\n\n";
            sendMessage(msg);
            delay(1000);

            Serial.printf("[WSc] Send Registration \n");
            msg = "SEND\ndestination:/server/RequestDrawInstructions\n\n{\"message\":\"Registration!\"}";
            sendMessage(msg);
            delay(1000);
        }
        else
        {
            std::string bodyString = std::string((char *)payload);
            bodyString = bodyString.substr(bodyString.find("\n\n"), bodyString.size());
            int endPos = bodyString.find(";;");
            while (endPos > 0)
            {
                std::string curString = bodyString.substr(0, endPos);
                Serial.println("curString");
                Serial.println(curString.c_str());
                StaticJsonDocument<768> body;
                DeserializationError error = deserializeJson(body, bodyString);
                if (error)
                {
                    Serial.print("deserializeJson() failed: ");
                    Serial.println(error.c_str());
                }
                String method = body["method"];
                int xStart = body["xStart"];
                int yStart = body["yStart"];
                int xEnd = body["xEnd"];
                int yEnd = body["yEnd"];
                long colorR = body["colorR"];
                long colorG = body["colorG"];
                long colorB = body["colorB"];
                int radius = body["radius"];
                int intParam = body["intParam"];
                int size = body["size"];
                String stringParam = body["stringParam"];

                uint16_t color = dma_display->color565(colorB, colorG, colorR);

                Serial.println(xStart);
                Serial.println(yStart);
                Serial.println(xEnd);
                Serial.println(yEnd);
                Serial.println(color);
                if (method.equals("drawLine"))
                {
                    dma_display->drawLine(xStart, yStart, xEnd, yEnd, color);
                }
                else if (method.equals("drawRect"))
                {
                    dma_display->drawRect(xStart, yStart, xEnd, yEnd, color);
                }
                else if (method.equals("fillRect"))
                {
                    dma_display->fillRect(xStart, yStart, xEnd, yEnd, color);
                }
                else if (method.equals("fillScreen"))
                {
                    dma_display->fillScreen(color);
                }
                else if (method.equals("clearScreen"))
                {
                    dma_display->clearScreen();
                }
                else if (method.equals("fillCircle"))
                {
                    dma_display->fillCircle(xStart, yStart, radius, color);
                }
                else if (method.equals("drawCircle"))
                {
                    dma_display->drawCircle(xStart, yStart, radius, color);
                }
                else if (method.equals("setBrightness"))
                {
                    dma_display->setBrightness(intParam);
                }
                else if (method.equals("drawText"))
                {
                    int xStartIterator = xStart;
                    for (int i = 0; i < stringParam.length(); i++)
                    {
                        if (stringParam.charAt(i) == ' ')
                        {
                            xStartIterator += 3;
                        }
                        else
                        {
                            dma_display->drawChar(xStartIterator, yStart, stringParam.charAt(i), color, color, size);
                            xStartIterator += 5 * size + 1;
                        }
                    }
                }
                else
                {
                    Serial.println("not a valid method");
                }
                bodyString = bodyString.substr(endPos + 2);
                endPos = bodyString.find(";;");
            }

            // do something with messages

            Serial.printf("[WSc] Send Registration \n");
            String msg = "SEND\ndestination:/server/RequestDrawInstructions\n\n{\"message\":\"Registration!\"}";
            sendMessage(msg);
        }

        break;
    }
    case WStype_BIN:
        Serial.printf("[WSc] get binary length: %u\n", length);

        // In Example Code it uses hash.h Library for this task but as it currently isnt included this does not work
        // hexdump(payload, length);

        // send data to server
        // webSocket.sendBIN(payload, length);
        break;
    }
}

void setupWebSocket()
{
    webSocket.begin(BACKEND_IP, BACKEND_WS_PORT, STOMPURL);
    webSocket.setExtraHeaders(); // remove "Origin: file://" header because it breaks the connection with Spring's default websocket config
    webSocket.onEvent(webSocketEvent);
}

void websocketLoop()
{
    webSocket.loop();
}