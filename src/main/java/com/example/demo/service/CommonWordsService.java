package com.example.demo.service;

import com.example.demo.exception.InvalidInputException;
import com.example.demo.model.FilesInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommonWordsService {

    private static final String REGEX = "\\W+";

    private static final String REPLACE_WITH = "";

    private static final String FILE_REGEX = " ";

    private static final Logger logger = LoggerFactory.getLogger(CommonWordsService.class);

    public Set<String> getCommonWords(final FilesInput filesInput) throws InvalidInputException {
        final List<String> filesPath = filesInput.getFilesPath();
        final Map<String, Set<String>> commonWordsMap = new HashMap<>();
        if (Objects.isNull(filesPath) || filesPath.isEmpty()) {
            throw new InvalidInputException("File Path can't be empty or null", HttpStatus.BAD_REQUEST.value());
        }
        for (final String fileName : filesPath) {
            processFiles(commonWordsMap, fileName);
        }
        logger.info("The resultant map is: " + commonWordsMap);
        return commonWordsMap.entrySet().stream()
                .filter(e -> e.getValue().size() == filesPath.size())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    private void processFiles(final Map<String, Set<String>> commonWordsMap, final String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            Set<String> file;
            while ((line = br.readLine()) != null) {
                final String[] output = line.split(FILE_REGEX);
                for (final String word : output) {
                    final String modifiedWord = word.replaceAll(REGEX, REPLACE_WITH).toLowerCase();
                    if (commonWordsMap.get(modifiedWord) == null) {
                        file = new HashSet<>();
                        file.add(fileName);
                        commonWordsMap.put(modifiedWord, file);
                    } else {
                        commonWordsMap.get(modifiedWord).add(fileName);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("There is some exception while parsing files. Please check.", e);
            throw new InvalidInputException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }
}
