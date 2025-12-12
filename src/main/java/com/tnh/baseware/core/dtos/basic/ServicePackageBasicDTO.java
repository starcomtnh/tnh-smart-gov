package com.tnh.baseware.core.dtos.basic;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServicePackageBasicDTO {
    UUID id;
    String code;
    String name;
    String description;
    BigDecimal basePrice;
}
