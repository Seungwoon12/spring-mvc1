package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "OK";
    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,  // 변수명을 파라미터명과 같게하면 ("username") 이렇게 쓰는 거 생략가능
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "OK";
    }

    /**
     * V4는 간편하긴 하지만 V3처럼 @RequestParam 써주는게 더 명확하고 직관적일 수 있음
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) { // 다 없애는 것도 가능. 대신 요청파라미터 이름과 꼭 맞아야함
        log.info("username={}, age={}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) { // age를 required = false 처리 해주고 싶으면 Integer로 해줘야함. int로 하면 null값을 처리못해서 에러발생하기 때문

        // null과 ""는 다름
        //cf) url에서 ...?username= 이렇게 치면 빈 문자("")로 인식함. 그래서 username이 required = true라도 통과가 됨
        log.info("username={}, age={}", username, age);
        return "OK";
    }

    /**
     *  defaultValue가 들어가면 required가 있던 없던 상관없음
     *  defaultValue를 적용하면 빈 문자일 경우에도 defaultValue값으로 적용됨
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "OK";
    }

}
