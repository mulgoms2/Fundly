package com.fundly.user.dto;

import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDto {
    private LikeDto likedto;
    private ProjectDto pjdto;
}
