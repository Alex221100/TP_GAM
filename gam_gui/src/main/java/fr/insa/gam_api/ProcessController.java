package fr.insa.gam_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class ProcessController {

    //@Value("${zuul.defaultRoute}")
    private String defaultRoute;

    @GetMapping("/process/gam_query/existById/{id}")
    public Object existById(@PathVariable long id, HttpServletRequest req, HttpServletResponse res) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(defaultRoute + "/gam_query/" + id, Boolean.class);
        return response;
    }
}
