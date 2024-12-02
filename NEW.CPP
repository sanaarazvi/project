#include <graphics.h>
#include <conio.h>
#include <dos.h> // For delay function

void drawTree(int x ,int y) {
    // Draw trunk
    setcolor(BROWN);
  line(120,300,120,500) ;
line(160,300,160,500) ;
    // Draw leaves
    setcolor(GREEN);
    setfillstyle(SOLID_FILL, GREEN);
ellipse( 100,300,90,300,50,35);
ellipse(100,240,90,280,50,35);
ellipse(140,200,330,200,50,45);
ellipse(180,240,270,90,50,35);
ellipse(180,300,240,90,50,35);
}
void drawApple(int x, int y) {
    setcolor(RED);
    setfillstyle(SOLID_FILL, RED);
    circle(x, y, 5); // Apple
    floodfill(x, y, RED);
circle(115,200,5);
circle(150,180,5);
circle(180,250,5);
circle(180,200,5);
circle(210,300,5);
circle(160,290,5);
circle(120,250,5);
circle(130,300,5);
circle(120,250,5);
circle(100,280,5);
circle(160,220,5);
circle(80,230,5);
}
void drawBoy(int x, int y) {
    setcolor(WHITE);
    setfillstyle(SOLID_FILL, WHITE);
    circle(x, y, 15); // Boy's head
    floodfill(x, y, WHITE);

    line(x, y + 10, x, y + 30); // Body
    line(x, y + 15, x - 10, y + 25); // Left arm
    line(x, y + 15, x + 10, y + 25); // Right arm
    line(x, y + 30, x - 10, y + 50); // Left leg
    line(x, y + 30, x + 10, y + 50); // Right leg
}
int main() {
    int gd = DETECT, gm;
    initgraph(&gd, &gm, "C:\\TURBOC3\\BGI");
    drawBoy(150, 290); // Boy drawing
    // Apple falling animation
    int appleX = 200, appleY = 300; // Starting position of the apple
    while (appleY < 500) { // Fall until it reaches near the boy's head
	cleardevice(); // Clear screen to redraw elements in new positions
       drawTree(120, 250);
	drawBoy(200, 400);
	drawApple(appleX, appleY);//current position of apple
	 appleY += 11;     //move apple slow down
	delay(100); // Delay to slow down the fall
    }
setcolor(CYAN);
setfillstyle(SOLID_FILL, CYAN);
ellipse(570,400, 0, 360, 190, 70); // Pond
floodfill(500, 350, CYAN);
ellipse(320,30,0,180,30,25);        //middle cloud
ellipse(350,50,280,450,40,30);
ellipse(335,70,210,360,25,30);
ellipse(300,60,180,300,30,30);
ellipse(290,50,85,235,30,25) ;

ellipse(75,30,40,180,20,20);//left cloud
ellipse(54,50,90,270,20,20);
ellipse(75,70,180,300,20,20);
ellipse(105,80,190,350,20,20);
ellipse(145,80,190,365,20,20);
ellipse(180,63,210,50,20,20);
ellipse(110,20,10,180,20,20);
ellipse(150,25,0,180,20,20);
ellipse(190,35,280,180,20,15);

ellipse(500,30,0,240,25,25);  //right cloud
ellipse(544,40,260,155,22,22);
ellipse(530,80,180,60,22,22);
ellipse(490,89,180,40,22,22);
ellipse(469,65,60,280,22,25);
    getch();
    closegraph();
return 0;
}