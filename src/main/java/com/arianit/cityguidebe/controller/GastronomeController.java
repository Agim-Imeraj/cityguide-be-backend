package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.request.GastronomeRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.dto.request.UpdateCityRequest;
import com.arianit.cityguidebe.dto.request.UpdateGastronomeRequest;
import com.arianit.cityguidebe.entity.TypeOfGastronome;
import com.arianit.cityguidebe.service.GastronomeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gastronomes")

public class GastronomeController {

    private final GastronomeService gastronomeService;

    public GastronomeController(GastronomeService gastronomeService) {
        this.gastronomeService = gastronomeService;
    }

    @PostMapping
    public ResponseEntity<GastronomeDto> createGastronome(@RequestBody @Valid GastronomeRequest gastronomeRequest) {
        GastronomeDto createdGastronome = gastronomeService.create(gastronomeRequest);
        return new ResponseEntity<>(createdGastronome, HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<List<GastronomeDto>> createAllGastronome(@RequestBody @Valid List<GastronomeRequest> gastronomeRequest) {
        List<GastronomeDto> createdGastronome = gastronomeService.createAll(gastronomeRequest);
        return new ResponseEntity<>(createdGastronome, HttpStatus.CREATED);
    }
    @GetMapping("/filter")
    public List<GastronomeDto> getGastronomesByCityIdAndTypes(
            @RequestParam Long cityId,
            @RequestParam List<TypeOfGastronome> types) {
        return gastronomeService.getGastronomesByCityIdAndTypes(cityId, types);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GastronomeDto> getGastronomeById(@PathVariable Long id) {
        GastronomeDto gastronomeDto = gastronomeService.getById(id);
        return ResponseEntity.ok(gastronomeDto);
    }

    @GetMapping("/pagable")
    public Page<GastronomeDto> getAllPagable(@Valid PageRequest pageRequest){
        return gastronomeService.getAllPagable(pageRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<List<GastronomeDto>> getAll() {
        List<GastronomeDto> gastronomeDtos = gastronomeService.getAll();
        return ResponseEntity.ok(gastronomeDtos);
    }

    @GetMapping("/get/theMostVisited")
    public ResponseEntity<List<GastronomeDto>> getTheMostVisited() {
        List<GastronomeDto> gastronomeDtos = gastronomeService.getTheMostVisitedGastronomes();
        return ResponseEntity.ok(gastronomeDtos);
    }

    @GetMapping
    public List<GastronomeDto> getAllByIds(@RequestParam List<Long> ids) {
        return gastronomeService.getAllByIds(ids);
    }


    @GetMapping("get//byCityId/{cityId}")
    public ResponseEntity<List<GastronomeDto>> getGastronomesByCityId(@PathVariable long cityId) {
        List<GastronomeDto> gastronomes = gastronomeService.getGastronomesByCityId(cityId);
        return new ResponseEntity<>(gastronomes, HttpStatus.OK);
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<GastronomeDto>> getGastronomesByUserId(@PathVariable long userId) {
        List<GastronomeDto> gastronomes = gastronomeService.getGastronomesByUserId(userId);
        return new ResponseEntity<>(gastronomes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastronomeDto> update(
            @PathVariable Long id,
            @RequestBody UpdateGastronomeRequest request) {
        GastronomeDto updatedGastronome = gastronomeService.update(id, request);
        return new ResponseEntity<>(updatedGastronome, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGastronome(@PathVariable Long id) {
        gastronomeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/typesOfGastronome")
    public List<TypeOfGastronome> getAllTypesOfGastronome() {
        return Arrays.asList(TypeOfGastronome.values());
    }
}
