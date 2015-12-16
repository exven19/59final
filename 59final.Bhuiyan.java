// MD NAYEEM U. BHUIYAN
// FINAL


  float sunX=0; 
  float sunY=50;
  float konX, konY; 
  float venX, venY; 
  
  int many = 5;
  Squid school[]= new Squid[many];
  String names[]= { "Fotto", "Fono", "Feca", "Fariel", "Fusala" };
  float spacing;
  
  Boat founty=  new Boat();
  
  float surface;
  int score=0;

void setup() {

  size( 800, 600 ) ;
  spacing=  width/(many+1);
  reset();

  konX = pmouseX-10;
  konY = pmouseY-10;

  venX = mouseX;
  venY = mouseY;

  // grass
  fill( 190, 255, 190 );
  rect(300,300,300,300);
  
}

void reset() {
  surface=  random(  height/4, height/2 );
  
  // Many squids.
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i]=  new Squid( names[i], x );
    x += spacing;
  }
  founty.name=  "Founty";
}


void draw() {
  // Next frame: sky, sun, hero, etc.
  
    // blue sky 
  background( 200, 250, 100 ) ; 

  // grass 
  fill( 100, 220, 250 );  
  rectMode (CORNER);
  rect( 0, height/4, width, height/0.5) ;

  // Sun
  fill( 255, 25, 120 ) ;
  ellipse( sunX, sunY, 80, 80 ) ;
  sunX = sunX + 3;
  sunX = sunX % + width;

  fill( 0 );              
  textSize( 25 );
  textAlign( BOTTOM, LEFT );
  text(" MD NAYEEM U. BHUIYAN " , width/10, height*7/8 );
  
  // Ragdoll ven
  venX = mouseX;
  venY = mouseY;
  fill(  250, 150, 40 ) ;
  strokeWeight( 6 ) ;
  rect( mouseX, mouseY, 30, 70 ) ; //body
  line( mouseX, mouseY, pmouseX-20, pmouseY+70 ) ; //arm
  line( mouseX+30, mouseY, pmouseX+50, pmouseY+70 ) ; //arm
  line( mouseX, mouseY+70, pmouseX, pmouseY+130 ) ; //leg
  line( mouseX+30, mouseY+75, pmouseX+30, pmouseY+130 ) ; //leg
  ellipse( pmouseX+15, pmouseY-15, 30, 30 ) ; //head

 //scene();
  if (key >= 'A' && key <= 'Z') {
    boatReport( 50, founty, 1 );
    fishReport( surface+50, school, school.length);
  }
  else action();
  show();
  messages();
}

void messages() {
  fill(0);
  textSize( 20 );
  text( "Poseidon's Water", width/3, 20 );
  textSize(12);
  text( "Armageddon", width/3, 40 );
  text( "FINAL EXAM", 10, height-10 );
  if (score>0) text( "SCORE:  "+score, width*3/4, 20 );
  if (score>900) {
    if (key == 'q') score=0;
    text( "Maximum score.\nQUITTING NOW\nPress the 'q' key to continue", width/2, 60 );
    if (score>10000) { exit(); }
  }
}

void action() {
  // Move squids & boats.
  for (int i=0; i<many; i++ ) {
    school[i].move();
  }
  founty.move();
  
}

void show() {
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i].x=  x;
    x += spacing;
    school[i].show();
  }
  founty.show();
}

void boatReport( float top, Boat b, int many ) {
  fill(255,200,200);
  rect( 50,top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150,0,0);
  text( "BOAT", x+20, y );
  text( "cargo", x+70, y );
  text( "x", x+105, y );
  text( "dx", x+205, y );
  fill(0);
  //
  y += 15;
  text( 1, x, y );
  text( b.name, x+20, y );
  text( b.cargo, x+70, y );
  text( b.x, x+100, y );
  text( b.dx, x+200, y );
}

void fishReport( float top, Squid[] a, int many ) {
  fill(255,255,200);
  rect( 50,top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150,0,0);
  text( "FISH", x+20, y );
  text( "legs", x+70, y );
  text( "x", x+105, y );
  text( "y", x+205, y );
  text( "dy", x+305, y );
  fill(0);
  for (int i=0; i<many; i++) {
    y += 15;    // Next line.
    text( i, x, y );
    text( a[i].name, x+20, y );
    text( a[i].legs, x+70, y );
    text( a[i].x, x+100, y );
    text( a[i].y, x+200, y );
    text( a[i].dy, x+300, y );
  }
}

