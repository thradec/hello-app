package cz.thradec.hello;

import java.util.List;
import java.util.Random;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloServiceImpl implements HelloService {

    private final Random random = new Random();
    private final HelloRepository helloRepository;

    @Override
    public Hello randomHello() {
        List<Hello> helloList = helloRepository.findAll();
        return helloList.get(random.nextInt(helloList.size()));
    }

}