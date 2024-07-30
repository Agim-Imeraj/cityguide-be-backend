package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.FavoriteRepository;
import com.arianit.cityguidebe.dto.*;
import com.arianit.cityguidebe.dto.request.FavoriteRequest;
import com.arianit.cityguidebe.entity.Favorite;
import com.arianit.cityguidebe.entity.User;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.FavoriteMapper;
import com.arianit.cityguidebe.mapper.GastronomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapper mapper;
    private final GastronomeService gastronomeService;



    public List<GastronomeDto> getUserFavoriteGastronomies(String username) {
        List<Favorite> favorites = favoriteRepository.findByNameOfUser(username);
        List<Long> gastronomesIds = favorites.stream()
                .map(Favorite::getGastronomeId)
                .collect(Collectors.toList());
        return gastronomeService.getAllByIds(gastronomesIds);
    }

    public FavoriteDto create(FavoriteRequest request){
        Favorite favorite = mapper.toEntity(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();
        favorite.setNameOfUser(loggedUser.getUsername());
        favorite.setUserId(loggedUser.getId());
        mapGastronomeToFavorite(request,favorite);
        return mapper.toDto(favoriteRepository.save(favorite));
    }

    public FavoriteDto getById(Long id){
        Favorite favoriteInDb = favoriteRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format("Favorite with %s id not found", id)
                )
        );
        return mapper.toDto(favoriteInDb);
    }

    public List<FavoriteDto> getAll (){
        List<Favorite> favorites = favoriteRepository.findAll();
        return favorites.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id){
        Favorite favoriteInDb = favoriteRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format("Favorite with %s id not found", id)
                )
        );
        favoriteRepository.deleteById(id);
    }

    public void deleteFavoritesByGasronomeId(Long gastronomeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();
        favoriteRepository.deleteByNameOfUserAndGastronomeId(loggedUser.getUsername(), gastronomeId);
    }

    private void mapGastronomeToFavorite(FavoriteRequest request, Favorite favorite){
        GastronomeDto gastronomeDto = gastronomeService.getById(request.gastronomeId());
        favorite.setGastronomeId(gastronomeDto.getId());
    }

    public List<Long> findGastronomeIdByNameOfUser(String username) {
        return favoriteRepository.findGastronomeIdByNameOfUser(username);
    }
}
