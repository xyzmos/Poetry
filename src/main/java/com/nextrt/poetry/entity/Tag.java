package com.nextrt.poetry.entity;

import lombok.*;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Tag implements Serializable {
    private Integer tagId;
    @NonNull
    private String tagName;
    @NonNull
    private Integer tagType;
    @NonNull
    private Integer tagSuperId;
    private static final long serialVersionUID = 1L;
}