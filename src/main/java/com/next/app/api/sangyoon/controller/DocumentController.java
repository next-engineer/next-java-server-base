package com.next.app.api.sangyoon.controller;

import com.next.app.api.sangyoon.entity.Document;
import com.next.app.api.sangyoon.service.DocumentService;
import com.next.app.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/documents")
@Tag(name = "Document Controller", description = "문서 관리 API")
@CrossOrigin(origins = "http://localhost")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/list")
    @Operation(summary = "문서 전체목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "기타오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @GetMapping("/list/{ownerId}")
    @Operation(summary = "회원에 따른 전체목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "기타오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<Document> getDocumentsByDocId(@PathVariable Long ownerId) {
        return documentService.getDocumentsByDocId(ownerId);
    }

    @GetMapping("/single/{docId}")
    @Operation(summary = "문서 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "문서 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "기타오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Document> getDocumentByDocId(@PathVariable Long docId) {
        return ResponseEntity.ok(documentService.getDocumentByDocId(docId));
    }

    @PostMapping
    @Operation(summary = "문서 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "경로 중복", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "기타오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        return ResponseEntity.ok(documentService.createDocument(document));
    }

    @PutMapping
    @Operation(summary = "문서 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "경로 중복", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "문서 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "기타오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Document> updateDocument(@RequestBody Document document) {
        return ResponseEntity.ok(documentService.updateDocument(document));
    }

    @DeleteMapping("/{docId}")
    @Operation(summary = "문서 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "문서 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "기타오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void deleteDocument(@PathVariable Long docId) {
        documentService.deleteDocument(docId);
    }

}