void keyPressed() {
  if (key == 'r') reset();
}

class Squid {
  float x,y;        // Coordinates
  float dx=0,dy=0;  // Speed
  float w=40,h=40;
  int legs=10;      // Number of legs.
  String name="";
  float r,g,b;      // Color.
  int count=0;
  
  Squid( String s, float x ) {
    this.name=  s;
    this.x=x;
    bottom();
    // Purplish
    r=  random(100, 255);
    g=  random(0, 100);
    b=  random(100, 250);
  }
  
  void bottom() {
    y=  height - h;
    dy=  -random( 0.1, 0.9 );
    legs=  int( random(1, 10.9) );
  }
  void move() {
    x += dx;
    y += dy;
    if (y>height) { 
      bottom();
      count++;
    }
    else if (y<surface) { 
      dy=  -3 * dy;        // Descend fast!
    }
  }
  

  void show() {
    fill(r,g,b);
    stroke(r,g,b);
    ellipse( x,y, w,h );         // round top
    rect( x-w/2,y, w,h/2 );      // flat bottom
    fill(255);
    float blink=10;
    if ( y%100 > 80) blink=2;
    ellipse( x,y-h/4, 10, blink );     // eye
    // Legs
    fill(r,g,b);                 // legs.
    float legX=  x-w/2, foot=0;
    foot=  dy>=0 ? 0 : (y%47 > 23) ? 5 : -5;
    strokeWeight(3);
    for (int i=0; i<legs; i++) {
      line( legX, y+h/2, legX+foot, 20+y+h/2 );
      legX += w / (legs-1);
    }
    
       strokeWeight(3);
    fill(200,200,0);
    text( name, x-w/2, y-10+h/2 );
    fill(0);
    text( legs, x+2-w/2, y+h/2 );
    fill(255);
    if (count>0) text( count, x, y+h/2 );
  }
  
  boolean hit( float xx, float yy ) {
    return dist( xx,yy, x,y ) < h;
  }
}  
 
class Boat {
  String name="";
  float x=0, y=surface, dx=5;
  int cargo=0, caught=0;
  void move() {
    //// Fish before move:  check each squid.
    int caught=0;
    for (int i=0; i<many; i++ ) {
      if (school[i].hit( founty.x, surface )) {
        caught += school[i].legs;
      }
    }
    
     cargo += caught;    
    //// Now, move the boat.
    x += dx;
    if (caught>0) x += 2*dx;      //  Jump after catch.
    if (x<0) {
      score += cargo;            // Add cargo to global score.
      cargo=0;
      dx = random( 1, 5 );      // Variable boat speed.
    }
    if (x>width)  {
      dx = -random( 0.5, 3 );    // Slower return.
    }
  }
  
  void show() {
    // Boat.
    fill(255,0,0);
    noStroke();
    rect( x, surface-20, 50, 20 );
    if (dx>0)   triangle( x+50,surface, x+50,surface-20, x+70,surface-20 );
    else        triangle( x,surface, x,surface-20, x-20,surface-20 );
    rect( x+12, surface-30, 25, 10 );      // Cabin.
    fill(0);
    rect( x+20, surface-40, 10, 10 );
    fill(255);
    text( name, x+5, surface-10 );
    fill(0);
    if (cargo>0) text( cargo, x+20, surface );
    // Smoke
    fill( 50,50,50, 200 );
    ellipse( x +20 -10*dx, surface-50, 20, 20 );
    ellipse( x +20 -20*dx, surface-60, 15, 10 );
    ellipse( x +20 -30*dx, surface-70, 8, 5 );
  }    
}

void kon() {
  // Monster kon
  ellipse( konX, konY, 100, 50 );
  fill(255);
  ellipse( konX, konY, 30, 30 );
  fill(100, 100, 255);
  ellipse( konX+(mouseX-pmouseX)/10, konY+(mouseY-pmouseY)/5, 15, 15 );
}



