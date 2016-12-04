package cz.thradec.hello.domain;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;
    private final HelloRepository helloRepository;

    @GetMapping
    public List<Hello> getAllHellos() {
        return helloRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hello getHello(@PathVariable Long id) {
        return helloRepository.findById(id);
    }

    @GetMapping("/random")
    public Hello getRandomHello() {
        return helloService.randomHello();
    }

    @PostMapping
    public Hello saveHello(@RequestBody Hello hello) {
        return helloRepository.save(hello);
    }

}