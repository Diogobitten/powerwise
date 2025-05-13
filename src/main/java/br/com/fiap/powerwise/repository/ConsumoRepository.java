package br.com.fiap.powerwise.repository;

import br.com.fiap.powerwise.model.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ConsumoRepository extends JpaRepository<Consumo, Long> {

    // Buscar registros acima de um determinado limite
    List<Consumo> findByConsumoKwhGreaterThan(double limite);

    // Buscar por nome do equipamento
    List<Consumo> findByEquipamentoIgnoreCase(String equipamento);

    // Buscar por intervalo de datas
    @Query("SELECT c FROM Consumo c WHERE c.dataLeitura BETWEEN :inicio AND :fim")
    List<Consumo> buscarPorPeriodo(LocalDate inicio, LocalDate fim);
}
