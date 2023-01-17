package Entidades;

import Entidades.MedicamentosEntity;
import Entidades.PacientesEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="Recetas")
public class RecetasEntity implements Serializable {
    @Id
    @Column(name = "idReceta")
    private int idReceta;

    @Column(name = "fechaFin")
    private java.sql.Date fechaFin;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="idMedicamento")
    private MedicamentosEntity medicamento;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="idPaciente")
    private PacientesEntity paciente;

    public RecetasEntity(int idReceta, MedicamentosEntity medicamento, PacientesEntity paciente, Date fechaFin) {
        this.idReceta = idReceta;
        this.medicamento = medicamento;
        this.paciente = paciente;
        this.fechaFin = fechaFin;
    }

    public RecetasEntity() {
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public MedicamentosEntity getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(MedicamentosEntity medicamento) {
        this.medicamento = medicamento;
    }

    public PacientesEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacientesEntity paciente) {
        this.paciente = paciente;
    }
}