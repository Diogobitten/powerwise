package br.com.fiap.powerwise.controller;

import br.com.fiap.powerwise.dto.ConsumoDTO;
import br.com.fiap.powerwise.model.Consumo;
import br.com.fiap.powerwise.service.ConsumoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/consumos")
public class ConsumoController {

    private final ConsumoService service;

    public ConsumoController(ConsumoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Consumo> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Consumo> cadastrar(@RequestBody ConsumoDTO dto) {
        Consumo novo = new Consumo(dto.getEquipamento(), dto.getConsumoKwh(), dto.getDataLeitura());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(novo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumo> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consumo> atualizar(@PathVariable Long id, @RequestBody ConsumoDTO dto) {
        return service.buscarPorId(id)
                .map(c -> {
                    c.setEquipamento(dto.getEquipamento());
                    c.setConsumoKwh(dto.getConsumoKwh());
                    c.setDataLeitura(dto.getDataLeitura());
                    return ResponseEntity.ok(service.salvar(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(c -> {
                    service.excluir(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Extra: buscar por consumo acima de um valor
    @GetMapping("/alto-consumo")
    public List<Consumo> buscarPorConsumoElevado(@RequestParam double limite) {
        return service.buscarPorConsumoElevado(limite);
    }

    @GetMapping("/equipamento")
    public List<Consumo> buscarPorEquipamento(@RequestParam String nome) {
        return service.buscarPorEquipamento(nome);
    }

    @GetMapping("/periodo")
    public List<Consumo> buscarPorPeriodo(
            @RequestParam("inicio") String inicio,
            @RequestParam("fim") String fim
    ) {
        return service.buscarPorPeriodo(LocalDate.parse(inicio), LocalDate.parse(fim));
    }

    @GetMapping("/media")
    public ResponseEntity<Double> calcularMedia(@RequestParam String equipamento) {
        Double media = service.calcularMediaConsumo(equipamento);
        if (media == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(media);
    }


}
