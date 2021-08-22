package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
@RestController
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("OK");

    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() throws IOException {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "OK";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(28);

        return new ResponseEntity<>(helloData, HttpStatus.OK); // ResponseEntity를 사용하면 이렇게 HTTP 상태코드 설정가능
    }


    /**
     *  Rest API(HTTP API)를 만들 때 주로 이렇게 만듬
     * @ResponseBody를 클래스 레벨에 두면 전체 메서드에 적용됨.
     * @RestController 어노테이션 안에 @ResponseBody가 적용되어 있음.
     */
    @ResponseStatus(HttpStatus.OK) // HTTP 상태코드를 이렇게 어노테이션으로 설정해줘야함
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(28);

        return helloData;
    }
}
