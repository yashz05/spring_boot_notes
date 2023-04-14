package com.twp.ajavaproj.repo;

import com.twp.ajavaproj.entites.notes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface noterepo extends JpaRepository<notes, Long> {

    @Transactional
    @Modifying
    @Query("update notes n set n.content = :content, n.title = :title where n.id =:id")
    public void updateById(long id, String content, String title);
}
