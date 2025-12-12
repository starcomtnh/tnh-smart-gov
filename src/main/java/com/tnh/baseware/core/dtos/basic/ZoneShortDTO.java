package com.tnh.baseware.core.dtos.basic;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZoneShortDTO {

    UUID id;
    String name;
    String description;
    String typeZone;
}
