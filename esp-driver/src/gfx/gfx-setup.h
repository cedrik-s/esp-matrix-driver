#ifndef SETUP_GFX
#define SETUP_GFX

#include <ESP32-HUB75-MatrixPanel-I2S-DMA.h>

void setupGFX();

extern  MatrixPanel_I2S_DMA *dma_display;

extern uint16_t myBLACK;
extern uint16_t myWHITE;
extern uint16_t myBLUE;
extern uint16_t myRED;
extern uint16_t myGREEN;

#endif