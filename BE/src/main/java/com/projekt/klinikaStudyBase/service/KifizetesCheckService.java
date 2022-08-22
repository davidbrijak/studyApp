package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.FizetesCheckListDto;
import com.projekt.klinikaStudyBase.dto.KifizetesChanged;

import java.util.List;

public interface KifizetesCheckService {

    List<FizetesCheckListDto> getCheckListBySzemely();

    List<FizetesCheckListDto> getCheckListBySzemelyDetail(Long szemelyId);

    void handleChanged(List<FizetesCheckListDto> kifChanged);




}
