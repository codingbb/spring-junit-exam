package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class) // 내가 만든 클래스는 import 해줘야함!
@DataJpaTest // DB 관련 객체들이 IoC에 뜬다. //애를 안 적으면 테스트를 못함
public class BoardRepositoryTest {

    // Test에서 DI하는 코드
    @Autowired //테스트 코드에서는 @Autowired -> @RequiredArgsConstructor 대신 넣는다.
    private BoardRepository boardRepository;

    @Test
    public void selectAll_test(){
        // given

        // when
        List<Board> boardList = boardRepository.selectAll();
        System.out.println(boardList.size());

        // then (id=1, title=제목1, content=내용1, author=홍길동)
        // System.out.println(boardList);
        Assertions.assertThat(boardList.get(0).getTitle()).isEqualTo("제목1");
        Assertions.assertThat(boardList.get(0).getContent()).isEqualTo("내용1");
        Assertions.assertThat(boardList.get(0).getAuthor()).isEqualTo("홍길동");
        Assertions.assertThat(boardList.size()).isEqualTo(8);
    }

    @Test
    public void selectOne_test() {
        //given
        int id = 1;

        //when
        Board board = boardRepository.selectOne(id);

        //then  //(상태 검사 = 객체의 필드. 상태는 행위를 통해 변경 시킨다) //행위검사는 아직 안함
        //System.out.println(board);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.getAuthor()).isEqualTo("홍길동");
    }

    @Test
    public void insert_test(){  //테스트 메서드는 파라미터, 리턴이 없다. 적는 순간 터짐!
        // given
        String title = "제목10";
        String content = "내용10";
        String author = "이순신";

        // when
        boardRepository.insert(title, content, author);

        // then -> 눈으로 확인 (쿼리)
    } // Rollback (자동)
}