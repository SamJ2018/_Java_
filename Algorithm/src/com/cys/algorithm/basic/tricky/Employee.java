package com.cys.algorithm.basic.tricky;

/**
 * @author missb
 * @create 2020--03--25 11:48 AM
 */

public class Employee {
    public String zs;
    public int i;
    public Employee(String zs, int i) {
        this.zs = zs;
        this.i = i;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "zs='" + zs + '\'' +
                ", i=" + i +
                '}';
    }
}
