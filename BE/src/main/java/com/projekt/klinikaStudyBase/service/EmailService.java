package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.data.entity.Meres;
import com.projekt.klinikaStudyBase.repository.MeresRepository;
import com.projekt.klinikaStudyBase.repository.impl.MeresRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final MeresRepository meresRepository;
    private final MeresRepositoryCustomImpl meresRepositoryCustom;

    final Log log = LogFactory.getLog(this.getClass());

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    @Value("${mail.message}")
    private String MESSAGE_TEXT;

    @Value("${mail.subject}")
    private String MESSAGE_SUBJECT;

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Megnezzuk, hogy van e olyan kifizetes, ami aktualis: akkor lesz aktualis ha a Study-ban foglalt meresek
     * datumat + fizetesi periodus idejet meghaladtuk es meg van olyan meres, ami nincs fizetve, illetve meg
     * nem kuldtunk ki emlekezteto emailt.
     */
    //@Scheduled(cron = "0 * * * * *") //teszt
    @Scheduled(cron = "0 0 2 1/1 * ?") //at 2:00 am
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public void checkKifizetesek() {
        final List<Meres> meresList = meresRepositoryCustom.findAllFizetendo();
        final Date currentDate = convertToDateViaSqlDate(LocalDate.now());
        for (Meres meres: meresList) {
            sendMessage(meres.getStudy().getPenzugyiKontakt().getEmail());
            meres.setFelszolitoEmailDatum(currentDate);
            meresRepository.save(meres);
        }
    }

    private void sendMessage(final String email) {
        SimpleMailMessage message;
        try {
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);
            message.setTo(email);
            message.setSubject(MESSAGE_SUBJECT);
            message.setText(MESSAGE_TEXT);
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Hiba email küldésekor a " + email + "cimre" + e);
        }
    }

    private Date convertToDateViaSqlDate(final LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
