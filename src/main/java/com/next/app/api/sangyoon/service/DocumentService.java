package com.next.app.api.sangyoon.service;

import com.next.app.api.sangyoon.entity.Document;
import com.next.app.api.sangyoon.repository.DocumentRepository;
import com.next.app.exception.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public List<Document> getDocumentsByDocId(Long ownerId) {
        return documentRepository.findByOwnerId(ownerId);
    }

    public Document getDocumentByDocId(Long docId) {
        return documentRepository.findById(docId)
                .orElseThrow(() -> DocumentException.documentNotFound(docId));
    }

    @Transactional
    public Document createDocument(Document document) {
        if (documentRepository.existsByFilePath(document.getFilePath())) {
            throw DocumentException.pathAlreadyExists(document.getFilePath());
        }
        return documentRepository.save(document);
    }

    @Transactional
    public Document updateDocument(Document document) {
        if (documentRepository.existsByFilePath(document.getFilePath())) {
            throw DocumentException.pathAlreadyExists(document.getFilePath());
        }
        int updatedCount = documentRepository.updateById(document);
        if (updatedCount == 0) {
            throw DocumentException.documentNotFound(document.getDocId());
        }
        return document;
    }

    @Transactional
    public void deleteDocument(Long docId) {
        int deletedCount = documentRepository.deleteByDocId(docId);
        if (deletedCount == 0) {
            throw DocumentException.documentNotFound(docId);
        }
    }
}
