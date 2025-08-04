package com.next.app.api.annocuments.repository;

import com.next.app.api.annocuments.entity.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementsRepository extends JpaRepository<Announcements,Long>{

}
