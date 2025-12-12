package com.tnh.baseware.core.enums;

import com.tnh.baseware.core.enums.base.BaseEnum;
import com.tnh.baseware.core.exceptions.BWCGenericRuntimeException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum OrganizationLevel implements BaseEnum<Integer> {
    COUNTRY(1, "country", "Quốc gia"),
    PROVINCE(2, "province", "Tỉnh/Thành phố"),
    COMMUNE(3, "commune", "Xã/Phường");

    Integer value;
    String name;
    String displayName;

    public static OrganizationLevel fromValue(Integer value) {
        for (OrganizationLevel level : OrganizationLevel.values()) {
            if (level.getValue().equals(value)) {
                return level;
            }
        }
        throw new BWCGenericRuntimeException("Unknown OrganizationLevel value: " + value);
    }
}
