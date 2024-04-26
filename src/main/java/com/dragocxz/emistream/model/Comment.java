package com.dragocxz.emistream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private UUID id;
    private String text;
    private UUID authorId;
    private Integer likeCount;
    private Integer deisLikeCount;
}
