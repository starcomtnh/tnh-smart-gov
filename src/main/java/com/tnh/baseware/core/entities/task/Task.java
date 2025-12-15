package com.tnh.baseware.core.entities.task;

import com.tnh.baseware.core.entities.audit.Auditable;
import com.tnh.baseware.core.enums.task.TaskPriority;
import com.tnh.baseware.core.enums.task.TaskStatus;
import com.tnh.baseware.core.enums.task.TaskType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    String title;

    @Column(columnDefinition = "text")
    String description;

    Instant startDate;
    Instant dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TaskType type;
}

