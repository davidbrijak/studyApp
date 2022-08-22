package com.projekt.klinikaStudyBase.service;

import java.io.IOException;
import java.util.stream.Stream;

import com.projekt.klinikaStudyBase.data.entity.FileDB;
import com.projekt.klinikaStudyBase.repository.FileDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class FileStorageService {

	private final FileDBRepository fileDBRepository;
	public FileDB store(final Long studyId, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(studyId, fileName, file.getContentType(), file.getBytes());
		return fileDBRepository.save(FileDB);
	}
	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}

	public void delete(String id) {
		fileDBRepository.deleteById(id);
	}
}