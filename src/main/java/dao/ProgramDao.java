package dao;

import entity.Program;

import java.util.List;

public interface ProgramDao {

    void create(Program program);

    void update(Program program);

    void delete(String id);

    Object findById(String id);

    List<Program> findAll();
}
