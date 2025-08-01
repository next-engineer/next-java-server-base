package com.next.app.api.user.service;

import com.next.app.api.user.entity.Announcements;
import com.next.app.api.user.repository.AnnouncementsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AnnoucementsService {

    @Autowired
    private AnnouncementsRepository announcementsRepository;

    public List<Announcements> getAllAnnouncements() { return announcementsRepository.findAll();}

    public Announcements getAnnouncementsById(Long announcement_id){
        return announcementsRepository.findById(announcement_id)
                .orElseThrow(() -> new RuntimeException("Announcements not found with id: " + announcement_id));
    }

    public Announcements createAnnouncements(Announcements announcements) {
        return announcementsRepository.save(announcements);
    }

    public Announcements updateAnnouncements(Long announcement_id, Announcements updatedAnnouncement) {
        Announcements announcements = announcementsRepository.findById(announcement_id)
                .orElseThrow(() -> new RuntimeException("Announcements not found with id: " + announcement_id));
        announcements.setTitle(updatedAnnouncement.getTitle());
        announcements.setContent(updatedAnnouncement.getContent());
        announcements.setCreated_by(updatedAnnouncement.getCreated_by());
        return announcementsRepository.save(announcements);
    }

    public void deleteAnnouncements(Long announcement_id)  {
        announcementsRepository.deleteById(announcement_id);
    }
}
