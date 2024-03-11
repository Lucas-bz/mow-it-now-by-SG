package com.bouzidi.samples.mowitnow.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FileStorageService.class)
class FileStorageServiceTest {

    @Autowired
    FileStorageService fileStorageService;


    @Test
    void testFileContent() {
        String fileContent = "5 5\n1 2 N\nGAGAGAGAA";
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", "file.txt",
                "text/plain", fileContent.getBytes());
        String[] lines = fileStorageService.getFileContent(mockMultipartFile);

        assertEquals(3, lines.length);
        assertTrue(new File("./upload/file.txt").exists());

    }
}