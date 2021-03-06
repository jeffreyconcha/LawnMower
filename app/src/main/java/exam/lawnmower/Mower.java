package exam.lawnmower;

import android.graphics.Bitmap;

public class Mower {

    public static final int SPEED = 5;
    public static final int NORTH = 90;
    public static final int SOUTH = 270;
    public static final int EAST = 0;
    public static final int WEST = 180;
    private final int DEFAULT = SOUTH;
    private boolean isFinished = false;
    private int width, height, x, y, dx, msArea;
    private int direction = DEFAULT;
    private int degree = DEFAULT;
    private long mowed, mtArea;
    private Bitmap bitmap;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setMTArea(long mtArea) {
        this.mtArea = mtArea;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        this.degree = direction;
    }

    public int centerX() {
        return x + (width / 2);
    }

    public int centerY() {
        return y + (height / 2);
    }

    public int getDegree() {
        return -(degree - DEFAULT);
    }

    public void setMSArea(int msArea) {
        this.msArea = msArea;
    }

    private void turnLeft() {
        degree += SPEED;
        if(degree >= 360) {
            degree = EAST;
        }
        checkDirection();
    }

    private void turnRight() {
        degree -= SPEED;
        if(degree <= -90) {
            degree = SOUTH;
        }
        checkDirection();
    }

    private void checkDirection() {
        if(degree == NORTH || degree == EAST || degree == WEST ||
                degree == SOUTH) {
            direction = degree;
            switch(direction) {
                case EAST:
                    dx++;
                    break;
            }
        }
    }

    public void move(int maxWidth, int maxHeight) {
        if(!isFinished) {
            switch(direction) {
                case NORTH:
                    if(y <= 0) {
                        turnRight();
                    }
                    else {
                        y -= SPEED;
                        mowLawn(width);
                    }
                    break;
                case SOUTH:
                    if(y >= maxHeight - height) {
                        turnLeft();
                    }
                    else {
                        y += SPEED;
                        mowLawn(width);
                    }
                    break;
                case EAST:
                    if(x >= width * dx) {
                        if(y > 0) {
                            turnLeft();
                        }
                        else {
                            turnRight();
                        }
                    }
                    else {
                        x += SPEED;
                        mowLawn(height);
                    }
                    break;
            }
        }
    }

    private void mowLawn(int length) {
        mowed += (length * SPEED);
        isFinished = mowed >= mtArea;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
