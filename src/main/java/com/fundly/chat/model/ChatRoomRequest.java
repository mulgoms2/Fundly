package com.fundly.chat.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ChatRoomRequest {
    private String user_id;
    private String to_user_id;
}
