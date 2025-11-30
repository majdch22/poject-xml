package com.demo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vehicule")
public class Vehicule {

    private int id;
    private String matricule;
    private int capacite;
    private boolean disponible;

    public Vehicule() {}

    public Vehicule(int id, String matricule, int capacite, boolean disponible) {
        this.id = id;
        this.matricule = matricule;
        this.capacite = capacite;
        this.disponible = disponible;
    }

    @XmlElement
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @XmlElement
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    @XmlElement
    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    @XmlElement
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "Vehicule{id=" + id + ", matricule=" + matricule + 
               ", capacite=" + capacite + ", disponible=" + disponible + "}";
    }
}