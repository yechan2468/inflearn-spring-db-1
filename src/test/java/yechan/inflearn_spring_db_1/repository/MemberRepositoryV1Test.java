package yechan.inflearn_spring_db_1.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import yechan.inflearn_spring_db_1.connection.ConnectionConst;
import yechan.inflearn_spring_db_1.domain.Member;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(ConnectionConst.URL);
        dataSource.setUsername(ConnectionConst.USERNAME);
        dataSource.setPassword(ConnectionConst.PASSWORD);
        repository = new MemberRepositoryV1(dataSource);
    }

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