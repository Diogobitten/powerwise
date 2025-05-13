package br.com.fiap.powerwise.service;

import br.com.fiap.powerwise.model.Consumo;
import br.com.fiap.powerwise.repository.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository repository;

    public List<Consumo> listarTodos() {
        return repository.findAll();
    }

    // Alias para "listarTodos"
    public List<Consumo> listar() {
        return listarTodos();
    }

    public Optional<Consumo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Consumo salvar(Consumo consumo) {
        return repository.save(consumo);
    }

    public Consumo atualizar(Long id, Consumo atualizado) {
        return repository.findById(id)
                .map(c -> {
                    c.setEquipamento(atualizado.getEquipamento());
                    c.setConsumoKwh(atualizado.getConsumoKwh());
                    c.setDataLeitura(atualizado.getDataLeitura());
                    return repository.save(c);
                })
                .orElse(null);
    }

    public boolean deletar(Long id) {
        return repository.findById(id)
                .map(c -> {
                    repository.delete(c);
                    return true;
                })
                .orElse(false);
    }

    // Alias para deletar
    public boolean excluir(Long id) {
        return deletar(id);
    }

    public List<Consumo> buscarPorEquipamento(String nome) {
        return repository.findByEquipamentoIgnoreCase(nome);
    }

    public List<Consumo> buscarAcimaDe(double limite) {
        return repository.findByConsumoKwhGreaterThan(limite);
    }

    // Alias para buscar por consumo elevado
    public List<Consumo> buscarPorConsumoElevado(double limite) {
        return buscarAcimaDe(limite);
    }

    public List<Consumo> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return repository.buscarPorPeriodo(inicio, fim);
    }

    public Double calcularMediaConsumo(String equipamento) {
        List<Consumo> consumos = repository.findByEquipamentoIgnoreCase(equipamento);
        if (consumos.isEmpty()) return null;

        return consumos.stream()
                .mapToDouble(Consumo::getConsumoKwh)
                .average()
                .orElse(0.0);
    }
}
