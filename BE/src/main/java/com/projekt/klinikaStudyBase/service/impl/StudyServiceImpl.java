package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.Study;
import com.projekt.klinikaStudyBase.dto.StudyDetailDto;
import com.projekt.klinikaStudyBase.dto.NewStudyDto;
import com.projekt.klinikaStudyBase.dto.StudyListDto;
import com.projekt.klinikaStudyBase.helper.mapper.StudyMapper;
import com.projekt.klinikaStudyBase.repository.StudyRepository;
import com.projekt.klinikaStudyBase.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final StudyMapper studyMapper;

    @Override
    public void add(final NewStudyDto newStudyDto) {
        final var studyEntity = studyMapper.toEntity(newStudyDto);
        studyRepository.save(studyEntity);
    }

    @Override
    public StudyDetailDto findById(final Long id) {
        final Study study = studyRepository.findById(id)
                .orElse(null);
        return studyMapper.toDto(study);
    }

    @Override
    public List<StudyListDto> findAll() {
        List<Study> studies = studyRepository.findAll();
        return studyMapper.toListDto(studies);
    }
}
