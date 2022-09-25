package com.mohammed.mosa.eg.drug_info.utility;


import java.io.Serializable;
import java.util.Objects;

public class Drug implements Serializable , Comparable<Drug>{

    private int id;
    private float price;
    private String firstName;
    private String lastName;
    private String company;
    private String pharmacology;
    private String srde;
    private String gi;
    private String route;

    public Drug() {

    }

    public Drug(int id, float price, String firstName, String lastName,
                String company, String pharmacology, String srde, String gi, String route) {
        this.id = id;
        this.price = price;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.pharmacology = pharmacology;
        this.srde = srde;
        this.gi = gi;
        this.route = route;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPharmacology() {
        return pharmacology;
    }

    public void setPharmacology(String pharmacology) {
        this.pharmacology = pharmacology;
    }

    public String getSrde() {
        return srde;
    }

    public void setSrde(String srde) {
        this.srde = srde;
    }

    public String getGi() {
        return gi;
    }

    public void setGi(String gi) {
        this.gi = gi;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return id == drug.id && Float.compare(drug.price, price) == 0
                && Objects.equals(firstName, drug.firstName) && Objects.equals(lastName, drug.lastName)
                && Objects.equals(company, drug.company) && Objects.equals(pharmacology, drug.pharmacology)
                && Objects.equals(srde, drug.srde) && Objects.equals(gi, drug.gi) && Objects.equals(route, drug.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, firstName, lastName, company, pharmacology, srde, gi, route);
    }


    @Override
    public int compareTo(Drug drug) {
        return new Float(getPrice()).compareTo(drug.getPrice());
    }
}
