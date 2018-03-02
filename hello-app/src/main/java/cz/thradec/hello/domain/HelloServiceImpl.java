package cz.thradec.hello.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static cz.thradec.hello.domain.HelloRepository.orderById;

@Service
@RequiredArgsConstructor
class HelloServiceImpl implements HelloService {

    @Value("${features.randomVersion:1}")
    private int randomVersion;

    private final Random random = new Random();
    private final HelloRepository helloRepository;

    @Override
    public Hello randomHello() {
        switch (randomVersion) {
            case 1:
                return randomHello_v1();
            case 2:
                return randomHello_v2();
            default:
                throw new IllegalArgumentException();
        }
    }

    private Hello randomHello_v1() {
        List<Hello> helloList = helloRepository.findAll(orderById());
        return helloList.get(random.nextInt(helloList.size()));
    }

    private Hello randomHello_v2() {
        return helloRepository.findRandom();
    }

}