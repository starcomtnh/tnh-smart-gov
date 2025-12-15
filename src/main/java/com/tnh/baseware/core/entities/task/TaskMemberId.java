package com.tnh.baseware.core.entities.task;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TaskMemberId implements Serializable {
    UUID task;
    UUID user;
}

