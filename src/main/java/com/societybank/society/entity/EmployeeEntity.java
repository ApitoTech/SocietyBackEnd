package com.societybank.society.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name = "emp")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false)
    private Long id;

    @Column(name = "emp_name", nullable = false, length = 20)
    private String empName;

    @Column(name = "mobile_number", nullable = false)
    private Long mobileNumber;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "view_access", nullable = false)
    private Boolean viewAccess = false;

    @Column(name = "add_access", nullable = false)
    private Boolean addAccess = false;

    @Column(name = "update_access", nullable = false)
    private Boolean updateAccess = false;

    @Column(name = "super_admin", nullable = false)
    private Boolean superAdmin = false;


}