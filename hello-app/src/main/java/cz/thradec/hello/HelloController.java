package cz.thradec.hello;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;
    private final HelloRepository helloRepository;

    @GetMapping
    public List<Hello> getAllHellos(@SortDefault("id") Sort sort) {
        return helloRepository.findAll(sort);
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
    public Hello saveHello(@RequestBody @Valid Hello hello) {
        return helloRepository.save(hello);
    }

    @DeleteMapping("/{id}")
    public Hello deleteHello(@PathVariable Long id) {
        return helloRepository.delete(id);
    }

}