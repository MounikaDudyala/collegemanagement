package com.collegemanagement.dao.impl;

import com.collegemanagement.dao.AbstractDao;
import com.collegemanagement.dao.CollegeDao;
import com.collegemanagement.pojos.Department;
import com.collegemanagement.pojos.Student;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

@Repository
public class CollegeDaoImpl extends AbstractDao implements CollegeDao {

	@Override
	public void createStudent(Student student) {
		final Session session = getSession();
		Department department;
		try {
			department = fetchDepartment(student.getDeptId());
			if (department != null) {
				student.setFirstname(student.getFirstname());
				student.setLastname(student.getLastname());
				student.setDeptId(student.getDeptId());
				session.beginTransaction();
				Serializable save = session.save(student);
				session.getTransaction().commit();

			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Student> fetchStudents() {
		List<Student> studentList = null;
		final Session session = getSession();
		try {
			Query query = session.createQuery("from Student");
			studentList = query.list();

		} finally {
			session.close();
		}
		return studentList;
	}

	@Override
	public Student fetchStudent(int id) {
		Student student = null;
		final Session session = getSession();
		try {
			Query query = session.createQuery("from Student S where S.id=:stId");
			query.setParameter("stId", id);
			student = (Student) query.uniqueResult();
		} finally {
			session.close();
		}
		return student;
	}

	@Override
	public void editStudent(Student student) {
		final Session session = getSession();
		try {
			Query query = session.createQuery(
					"update Student S set S.firstname=:firstname,S.lastname=:lastname,S.deptId=:deptId where S.id=:id");
			query.setParameter("firstname", student.getFirstname());
			query.setParameter("lastname", student.getLastname());
			query.setParameter("deptId", student.getDeptId());
			query.setParameter("id", student.getId());
			Transaction t = session.beginTransaction();
			int i = query.executeUpdate();
			t.commit();

		} finally {
			session.close();
		}
	}

	@Override
	public Department fetchDepartment(int id) {
		final Session session = getSession();
		Department department;
		try {
			Query query = session.createQuery("from Department d where d.id=:deptId");
			query.setParameter("deptId", id);
			department = (Department) query.uniqueResult();
		} finally {
			session.close();
		}
		return department;

	}

	/*
	 * public static void main(String args[]) { CollegeDaoImpl collegeDao = new
	 * CollegeDaoImpl();
	 * 
	 * System.out.println(collegeDao.editStudent(2));
	 * 
	 * 
	 * System.out.println(collegeDao.fetchStudent(2).toString());
	 * 
	 * 
	 * List<Student> studentList=collegeDao.fetchStudents(); for (Student student1 :
	 * studentList) { System.out.println("  " + student1.toString()); }
	 * 
	 * 
	 * }
	 */
}
