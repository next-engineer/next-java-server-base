package com.next.app.api.user.controller;

import com.next.app.api.user.entity.Project;
import com.next.app.api.user.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Project Controller", description = "프로젝트 관리 API")
@CrossOrigin(origins = "http://localhost")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @Operation(summary = "모든 프로젝트 조회", description = "등록된 모든 프로젝트 목록을 반환합니다.")
    public List<Project> getAllProjects() { return projectService.getAllProjects(); }

    @GetMapping("/{id}")
    @Operation(summary = "프로젝트 아이디로 조회", description = "ID로 특정 프로젝트를 조회합니다.")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "프로젝트 이름으로 조회", description = "프로젝트명으로 특정 프로젝트를 조회합니다.")
    public ResponseEntity<?> getProjectByName(@PathVariable("name") String name) {
        return projectService.getProjectByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "프로젝트 생성", description = "새로운 프로젝트를 생성합니다.")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        try {
            Project saved = projectService.createProject(project);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Project crate 유효성 오류: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Project 생성 실패: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "프로젝트 수정", description = "기존 프로젝트 정의를 수정합니다.")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        try {
            Project updated = projectService.updateProject(id, projectDetails);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Project update 유효성 오류: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Project 수정 실패: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "프로젝트 삭제", description = "프로젝트를 삭제합니다.")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok().body("Project 삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Project 삭제 실패: " + e.getMessage());
        }
    }
}
