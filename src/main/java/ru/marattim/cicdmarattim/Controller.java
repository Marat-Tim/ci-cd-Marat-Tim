package ru.marattim.cicdmarattim;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class Controller {
    private final int randomInt;

    @GetMapping
    int get() {
        log.info("Request arrived");
        return randomInt;
    }
}
