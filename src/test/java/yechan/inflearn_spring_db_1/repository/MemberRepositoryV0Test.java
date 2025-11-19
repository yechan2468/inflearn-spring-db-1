package yechan.inflearn_spring_db_1.repository;

import org.junit.jupiter.api.Test;
import yechan.inflearn_spring_db_1.domain.Member;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() {
        Member userA = new Member("userC", 10000);
        Member saved = repository.save(userA);

        Member member = repository.findById(saved.getMemberId());
        assertThat(member).isEqualTo(userA);

        repository.update(member.getMemberId(), 20000);
        Member member1 = repository.findById(member.getMemberId());
        assertThat(member1.getMoney()).isEqualTo(20000);

        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}