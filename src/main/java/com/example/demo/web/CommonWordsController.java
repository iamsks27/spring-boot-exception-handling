package com.example.demo.web;

import com.example.demo.exception.InvalidInputException;
import com.example.demo.model.FilesInput;
import com.example.demo.service.CommonWordsService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CommonWordsController {

    private final CommonWordsService wordsService;

    public CommonWordsController(final CommonWordsService wordsService) {
        this.wordsService = wordsService;
    }

    @PostMapping("/commonwords")
    public Set<String> getCommonWords(@RequestBody final FilesInput filesInput) throws InvalidInputException {
        return wordsService.getCommonWords(filesInput);
    }

    @GetMapping("/test")
    public String getResponse(@RequestParam("val") final Integer val) {
        return "Done";
    }
}
