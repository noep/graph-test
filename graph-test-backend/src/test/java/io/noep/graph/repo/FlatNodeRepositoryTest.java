package io.noep.graph.repo;

import io.noep.graph.domain.FlatNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

/**
 * Desc  :
 */

@SpringBootTest
public class FlatNodeRepositoryTest {

    @Autowired
    private FlatNodeRepository flatNodeRepository;

    @BeforeEach
    private void beforeEach() {
        flatNodeRepository.deleteAll().block();
    }

    @DisplayName("빈 값 조회 테스트")
    @Test
    public void findEmptyTest() {

        StepVerifier.create(flatNodeRepository.findAll())
                .expectComplete()
                .log()
                .verify();
    }

    @DisplayName("생성 테스트")
    @Test
    public void createTest() {

        FlatNode flatNode = new FlatNode();

        StepVerifier.create(flatNodeRepository.save(flatNode))
                .consumeNextWith(result -> System.out.println(result))
                .expectComplete()
                .log().verify();

    }
}
