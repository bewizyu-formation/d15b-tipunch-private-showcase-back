package fr.formation.repository;

import fr.formation.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepositoryImpl {

    private static final String DB_TABLE_DEPT = "departement";
    private static final String DB_DEPT_ID = "departement_id";
    private static final String DB_DEPT_NAME = "departement_nom";
    private static final String DB_DEPT_CODE = "departement_code";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Department> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM " + DB_TABLE_DEPT,
                (rs, rowNum) -> new Department(rs.getInt(DB_DEPT_ID), rs.getString(DB_DEPT_CODE), rs.getString(DB_DEPT_NAME))
        );
    }

    public Department findOne(int id) {
        Department dept = jdbcTemplate.queryForObject(
                "SELECT * FROM " + DB_TABLE_DEPT + " WHERE " + DB_DEPT_ID + " = ?", new Object[]{id},
                (rs, rowNum) -> new Department(rs.getInt(DB_DEPT_ID), rs.getString(DB_DEPT_CODE), rs.getString(DB_DEPT_NAME))
        );
        return dept;
    }

}
