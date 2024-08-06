package ac.su.kdt.inclassloggingcontrol.controller;

import ac.su.kdt.inclassloggingcontrol.domain.CartForm;
import ac.su.kdt.inclassloggingcontrol.domain.OrderForm;
import ac.su.kdt.inclassloggingcontrol.logger.CustomLogger;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

// 쇼핑몰의 REST API
@RestController
public class LogController {
    Random random = new Random();
    // 로그 config 에 등록된 클래스 단위에서 실제 로그 기록 메서드 호출 실행
    // -> 로그 기록
    // 아래 EndPoint 는 DB를 만들지 않기(더미 호출 구현)

    // GET /products                       (상품 리스트 조회)
    // ##### 랜덤한 상품 5개 (1~100) 조회하는 로직 구현

    // GET /products/{productId}      (상품 상세 조회)
    // ##### 접수된 상품 id 있다 치고 응답하는 로직 구현

    // POST /cart                           (상품을 장바구니에 추가)
    // ##### RequestBody (CartForm) 에 포함될 내용을 정의 후 해당 내용 성공응답 로직 구현

    // POST /order                          (장바구니에 담긴 상품 주문 또는 즉시 주문)
    // ##### RequestBody (OrderForm) 에 포함될 내용을 정의 후 해당 내용 성공응답 로직 구현

//    @GetMapping("/products")
//    public List<Long> getProducts() {
//        List<Long> products = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            products.add((long) (random.nextInt(100) + 1));
//        }
//        return products;
//    }

    @GetMapping("/products")
    public String showProductList(
            @RequestParam (name = "userid", required = false) String userId,
            HttpServletRequest request
    ) {
        // 랜덤 상품 리스트 생성
        List<Long> productList = LongStream.range(0, 5).
                mapToObj(i -> random.nextLong(100) + 1 ).
                toList();
        // 로그 기록하기 (상품 하나하나에 대한 노출이 발생했음을 기록해야 함!)
        // 리스트로 보장 어렵고, 어떤 것은 하나씩 나오고 어떤 것은 리스트로 나올 때 분석 측면에서 별로다.
        // 추후 분리해야함!!! 2단계 이상 분리
        for (Long productId : productList) {
            // 노출된 상품 ID 하나마다 로그 1행 기록
            // 로그 스키마는 로그 한 줄의 구성을 가지고 특별한 의미 (로직 및 데이터 분석용) 부여할 수 있도록 구현
            // csv 형태로 다루면 한줄의 여러개의 컬럼 표현 가능
//            logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", // 컬럼 개수 선언 / \t : tab
            CustomLogger.logRequest(
                    "l",    // 파라미터 명으로 관리되지 못하고 있음. -> 메서드 분리
                    "/products",
                    "GET",
                    userId != null ? userId : "-",  // userId 해도 되지만 용량 상..
                    "-",
                    productId.toString(),
                    "-",
                    "-",
                    "-",
                    request
//                    "ip_dummy",     // HttpServletRequest 에서 추출 필요
//                    "UA_dummy",
//                    "referrer_dummy"
            );
        }
        return productList.toString();
    }

    @GetMapping("/products/{productId}")
    public String getProductById(@PathVariable Long productId) {
        return productId + "번째 상품 상세 조회 ";
    }

    @PostMapping("/carts")
    public String postCart(@RequestBody CartForm cartForm) {
        return "장바구니에 추가된 상품: " + cartForm.toString();
    }

    @PostMapping("/orders")
    public String postOrder(@RequestBody OrderForm orderForm) {
        return "주문 정보: " + orderForm.toString();
    }
}
