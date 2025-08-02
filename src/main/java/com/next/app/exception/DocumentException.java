package com.next.app.exception;

public class DocumentException extends BusinessException {


    public DocumentException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static DocumentException pathAlreadyExists(String path) {
        return new DocumentException(ErrorCode.BUSINESS_RULE_VIOLATION, "파일 경로 " + path + "는 이미 사용 중입니다.");
    }

    public static DocumentException documentNotFound(Long docId) {
        return new DocumentException(ErrorCode.ORDER_NOT_FOUND, "문서 " + docId + "를 찾을 수 없습니다.");
    }

}
