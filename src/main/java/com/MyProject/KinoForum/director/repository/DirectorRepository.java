package com.MyProject.KinoForum.director.repository;

import com.MyProject.KinoForum.director.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
