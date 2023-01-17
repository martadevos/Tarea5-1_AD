package Entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Medicamentos")
public class MedicamentosEntity implements Serializable {
    @Id
    @Column(name = "idMedicamento")
    private int idMedicamento;

    @Column(name = "composicion")
    private String composicion;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idMedicamento")
    private List<RecetasEntity> listaRecetas;

    public MedicamentosEntity(int idMedicamento, String composicion) {
        this.idMedicamento = idMedicamento;
        this.composicion = composicion;
    }

    public MedicamentosEntity() {
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public List<RecetasEntity> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<RecetasEntity> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }
}
