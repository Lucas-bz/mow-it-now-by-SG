package com.bouzidi.samples.mowitnow.service;


import com.bouzidi.samples.mowitnow.exceptions.FileStorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A service Component to store given file
 */

@Service
@Slf4j
public class FileStorageService {

    private final Path fileStorageLocation;


    public FileStorageService() {
        this.fileStorageLocation = Paths.get("./upload")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }


    public String[] getFileContent(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        if (fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        copyFile(file, targetLocation);

        try (Stream<String> stream = getLinesStream(targetLocation).get()) {
            return stream.toArray(String[]::new);
        }

    }

    private Supplier<Stream<String>> getLinesStream(Path target) {
        return () -> {
            try {
                return Files.lines(target);
            } catch (IOException e) {
                log.error("Exception while fetching data from file {}", e.getMessage());
                return Stream.empty();
            }

        };
    }

    private void copyFile(MultipartFile file, Path location) {
        try {
            Files.copy(file.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            log.error("Failed to copy file {}", ex.getMessage());
        }
    }


}
