package Entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Pacientes")
public class PacientesEntity implements Serializable {
    @Id
    @Column(name = "idPaciente")
    private int idPaciente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nss")
    private String nss;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idPaciente")
    private List<RecetasEntity> listaRecetas;

    public PacientesEntity(int idPaciente, String nombre, String apellidos, String nss) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nss = nss;
    }

    public PacientesEntity() {
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public List<RecetasEntity> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<RecetasEntity> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }
}
