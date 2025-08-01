package com.next.app.api.user.service;

import com.next.app.api.user.entity.Project;
import com.next.app.api.user.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectsRepository;

    public List<Project> getAllProjects() {return projectsRepository.findAll(); }

    public Optional<Project> getProjectById(Long id) { return projectsRepository.findById(id); }

    public Optional<Project> getProjectByName(String name) {return projectsRepository.findByName(name);}

    public Project createProject(Project project) {
        // 프로젝트명 중복 체크
        if (projectsRepository.existsByName(project.getName())) {
            throw new RuntimeException("이미 존재하는 프로젝트입니다: " + project.getName());
        }
        return projectsRepository.save(project);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다."));

        // 프로젝트명이 바뀌는 경우에만 중복 체크
        if (!project.getName().equals(projectDetails.getName())) {
            boolean nameExists = projectsRepository.existsByName(projectDetails.getName());
            if (nameExists) {
                throw new RuntimeException("이미 존재하는 프로젝트입니다: " + projectDetails.getName());
            }
        }

        // 값 업데이트
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());
        project.setStatus(projectDetails.getStatus());

        return projectsRepository.save(project);
    }

    public void deleteProject(Long id) { projectsRepository.deleteById(id); }



}
