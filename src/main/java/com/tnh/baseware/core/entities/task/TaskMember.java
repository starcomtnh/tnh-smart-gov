package com.tnh.baseware.core.entities.task;

import com.tnh.baseware.core.entities.user.User;
import com.tnh.baseware.core.enums.task.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_members")
@IdClass(TaskMemberId.class)
public class TaskMember {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    Task task;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(nullable = false)
    String role; // LEAD, ASSIGNEE, REVIEWER, WATCHER

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    MemberStatus status;

    Instant joinedAt;
    Instant completedAt;
}

