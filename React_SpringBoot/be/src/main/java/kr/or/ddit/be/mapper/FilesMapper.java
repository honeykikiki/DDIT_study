package kr.or.ddit.be.mapper;

import kr.or.ddit.be.vo.BoardVO;
import kr.or.ddit.be.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilesMapper {
    public List<FileVO> list(BoardVO boardVO);

    public int insertList(FileVO[] fileVOList);

    public int insert(FileVO fileVO);
}
