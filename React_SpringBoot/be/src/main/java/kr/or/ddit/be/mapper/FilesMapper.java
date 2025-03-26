package kr.or.ddit.be.mapper;

import kr.or.ddit.be.vo.AttachFileVO;
import kr.or.ddit.be.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilesMapper {
    public List<AttachFileVO> list(BoardVO boardVO);

    public int insertList(AttachFileVO[] attachFileVOList);

    public int insert(AttachFileVO attachFileVO);

    void delete(List<Integer> fileIdList);
}
