package br.com.arthurssrichard.regescweb.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private StatusProfessor statusProfessor;

    public Professor() {}

    public Professor(String name, BigDecimal salary, StatusProfessor statusProfessor) {
        this.name = name;
        this.salary = salary;
        this.statusProfessor = statusProfessor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", status=" + statusProfessor +
                '}';
    }
}
