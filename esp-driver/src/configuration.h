/*
Personal Configuration
Only Add Attributes here if these need to be configured individually by each User else add them to the default parameters below

Example in the following line:

Please extend if you add new Configurations and only commit the Comment and not your private configuration

#define WIFI_SSID "yourWifiSSID"
#define WIFI_PASSWORD "yourWifiPassword"
#define BACKEND_IP "IP Adress of your matrix-server" 

*/

/*
General Configuration add everything which runs with default parameters here

*/
#define BACKEND_WS_PORT 8080
#define STOMPURL "/ws"


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