package com.example.domain.user.model;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="t_salary" )
public class Salary {
    //private String userId;
    //private String yearMonth;
    @EmbeddedId
    private SalaryKey salaryKey;
    private Integer salary ;
}