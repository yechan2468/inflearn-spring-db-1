package yechan.inflearn_spring_db_1.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import yechan.inflearn_spring_db_1.domain.Member;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

    @Test
    void save() {
        Member userA = new Member("userA", 10000);
        Member saved = repositoryV0.save(userA);
        Assertions.assertThat(saved).isNotNull();
    }
}