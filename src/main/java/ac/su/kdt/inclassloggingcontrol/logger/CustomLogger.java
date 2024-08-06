package ac.su.kdt.inclassloggingcontrol.logger;

import ac.su.kdt.inclassloggingcontrol.controller.LogController;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class CustomLogger {
    public static final Logger logger = LogManager.getLogger(LogController.class);  // 선언된 로그 타입에 맞는 로거 생성

    public static void logRequest(
            String logType, // 요청 유형 (이니셜로 쓰면 좋음)
            String url, // 요청 엔드포인트
            String method, // HTTP METHODS (GET/POST/PUT/PATCH/DELETE)
            String userId, // 사용자 고유 번호
            String transactionId, // 요청 고유값 (nullable)
            String productId, // 상품 고유번호 (not null)
            String cartId, // 카트 번호
            String orderId, // 주문 번호
            String payload,// 수량 등 기타 데이터
            HttpServletRequest request
//            String clientIp, // 고객 IP
//            String userAgent, // 브라우저 등 요청에 사용된 SW 정보
//            String referrer // 직전 페이지 URL
    ) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        String userAgent = request.getHeader("User-Agent");
        String referrer = request.getHeader("Referer");

//        logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
        logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", // 컬럼 개수 선언 / \t : tab
                logType,
                LocalDateTime.now().toString(),
                url,
                method,
                userId != null ? userId : "-",
                transactionId,
                productId,
                cartId,
                orderId,
                payload,
                clientIp != null ? clientIp : "-",
                userAgent != null ? userAgent : "-",
                referrer != null ? referrer : "-"
//                "ip_dummy",     // HttpServletRequest 에서 추출 필요
//                "UA_dummy",
//                "referer_dummy"
        ));
    }
}