package com.tnh.baseware.core.enums;

import com.tnh.baseware.core.enums.base.BaseEnum;
import com.tnh.baseware.core.exceptions.BWCGenericRuntimeException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ZoneType implements BaseEnum<String> {
    CLASSROOM("CLASSROOM", "classroom", "Phòng học"),
    SCHOOL("SCHOOL", "school", "Trường học"),
    OUTDOOR("OUTDOOR", "outdoor", "Khu vực ngoài trời"),
    REGION("REGION", "region", "Khu vực");

    private final String value;
    private final String name;
    private final String displayName;

    public static ZoneType fromValue(String value) {
        for (var type : ZoneType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new BWCGenericRuntimeException("Unknown type: " + value);
    }
}
