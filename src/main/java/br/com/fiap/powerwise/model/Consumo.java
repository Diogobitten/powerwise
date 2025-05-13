package br.com.fiap.powerwise.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TBL_CONSUMO")
public class Consumo {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CONSUMO_SEQ"
    )
    @SequenceGenerator(
            name = "CONSUMO_SEQ",
            sequenceName = "CONSUMO_SEQ",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String equipamento;

    @Column(name = "consumo_kwh", nullable = false)
    private double consumoKwh;

    @Column(name = "data_leitura", nullable = false)
    private LocalDate dataLeitura;

    // Construtores
    public Consumo() {}

    public Consumo(String equipamento, double consumoKwh, LocalDate dataLeitura) {
        this.equipamento = equipamento;
        this.consumoKwh = consumoKwh;
        this.dataLeitura = dataLeitura;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public double getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(double consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public LocalDate getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(LocalDate dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    // equals e hashCode (boas práticas para JPA)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumo consumo = (Consumo) o;
        return Double.compare(consumoKwh, consumo.consumoKwh) == 0 &&
                Objects.equals(id, consumo.id) &&
                Objects.equals(equipamento, consumo.equipamento) &&
                Objects.equals(dataLeitura, consumo.dataLeitura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipamento, consumoKwh, dataLeitura);
    }
}
