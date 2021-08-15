package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController를 사용하면 반환 시 뷰가 아니라 String 그 자체가 반환된다.
 * 즉, 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
 * @ResponseBody 와 관련이 있다.
 */

@Slf4j
@RestController
public class LogTestController {
    /**
     * 주의! import org.slf4j.Logger; 이걸로 임포트 해줘야함
     * @Slf4j 사용하면  private final Logger log = LoggerFactory.getLogger(getClass()); 코드 안적어줘도 됨
     */
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class); // 아래 코드와 동일
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String LogTest() {
        String name = "Spring";
        String name2 = "Learning";

        /**
         * 로그가 출력되는 포멧: 시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스 명, 로그 메시지
         * 로그 LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
         * 개발 서버는 debug 레벨로 출력
         * 운영 서버는 info 레벨로 출력. cf) 운영 서버는 워낙 트래픽이 많아서 중요한 정보만 남겨야 해서 info 레벨로 설정
         * application.properties에 설정을 안하면 기본으로 info 레벨이 적용됨
         */

        // 아래 코드처럼 + 이렇게 사용하면 안됨. + 때문에 연산이 일어나서 메모리도 사용하고 CPU도 사용.
        // 예를 들어, trace 레벨을 사용안하는데 이렇게 써버리면 + 때문에 연산이 발생해서 쓸모없는 자원 낭비가 됨.
        log.trace(" trace log=" + name);

        log.trace(" trace log={}", name); // +가 아니라 이렇게 작성해야 아무 연산이 일어나지 않음
        log.debug(" debug log={}", name);
        log.info(" info log={}", name);
        log.info(" info log={}, info log2={}", name, name2);
        log.warn(" warn log={}", name);
        log.error(" error log={}", name);

        return "ok";
    }



}
