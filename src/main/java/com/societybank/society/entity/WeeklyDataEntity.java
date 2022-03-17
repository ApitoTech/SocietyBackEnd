package com.societybank.society.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name = "weeklyData")
public class WeeklyDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weeklyData_id", nullable = false)
    private Long id;

    @Column(name = "weekYear", nullable = false)
    private Long weekYear;

    @Column(name = "genratedDate", nullable = false)
    private LocalDate genratedDate;

    @Column(name = "subscriberCount", nullable = false)
    private Long subscriberCount;

    @Column(name = "complementaryCount", nullable = false)
    private Long complementaryCount;

    @Column(name = "total", nullable = false)
    private Long total;

}