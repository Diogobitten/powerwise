package br.com.fiap.powerwise.dto;

import java.time.LocalDate;

public class ConsumoDTO {

    private String equipamento;
    private double consumoKwh;
    private LocalDate dataLeitura;

    public ConsumoDTO() {}

    public ConsumoDTO(String equipamento, double consumoKwh, LocalDate dataLeitura) {
        this.equipamento = equipamento;
        this.consumoKwh = consumoKwh;
        this.dataLeitura = dataLeitura;
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
}
