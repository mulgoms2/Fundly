package com.fundly.project.model;

import com.persistence.dto.ProjectDto;
import config.RootContext;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringJUnitWebConfig(classes = RootContext.class)
class ProjectMapperTest {
    @Autowired
    ApplicationContext ac;
    @Autowired
    ProjectMapper projectMapper;
    ProjectDto project1;
    ProjectDto project2;
    ProjectDto project3;
    String user_id;

    @BeforeEach
    @DisplayName("테스트 세팅. projectDto")
    void setUp() {
        user_id = "dbswoi123";

        this.project1 = ProjectDto.builder().pj_id("1").pj_sel_id(user_id).build();
        this.project2 = ProjectDto.builder().pj_id("2").pj_sel_id(user_id).build();
        this.project3 = ProjectDto.builder().pj_id("3").pj_sel_id(user_id).build();

        projectMapper.deleteAll();
        assertEquals(0, projectMapper.count());
    }

    @Test
    @DisplayName("전체 데이터 삭제")
    void delete() {
//        전체 데이터가 삭제되었음을 보장하는 방법
//        전체를 삭제한다.
        projectMapper.deleteAll();
//        테이블의 데이터가 남았는지 확인한다.
        assertEquals(0, projectMapper.count());

        projectMapper.insert(project1);
        projectMapper.insert(project2);
        projectMapper.insert(project3);

        assertEquals(3, projectMapper.count());

        projectMapper.deleteAll();

        assertEquals(0, projectMapper.count());
    }

    @Test
    @DisplayName("행 카운트하기")
    void count() {
        //조건
        System.out.println("\n\n\n\n" + this);
        System.out.println(ac);

        projectMapper.deleteAll();
        assertEquals(0, projectMapper.count());

        projectMapper.insert(project1);
        assertEquals(1, projectMapper.count());

        projectMapper.insert(project2);
        assertEquals(2, projectMapper.count());

        projectMapper.insert(project3);
        assertEquals(3, projectMapper.count());
    }

    @Test
    @DisplayName("프로젝트 한개 인서트하기")
    void insert() {
        projectMapper.deleteAll();
//        int insertCount = projectMapper.insert(project1);
//
//        assertEquals(1, insertCount);
    }

    @Test
    @DisplayName("널을 인서트 해보기")
    void nullInsert() {
        assertThrows(DataAccessException.class, () -> projectMapper.insert(null));
    }

    @Test
    @DisplayName("같은 id의 프로젝트는 중복될 수 없다")
    void duplicateTest() {
        projectMapper.insert(project1);
//        같은 이름의 프로젝트 중복 삽입시 예외가 발생해야 한다.
        assertThrowsExactly(DuplicateKeyException.class, () -> projectMapper.insert(project1));
        assertThrows(DataAccessException.class, () -> projectMapper.insert(project1));
    }

    @Test
    @DisplayName("프로젝트 불러오기")
    void selectProject() {
//        프로젝트 아이디를 주면 프로젝트를 가져온다.
//        조건
//        프로젝트 아이디를 전달한다.
        projectMapper.insert(project1);
        projectMapper.insert(project3);
        assertEquals(2, projectMapper.count());
        String pj_id = project3.getPj_id();

//        행위
//        프로젝트 아이디로 프로젝트를 가져온다.
        ProjectDto resultProject = projectMapper.getByPjId(pj_id);

//        결과
//        셀렉트 결과로 나온 프로젝트가 삽입한 프로젝트와 일치해야한다.
        assertEquals(project3, resultProject);
    }

    @Test
    @DisplayName("null값 대입 검사")
    void getPjWithNull() {
        assertDoesNotThrow(() -> projectMapper.getByPjId(null));
        assertDoesNotThrow(() -> projectMapper.getListByUserId(null));
        assertDoesNotThrow(() -> projectMapper.delete(null));
        assertThrows(DataAccessException.class, () -> projectMapper.insert(null));
        assertThrowsExactly(DataIntegrityViolationException.class, () -> projectMapper.insert(null));
        assertDoesNotThrow(() -> projectMapper.update(null));
        assertDoesNotThrow(() -> projectMapper.delete(null));
    }

    @Test
    @DisplayName("유저 아이디로 프로젝트 목록 조회하기")
    void selectByUserId() {
//        조건
//        유저 아이디가 적힌 프로젝트 데이터가 존재할때
        projectMapper.insert(project1);
        projectMapper.insert(project3);

        assertEquals(2, projectMapper.count());
//        행위
//        유저아이디로 프로젝트를 식별하면 프로젝트 목록이 나온다.
        List<ProjectDto> resultPj = projectMapper.getListByUserId(user_id);
//        결과
//        리스트에 해당 프로젝트가 존재한다.
        assertNotEquals(-1, resultPj.indexOf(project1));
        assertNotEquals(-1, resultPj.indexOf(project3));

        assertEquals(-1, resultPj.indexOf(project2));
    }

    @Test
    @DisplayName("특정 유저의 작성중인 프로젝트 목록 조회하기")
    void selectWritingProject() {
//        유저의 아이디를 입력하면, 작성중인 프로젝트 목록이 반환된다.

//        조건. 테이블에 유저아이디와 작성상태를 가진 프로젝트가 존재할때
        project1.setPj_status("심사반려");
        project2.setPj_status("심사중");
        project3.setPj_status("심사중");
    }

