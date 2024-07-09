package com.example.demo.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long>{ //nas entitiy in type of primary key
    List<NoteEntity> findAllByTitle (String title); //da bomo lahko iskali po titlih note


}
