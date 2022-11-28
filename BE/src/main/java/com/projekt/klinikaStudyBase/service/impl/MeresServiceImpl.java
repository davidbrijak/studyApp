package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.KifizetesekKezelese;
import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.data.entity.Study;
import com.projekt.klinikaStudyBase.data.entity.Szemely;
import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import com.projekt.klinikaStudyBase.data.enums.SzerepkorEnum;
import com.projekt.klinikaStudyBase.dto.MeresDetailDto;
import com.projekt.klinikaStudyBase.dto.MeresFilterDto;
import com.projekt.klinikaStudyBase.dto.NewMeresDto;
import com.projekt.klinikaStudyBase.exception.VizsgalatiModNotFoundException;
import com.projekt.klinikaStudyBase.helper.mapper.MeresMapper;
import com.projekt.klinikaStudyBase.repository.KifizetesekKezeleseRepository;
import com.projekt.klinikaStudyBase.repository.StudyRepository;
import com.projekt.klinikaStudyBase.repository.SzemelyRepository;
import com.projekt.klinikaStudyBase.repository.SzerepkorRepository;
import com.projekt.klinikaStudyBase.service.MeresService;
import com.projekt.klinikaStudyBase.service.criteria.MeresListCriteria;
import com.projekt.klinikaStudyBase.repository.MeresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeresServiceImpl implements MeresService {

    private final MeresRepository meresRepository;
    private final StudyRepository studyRepository;
    private final MeresListCriteria meresListCriteria;
    private final MeresMapper meresMapper;
    private final SzemelyRepository szemelyRepository;
    private final SzerepkorRepository szerepkorRepository;
    private final KifizetesekKezeleseRepository kifizetesekKezeleseRepositoryRepo;

    @Override
    public NewMeresDto add(final NewMeresDto newMeresDto) {
        List<Szemely> szemelyekForSave = new ArrayList<>();
        final var entity = meresMapper.toEntity(newMeresDto);

        final Meres savedMeres = meresRepository.save(entity);
        final Szemely operator_1 = savedMeres.getOperator_1();
        if (operator_1 != null) {
            operator_1.getMeresek().add(savedMeres);
            szemelyekForSave.add(operator_1);
        }

        final Szemely operator_2 = savedMeres.getOperator_2();

        if (operator_2!=null) {
            operator_2.getMeresek().add(savedMeres);
            szemelyekForSave.add(operator_2);
        }

        final Szemely vizsgaloOrvos = savedMeres.getVizsgaloOrvos();
        vizsgaloOrvos.getMeresek().add(savedMeres);

        if (newMeresDto.getOperator_1() != null) {
            addKifizetesKezelesEntity(savedMeres, operator_1, SzerepkorEnum.OPERATOR_1);
        }
        if (newMeresDto.getOperator_2() != null) {
            addKifizetesKezelesEntity(savedMeres, operator_2, SzerepkorEnum.OPERATOR_2);
        }
        addKifizetesKezelesEntity(savedMeres, vizsgaloOrvos, SzerepkorEnum.VIZSGALO_ORVOS);
        szemelyekForSave.add(vizsgaloOrvos);

        //szemelyRepository.saveAll(szemelyekForSave);
        return null;
    }

    @Override
    public List<MeresDetailDto> findAll() {
        List<Meres> meresList = meresRepository.findAll();
        return meresMapper.toListDto(meresList);
    }

    @Override
    public NewMeresDto findById(final Long id) {
        final var meresEntity = meresRepository.findById(id)
                .orElseThrow(() -> new VizsgalatiModNotFoundException(id.toString()));
        return meresMapper.toNewMeres(meresEntity);
    }

    @Override
    public List<MeresDetailDto> findByFilter(final MeresFilterDto meresFilterDto) {
        Specification<Meres> meresSpecification = meresListCriteria.create(meresFilterDto);
        List<Meres> meresList = meresRepository.findAll(meresSpecification);
        return meresMapper.toListDto(meresList);
    }

    @Override
    public List<MeresDetailDto> findStudy(final Long studyAzonosito) {
        final Study study = studyRepository.findById(studyAzonosito)
                .orElse(null);
        final List<Meres> meresDetailDtos = meresRepository.findAllByStudy(study);
        return meresMapper.toListDto(meresDetailDtos);
    }

    private void addKifizetesKezelesEntity(final Meres meres, final Szemely szemely, final SzerepkorEnum szerepkorEnum) {
        final Szerepkor szerepkor = szerepkorRepository.findSzerepkorByHivatkozasiNev(szerepkorEnum.name()).get();
        final KifizetesekKezelese kifizetesekKezelese = KifizetesekKezelese.builder()
                .fizetesiHatarido(meres.getFizetesiHatarIdo())
                .isFizetveBySzervezet(false)
                .isFizetveToSzemely(false)
                .szemely(szemely)
                .meresbenBetoltottSzerepkor(szerepkorEnum)
                .kifizetesOsszege(meres.getVizsgalatiMod().getHuf() * szerepkor.getKifizetesAranya()/100)
                .meres(meres)
                .build();

        kifizetesekKezeleseRepositoryRepo.save(kifizetesekKezelese);
    }
}
