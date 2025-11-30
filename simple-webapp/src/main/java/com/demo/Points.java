package com.demo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "points")
public class Points {
    private List<Point> points = new ArrayList<>();

    @XmlElement(name = "point")
    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public boolean removePoint(Point point) {
        return this.points.remove(point);
    }
}