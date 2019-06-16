package com.nextrt.poetry.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poetry implements Serializable {
    private Integer poetryId;

    private String subject;

    private String dynasty;

    private String author;

    private String content;

    private static final long serialVersionUID = 1L;

}