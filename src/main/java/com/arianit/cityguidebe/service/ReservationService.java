package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.GastronomeRepository;
import com.arianit.cityguidebe.dao.ReservationRepository;
import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.ReservationDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.GastronomeRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.dto.request.ReservationRequest;
import com.arianit.cityguidebe.dto.request.UpdateReservationRequest;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.entity.Reservation;
import com.arianit.cityguidebe.entity.User;
import com.arianit.cityguidebe.exception.MismatchedInputException;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.ReservationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final GastronomeRepository gastronomeRepository;
    private final GastronomeService gastronomeService;

    public ReservationDto create(ReservationRequest reservationRequest){
        Gastronome gastronomeInDb = gastronomeRepository.findById(reservationRequest.gastronomeId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",reservationRequest.gastronomeId())
                ));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();
        Reservation reservation = reservationMapper.toEntity(reservationRequest);
        reservation.setUserId(loggedUser.getId());
        mapReservationToGastronome(reservationRequest,reservation);
        Reservation reservationInDb = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservationInDb);
    }

    public ReservationDto getById(Long id){
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",id)
                ));
        return reservationMapper.toDto(reservationInDb);
    }
    public Page<ReservationDto> getAllPagable(@Valid PageRequest pageRequest){
        return reservationRepository.findAll(pageRequest.getPageable()).map(
                reservationMapper::toDto
        );
    }

    public List<ReservationDto> getAll(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }



    public List<ReservationDto> getByUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();
        return reservationRepository.findByUserId(loggedUser.getId());
    }

    public ReservationDto update (Long id, UpdateReservationRequest request){
        if (!(id.equals(request.id()))){
            throw new MismatchedInputException("Ids dosent match");
        }
        Reservation reservationInDb = reservationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        String.format("Reservation with %s id not found", id)));
        reservationMapper.toEntity(request,reservationInDb);
        mapReservationToUpdateGastronome(request,reservationInDb);
        return reservationMapper.toDto(reservationRepository.save(reservationInDb));
    }

    public void delete(Long id){
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",id)
                ));
        reservationRepository.deleteById(id);
    }

    public void mapReservationToGastronome(ReservationRequest reservationRequest, Reservation reservation){
        GastronomeDto gastronomeDto = gastronomeService.getById(reservationRequest.gastronomeId());
        reservation.setGastronomeId(gastronomeDto.getId());
    }

    public void mapReservationToUpdateGastronome(UpdateReservationRequest request, Reservation reservation){
        if(request.gastronomeId() != null){
        GastronomeDto gastronomeDto = gastronomeService.getById(request.gastronomeId());
        reservation.setGastronomeId(gastronomeDto.getId());
        }
    }
}
