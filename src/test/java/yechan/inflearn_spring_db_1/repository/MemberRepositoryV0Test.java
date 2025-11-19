package yechan.inflearn_spring_db_1.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import yechan.inflearn_spring_db_1.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() {
        Member userA = new Member("userA", 10000);
        Member saved = repository.save(userA);

        Member member = repository.findById(saved.getMemberId());
        assertThat(member).isEqualTo(userA);
    }
}