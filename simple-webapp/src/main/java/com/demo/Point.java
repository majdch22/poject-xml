package com.demo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "point")
public class Point {
    private int id;
    private String localisation;
    private String typeElectrique;
    private String etat;
    private String contexte;

    public Point() {}

    public Point(int id, String localisation, String typeElectrique, String etat, String contexte) {
        this.id = id;
        this.localisation = localisation;
        this.typeElectrique = typeElectrique;
        this.etat = etat;
        this.contexte = contexte;
    }

    @XmlElement
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @XmlElement
    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    @XmlElement
    public String getTypeElectrique() { return typeElectrique; }
    public void setTypeElectrique(String typeElectrique) { this.typeElectrique = typeElectrique; }

    @XmlElement
    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    @XmlElement
    public String getContexte() { return contexte; }
    public void setContexte(String contexte) { this.contexte = contexte; }

    @Override
    public String toString() {
        return "PointCollecte{id=" + id + ", localisation=" + localisation + 
               ", typeElectrique=" + typeElectrique + ", etat=" + etat + 
               ", contexte=" + contexte + "}";
    }
}