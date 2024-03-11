package com.bouzidi.samples.mowitnow.controller;


import com.bouzidi.samples.mowitnow.domain.Lawn;
import com.bouzidi.samples.mowitnow.exceptions.FileFormatException;
import com.bouzidi.samples.mowitnow.service.FileStorageService;
import com.bouzidi.samples.mowitnow.service.MowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * MowItController expose the REST entryPoint
 */

@Slf4j
@RestController
@AllArgsConstructor
public class MowItNowController {


    private FileStorageService fileStorageService;

    private MowService mowService;


    @PostMapping("/uploadFile")
    public MowItNowResponse uploadFile(@RequestParam("file") MultipartFile file) {

        String[] rows = fileStorageService.getFileContent(file);
        Lawn initial = mowService.processEntry(rows);
        Lawn last = mowService.executeMowers(initial);
        return MowItNowResponse.builder().initial(initial).last(last).build();
    }


    @ExceptionHandler(FileFormatException.class)
    public ResponseEntity<Object> handleException(FileFormatException ex) {
        return buildResponseEntity(ApiError.builder().messages(ex.getMessages()).status(HttpStatus.BAD_REQUEST).build());

    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
