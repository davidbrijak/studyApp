package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.NewSzemelyDto;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;
import com.projekt.klinikaStudyBase.dto.SzemelyFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SzemelyService {

    SzemelyDetailDto addSzemelyWithSzerepkor(NewSzemelyDto newSzemelyDto);

    SzemelyDetailDto findbyId(Long id);

    List<SzemelyDetailDto> findAll();

    List<SzemelyDetailDto> search(SzemelyFilter szemelyFilter);

    List<SzemelyDetailDto> searchPageAble(PageRequest pageRequest);

    boolean hasAnySzemelyBySzerepkor(Long szerepkorId);
}
