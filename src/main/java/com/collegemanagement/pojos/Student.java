package com.collegemanagement.pojos;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer deptId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 30)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = true, length = 30)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    @ManyToOne
    @JoinColumn(name="dept_id")
    private Department department;
   /* public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }*/
    @Basic
    @Column(name="dept_id")
    public Integer getDeptId()
    {
        return this.deptId;
    }
    public void setDeptId(int deptId1)
    {   this.deptId=deptId1;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (firstname != null ? !firstname.equals(student.firstname) : student.firstname != null) return false;
        if (lastname != null ? !lastname.equals(student.lastname) : student.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
    @Override
    public String toString()
    {
        return "student{Id:"+this.id+",firstname:"+this.firstname+
                ",lastname:"+this.lastname+",deptId:"+this.deptId+"}";
    }

}
