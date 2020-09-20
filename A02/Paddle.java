public class Paddle {

int RECT_WIDTH = 200;
int RECT_HEIGHT = 100;
int x = guigumdrops.WIN_WIDTH/2-100;
int y = guigumdrops.WIN_HEIGHT/2-50;
int xVelocity = 0;
int yVelocity = 0;

public void paint(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(x, y, RECT_WIDTH, RECT_HEIGHT);
}

public void update() {
    x += xVelocity;
    y += yVelocity;
    if(x < 0 || x > Shapes.WIN_WIDTH) {
        xVelocity = -xVelocity;
    }
    if(y < 0 || y > Shapes.WIN_HEIGHT) {
        yVelocity = -yVelocity;
    }
}
}
