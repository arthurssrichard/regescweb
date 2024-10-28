package br.com.arthurssrichard.regescweb.dto;

import br.com.arthurssrichard.regescweb.models.Professor;
import br.com.arthurssrichard.regescweb.models.StatusProfessor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RequisicaoNovoProfessor {
    @NotBlank
    @NotNull
    private String name;
    @NotNull
    @DecimalMin(value="0.0", inclusive = false)
    private BigDecimal salary;
    private StatusProfessor statusProfessor;

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

    public Professor toProfessor(){
        Professor professor = new Professor();
        professor.setName(name);
        professor.setSalary(salary);
        professor.setStatusProfessor(statusProfessor);
        return professor;
    }

    @Override
    public String toString() {
        return "RequisicaoNovoProfessor{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", statusProfessor=" + statusProfessor +
                '}';
    }
}