    @Test
    @DisplayName("테이블에 존재하는 프로젝트의 상태 변경하기")
    void updateProject() {
        String beforeStatus = "작성중";
        String afterStatus = "심사반려";
        String pj_id = project1.getPj_id();
//        작성중인 프로젝트를 삽입한다.
        project1.setPj_status(beforeStatus);
        projectMapper.insert(project1);

//      해당 프로젝트를 조회한 후 상태를 확인한다.
        ProjectDto beforePj = projectMapper.getByPjId(pj_id);
        assertEquals(beforeStatus, beforePj.getPj_status());

//        프로젝트의 상태를 업데이트한 후 DB에 반영한다.
        project1.setPj_status(afterStatus);
        int i = projectMapper.update(project1);
//        해당 프로젝트의 상태변경이 반영됐는지 확인한다.
        ProjectDto resultPj = projectMapper.getByPjId(pj_id);

        assertEquals(1, i);
        assertEquals(afterStatus, resultPj.getPj_status());
    }

    @Test
    @DisplayName("특정 프로젝트를 삭제한다")
    void deleteTest() {
//        조건: 테이블에 식별 가능한 프로젝트가 여럿 있다.
        projectMapper.insert(project1);
        projectMapper.insert(project2);
        projectMapper.insert(project3);
//        삭제하려는 대상 프로젝트 아이디
        String pj_id = project2.getPj_id();

//        행위: 프로젝트 아이디를 대입하면 프로젝트가 삭제된다.
        int beforeRowCount = projectMapper.count();
        int i = projectMapper.delete(pj_id);
        int afterRowCount = projectMapper.count();

//        결과: 디비에서 삭제된 프로젝트를 찾을 수 없다.
//        다른 데이터는 삭제되면 안된다.
        assertEquals(1, i);
        assertEquals(afterRowCount, beforeRowCount - 1);
        assertEquals(null, projectMapper.getByPjId(pj_id));
//        assertThrows(ClassNotFoundException.class, () -> projectMapper.getByPjId(pj_id));

    }

    @Test
    @DisplayName("delete 메서드에 null 넣기")
    void deleteNull() {
        // 조건 : 프로젝트 아이디가 null일때
        String pj_id = null;
        int beforeDelete = projectMapper.count();

        // 행위 : null값으로 조회되는 프로젝트를 삭제한다.
        projectMapper.delete(pj_id);
        int delete = projectMapper.delete(null);

        // 결과 : 아무런 데이터가 삭제되지 않아야 한다
        int afterCount = projectMapper.count();


        assertEquals(beforeDelete, afterCount);
        assertEquals(0, delete);
    }

    @Test
    @DisplayName("전체 프로젝트를 조회하는 기능 검사")
    void selectAllProject() {
//        조건
        projectMapper.insert(project1);
        projectMapper.insert(project2);
        projectMapper.insert(project3);
//        행위
        List<ProjectDto> projectDtoList = projectMapper.selectAllPj();
//        결과
        assertEquals(3, projectDtoList.size());
        assertEquals(true, projectDtoList.contains(project1));
        assertEquals(true, projectDtoList.contains(project2));
        assertEquals(true, projectDtoList.contains(project3));
    }

    @Test
    @DisplayName("테이블이 비어있을경우 프로젝트 리스트 조회하기")
    void emptyTableTest() {
        projectMapper.deleteAll();
        assertEquals(0, projectMapper.count());

        List<ProjectDto> projectDtoList = projectMapper.selectAllPj();

        assertEquals(true, projectDtoList.isEmpty());
    }

    @Test
    @DisplayName("유저의 좋아요 프로젝트 목록중 진행중인 프로젝트 조회하기")
    void selectByStatus() {
        String pj_id;
        String status = "ing";

//        조건
        //프로젝트 테이블에 ing 상태인 프로젝트가 존재한다.
        project1.setPj_status("ing");
        project2.setPj_status("ing");
        project3.setPj_status("end");
        projectMapper.insert(project1);
        projectMapper.insert(project2);
        projectMapper.insert(project3);

        List<ProjectDto> likedList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            ProjectDto likedPj = projectMapper.selectByStatus(String.valueOf(i), "ing");

//            if (likedPj != null) {
                likedList.add(likedPj);
//            }
        }

//        boolean result = likedList.stream().allMatch(pj -> pj.getPj_status().equals("ing"));
        assertEquals(true, likedList.contains(project1));
        assertEquals(true, likedList.contains(project2));
        assertEquals(false, likedList.contains(project3));
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요수증가테스트")
    void upLikeCnt() {
        this.project1 = ProjectDto.builder()
                .pj_id("1")
                .pj_sel_id(user_id)
                .curr_pj_like_cnt(1)
                .build();
        int insertCount = projectMapper.insert(project1);

        assertEquals(1, insertCount);
        projectMapper.upLikeCnt(project1);
        assertEquals(1, projectMapper.upLikeCnt(project1));
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요수감소테스트") void downLikeCnt() {
        this.project1 = ProjectDto.builder()
                .pj_id("1")
                .pj_sel_id(user_id)
                .curr_pj_like_cnt(1)
                .build();
        int insertCount = projectMapper.insert(project1);

        assertEquals(1, insertCount);
        projectMapper.downLikeCnt(project1);
        assertEquals(1, projectMapper.upLikeCnt(project1));

    }

    @Test
    @DisplayName("insert 후 저장된 프로젝트객체 반환하기 테스트")
    void getSavedDto() {
        projectMapper.insert(project1);
        String pj_id = project1.getPj_id();
        ProjectDto savedDto = projectMapper.getByPjId(pj_id);

        System.out.println("\n\n"+project1);
        System.out.println("\n\n"+savedDto);

    }

    @Test
    @DisplayName("예외 캐치 테스트")
    void catchException() {
            assertThatThrownBy(()->{
                projectMapper.insert(project1);
                projectMapper.insert(project1);
            }).isInstanceOf(NonTransientDataAccessException.class);
    }
}