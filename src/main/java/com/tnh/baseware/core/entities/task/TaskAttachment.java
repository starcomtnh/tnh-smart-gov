package com.tnh.baseware.core.entities.task;

import com.tnh.baseware.core.entities.audit.Auditable;
import com.tnh.baseware.core.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_attachments")
public class TaskAttachment extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id", nullable = false)
    User uploader;

    String fileName;
    String filePath;
    String contentType;
    Long fileSize;
}

