package com.example.demo.entity.dbo;

import com.example.demo.entity.dto.TaskDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "TABLE")
public class TaskDbo {

    @Column(name = "NAME")
    private String name;

    @Column(name = "ID")
    @Id
    private Integer id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "EMAIL")
    private String email;

    public TaskDto toDto() {
        return TaskDto.builder().email(email).id(id).name(name).userId(userId).build();
    }
}
