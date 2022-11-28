package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.Szerepkor;
import com.projekt.klinikaStudyBase.dto.SzerepkorDetailDto;
import com.projekt.klinikaStudyBase.helper.mapper.SzerepkorMapper;
import com.projekt.klinikaStudyBase.repository.SzerepkorRepository;
import com.projekt.klinikaStudyBase.service.SzerepkorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SzerepkorServiceImpl implements SzerepkorService {
    private final SzerepkorRepository szerepkorRepository;
    private final SzerepkorMapper szerepkorMapper;

    @Override
    public SzerepkorDetailDto add(final SzerepkorDetailDto ujSzerepkor) {
        final var entity = szerepkorMapper.toEntity(ujSzerepkor);
        final var mentett = szerepkorRepository.save(entity);
        return szerepkorMapper.toDto(mentett);
    }

    @Override
    public List<SzerepkorDetailDto> findAll() {
        final List<Szerepkor> szerepkorList = szerepkorRepository.findAll();
        return szerepkorMapper.toListDto(szerepkorList);
    }

    @Override
    public void delete(final Long id) {
        szerepkorRepository.deleteById(id);
    }

    @Override
    public SzerepkorDetailDto get(final Long id) {
        final Szerepkor findSzerepkor = szerepkorRepository.findById(id).get();
        return szerepkorMapper.toDto(findSzerepkor);
    }
}
