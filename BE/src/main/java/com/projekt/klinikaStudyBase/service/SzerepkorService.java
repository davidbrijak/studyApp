package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.dto.SzerepkorDetailDto;
import java.util.List;

public interface SzerepkorService {

    /**
     * Uj szerepkor hozzaddasa
     * @param szerepkorDetailDto
     * @return
     */
    SzerepkorDetailDto add(SzerepkorDetailDto szerepkorDetailDto);

    /**
     * Osszes szerepkor lekerese
     * @return
     */
    List<SzerepkorDetailDto> findAll();

    /**
     * Szerepkor torlese id alapjan
     * @param id
     */
    void delete(Long id);

    /**
     * Egy szerepkor lekerese id alapjan
     * @param id
     * @return
     */
    SzerepkorDetailDto get(Long id);
}
