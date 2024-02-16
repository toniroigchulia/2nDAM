package hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;

public class HibernateExercise {
    static SessionFactory factory;

    // Main
    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("/com/mycompany/config/hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();

        // Add
        insertStudent("Maria", "Gomez", 17, "maria@email.com", "981223456");
        insertStudent("Carlos", "Rodriguez", 15, "carlos@email.com", "962345678");
        insertStudent("Laura", "Lopez", 18, "laura@email.com", "973456789");
        insertStudent("Javier", "Garcia", 16, "javier@email.com", "984567890");        

        // Update
        updateStudent(1, "Pepito", "Martinez",16, "pepito@email.com", "971112312");

        // Delete
        deleteStudent(2);

        // Get one item
        System.out.println("Nombre del estudiante numero 1: " + getStudent(1).getName());

        // Print all students
        System.out.println("Estudiantes: ");
        for (Student student : listStudents()) {
            System.out.println(student.getId() + " : " + student.getName());
        }

        // Close and finish
        factory.close();
    }

    // Insert a student
    private static void insertStudent(String name, String lastname, int age, String email, String phone) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Student newStudent = new Student();
                newStudent.setName(name);
                newStudent.setLastname(lastname);
                newStudent.setAge(age);
                newStudent.setEmail(email);
                newStudent.setPhone(phone);

                session.save(newStudent);

                transaction.commit();

                System.out.println("Estudiante añadido correctamente");
            } catch (Exception e) {

                if (transaction != null) {

                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } finally {

            if (session != null) {

                session.close();
            }
        }
    }

    private static void updateStudent(int id, String name, String lastname, int age, String email, String phone) {
        Session session = null;
        try {

            session = factory.openSession();
            Transaction transaction = null;
            try {

                transaction = session.beginTransaction();

                Student existingStudent = (Student) session.get(Student.class, id);
                if (existingStudent != null) {

                    existingStudent.setName(name);
                    existingStudent.setLastname(lastname);
                    existingStudent.setAge(age);
                    existingStudent.setEmail(email);
                    existingStudent.setPhone(phone);

                    session.update(existingStudent);

                    transaction.commit();

                    System.out.println("Estudiante actualizado correctamente");
                } else {

                    System.out.println("Estudiante con id " + id + " no ha sido encontrado.");
                }
            } catch (Exception e) {

                if (transaction != null) {
                    transaction.rollback();
                }

                e.printStackTrace();
            }
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    private static void deleteStudent(int id) {

        Session session = null;
        try {

            session = factory.openSession();
            Transaction transaction = null;
            try {

                transaction = session.beginTransaction();

                Student existingStudent = (Student) session.get(Student.class, id);
                if (existingStudent != null) {

                    session.delete(existingStudent);

                    transaction.commit();

                    System.out.println("Estudiante eliminado correctamente");
                } else {
                    System.out.println("Estudiante con id " + id + " no ha sido encontrado");
                }
            } catch (Exception e) {

                if (transaction != null) {
                    transaction.rollback();
                }

                e.printStackTrace();
            }
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    private static Student getStudent(int id) {

        Session session = null;
        try {

            session = factory.openSession();

            Student student = (Student) session.get(Student.class, id);

            if (student != null) {

                return student;
            } else {

                System.out.println("Estudiante con id " + id + " no ha sido encontrado");
            }
        } finally {

            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    private static List<Student> listStudents() {
        List<Student> students = new ArrayList<Student>();
        Session session = null;

        try {

            session = factory.openSession();

            String hql = "FROM Student";
            Query query = session.createQuery(hql);
            students = query.list();
        } finally {

            if (session != null) {
                session.close();
            }
        }

        return students;
    }
}