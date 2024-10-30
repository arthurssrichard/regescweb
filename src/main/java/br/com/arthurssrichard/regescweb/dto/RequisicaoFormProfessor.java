package br.com.arthurssrichard.regescweb.dto;

import br.com.arthurssrichard.regescweb.models.Professor;
import br.com.arthurssrichard.regescweb.models.StatusProfessor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RequisicaoFormProfessor {
    @NotBlank
    @NotNull
    private String name; // em caso de erro: NotNull.RequisicaoNovoProfessor.nome
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

    public Professor toProfessor(Professor professor){
        professor.setName(this.name);
        professor.setSalary(this.salary);
        professor.setStatusProfessor(this.statusProfessor);
        return professor;
    }

    public boolean fromProfessor(Professor professor){
        if(professor.getStatusProfessor() != null && professor.getName() != null && professor.getSalary() != null){
            this.statusProfessor = professor.getStatusProfessor();
            this.name = professor.getName();
            this.salary = professor.getSalary();

            return true;
        }
    return false;
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
