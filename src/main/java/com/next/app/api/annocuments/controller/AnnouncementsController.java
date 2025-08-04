package com.next.app.api.annocuments.controller;

import com.next.app.api.annocuments.entity.Announcements;
import com.next.app.api.annocuments.service.AnnoucementsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@Tag(name = "Announcements Controller", description = "공지사항 관리 API")
@CrossOrigin(origins = "http://localhost")

public class AnnouncementsController {
    private final AnnoucementsService annoucementsService;
    @Autowired
    public AnnouncementsController(AnnoucementsService annoucementsService) {
        this.annoucementsService = annoucementsService;
    }

    @GetMapping
    @Operation(summary = "모든 공지사항 조회", description = "등록된 모든 공지사항을 출력합니다.")
    public List<Announcements> getAllAnnouncements() {
        return annoucementsService.getAllAnnouncements();
    }

    @GetMapping("/{announcement_id}")
    @Operation(summary = "공지사항 조회", description = "ID로 공지사항을 조회합니다.")
    public Announcements getAnnouncementsById(@PathVariable Long announcement_id) {
        return annoucementsService.getAnnouncementsById(announcement_id);
    }

    @PostMapping
    @Operation(summary = "공지사항 생성", description = "공지사항을 생성합니다.")
    public Announcements createAnnouncements(@RequestBody Announcements announcements) {
        return annoucementsService.createAnnouncements(announcements);
    }


    @PutMapping("/{announcement_id}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Operation(summary = "공지사항 수정", description = "현재 공지사항은 게시 후 수정이 불가능합니다.")
    public void updateAnnouncements(@PathVariable Long announcement_id, @RequestBody Announcements announcements) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "공지사항은 수정할 수 없습니다.");
    }

    @DeleteMapping("/{announcement_id}")
    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
    public void deleteAnnouncements (@PathVariable Long announcement_id) {
        annoucementsService.deleteAnnouncements(announcement_id);
    }
}
