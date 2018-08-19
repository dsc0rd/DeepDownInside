package me.dsc0rd.bungeongame.logic;

public class Vector3 {

    private double x;
    private double y;
    private double z;

    public Vector3() {
        this(0, 0, 0);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private void add(double _x, double _y, double _z) {
        this.x += _x;
        this.y += _y;
        this.z += _z;
    }

    private void sub(double _x, double _y, double _z) {
        this.x -= _x;
        this.y -= _y;
        this.z -= _z;
    }

    private void mul(double _x, double _y, double _z) {
        this.x *= _x;
        this.y *= _y;
        this.z *= _z;
    }

    private void div(double _x, double _y, double _z) {
        this.x /= _x;
        this.y /= _y;
        this.z /= _z;
    }


    public Vector3 add(Vector3 target) {
        this.add(target.x, target.y, target.z);
        return this;
    }

    public Vector3 sub(Vector3 target) {
        this.sub(target.x, target.y, target.z);
        return this;
    }

    public Vector3 mul(Vector3 target) {
        this.mul(target.x, target.y, target.z);
        return this;
    }

    public Vector3 div(Vector3 target) {
        this.div(target.x, target.y, target.z);
        return this;
    }

    public Vector3 scale(double v) {
        this.mul(v, v, v);
        return this;
    }

    public Vector3 scale(double x, double y, double z) {
        this.mul(x, y, z);
        return this;
    }

    public Vector3 set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3 diff(Vector3 target) {

        return target.sub(this).abs();
    }

    public Vector3 abs() {
        return new Vector3(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }


    @Override
    public Vector3 clone() throws CloneNotSupportedException {
        return new Vector3(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public String toString() {
        return this.getX() + " " + this.getY() + " " + this.getZ();
    }

    public Vector3 max(double maximum) {
        this.x = x < -maximum ? -maximum : x > maximum ? maximum : x;
        this.y = y < -maximum ? -maximum : y > maximum ? maximum : y;
        this.z = z < -maximum ? -maximum : z > maximum ? maximum : z;
        return this;
    }

    public boolean inRadius(Vector3 target, double radius1, double radius2) {
        return (
                this.x + radius1 >= target.x - radius2 &&
                        this.x + radius1 <= target.x + radius2 &&
                        this.y + radius1 >= target.y - radius2 &&
                        this.y + radius1 <= target.y + radius2
        )
                ||
                (
                        this.x - radius1 >= target.x - radius2 &&
                                this.x - radius1 <= target.x + radius2 &&
                                this.y - radius1 >= target.y - radius2 &&
                                this.y - radius1 <= target.y + radius2
                );
    }

    public double angleFromPoint(Vector3 start) {
        return angleFromPoint(start.x, start.y);
    }

    public double angleFromPoint(double x, double y) {
        return MathUtils.getAngleOfLineBetweenTwoPoints(x, y, this.x, this.y);
    }

    public Vector3 copy() {
        return new Vector3(this.x, this.y, this.z);
    }
}
