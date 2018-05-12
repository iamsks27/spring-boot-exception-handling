package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FilesInput {

    @JsonProperty("files")
    private List<String> filesPath;


    public List<String> getFilesPath() {
        return filesPath;
    }
}
