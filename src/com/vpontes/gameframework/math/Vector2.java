package com.vpontes.gameframework.math;

public class Vector2 {
    public static double TO_RADIANS = (1 / 180.0f) * (double)Math.PI;
    public static double TO_DEGREES = (1 / (double)Math.PI) * 180;
    public double x, y;
    
    public Vector2() {    
    }       
    
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }
    
    public Vector2 cpy() {
        return new Vector2(x, y);
    }       
    
    public Vector2 set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    
    public Vector2 set(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
        return this;
    }
    
    public Vector2 add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }
    
    public Vector2 add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }
    
    public Vector2 sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }
    
    public Vector2 sub(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }
    
    public Vector2 mul(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }
    
    public double len() {               
        return Math.sqrt(x*x + y*y);
    }
    
    public Vector2 nor() {
        double len = len();
        if(len!=0) {
            this.x /= len;
            this.y /= len;
        }
        return this;
    }       
        
    public double angle() {
        double angle = (double)Math.atan2(y, x) * TO_DEGREES;
        if(angle < 0)
            angle += 360;
        return angle;
    }       
    
    public Vector2 rotate(double angle) {
        double rad = angle * TO_RADIANS;
        double cos = (double) Math.cos(rad);
        double sin = Math.sin(rad);
        
        double newX = this.x * cos - this.y * sin;
        double newY = this.x * sin + this.y * cos;
        
        this.x = newX;
        this.y = newY;
        
        return this;
    }

    public double dist(Vector2 other) {
        double distX = this.x - other.x;
        double distY = this.y - other.y;        
        return Math.sqrt(distX*distX + distY*distY);
    }   
    
    public double dist(double x, double y) {
        double distX = this.x - x;
        double distY = this.y - y;        
        return Math.sqrt(distX*distX + distY*distY);
    }   
    
    public double distSquared(Vector2 other) {
        double distX = this.x - other.x;
        double distY = this.y - other.y;        
        return distX*distX + distY*distY;
    }

    public double distSquared(double x, double y) {
        double distX = this.x - x;
        double distY = this.y - y;        
        return distX*distX + distY*distY;
    }       
}