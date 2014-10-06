import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SnowflakeCatcher extends PApplet {

SnowFlake [] snow;
Flower [] pinkflowers;
Orange [] oranges;

public void setup()
{
  size(600, 600);
  background(0);
  snow = new SnowFlake[1001];
  pinkflowers = new Flower[800];
  oranges = new Orange[600];
  for(int i = 0; i < snow.length; i++)
  {
    snow[i] = new SnowFlake();
  }
  for(int i = 0; i < pinkflowers.length; i++)
  {
    pinkflowers[i] = new Flower();
  }
  for(int i = 0; i < oranges.length; i++)
  {
    oranges[i] = new Orange();
  }
}

int what = 1;

public void draw()
{
  //background(0);
  rect(0, 600, 600, 10);
  if(what == 1)
  {
    for(int i = 0; i < snow.length; i++)
    {
      snow[i].move();
      snow[i].lookDown();
      snow[i].show();
      snow[i].erase();
      snow[i].wrap();
    }
  }
  if(what == 2)
  {
    for(int i = 0; i < pinkflowers.length; i++)
    {
      pinkflowers[i].erase();
      pinkflowers[i].move();
      pinkflowers[i].lookDown();
      pinkflowers[i].show();
      pinkflowers[i].wrap();
    }
  }
  if(what == 3)
  {
    for(int i = 0; i < oranges.length; i++)
    {
      oranges[i].erase();
      oranges[i].move();
      oranges[i].lookDown();
      oranges[i].show();
      oranges[i].wrap();
    }
  }
}

public void keyPressed()
{
  if(key == 'q')
  {
    background(0);
    for(int i = 0; i < snow.length; i++) //new positions for snowflakes
    {
      snow[i] = new SnowFlake();
    }
    what = 1;
  }
  if(key == 'w')
  {
    background(0);
    for(int i = 0; i < pinkflowers.length; i++) //new positions for flowers
    {
      pinkflowers[i] = new Flower();
    }
    what = 2;
  }
  if(key == 'e')
  {
    background(0);
    for(int i = 0; i < oranges.length; i++) //new positions for oranges
    {
      oranges[i] = new Orange();
    }
    what = 3;
  }
  System.out.println(what);
}

public void mouseDragged()
{
  noStroke();
  if(mouseButton == LEFT)
  {
    if(what == 1)
    {
      fill(127, 162, 189);
    }
    else
    {
      fill(103, 158, 97);
    }
    ellipse(mouseX, mouseY, 20, 20);
  }
  if(mouseButton == RIGHT)
  {
    fill(0);
    ellipse(mouseX, mouseY, 20, 20);
  }
}

class SnowFlake
{
  int x, y;
  boolean isMoving;
  SnowFlake()
  {
    x = (int)(Math.random() * 600);
    y = (int)(Math.random() * 600);
    isMoving = true;
  }
  public void show()
  {
    noStroke();
    fill(255);
    ellipse(x, y, 2, 2);
  }
  public void lookDown()
  {
    int c = color(0);
    if(y >= 0 && y < 600 && get(x, y+3) != c)
    {
      isMoving = false;
    }
    else
    {
      isMoving = true;
    }
  }
  public void erase()
  {
    fill(0);
    ellipse(x, y-2, 3, 3);
    if(y >= 596)
    {
      int myX = x;
      int myY = y;
      ellipse(myX, myY, 3, 3);
    }
  }
  public void move()
  {
    if(isMoving == true)
    {
      y = y + (int)(Math.random() * 2) + 1;
    }
  }
  public void wrap()
  {
    if(y >= 596)
    {
      x = (int)(Math.random() * 600);
      y = 0;
    }
  }
}

class Flower
{
  int x, y;
  boolean isMoving;
  Flower()
  {
    x = (int)(Math.random() * 600);
    y = (int)(Math.random() * 600);
    isMoving = true;
  }
  public void show()
  {
    noStroke();
    fill(255, 153, 204);
    ellipse(x, y, 2, 2);
    ellipse(x, y-2, 2, 2);
    ellipse(x+2, y, 2, 2);
    ellipse(x+1, y+2, 2, 2);
    ellipse(x-1, y+2, 2, 2);
    ellipse(x-2, y, 2, 2);
  }
  public void lookDown()
  {
    int c = color(0);
    if(y >= 0 && y < 600 && get(x, y+4) != c)
    {
      isMoving = false;
    }
    else
    {
      isMoving = true;
    }
  }
  public void erase()
  {
    fill(0);
    int myX = x;
    int myY = y;
    ellipse(myX, myY, 7, 7);
  }
  public void move()
  {
    if(isMoving == true)
    {
      y = y + (int)(Math.random() * 2) + 1;
    }
  }
  public void wrap()
  {
    if(y >= 595)
    {
      int myX = x;
      int myY = y;
      x = (int)(Math.random() * 600);
      y = 0;
      fill(0);
      ellipse(myX, myY, 8, 8); //black circle to replace flower
    }
  }
}

class Orange
{
  int x, y;
  boolean isMoving;
  Orange()
  {
    x = (int)(Math.random() * 600);
    y = (int)(Math.random() * 600);
    isMoving = true;
  }
  public void show()
  {
    noStroke();
    fill(255,127,80);
    ellipse(x, y, 8, 8);
    fill(223, 86, 76);
    ellipse(x+1, y-1, 3, 3);
  }
  public void lookDown()
  {
    int c = color(0);
    if(y >= 0 && y < 600 && get(x, y+7) != c)
    {
      isMoving = false;
    }
    else
    {
      isMoving = true;
    }
  }
  public void erase()
  {
    noStroke();
    fill(0);
    int myX = x;
    int myY = y;
    ellipse(myX, myY, 11, 11);
  }
  public void move()
  {
    if(isMoving == true)
    {
      y = y + (int)(Math.random() * 2) + 1;
    }
  }
  public void wrap()
  {
    if(y >= 590)
    {
      int myX = x;
      int myY = y;
      x = (int)(Math.random() * 600);
      y = 0;
      noStroke();
      fill(0);
      ellipse(myX, myY, 11, 11); //black circle 
    }
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SnowflakeCatcher" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
